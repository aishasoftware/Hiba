package aisha.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aisha.bean.Package;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aisha.bean.BasicBean;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Resource;
import aisha.bean.Startup;
import aisha.bean.Subscription;
import aisha.bean.Talent;
import aisha.security.services.SystemUserService;
import aisha.service.PackageService;
import aisha.service.SubscriptionService;
import aisha.service.TalentService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;
import aisha.util.bean.FieldAttributes;
@Controller
@RequestMapping(value = "/SubscriptionController")
public class SubscriptionController {
	//General
	protected static Logger logger = Logger.getLogger(SubscriptionController.class);
	@Autowired
	private SubscriptionService service;
	@Autowired
	private SystemUserService sysService;
	@Autowired
	private PackageService packService;
	@Autowired
	private TalentService talentService;


	
	 
	 @RequestMapping(value="/getSubscriptionList", method=RequestMethod.GET)
	 public String getSubscriptionList(Model model, HttpServletRequest request) {
		 logger.debug("Entering method SubscriptionController.getSubscriptionList");
		 String thisOperation = "List";
		 String thisBean = "Subscription";
				model.addAttribute("role", CurrentUser.getUserRole());
				 model.addAttribute("userName", CurrentUser.getUserName());
			int pageNumber = 1;
			if (request.getParameter("currentPage") != null) {
				int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
				if (request.getParameter("left") != null)
					pageNumber = currentPageNumber - 1;
				else if (request.getParameter("right") != null)
					pageNumber = currentPageNumber + 1;
			}
			pageNumber--;

			BasicBean profile = new Subscription();
			profile.setFirstPage(pageNumber * 5);
			profile.setMaxResult(5);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Subscription.getSearchFields();

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
/*			HashMap<String, Object> searchCriteria = new HashMap<>();
			if(CurrentUser.getUserType()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
				searchCriteria.put("status", "active");
			profile.setSearchCriteria(searchCriteria);*/
			logger.debug("Inside SubscriptionController.getSubscriptionList : Getting Subscriptions from DB");
			BasicBean profileList = service.listBeans(profile);
			logger.debug("Entering method SubscriptionController.getSubscriptionList: after getting beans from DB");
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
				 } catch (Exception e) {
				// TODO Auto-generated catch block
					 logger.debug("Entering method SubscriptionController.getSubscriptionList: throwing exception: " + e.getMessage());
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
			
			logger.debug("Existing SubscriptionController.getSubscriptionList");
			return "getAllBeans";			
	 }


	@RequestMapping(value = "/addSubscription", method = RequestMethod.GET)
	 public String addSubscription(Model model) {
		logger.debug("Entering method SubscriptionController.addSubscription");
		String thisOperation = "Add";
		String thisBean = "Package";
		
	     try {
			
			model = FormFields.fillModelGeneric( model, thisBean, thisOperation, new Subscription(), new Subscription()); 
				} catch (Exception e) {
			// TODO Auto-generated catch block
					logger.debug("Inside method SubscriptionController.addSubscription : throwing excpetion " + e.getMessage());
			e.printStackTrace();
		}
	    addLinks(model);
	    logger.debug("Existing method SubscriptionController.addSubscription");
	 	return "addBean";
	 }	 
		
	@RequestMapping(value = "/submitAddSubscription", method = RequestMethod.GET)
	public String submitAddSubscription(HttpServletRequest request,
			@RequestParam("id") Integer packageId, Model model)
			throws IOException {
		String thisOperation = "SubmitAdd";
		String thisBean = "Package";
		ArrayList<String> messages = new ArrayList<String>();
		
	if (CurrentUser.getUserStatus().equals("anonymousUser"))
		{
		
		messages.add("Sorry, You need to login first");
		model.addAttribute("messages", messages);
		addLinks(model);
		return getPackageList(model, request);
    	}
		Subscription subs = new Subscription();
		try {
			int currentUserID = CurrentUser.getUserId();
			subs.setPackageId(packageId);
			subs.setPackUserId(currentUserID);
			service.addBean(subs);

			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					subs, subs);

			messages.add("Your prrofile creation request is recieved successfully, please wait 249 feedback");
			model.addAttribute("messages", messages);
		}

