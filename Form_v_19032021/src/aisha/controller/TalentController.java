package aisha.controller;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.Connection;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Program;
import aisha.bean.Startup;
import aisha.bean.Talent;
import aisha.controller.StartupController.NullAwareBeanUtilsBean;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;
import aisha.service.ApplicationTemplateService;
import aisha.service.ConnectionService;
import aisha.service.TalentService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;
import aisha.test.getFromXML;

@Controller
@RequestMapping(value="/TalentController")
public class TalentController {
	
	protected static Logger logger = Logger.getLogger(TalentController.class);
	public static String thisBean = "Talent";
	@Autowired
	private TalentService service; 
	@Autowired
	private SystemUserService userService;
	@Autowired
	private ApplicationTemplateService appService;
	@Autowired
	private ConnectionService connectionService;
	
	 @RequestMapping(value = "/addTalent", method = RequestMethod.GET)
	 public String addTalent(Model model) {
		 logger.debug("Entering method TalentController.addTalent");
		 String thisOperation = "Add";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Create Talent Profile");

	     try {
	    	 logger.debug("Inside method TalentController.addTalent: filling model");
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation, new Talent(), new Talent());
				} catch (Exception e) {
			 logger.debug("Throwing exception in method TalentController.addTalent : " + e.getMessage());
			e.printStackTrace();
		}
	     logger.debug("Exsiting method TalentController.addTalent");
	 	return "addBean";
	 }
	 
		@RequestMapping(value= "/submitAddTalent", method = RequestMethod.POST)
		 public String submitAddTalent(HttpServletRequest request, @RequestParam("uploadedFileName") MultipartFile multipart, @ModelAttribute("bean") Talent bean, BindingResult result, Model model) throws IOException, ClassNotFoundException, InstantiationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			 //logging
			 logger.debug("Entering method TalentController.submitAddTalent");
			 
			 //config
			 String thisOperation = "SubmitAdd";
			 
			 //fill page
			 model.addAttribute("userName", CurrentUser.getUserName());
		     model.addAttribute("role", CurrentUser.getUserRole());
		     model.addAttribute("title", "Create Talent Profile");	
		     
		     //definitions
			 Talent childBean = (Talent) bean;
			 PlatformUser sysUser = new PlatformUser();
			 ArrayList<String> messages = new ArrayList<String>();
			 Long beanId = 0L;
			 
			 //save file if exist
				try {
						 if(multipart.getSize() != 0)
							{
							
							 String fileName = multipart.getOriginalFilename();
							 String extension = FilenameUtils.getExtension(fileName);	
							 if(!extension.equals("pdf"))
							 {
								 logger.debug("Inside TalentController.submitAddTalent: Error in file upload");
									messages.add("Error in file upload, please use pdf format");
									model.addAttribute("messages", messages);
									model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
									addLinks(model);
									logger.debug("Exiting TalentController.submitAddTalent");
									return "addBean";
							 }
							 byte[] bytes = multipart.getBytes();
							 //make sure to make path configurable
					         Path path = Paths.get("C://Users//hp//Desktop//AishaSoftware//Home//" + fileName );
					         Files.write(path, bytes);
					         bean.setFile1(fileName);     

							}

				}
					 catch(Exception e)
					 {
								logger.debug("Inside TalentController.submitAddTalent: exception thrown during processing file upload");
								messages.add("Error uploading your file");
								model.addAttribute("messages", messages);
								model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
								addLinks(model);
								logger.debug("Exiting TalentController.submitAddTalent");
								return "addBean";
					 }
					 logger.debug("Inside TalentController.submitAddTalent: before adding talent to DB");
					
						 beanId = service.addBean(bean);	  
					if(beanId != null && beanId!= 0)
					    logger.debug("Inside TalentController.submitAddTalent: talent added successfully to DB");
					else
					{
						logger.debug("Inside TalentController.submitAddTalent: failed to add talent to DB");
						messages.add("Error occured during adding talent to DB");
						model.addAttribute("messages", messages);
						model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
						addLinks(model);
						logger.debug("Exiting TalentController.submitAddTalent");
						return "addBean";
				    }
							 
				  model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
				  sysUser.setUserName(childBean.getField13());

							sysUser.setEmail(childBean.getField2());
							sysUser.setUserType(thisBean);
							sysUser.setUserRole("TalentAdmin");
							sysUser.setUserKey(beanId.toString());
							
							logger.debug("Inside TalentController.submitAddTalent: adding user to DB");
							Long userId = userService.addSystemUser(sysUser);
							if(userId != null  && userId!= 0)
								logger.debug("Inside TalentController.submitAddTalent: user added successfully to DB");
							else
							{
							logger.debug("Inside TalentController.submitAddTalent: failed to add user to DB");
							messages.add("Error occured during adding user to DB");
							model.addAttribute("messages", messages);
							model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
							addLinks(model);
							service.deleteTalent(childBean);
							logger.debug("Exiting TalentController.submitAddTalent");
							return "addBean";
							}
							
							messages.add("Your profile creation request is received successfully, You should login first  in order to be able to view opportunities .");
							model.addAttribute("messages", messages);
							model = FormFields.fillModelGeneric(model, thisBean, thisOperation, bean, bean);
							addLinks(model);
							logger.debug("Exiting TalentController.submitAddTalent: talent and user successfully added to DB");
							return "addBean";
   }
		
		 @RequestMapping(value="/download", method=RequestMethod.GET)
		 public void downloadFile(HttpServletResponse response,Model model, @RequestParam("fileName") String fileName) {
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

		 @RequestMapping(value= "/viewMyProfile", method = RequestMethod.GET)
		 public String viewMyProfile(Model model) throws IOException{
			 logger.debug("Entering method TalentController.viewMyProfile");
			 String thisOperation = null;
			 model.addAttribute("role", CurrentUser.getUserRole());
			 model.addAttribute("userName", CurrentUser.getUserName());
			
			 Talent searchBean = new Talent();
			 BasicBean resultBean = new Talent();
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
				  model = FormFields.fillModelGeneric( model, thisBean, thisOperation, resultBean, resultBean);
			}
			catch(Exception e)
			{
				logger.debug("Inside method TalentController.viewMyProfile: an errror occured");
			}
			model.addAttribute("view", "yes");
			addLinks(model); 
			logger.debug("Existing method TalentController.viewMyProfile: an errror occured");
			return "addBean";
				}
		
		
		/* @RequestMapping(value = "/viewTalentApplications", method = RequestMethod.GET)
	 public String viewTalentApplications(HttpServletRequest request,Model model) throws ParserConfigurationException, SAXException, IOException {
		 logger.debug("Entering method TalentController.viewProfile");
		 
		 logger.debug("Inside method TalentController.viewProfile, before set talent bean : ");
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
			app.setFirstPage(pageNumber * 5);
			app.setMaxResult(5);
			Talent search = new Talent();
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> dateFilter = new HashMap<>();

			ArrayList<String> searchFields = TalentApplications.getSearchFields();
			search.setSearchCriteria(criteria);
		 model.addAttribute("tableFields",  TalentApplications.getTableFields());
		 
		 model.addAttribute("xmlFields",  getFromXML.getFormFields("Talent"));
	    model.addAttribute("controllerName","TalentController");
	    Talent result = talentService.listBeansCustom(new Talent());
	    
	    List<Talent> appList = result.getResults();
		int totalCount = appList.size();
		logger.debug(
				"Inside method ApplicationController.getApplicationList , after retrieving applications from database. no of records:"
						+ totalCount);
		Integer nOfRecords = totalCount;
		Integer nOfPages = (totalCount) / 2;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
			model.addAttribute("beanList", appList);
			model.addAttribute("currentPage", "1");
		} else {

			Integer currentPage;
			if (request.getParameter("left") == null)
				currentPage = new Integer(request.getParameter("currentPage")) + 1;
			else
				currentPage = new Integer(request.getParameter("currentPage")) - 1;
			model.addAttribute("beanList", appList);

			model.addAttribute("currentPage", currentPage.toString());
		}


		
	    model.addAttribute("beanList",result.getResults());
	     logger.debug("Exiting method TalentController.viewProfile");
	 	return "JobTemp_viewTalentApplications";
	 }
*/	
	 @RequestMapping(value="/getTalent", method=RequestMethod.GET)
	 public String getTalent(Model model, @RequestParam("id") Integer id) {
		 String thisOperation = "View";
		 model.addAttribute("title", "Talent Info");
		 Talent talent = new Talent();
		 BasicBean currentBean = new Talent();
		 BasicBean oldBean = new Talent();
		 List<BasicBean> beanList = new ArrayList<BasicBean>();
		 HashMap<String, Object> searchCriteria = new HashMap<>();
		   searchCriteria.put("id", id);
		   talent.setSearchCriteria(searchCriteria);
		   currentBean = service.getBean(talent);

	try {
		model = FormFields.fillModelGeneric(model, thisBean, thisOperation, currentBean, oldBean);
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

		 logger.debug("Exiting method TalentController.getTalentList");
		 return "addBean";
	
	 }
	 
	 @RequestMapping(value= "/submitUpdateTalent", method = RequestMethod.POST)
	 public String submitUpdateTalent(@RequestParam("uploadedFileName") MultipartFile multipart,HttpServletRequest request, @RequestParam("oldBean") String oldBean,@ModelAttribute("bean") Talent bean, @ModelAttribute("id") String id, BindingResult result, Model model) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		 logger.debug("Entering method TalentController.submitUpdateTalent");
		 String thisOperation = "View";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Update Talent Profile");
		 Talent childBean = (Talent) bean;
	     ObjectMapper mapper = new ObjectMapper();
		 Talent beanBack = null;

		 if(multipart.getSize() != 0)
			{
			 String fileName = multipart.getOriginalFilename();
				
			 byte[] bytes = multipart.getBytes();
	         Path path = Paths.get("//home//CVs//OrangeCorners//" + fileName );
	         Files.write(path, bytes);
	         bean.setField16(fileName);     

			}

try {
	beanBack = mapper.readValue(oldBean, Talent.class);
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
service.updateBean(beanBack);

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
				    logger.debug("Inside TalentController.submitUpdateTalent: failed to get talent from DB");
					messages.add("Error occured during get talent from DB");
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

	try{			 
			  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, beanBack, beanBack);
			  

} 		
	catch(Exception e)
	{
	    logger.debug("Inside TalentController.submitUpdateTalent: throwing an exception:" + e.getMessage());
		messages.add("An error occured");
		model.addAttribute("messages", messages);
		model = FormFields.fillModelGeneric( model, thisBean,   "Add", bean, bean);
		addLinks(model);
		return "addBean"; 	
	}
		messages.add("Profile updated successfully!");
		model.addAttribute("messages",messages);
		logger.debug("Exiting TalentController.submitUpdateTalent");
		return "addBean";
	 	}

	 @RequestMapping(value="/getTalentList", method=RequestMethod.GET)
	 public String getTalentList(Model model, HttpServletRequest request) {
		 logger.debug("Entering method TalentController.getTalentList");
		 String thisOperation = "List";
				model.addAttribute("role", CurrentUser.getUserRole());
				 model.addAttribute("userName", CurrentUser.getUserName());
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

			BasicBean profile = new Talent();
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
			logger.debug("Inside TalentController.getTalentList : Getting Talents from DB");
			BasicBean profileList = service.listBeans(profile);
			List<BasicBean> resultList = profileList.getResults();
			
			try {
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
			addLinks(model);
			
			Map<String,String> x = new HashMap<String,String>();
			x.put("1", "hi");
		//	x = CurrentUser.getConnections();
			if(x == null || x.isEmpty())
				model.addAttribute("myConnections", x);
			else
				model.addAttribute("myConnections", x);
			
			logger.debug("Existing TalentController.getTalentList");
			return "getAllBeans";

		
	 }
 

	    @RequestMapping(value="/getPage", method=RequestMethod.GET)
	    public String getPage(Model model, HttpServletRequest request,
	    		@RequestParam("id") Integer pageNumber) {


	    	
	    	 logger.debug("Entering method TalentController.getTalentList");
			 String thisOperation = "List";
			 String thisBean = "Talent";
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

				BasicBean profile = new Talent();
				profile.setFirstPage(pageNumber * 5);
				profile.setMaxResult(5);
				HashMap<String, Object> criteria = new HashMap<>();
				HashMap<String, Object> createDateFilter = new HashMap<>();
				HashMap<String, Object> modifyDateFilter = new HashMap<>();
				ArrayList<String> searchFields = Talent.getSearchFields();

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
				logger.debug("Inside method TalentController.getTalentList: after getting beans from DB");
				List<BasicBean> resultList = profileList.getResults();
				
				try {
					
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
					 } catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug("Inside method TalentController.getTalentList: throwing exception " + e.getMessage());
					
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
				logger.debug("Existing method TalentController.getTalentList");
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
			ArrayList<String> searchFields = Program.getSearchFields();

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
				/*if (!searchFields.get(i).equals("fromDate") && !searchFields.get(i).equals("toDate")
						&& request.getParameter(searchFields.get(i)) != null)
					criteria.put(searchFields.get(i), request.getParameter(searchFields.get(i)));*/
				
			}
			
			if (!createDateFilter.isEmpty())
				{
				criteria.put("creationTime", createDateFilter);
				}
			if (!modifyDateFilter.isEmpty())
			{
				criteria.put("LastUpdateTime", modifyDateFilter);
		    }
			HashMap<String, Object> searchCriteria = new HashMap<>();
				searchCriteria.put("outerId", Integer.valueOf(CurrentUser.getEntityId()));
			Connection conn = new Connection();
			conn.setSearchCriteria(searchCriteria);
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
			
			addLinks(model); 
	Map<String,String> x = new HashMap<String,String>();
			
			x = CurrentUser.getConnections();
			if(x == null || x.isEmpty())
				model.addAttribute("myConnections", null);
			else
				model.addAttribute("myConnections", x);
			return "getAllBeans";

		
	 }

	 @RequestMapping(value = "/getApplicationList", method = RequestMethod.GET)
		public String getApplicationList(Model model, HttpServletRequest request) {
			logger.debug("Entering method TalentController.getApplicationList");
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
			
			criteria.put("applicantId", Long.valueOf(CurrentUser.getUserKey()));
			app.setSearchCriteria(criteria);
			logger.debug("Inside method TalentController.getApplicationList : before retrieving submitted applications from DB");
			Application appList = appService.listApplicationTemplate(app);
			List<BasicBean> resultList = appList.getResults();
			try {
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
			logger.debug("Inside method TalentController.getApplicationList : after retrieving submitted applications from DB, count : " + totalCount);
			
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
			logger.debug("Existing method TalentController.getApplicationList");
			return "getAllBeans";
		} 
	 
	 @RequestMapping(value="/connect", method=RequestMethod.GET)
	 public String connect(HttpServletRequest request, Model model,@RequestParam("id") Integer id) {
		   HashMap<String, Object> searchCriteria = new HashMap<>();
		   ArrayList<String> messages = new ArrayList<String>();
		   Connection connect = new Connection();
	  
	   connect.setOuterId(id);

	   try
	   {
	   connect.setInnerId(Integer.valueOf(CurrentUser.getEntityId()));
	   searchCriteria.put("id", CurrentUser.getEntityId());
	   }
	   catch(Exception e)
	   {
		   messages.add("You need to Log-In first");
		   model.addAttribute("tableMessages", messages);
	       return getTalentList(model, request);	   
	   }
	   String beanName = null;

	   Startup startup = new Startup();
	   Startup result = new Startup();
		 HashMap<String, Object> searchCInvestor = new HashMap<>();
		 searchCInvestor.put("id", CurrentUser.getEntityId());
		 startup.setSearchCriteria(searchCriteria);
		 result = (Startup) service.getBean(startup);

	   connect.setConnectionName(result.getField1());
	   connect.setStatus("pending");
	   connect.setRelationType(CurrentUser.getUserType());
	   connectionService.addConnection(connect);
	   
	   messages.add("Connection request sent successfully");
	   model.addAttribute("tableMessages", messages);
       return getTalentList(model, request);
	 }
	
	@InitBinder
	public void initBinder(final WebDataBinder binder){
	  final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	  binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	 public BasicBean updateOldBean(String oldBeanString, BasicBean newBean)
	 {
		 ObjectMapper mapper = new ObjectMapper();
		 Talent beanBack = null;
		try {
			beanBack = mapper.readValue(oldBeanString, Talent.class);
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
				if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Talent"))
				{
					model.addAttribute("linkKeys",  Talent.getLinks().keySet());
					model.addAttribute("links",  Talent.getLinks());
				}
			}	
	  
	 public class NullAwareBeanUtilsBean extends BeanUtilsBean{

		    @Override
		    public void copyProperty(Object dest, String name, Object value)
		            throws IllegalAccessException, InvocationTargetException {
		        if(value==null)
		        	return;
		        super.copyProperty(dest, name, value);
		    }
	 }
}
