package aisha.controller;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.Connection;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Program;
import aisha.bean.Resource;
import aisha.bean.Startup;
import aisha.bean.Subscription;
import aisha.bean.Talent;
import aisha.controller.TalentController.NullAwareBeanUtilsBean;
import aisha.security.services.SystemUserService;
import aisha.service.ApplicationTemplateService;
import aisha.service.ConnectionService;
import aisha.service.StartupService;
import aisha.service.TalentService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;
import aisha.util.bean.FieldAttributes;
@Controller
@RequestMapping(value = "/StartupController")
public class StartupController {
	protected static Logger logger = Logger.getLogger(StartupController.class);
	private static String thisBean = "Startup";
	@Autowired
	private StartupService service;
	
	@Autowired
	private TalentService talentService;

	@Autowired
	private SystemUserService userService;
	
	@Autowired
	private ConnectionService connectionService;
	
	@Autowired
	private ApplicationTemplateService appService;
	
	 @RequestMapping(value="/getStartupList", method=RequestMethod.GET)
	 public String getStartupList(Model model, HttpServletRequest request) {
		 logger.debug("Entering method StartupController.getStartupList");
		 String thisOperation = "List";
			

			int pageNumber = 1;
			if (request.getParameter("currentPage") != null) {
				int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
				if (request.getParameter("left") != null)
					pageNumber = currentPageNumber - 1;
				else if (request.getParameter("right") != null)
					pageNumber = currentPageNumber + 1;
			}
			pageNumber--;

			BasicBean profile = new Startup();
			profile.setFirstPage(pageNumber * 30);
			profile.setMaxResult(30);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Startup.getSearchFields();

			for (int i = 0; i < searchFields.size(); i++) {
				if (searchFields.get(i).equals("fromCreate") || searchFields.get(i).equals("toCreate")) {
					if (request.getParameter(searchFields.get(i)) != null
							&& !request.getParameter(searchFields.get(i)).isEmpty())

					{
						createDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

					}
				}
				
				if (searchFields.get(i).equals("fromModify") || searchFields.get(i).equals("toModify")) {
					if (request.getParameter(searchFields.get(i)) != null
							&& !request.getParameter(searchFields.get(i)).isEmpty())

					{
						modifyDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

					}
				}
				if (!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate")
						&& request.getParameter(searchFields.get(i)) != null)
					criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));
				
			}
			
			if (!createDateFilter.isEmpty())
				{
				criteria.put("creationTime", createDateFilter);
				criteria.remove("toCreate");
				criteria.remove("fromCreate");
				}
			if (!modifyDateFilter.isEmpty())
			{
				criteria.put("LastUpdateTime", modifyDateFilter);
				criteria.remove("toModify");
				criteria.remove("fromModify");
		    }
			HashMap<String, Object> searchCriteria = new HashMap<>();
			if(CurrentUser.getUserType()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
				criteria.put("status", "active");
			profile.setSearchCriteria(criteria);
			BasicBean profileList = service.listBeans(profile);
			logger.debug("Inside method StartupController.getStartupList: after getting beans from DB");
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
				 } catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("Inside method StartupController.getStartupList: throwing exception " + e.getMessage());
				
			}
			
			
			int totalCount = profile.getTotalResult();
			Integer nOfRecords = totalCount;
			Integer nOfPages = (totalCount + 29) / 30;
			if (nOfPages == 0)
				nOfPages = 1;
			model.addAttribute("nOfRecords", nOfRecords);
			model.addAttribute("nOfPages", nOfPages);

			if (request.getParameter("currentPage") == null) {
				model.addAttribute("beanList", resultList);
				model.addAttribute("currentPage", "1");
			} else {

				Integer currentPage;
				if (request.getParameter("left") == null)
					currentPage = new Integer(request.getParameter("currentPage")) + 1;
				else
					currentPage = new Integer(request.getParameter("currentPage")) - 1;
				model.addAttribute("beanList", resultList);

				model.addAttribute("currentPage", currentPage.toString());
			}
			
			addLinks(model);
			Map<String,String> x = new HashMap<String,String>();
			
			x = CurrentUser.getConnections(CurrentUser.getUserKey(), "User");
			if(x == null || x.isEmpty())
				model.addAttribute("myConnections", null);
			else
				model.addAttribute("myConnections", x);
			model.addAttribute("role", "InvestorAdmin");
			logger.debug("Existing method StartupController.getStartupList");
			return "getAllBeans";	
	 }
 

	    @RequestMapping(value="/getPage", method=RequestMethod.GET)
	    public String getPage(Model model, HttpServletRequest request,
	    		@RequestParam("id") Integer pageNumber) {


	    	
	    	 logger.debug("Entering method StartupController.getStartupList");
			 String thisOperation = "List";
			 String thisBean = "Startup";
					model.addAttribute("role", CurrentUser.getUserRole());

				//int pageNumber = 1;
				if (request.getParameter("currentPage") != null) {
					int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
					if (request.getParameter("left") != null)
						pageNumber = currentPageNumber - 1;
					else if (request.getParameter("right") != null)
						pageNumber = currentPageNumber + 1;
				}
				pageNumber--;

				BasicBean profile = new Startup();
				profile.setFirstPage(pageNumber * 5);
				profile.setMaxResult(5);
				HashMap<String, Object> criteria = new HashMap<>();
				HashMap<String, Object> createDateFilter = new HashMap<>();
				HashMap<String, Object> modifyDateFilter = new HashMap<>();
				ArrayList<String> searchFields = Startup.getSearchFields();

				for (int i = 0; i < searchFields.size(); i++) {
					if (searchFields.get(i).equals("fromCreate") || searchFields.get(i).equals("toCreate")) {
						if (request.getParameter(searchFields.get(i)) != null
								&& !request.getParameter(searchFields.get(i)).isEmpty())

						{
							createDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

						}
					}
					
					if (searchFields.get(i).equals("fromModify") || searchFields.get(i).equals("toModify")) {
						if (request.getParameter(searchFields.get(i)) != null
								&& !request.getParameter(searchFields.get(i)).isEmpty())

						{
							modifyDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

						}
					}
					if (!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate")
							&& request.getParameter(searchFields.get(i)) != null)
						criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));
					
				}
				
				if (!createDateFilter.isEmpty())
					{
					criteria.put("creationTime", createDateFilter);
					criteria.remove("toCreate");
					criteria.remove("fromCreate");
					}
				if (!modifyDateFilter.isEmpty())
				{
					criteria.put("LastUpdateTime", modifyDateFilter);
					criteria.remove("toModify");
					criteria.remove("fromModify");
			    }
				HashMap<String, Object> searchCriteria = new HashMap<>();
				if(CurrentUser.getUserType()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
					criteria.put("status", "active");
				profile.setSearchCriteria(criteria);
				BasicBean profileList = service.listBeans(profile);
				logger.debug("Inside method StartupController.getStartupList: after getting beans from DB");
				List<BasicBean> resultList = profileList.getResults();
				
				try {
					
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
					 } catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug("Inside method StartupController.getStartupList: throwing exception " + e.getMessage());
					
				}
				
				
				int totalCount = profile.getTotalResult();
				Integer nOfRecords = totalCount;
				Integer nOfPages = (totalCount + 4) / 5;
				if (nOfPages == 0)
					nOfPages = 1;
				model.addAttribute("nOfRecords", nOfRecords);
				model.addAttribute("nOfPages", nOfPages);
				model.addAttribute("pageNumber", pageNumber);
				

				if (request.getParameter("currentPage") == null) {
					model.addAttribute("beanList", resultList);
					model.addAttribute("currentPage", "1");
				} else {

					Integer currentPage;
					if (request.getParameter("left") == null)
						currentPage = new Integer(request.getParameter("currentPage")) + 1;
					else
						currentPage = new Integer(request.getParameter("currentPage")) - 1;
					model.addAttribute("beanList", resultList);

					model.addAttribute("currentPage", currentPage.toString());
				}
				
				addLinks(model);
				logger.debug("Existing method StartupController.getStartupList");
				return "getAllBeans";	     
	    }
	    


	 
	@RequestMapping(value = "/addStartup", method = RequestMethod.GET)
	 public String addStartup(Model model) {
		logger.debug("Entering method StartupController.addStartup");
		String thisOperation = "Add";

	     model.addAttribute("role", CurrentUser.getUserRole());
		
	     try {
			
			model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, new Startup(), new Startup()); 
				} catch (Exception e) {
			// TODO Auto-generated catch block
					logger.debug("Inside method StartupController.addStartup : throwing excpetion " + e.getMessage());
			e.printStackTrace();
		}
	     logger.debug("Existing method StartupController.addStartup");
	 	return "addBean";
	 }
	 
		@RequestMapping(value= "/submitAddStartup", method = RequestMethod.POST)
		 public String submitAddStartup(HttpServletRequest request, @RequestParam("uploadedFileName") MultipartFile multipart, @ModelAttribute("bean") Startup bean, BindingResult result, Model model) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			logger.debug("Entering method StartupController.submitAddStartup");
			String thisOperation = "SubmitAdd";
			 model.addAttribute("role", CurrentUser.getUserRole());
			 Startup childBean = (Startup) bean;
			 PlatformUser sysUser = new PlatformUser();
			 ArrayList<String> messages = new ArrayList<String>();
			 
				try {
			
						 if(multipart.getSize() != 0)
							{
							
							 String fileName = multipart.getOriginalFilename();
							 String extension = FilenameUtils.getExtension(fileName);	
							 if(!extension.equals("pdf"))
							 {
								 logger.debug("Inside TalentController.submitAddTalent: Error in File upload");
									messages.add("Error in File upload, please use pdf format");
									model.addAttribute("messages", messages);
									model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
									addLinks(model);
									logger.debug("Exiting TalentController.submitAddTalent");
									return "addBean";
							 }
							 byte[] bytes = multipart.getBytes();
					         Path path = Paths.get("C://Users//hp//Desktop//AishaSoftware//Home//" + fileName );
					         Files.write(path, bytes);
					         bean.setFile1(fileName);     

							}

				}
					 catch(Exception e)
					 {
								logger.debug("Inside TalentController.submitAddTalent: exception thrown during processing CV upload");
								messages.add("Error uploading your CV");
								model.addAttribute("messages", messages);
								model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
								addLinks(model);
								logger.debug("Exiting TalentController.submitAddTalent");
								return "addBean";
					 }
					 logger.debug("Inside TalentController.submitAddTalent: before adding talent to DB");

			 Long beanId = 0L;
			 
			 try {
					logger.debug("Inside StartupController.submitAddStartup: adding startup to DB");
					beanId = service.addBean(bean);	  
					if(beanId != null && beanId!= 0)
					    logger.debug("Inside StartupController.submitAddStartup: startup added successfully to DB");
					else
					{
						logger.debug("Inside StartupController.submitAddStartup: failed to add startup to DB");
						messages.add("Error occured during adding startup to DB");
						model.addAttribute("messages", messages);
						model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
						addLinks(model);
						return "addBean";
				    }
				}
				catch (Exception e)
				{
					logger.debug("Inside StartupController.submitAddStartup: exception thrown during adding startup to DB");
					messages.add("This startup name is already used, please try another startup name");
					model.addAttribute("messages", messages);
					model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
					addLinks(model);
					return "addBean";
				}
				  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, bean, bean);
				  sysUser.setUserName(childBean.getField13());
					if(!(childBean.getField14()).equals( childBean.getField15()))			
					        {
								  messages.add("There is a mismatch between password and confirmed password");
								  model.addAttribute("messages", messages);
								  addLinks(model);
								  return "addBean";
							} 
							
							sysUser.setEmail(childBean.getField2());
							sysUser.setUserType(thisBean);
							sysUser.setUserRole("ProfileOWner");
							sysUser.setUserKey(beanId.toString());
							sysUser.setPassword(childBean.getField14());
							sysUser.setConfirmedPassword(childBean.getField15());
							try {
							logger.debug("Inside StartupController.submitAddStartup: adding user to DB");
							Long userId = userService.addSystemUser(sysUser);
							if(userId != null  && userId!= 0)
								logger.debug("Inside StartupController.submitAddStartup: user added successfully to DB");
							else
							{
							logger.debug("Inside StartupController.submitAddStartup: failed to add user to DB");
							messages.add("Error occured during adding user to DB");
							model.addAttribute("messages", messages);
							model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
							addLinks(model);
							service.deleteBean(childBean);
							return "addBean";
							}
							
							}
							catch(Exception e)
							{		
							logger.debug("Inside StartupController.submitAddStartup: exception thrown during adding user to DB");
							messages.add("This user name is already used, please try another user name");
							model.addAttribute("messages", messages);
							model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
							addLinks(model);
							service.deleteBean(childBean);
							return "addBean";
							}
							
							messages.add("Your profile creation request is recieved successfully, please wait 249 feedback");
							model.addAttribute("messages", messages);
							model = FormFields.fillModelGeneric( model, thisBean,   "View", bean, bean);
							addLinks(model);
							logger.debug("Exiting StartupController.submitAddStartup: startup and user successfully added to DB");
							return "addBean";
}




		

		
		 @RequestMapping(value="/getStartup", method=RequestMethod.GET)
		 public String getStartup(Model model, @RequestParam("id") Integer id) {
			 model.addAttribute("title", "Startup Info");	
			 String thisOperation = null;
			// if(CurrentUser.getEntityId().equals(id))
			 Map<String,String> myConnections = CurrentUser.getConnections(String.valueOf(id), "Entity");
			 if(myConnections != null && myConnections.containsKey(String.valueOf(id)))
				 thisOperation = "View";
			 else
				 thisOperation = "View";
			// else
				// thisOperation = "Get";
			 model.addAttribute("role", CurrentUser.getUserRole());
			 Startup startup = new Startup();
			 BasicBean result = new Startup();
			 List<BasicBean> beanList = new ArrayList<BasicBean>();
			 //startup.setFirstResults(10);
			// startup.setMaxResults(10);
		
			 HashMap<String, Object> searchCriteria = new HashMap<>();
			   searchCriteria.put("id", id);
			   startup.setSearchCriteria(searchCriteria);
			 result = service.getBean(startup);
	try
	{
		model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, result, result);
		
				} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			// logger.debug("Inside method StartupController.getStartupList, before list startups with criteria : " + startup.getFilter());
	        // addLinks(model);
			 logger.debug("Exiting method StartupController.getStartupList");
			 return "addBean";
		
		 }
		 
		 @RequestMapping(value= "/submitUpdateStartup", method = RequestMethod.POST)
		 public String submitUpdateStartup(HttpServletRequest request, @RequestParam("oldBean") String oldBean,@ModelAttribute("bean") Startup bean, @ModelAttribute("id") String id, BindingResult result, Model model) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			 logger.debug("Entering method StartupController.submitUpdateStartup");
			 String thisOperation = "View";
			 model.addAttribute("userName", CurrentUser.getUserName());
		     model.addAttribute("role", CurrentUser.getUserRole());
		     model.addAttribute("title", "Update Startup Profile");			
			 Startup childBean = (Startup) bean;
		     ObjectMapper mapper = new ObjectMapper();
			 Startup beanBack = null;
			try {
				beanBack = mapper.readValue(oldBean, Startup.class);
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
			 try {
				notNull.copyProperties(beanBack, childBean);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 PlatformUser sysUser = new PlatformUser();
			 ArrayList<String> messages = new ArrayList<String>();
			 HashMap<String, Object> searchCriteria = new HashMap<>();

		
				  searchCriteria.put("userKey", id);
				  sysUser.setSearchCriteria(searchCriteria);
				  PlatformUser oldUser = userService.listSystemUsers(sysUser);
				  
				  if(oldUser.getResults() != null && oldUser.getResults().size() > 0)
					  sysUser = (PlatformUser) oldUser.getResults().get(0);
				  else
				  {
					    logger.debug("Inside StartupController.submitUpdateStartup: failed to get startup from DB");
						messages.add("Error occured during get startup from DB");
						model.addAttribute("messages", messages);
						model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
						addLinks(model);
						return "addBean";  
				  }
					 if(!sysUser.getEmail().equals(beanBack.getField2()))
					 {
						 sysUser.setEmail(beanBack.getField2());
					     userService.updateSystemUser((sysUser));
					 }
					 
					 BasicBean savedBean = updateOldBean(oldBean,bean);
		try{			 
				  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, savedBean, savedBean);
				  

	} 		
		catch(Exception e)
		{
		    logger.debug("Inside StartupController.submitUpdateStartup: throwing an exception:" + e.getMessage());
			messages.add("An error occured");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
			addLinks(model);
			return "addBean"; 	
		}
			messages.add("Profile updated successfully!");
			model.addAttribute("messages",messages);	
			logger.debug("Exiting StartupController.submitUpdateStartup");
			return "addBean";
			/*if(multipart.getSize() != 0)
			{
				String fileName = multipart.getOriginalFilename();
			
			 byte[] bytes = multipart.getBytes();
	         Path path = Paths.get("//home//CVs//" + startup.getFullName() + ".docx");
	         Files.write(path, bytes);

			startup.setFilePath(fileName);
			}*/
		 	}	
		 
		 @RequestMapping(value="/download", method=RequestMethod.GET)
		 public void download(HttpServletResponse response,Model model, @RequestParam("fileName") String fileName) throws IOException {
			  //System.out.println("Calling Download:- " + fileName);
			//  ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);
			//  File file = new File("C://Users//hp//Desktop//DS//CVs//" + fileName);
			  Path file = Paths.get("C://Users//hp//Desktop//AishaSoftware//Home//", fileName);
		            response.setContentType("application/pdf");
		            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		          //  response.addHeader("Content-Disposition", "attachment; filename="+fileName + ".pdf");
		            try
		            {
		                Files.copy(file, response.getOutputStream());
		                response.getOutputStream().flush();
		            } 
		            catch (IOException ex) {
		                ex.printStackTrace();
		            }
		 }
			  
			  
			  
			  
		 public void downloadFile1(HttpServletResponse response,Model model, @RequestParam("fileName") String fileName) {
			  File file = new File("C://Users//hp//Desktop//DS//CVs//" + fileName);
			   org.springframework.http.MediaType mm = org.springframework.http.MediaType.parseMediaType("application/pdf");
				try {
					InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				      response.setContentType("application/pdf");
	      response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() + ".pdf");
	     // response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
	      BufferedInputStream inStrem = null;

				inStrem = new BufferedInputStream(new FileInputStream(file));
				
				BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
		        
		        byte[] buffer = new byte[1024];
		        int bytesRead = 0;
		        while ((bytesRead = inStrem.read(buffer)) != -1) {
		          outStream.write(buffer, 0, bytesRead);

		    	}
		        
		        outStream.flush();
		        inStrem.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		 }

		 
		 @RequestMapping(value= "/viewMyProfile", method = RequestMethod.GET)
			 public String viewMyProfile(Model model) throws IOException{
				 logger.debug("Entering method StartupController.viewMyProfile");
				 String thisOperation = null;
				 model.addAttribute("role", CurrentUser.getUserRole());
				 model.addAttribute("userName", CurrentUser.getUserName());
				
				 Startup searchBean = new Startup();
				 BasicBean resultBean = new Startup();
				 ArrayList<String> messages = new ArrayList<String>();
				 HashMap<String, Object> searchCriteria = new HashMap<>();			 
			    	try {
					
					  searchCriteria.put("id", CurrentUser.getUserKey());
					  searchBean.setSearchCriteria(searchCriteria);
					  resultBean = service.getBean(searchBean);	  
					  if(resultBean.getStatus().equals("closed"))
						  thisOperation = "View";
					  else
						  thisOperation = "Update";
					  System.out.println("$$$$$$$$$$$$$$$$ thisOperation : " + thisOperation);
					  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, resultBean, resultBean);
				}
				catch(Exception e)
				{
					logger.debug("Inside method StartupController.viewMyProfile: an errror occured");
				}
				
				addLinks(model); 
				logger.debug("Existing method StartupController.viewMyProfile: an errror occured");
				return "addBean";
					}

		 @RequestMapping(value = "/getApplicationList", method = RequestMethod.GET)
			public String getApplicationList(Model model, HttpServletRequest request) {
				logger.debug("Entering method ApplicationController.getSubmittedApplications");
				String thisOperation = "ListApps";
				String thisBean = "Application";
				model.addAttribute("role", CurrentUser.getUserRole());
				int pageNumber = 1;
				if (request.getParameter("currentPage") != null) {
					int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
					if (request.getParameter("left") != null)
						pageNumber = currentPageNumber - 1;
					else if (request.getParameter("right") != null)
						pageNumber = currentPageNumber + 1;
				}
				pageNumber--;

				Application app = new Application();
				app.setId(1);
				app.setFirstPage(pageNumber * 2);
				app.setMaxResult(2);
				HashMap<String, Object> criteria = new HashMap<>();
				HashMap<String, Object> dateFilter = new HashMap<>();
				ArrayList<String> searchFields = Application.getSearchFields();
			
				for (int i = 0; i < searchFields.size(); i++) {
					if (searchFields.get(i).equals("fromDate") || searchFields.get(i).equals("toDate")) {
						if (request.getParameter(searchFields.get(i)) != null
								&& !request.getParameter(searchFields.get(i)).isEmpty())

						{
							dateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

						}
					}
					if (!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate")
							&& request.getParameter(searchFields.get(i)) != null)
						criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));
					if (!dateFilter.isEmpty())
						criteria.put("submTime", dateFilter);
				}
				
				criteria.put("applicantId", Long.valueOf(CurrentUser.getEntityId()));
				app.setSearchCriteria(criteria);
				logger.debug(
						"Inside method ApplicationController.getSubmittedApplications , before retrieving submitted applications from database");
				Application appList = appService.listApplicationTemplate(app);
				List<BasicBean> resultList = appList.getResults();
				try {
					//model = FormFields.fillModel( model,  "Investor",  "InvestorController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, appList, appList);
					 } catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int totalCount = app.getTotalResult();
				logger.debug(
						"Inside method ApplicationController.getSubmittedApplications , after retrieving submitted applications from database, no of records : "
								+ totalCount);
				
				Integer nOfRecords = totalCount;
				Integer nOfPages = (totalCount + 1) / 2;
				if (nOfPages == 0)
					nOfPages = 1;
				model.addAttribute("nOfRecords", nOfRecords);
				model.addAttribute("nOfPages", nOfPages);

				if (request.getParameter("currentPage") == null) {
					model.addAttribute("beanList", resultList);
					model.addAttribute("currentPage", "1");
				} else {

					Integer currentPage;
					if (request.getParameter("left") == null)
						currentPage = new Integer(request.getParameter("currentPage")) + 1;
					else
						currentPage = new Integer(request.getParameter("currentPage")) - 1;
					model.addAttribute("beanList", resultList);

					model.addAttribute("currentPage", currentPage.toString());
				}
				addLinks(model); 
				logger.debug("Exiting method ApplicationController.getSubmittedApplications");
				return "getAllBeans";

			} 
	
		 
		 @RequestMapping(value= "/submitAddPlatformUser", method = RequestMethod.GET)
		 public String submitAddPlatformUser(HttpServletRequest request, @RequestParam("id") String talentId, Model model) throws IOException{
			 String thisOperation = "SubmitAdd";

			 PlatformUser talentUser = new PlatformUser();
			 PlatformUser startupUser = new PlatformUser();
			 ArrayList<String> messages = new ArrayList<String>();
			 HashMap<String, Object> searchCriteria = new HashMap<>();
			 searchCriteria.put("userKey", talentId);
			 talentUser.setSearchCriteria(searchCriteria);
			 talentUser = userService.getSystemUser(talentUser);
			 startupUser.setUserType("Startup");
			 startupUser.setUserKey(CurrentUser.getEntityId());
			 startupUser.setUserRole("StartupAdmin");
			 startupUser.setUserName(talentUser.getUsername() + CurrentUser.getEntityId());
			 Long  beanId = userService.addSystemUser(startupUser);	  
			
			return  getTalentsList (model, request);
				}
		 
	 
	 @RequestMapping(value = "/getTalentsList", method = RequestMethod.GET)
		public String getTalentsList(Model model, HttpServletRequest request) {
		//	logger.debug("Entering method ApplicationController.getSubmittedApplications");
			PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//model.addAttribute("role", currentUser.getUserRole());
			String thisOperation = "ListTalents";	
			int pageNumber = 1;
			if (request.getParameter("currentPage") != null) {
				int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
				if (request.getParameter("left") != null)
					pageNumber = currentPageNumber - 1;
				else if (request.getParameter("right") != null)
					pageNumber = currentPageNumber + 1;
			}
			pageNumber--;

			Talent talent = new Talent();
			
			talent.setFirstPage(pageNumber * 50);
			talent.setMaxResult(50);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> dateFilter = new HashMap<>();
			ArrayList<String> searchFields;
			//= Talent.getSearchFields();
			//model.addAttribute("controllerName", "TalentController");
			//model.addAttribute("beanName", "Talent");
			Connection connect = new Connection();
			criteria.put("outerId", Integer.valueOf(CurrentUser.getEntityId()));
			criteria.put("relationType", "Talent");
			connect.setSearchCriteria(criteria);
			BasicBean profileList = connectionService.listConnections(connect);
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				//model = FormFields.fillModel( model,  "Program",  "ProgramController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
				model = FormFields.fillModelGeneric( model, "Connection", thisOperation, profileList, profileList);
				 } catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			int totalCount = profileList.getTotalResult();
			Integer nOfRecords = totalCount;
			Integer nOfPages = (totalCount + 4) / 5;
			if (nOfPages == 0)
				nOfPages = 1;
			model.addAttribute("nOfRecords", nOfRecords);
			model.addAttribute("nOfPages", nOfPages);

			if (request.getParameter("currentPage") == null) {
				model.addAttribute("beanList", resultList);
				model.addAttribute("currentPage", "1");
			} else {

				Integer currentPage;
				if (request.getParameter("left") == null)
					currentPage = new Integer(request.getParameter("currentPage")) + 1;
				else
					currentPage = new Integer(request.getParameter("currentPage")) - 1;
				model.addAttribute("beanList", resultList);

				model.addAttribute("currentPage", currentPage.toString());
			}
			
			addLinks(model);
			return "getAllBeans";

		}
	 
	 
	 @RequestMapping(value="/getConnectionList", method=RequestMethod.GET)
	 public String getConnectionList(Model model, HttpServletRequest request) {
		 String thisOperation = "List";
		 String  thisBean = "Connection";
				model.addAttribute("role", CurrentUser.getUserRole());
		
		
			int pageNumber = 1;
			if (request.getParameter("currentPage") != null) {
				int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
				if (request.getParameter("left") != null)
					pageNumber = currentPageNumber - 1;
				else if (request.getParameter("right") != null)
					pageNumber = currentPageNumber + 1;
			}
			pageNumber--;

			BasicBean profile = new Program();
			profile.setFirstPage(pageNumber * 50);
			profile.setMaxResult(50);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Connection.getSearchFields();

			for (int i = 0; i < searchFields.size(); i++) {
				if (searchFields.get(i).equals("fromCreate") || searchFields.get(i).equals("toCreate")) {
					if (request.getParameter(searchFields.get(i)) != null
							&& !request.getParameter(searchFields.get(i)).isEmpty())

					{
						createDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

					}
				}
				
				if (searchFields.get(i).equals("fromModify") || searchFields.get(i).equals("toModify")) {
					if (request.getParameter(searchFields.get(i)) != null
							&& !request.getParameter(searchFields.get(i)).isEmpty())

					{
						modifyDateFilter.put(searchFields.get(i), request.getParameter(searchFields.get(i)));

					}
				}
				if (!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate")
						&& request.getParameter(searchFields.get(i)) != null)
					criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));
				
			}
			
			if (!createDateFilter.isEmpty())
				{
				criteria.put("creationTime", createDateFilter);
				criteria.remove("fromCreate");
				criteria.remove("toCreate");
				}
			if (!modifyDateFilter.isEmpty())
			{
				criteria.put("LastUpdateTime", modifyDateFilter);
		    }
			HashMap<String, Object> searchCriteria = new HashMap<>();
			criteria.put("outerId", Integer.valueOf(CurrentUser.getEntityId()));
			Connection conn = new Connection();
			conn.setSearchCriteria(criteria);
			//profile.setSearchCriteria(searchCriteria);
			BasicBean profileList = connectionService.listConnections(conn);
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				//model = FormFields.fillModel( model,  "Program",  "ProgramController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
				 } catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int totalCount = profile.getTotalResult();
			Integer nOfRecords = totalCount;
			Integer nOfPages = (totalCount + 4) / 5;
			if (nOfPages == 0)
				nOfPages = 1;
			model.addAttribute("nOfRecords", nOfRecords);
			model.addAttribute("nOfPages", nOfPages);

			if (request.getParameter("currentPage") == null) {
				model.addAttribute("beanList", resultList);
				model.addAttribute("currentPage", "1");
			} else {

				Integer currentPage;
				if (request.getParameter("left") == null)
					currentPage = new Integer(request.getParameter("currentPage")) + 1;
				else
					currentPage = new Integer(request.getParameter("currentPage")) - 1;
				model.addAttribute("beanList", resultList);

				model.addAttribute("currentPage", currentPage.toString());
			}
			
	Map<String,String> x = new HashMap<String,String>();
			
			x = CurrentUser.getConnections(CurrentUser.getUserKey(), "User");
			if(x == null || x.isEmpty())
				model.addAttribute("myConnections", null);
			else
				model.addAttribute("myConnections", x);
			
			addLinks(model); 
			return "getAllBeans";
	 }
	 
	 @RequestMapping(value="/connect", method=RequestMethod.GET)
	 public String connect(HttpServletRequest request, Model model,@RequestParam("id") String idsArrayy) {
		// model.addAttribute("title", "Startup Info");
			String[] ids = idsArrayy.split(",");
			String id = ids[0];
		//	String outerName = ids[1];
		   HashMap<String, Object> searchCriteria = new HashMap<>();
		   ArrayList<String> messages = new ArrayList<String>();
		   Connection connect = new Connection();
		   
		   if(CurrentUser.getUserStatus().equals("anonymousUser"))
			{		 
				messages.add("You need to Log-In first");
			   model.addAttribute("tableMessages", messages);
		       return getStartupList(model, request);	   
		    }	 
		   
	   connect.setOuterId(Integer.valueOf(id));

	   try
	   {
	   connect.setInnerId(Integer.valueOf(CurrentUser.getUserId()));
	   searchCriteria.put("id", CurrentUser.getUserId());
	   }
	   catch(Exception e)
	   {
		   messages.add("You need to Log-In first");
		   model.addAttribute("tableMessages", messages);
	       return getStartupList(model, request);	   
	   }
	   String beanName = null;
	 //  connect.setOuterName(outerName);
	   //make sure to fill userfullname with investor name during profile creation
	   connect.setInnerName(CurrentUser.getUserFullName());
	   connect.setStatus("pending");
	   connect.setRelationType(CurrentUser.getUserType());
	   connectionService.addConnection(connect);
	   
	   messages.add("Connection request sent successfully");
	   model.addAttribute("tableMessages", messages);
       return getStartupList(model, request);
	 }
	 
	 @RequestMapping(value="/getStartupAdminView", method=RequestMethod.GET)
	 public String getStartupAdminView(Model model,@RequestParam("id") Integer id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	   Startup startup = new Startup();
	   HashMap<String, Object> searchCriteria = new HashMap<>();
	   searchCriteria.put("id", id);
	   startup.setSearchCriteria(searchCriteria);
	   startup = startupService.getStartup(startup);
	  

    	   model = FormFields.fillModel( model,  "Startup",  "StartupController",  "view", "platform-body-view-get", null,  startup ,"Admin");
	

	   return "addBean";
	 }
	
	 public BasicBean updateOldBean(String oldBeanString, BasicBean newBean)
	 {
		 ObjectMapper mapper = new ObjectMapper();
		 Startup beanBack = null;
		try {
			beanBack = mapper.readValue(oldBeanString, Startup.class);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 BeanUtilsBean notNull=new NullAwareBeanUtilsBean();
		 try {
			notNull.copyProperties(beanBack, newBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//talent.setId(Long.valueOf(id));
		//talent.setUserId("131072");
		service.updateBean(beanBack);
		return beanBack;
		}
	 
	  public static void addLinks(Model model)
		{
			if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Startup"))
			{
				model.addAttribute("linkKeys",  Startup.getLinks().keySet());
				model.addAttribute("links",  Startup.getLinks());
			}
			
	}
	  
	/* @RequestMapping(value="/connectStartup", method=RequestMethod.GET)
	 public String connectStartup(Model model,@RequestParam("id") Integer id) {
		 logger.debug("Entering method TalentController.getTalent");
		 String startupID = null ;
				String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				if (x != "anonymousUser")
				{
					PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					//currentUser.getuserkey;
					startupID = currentUser.getUserId();
				}
				
	Investor investor = new Investor();
	investor.setId(Long.valueOf("3637248"));
				Startup startup = new Startup();
				startup.getInvestors().add(investor);
				startup.setId(Long.valueOf(id));
				
				startupService.updateStartup(startup);
	   return "viewBean";
	 }*/
	 public class NullAwareBeanUtilsBean extends BeanUtilsBean{

		    @Override
		    public void copyProperty(Object dest, String name, Object value)
		            throws IllegalAccessException, InvocationTargetException {
		        if(value==null)return;
		        super.copyProperty(dest, name, value);
		    }

		    
	 }	  
}