		catch (NoSuchMethodException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (SecurityException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

		catch (IllegalAccessException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addLinks(model);
		//return getPackageList(model, request);
		return "addBean";
	}
	
	 @RequestMapping(value="/getSubscription", method=RequestMethod.GET)
	 public String getSubscription(Model model, @RequestParam("id") Integer id) {
		 String thisOperation = "View";
		 String thisBean = "Subscription";
		 
		 Subscription subs = new Subscription();
		 BasicBean result = new Subscription();
		 List<BasicBean> beanList = new ArrayList<BasicBean>();

		 HashMap<String, Object> searchCriteria = new HashMap<>();
		   searchCriteria.put("id", id);
		   subs.setSearchCriteria(searchCriteria);
		 result = service.getBean(subs);
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
		
         addLinks(model);
		 logger.debug("Exiting method SubscriptionController.getSubscriptionList");
		 return "addBean";
	
	 }

	 @RequestMapping(value= "/viewMyProfile", method = RequestMethod.GET)
	 public String viewMyProfile(Model model) throws IOException{
		 logger.debug("Entering method SubscriptionController.viewMyProfile");
		 String thisOperation = "View";
		 String thisBean = "Subscription";

		 Subscription searchBean = new Subscription();
		 BasicBean resultBean = new Subscription();
		 ArrayList<String> messages = new ArrayList<String>();
		 HashMap<String, Object> searchCriteria = new HashMap<>();			 
	    	try {
			
			  searchCriteria.put("id", CurrentUser.getUserKey());
			  searchBean.setSearchCriteria(searchCriteria);
			  resultBean = service.getBean(searchBean);	  

			  System.out.println("$$$$$$$$$$$$$$$$ thisOperation : " + thisOperation);
			  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, resultBean, resultBean);
		}
		catch(Exception e)
		{
			logger.debug("Inside method SubscriptionController.viewMyProfile: an errror occured");
		}
		
		addLinks(model); 
		logger.debug("Existing method SubscriptionController.viewMyProfile: an errror occured");
		return "addBean";
			}
	 
	 @RequestMapping(value= "/submitUpdateSubscription", method = RequestMethod.GET)
	 public String submitUpdateSubscription(HttpServletRequest request, @RequestParam("id") String id, Model model) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		 logger.debug("Entering method SubscriptionController.submitUpdateSubscription");
		 String thisOperation = "View";
		 String thisBean = "Subscription";

	     model.addAttribute("title", "Update Subscription Profile");

		 Subscription subs = new Subscription();
		 ArrayList<String> messages = new ArrayList<String>();
		 HashMap<String, Object> searchCriteria = new HashMap<>();
	
			  searchCriteria.put("id", id);
			  subs.setSearchCriteria(searchCriteria);
              subs = (Subscription) service.getBean(subs);
              subs.setStatus("active");
			  service.updateBean(subs);
			  
					try{			 
			  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, subs, subs);
			  

} 		
	catch(Exception e)
	{
	    logger.debug("Inside SubscriptionController.submitUpdateSubscription: throwing an exception:" + e.getMessage());
		messages.add("An error occured");
		model.addAttribute("messages", messages);
		model = FormFields.fillModelGeneric( model, thisBean,   "Add", subs, subs);
		return "addBean"; 	
	}
		messages.add("Subscription approaved successfully!");
		model.addAttribute("messages",messages);	
		logger.debug("Exiting SubscriptionController.submitUpdateSubscription");
		return getSubscriptionList(model,request) ;
	 	}		 
 
	 
	
		@RequestMapping(value = "/addPackage", method = RequestMethod.GET)
		 public String addPackage(Model model) {
			logger.debug("Entering method PackageController.addPackage");
			String thisOperation = "Add";
			String thisBean = "Package";
			
		     try {
				
				model = FormFields.fillModelGeneric( model, thisBean, thisOperation, new Package(), new Package()); 
					} catch (Exception e) {
				// TODO Auto-generated catch block
						logger.debug("Inside method PackageController.addPackage : throwing excpetion " + e.getMessage());
				e.printStackTrace();
			}
		    addLinks(model);
		    logger.debug("Existing method PackageController.addPackage");
		 	return "addBean";
		 }	
	 
		@RequestMapping(value = "/submitAddPackage", method = RequestMethod.POST)
		public String submitAddPackage(HttpServletRequest request,
				@RequestParam("id") Integer packageId, Model model)
				throws IOException {
			String thisOperation = "SubmitAdd";
			String thisBean = "Package";
			ArrayList<String> messages = new ArrayList<String>();
			
			Package pack = new Package();
			try {
						model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
						pack, pack);

				messages.add("Your prrofile creation request is recieved successfully, please wait 249 feedback");
				model.addAttribute("messages", messages);
				model = FormFields.fillModelGeneric(model, thisBean, "View", pack,
						pack);

			}

			catch (NoSuchMethodException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} catch (SecurityException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}

			catch (IllegalAccessException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}

			catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addLinks(model);
			return "addBean";
		}
	 
	
	 
	 @RequestMapping(value="/getPackage", method=RequestMethod.GET)
	 public String getPackage(Model model, @RequestParam("id") Integer id) {
		 String thisOperation = "View";
		 String thisBean = "Package";
		 
		 Package pack = new Package();
		 BasicBean result = new Package();
		 List<BasicBean> beanList = new ArrayList<BasicBean>();

		 HashMap<String, Object> searchCriteria = new HashMap<>();
		   searchCriteria.put("id", id);
		   pack.setSearchCriteria(searchCriteria);
		 result = service.getBean(pack);
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
		
         addLinks(model);
		 logger.debug("Exiting method PackageController.getPackageList");
		 return "addBean";
	
	 }
	 
	 
	 @RequestMapping(value="/getPackageList", method=RequestMethod.GET)
	 public String getPackageList(Model model, HttpServletRequest request) {
		 logger.debug("Entering method PackageController.getPackageList");
		 String thisOperation = "List";
		 String thisBean = "Package";
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

			BasicBean profile = new Package();
			profile.setFirstPage(pageNumber * 5);
			profile.setMaxResult(5);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Package.getSearchFields();

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
			logger.debug("Inside method PackageController.getPackageList: after getting beans from DB");
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
				 } catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("Inside method PackageController.getPackageList: throwing exception " + e.getMessage());
				
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
			logger.debug("Existing method PackageController.getPackageList");
			return "getAllBeans";	
	 }
	 
	 

	    @RequestMapping(value="/getPage", method=RequestMethod.GET)
	    public String getPage(Model model, HttpServletRequest request,
	    		@RequestParam("id") Integer pageNumber) {


	    	
	    	 logger.debug("Entering method PackageController.getPackageList");
			 String thisOperation = "List";
			 String thisBean = "Package";
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

				BasicBean profile = new Package();
				profile.setFirstPage(pageNumber * 5);
				profile.setMaxResult(5);
				HashMap<String, Object> criteria = new HashMap<>();
				HashMap<String, Object> createDateFilter = new HashMap<>();
				HashMap<String, Object> modifyDateFilter = new HashMap<>();
				ArrayList<String> searchFields = Package.getSearchFields();

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
				logger.debug("Inside method PackageController.getPackageList: after getting beans from DB");
				List<BasicBean> resultList = profileList.getResults();
				
				try {
					
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
					 } catch (Exception e) {
					// TODO Auto-generated catch block
					logger.debug("Inside method PackageController.getPackageList: throwing exception " + e.getMessage());
					
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
				logger.debug("Existing method PackageController.getPackageList");
				return "getAllBeans";	

	      
	    }
	    

	 
	 
/*	 @RequestMapping(value="/connectSubscription", method=RequestMethod.GET)
	 public String connectSubscription(Model model,@RequestParam("id") Integer id) {
		 logger.debug("Entering method TalentController.getTalent");
		 String subID = null ;
				String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				if (x != "anonymousUser")
				{
					PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					//currentUser.getuserkey;
					subID = currentUser.getUserId();
				}
				
	Investor investor = new Investor();
	investor.setId(Long.valueOf("3637248"));
				Subscription sub = new Subscription();
				sub.getInvestors().add(investor);
				sub.setId(Long.valueOf(id));
				
				subService.updateSubscription(sub);
	   return "viewBean";
	 }
	 */
	 
	 
	 //this to be put in common generic utilities
	 public class NullAwareBeanUtilsBean extends BeanUtilsBean{

		    @Override
		    public void copyProperty(Object dest, String name, Object value)
		            throws IllegalAccessException, InvocationTargetException {
		        if(value==null)return;
		        super.copyProperty(dest, name, value);
		    }

		}

	 //this to be put in common generic utilities
	  public static void addLinks(Model model)
		{
			if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Startup"))
			{
				model.addAttribute("linkKeys",  Startup.getLinks().keySet());
				model.addAttribute("links",  Startup.getLinks());
			}
			
	}
	  
}
