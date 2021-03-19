package aisha.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import aisha.bean.PlatformUser;
import aisha.bean.ProgramOld;
import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.Startup;
import aisha.bean.Talent;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;
import aisha.service.ApplicationService;
import aisha.service.ApplicationTemplateService;
import aisha.service.TalentService;
import aisha.test.getFromXML;
import aisha.util.CurrentUser;

@Controller
@RequestMapping(value = "/ApplicationController")
public class ApplicationController {
	//protected ApplicationTemplate subApp = new ApplicationTemplate();

	@Autowired
	private TalentService talentService; 
	@Autowired
	private SystemUserService userService; 
	@Autowired
	protected SystemUser currentUser;
	protected static Logger logger = Logger.getLogger(ApplicationController.class);

/*	public void setCurrentAppTemp(ApplicationTemplate subApp) {
		this.subApp = subApp;
	}

	public ApplicationTemplate getCurrentAppTemp() {
		return this.subApp;
	}*/

	@Autowired
	private ApplicationService appService;

	@Autowired
	private ApplicationTemplateService appTempService;

/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws ParserConfigurationException, SAXException, IOException {
		logger.debug("Entering method ApplicationController.login");
		return "JobTemp_login";
	}

	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public String loginError(Model model) throws ParserConfigurationException, SAXException, IOException {
		logger.debug("Entering method ApplicationController.loginError");
		model.addAttribute("error", "Wrong User Name or Password, Please try again ");
		return "JobTemp_login";

	}*/

	 @RequestMapping(value = "/addTalent", method = RequestMethod.GET)
	 public String addTalent(@ModelAttribute("controllerName") String controllerName,Model model) {
		logger.debug("Entering method TalentController.addTalent");
	 	
	 	model.addAttribute("bean", new Talent());
	 	model.addAttribute("beanName","Talent");
	    model.addAttribute("addFields", Talent.getAddFields());
	    logger.debug("Exiting method TalentController.addTalent");
	 	return "JobTemp_addTalent";
	 }

	 @RequestMapping(value= "/submitAddTalent", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	 public String submitAddTalent(Model model,HttpServletRequest request,@RequestParam("uploadedFileName") MultipartFile multipart, @ModelAttribute("bean") @Valid Talent talent, BindingResult result) {
		/*@RequestParam("uploadedFileName") MultipartFile multipart,   consumes = {"multipart/form-data"}, */
		 /*@RequestParam("uploadedFileName") MultipartFile multipart,*/
		
		String dateString = request.getParameter("dateOfBirth");
		String declaration = request.getParameter("declaration");
		Date dateOfBirth = null;
		try {
			 dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*talent.setDateOfBirth(dateOfBirth);
		if(declaration!=null)
		talent.setDeclaration(declaration);
		else
	    talent.setDeclaration("No");
		logger.debug("Entering method TalentController.submitAddTalent");
		*/
		
		Talent t = new Talent();
		
System.out.println("$$$$$$$$ multipart :"+multipart.getOriginalFilename());
		logger.debug("Inside method TalentController.submitAddTalent, before add bean : " + talent);
		System.out.println("ADDING TALENT  talent :"+talent);
		long talentID = talentService.addTalent(talent);
		System.out.println(" TALENT  ID :"+talent);
			if(multipart.getSize() != 0 && talentID !=0)
		{
			 byte[] bytes = null;
			 String fileName = null;
			 String ext = null;
			try {
			
			ext = FilenameUtils.getExtension(multipart.getOriginalFilename());
		
		
			bytes = multipart.getBytes();
			fileName = "talent_id_" + talentID +"."+ ext;
	         Path path = Paths.get("//home//CVs//" + fileName);
	         Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//talentModel.addAttribute("error", "Error in uploading your CV, please use the requested format and try again");
			e.printStackTrace();
			
		}
			 
/*         talent.setFilePath(fileName);
		talentService.updateTalent(talent);*/
		}
			
			t.setId(talentID);


			model.addAttribute("bean", new Application());
			try {
				model.addAttribute("xmlFields", getFromXML.getFormFields(1));
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("searchBean", new Talent());
			model.addAttribute("beanName", "ApplicationTemplate");
			model.addAttribute("addFields", Talent.getAddFields());
			model.addAttribute("controllerName", "TalentController");
		//	model.addAttribute("talent", t);
			logger.debug("Exiting method ApplicationController.submitAddTalent");
			return "JobTemp_getOpportunityForm_v_1.1";
		
	 	}


	@RequestMapping(value = "/getApplicationList", method = RequestMethod.GET)
	public String getApplicationList(Model model, HttpServletRequest request) {
		logger.debug("Entering method ApplicationController.getApplicationList");
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (x == "anonymousUser")
			model.addAttribute("role", "Guest");

		int pageNumber = 1;

		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		ProgramOld app = new ProgramOld();
		app.setId(1);
		app.setFirstPage(pageNumber * 5);
		app.setMaxResult(5);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> dateFilter = new HashMap<>();

		ArrayList<String> searchFields = ProgramOld.getSearchFields();

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
				criteria.put("CREATION_TIME", dateFilter);
		}
		model.addAttribute("controllerName", "ApplicationController");

		app.setSearchCriteria(criteria);
		logger.debug(
				"Inside method ApplicationController.getApplicationList , before retrieving applications from database");
		ProgramOld appList = appService.listApplications(app);
		int totalCount = appList.getTotalResult();
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

		model.addAttribute("tableFields", ProgramOld.getTableFields());
		model.addAttribute("controllerName", "ApplicationController");
		logger.debug("Exiting method ApplicationController.getApplicationList");
		return "JobTemp_getOpportunities";
	}

	@RequestMapping(value = "/getSystemUsers", method = RequestMethod.GET)
	public String getSystemUsers(Model model, HttpServletRequest request) {
		logger.debug("Entering method ApplicationController.getSubmittedApplications");
		currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("role", currentUser.getUserRole());
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		SystemUser user = new SystemUser();
		
		user.setFirstPage(pageNumber * 50);
		user.setMaxResult(50);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> dateFilter = new HashMap<>();
		ArrayList<String> searchFields = Application.getSearchFields();
		model.addAttribute("beanName", "ApplicationController");

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
		model.addAttribute("controllerName", "ApplicationController");
		user.setSearchCriteria(criteria);
		logger.debug(
				"Inside method ApplicationController.getSubmittedApplications , before retrieving submitted applications from database");
		SystemUser userList;
		//= userService.listSystemUsers(user);
		List<BasicBean> resultList;
		//= userList.getResults();
		int totalCount = user.getTotalResult();
		logger.debug(
				"Inside method ApplicationController.getSubmittedApplications , after retrieving submitted applications from database, no of records : "
						+ totalCount);
		//model.addAttribute("beanList", resultList);
		model.addAttribute("tableFields", SystemUser.getTableFields());
		Integer nOfRecords = totalCount;
		Integer nOfPages = (totalCount + 4) / 5;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
		//	model.addAttribute("beanList", resultList);
			model.addAttribute("currentPage", "1");
		} else {

			Integer currentPage;
			if (request.getParameter("left") == null)
				currentPage = new Integer(request.getParameter("currentPage")) + 1;
			else
				currentPage = new Integer(request.getParameter("currentPage")) - 1;
			//model.addAttribute("beanList", resultList);

			model.addAttribute("currentPage", currentPage.toString());
		}
		logger.debug("Exiting method ApplicationController.getSubmittedApplications");
		return "JobTemp_getSystemUsers";

	}

	 @RequestMapping(value="/getSystemUser", method=RequestMethod.GET)
	 public String getSystemUser(Model model,@RequestParam("id") String id) {
		 logger.debug("Entering method TalentController.getTalent");
	   model.addAttribute("beanName","SystemUser");
	   model.addAttribute("controllerName","ApplicationController");
	   SystemUser user = new SystemUser();
	   HashMap<String,Object> searchCriteria= new HashMap<>();
	   searchCriteria.put("id", Long.valueOf(id));
	   user.setSearchCriteria(searchCriteria);
	  // user = userService.getSystemUser(user);
	   model.addAttribute("bean", user);
	   model.addAttribute("viewFields", SystemUser.getTableFields());
	   logger.debug("Exiting method TalentController.getTalent");
	   return "JobTemp_getSystemUser";
	 }
	 
	@RequestMapping(value = "/getSubmittedApplications", method = RequestMethod.GET)
	public String getSubmittedApplications(Model model, HttpServletRequest request) {
		logger.debug("Entering method ApplicationController.getSubmittedApplications");
		currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("role", currentUser.getUserRole());
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
		app.setFirstPage(pageNumber * 50);
		app.setMaxResult(50);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> dateFilter = new HashMap<>();
		ArrayList<String> searchFields = Application.getSearchFields();
		model.addAttribute("beanName", "ApplicationController");

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
		model.addAttribute("controllerName", "ApplicationController");
		app.setCriteria(criteria);
		logger.debug(
				"Inside method ApplicationController.getSubmittedApplications , before retrieving submitted applications from database");
		Application appList = appTempService.listApplicationTemplate(app);
		List<Application> resultList = appList.getResults();
		int totalCount = app.getTotalResult();
		logger.debug(
				"Inside method ApplicationController.getSubmittedApplications , after retrieving submitted applications from database, no of records : "
						+ totalCount);
		model.addAttribute("beanList", resultList);
		model.addAttribute("tableFields", Application.getTableFields());
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
		logger.debug("Exiting method ApplicationController.getSubmittedApplications");
		return "JobTemp_getSubmittedApps";

	}

	
	@RequestMapping(value = "/viewSubmittedApp3", method = RequestMethod.GET)
	public String viewSubmittedApp3(HttpServletRequest request,@ModelAttribute("bean") Application app, Model model,@RequestParam("id") String id)
			throws ParserConfigurationException, SAXException, IOException {
		//@RequestParam("id") String id,
				int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;


		Application searchApp = new Application();
		searchApp.setApp_Id(id);
		searchApp.setFirstPage(pageNumber * 1);
		searchApp.setMaxResult(5);

		logger.debug("Entering method ApplicationController.viewSubmittedApp");
HashMap<String, Object> criteria = new HashMap<>();
//criteria.put("id", Long.valueOf(id));
Application search = new Application();
search.setCriteria(criteria);
searchApp.setCriteria(criteria);
Application results = appTempService.listApplicationTemplate(searchApp);
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userRoleType", currentUser.getUserRole());
			String evaluator = currentUser.getUserType();
			if(currentUser.getUserRole().equals("AdminEvaluator"))
			{	model.addAttribute("adminEvaFields", Application.getAllFields());}
			if (evaluator != null)
				model.addAttribute("evaFields", Application.getEvaluatorFields(Integer.valueOf(evaluator)));
			model.addAttribute("interviewFields", Application.getInterviewerFields());
		}
		
		List<Application> resultList = results.getResults();
		int totalCount = results.getTotalResult();
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		model.addAttribute("bean",resultList.get(0));
		model.addAttribute("id", results.getId());
		model.addAttribute("beanName", "ApplicationTemplate");
		

	
		Integer nOfRecords = totalCount;
		Integer nOfPages = totalCount;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
			model.addAttribute("beanList", resultList);
			model.addAttribute("currentPage", "1");
		} else {

			Integer currentPage = 0;
			if (request.getParameter("left") == null)
			{
				//model.addAttribute("nextId", resultList.get(currentPage+1).getId());
				currentPage = new Integer(request.getParameter("currentPage")) + 1;
			}
			else
			{
				//model.addAttribute("nextId", resultList.get(currentPage+1).getId());
				currentPage = new Integer(request.getParameter("currentPage")) - 1;
			
			}

			model.addAttribute("currentPage", currentPage.toString());
		}

		
		if(resultList.get(0).getAppStatus().equals("Under-Processing") || resultList.get(0).getAppStatus().equals("Short-Listed") || resultList.get(0).getAppStatus().equals("Rejected"))
			model.addAttribute("disabled", "disabled");
		Application w= resultList.get(0);
		model.addAttribute("shishi", w);
		logger.debug("Exiting method ApplicationController.viewSubmittedApp");
		return "JobTemp_viewSubmittedApp";
	}
	
	
	@RequestMapping(value = "/submitEvaluation", method = RequestMethod.POST)
	public String submitEvaluation(HttpServletRequest request,@RequestParam("id") String id,@RequestParam("currentPage") String currentPage,@ModelAttribute("bean") Application app,Model model)
			throws ParserConfigurationException, SAXException, IOException {

		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		logger.debug("Exiting method ApplicationController.submitEvaluation");
		if(!id.isEmpty())
			
		app.setId(Long.parseLong(id));

		Application subApp = appTempService.getApplicationTemplate(app);	
		if (app.getEv_1_comment() != null)
			subApp.setEv_1_comment(app.getEv_1_comment());
		if (app.getEv_2_comment() != null)
			subApp.setEv_2_comment(app.getEv_2_comment());
		if (app.getEv_3_comment() != null)
			subApp.setEv_3_comment(app.getEv_3_comment());
		if (app.getEv_4_comment() != null)
			subApp.setEv_4_comment(app.getEv_4_comment());
		if (app.getEv_5_comment() != null)
			subApp.setEv_5_comment(app.getEv_5_comment());
		if (app.getInterviewComment() != null)
			subApp.setInterviewComment(app.getInterviewComment());
		if (app.getEv_1_score() != null)
			subApp.setEv_1_score(app.getEv_1_score());
		if (app.getEv_2_score() != null)
			subApp.setEv_2_score(app.getEv_2_score());
		if (app.getEv_3_score() != null)
			subApp.setEv_3_score(app.getEv_3_score());
		if (app.getEv_4_score() != null)
			subApp.setEv_4_score(app.getEv_4_score());
		if (app.getEv_5_score() != null)
			subApp.setEv_5_score(app.getEv_5_score());
		if (app.getInterview_score() != null)
			subApp.setInterview_score(app.getInterview_score());
		if (app.getAppStatus() != null)
			subApp.setAppStatus(app.getAppStatus());
	
		appTempService.updateApplicationTemplate(subApp);

		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userRoleType", currentUser.getUserRole());
			String evaluator = currentUser.getUserType();
			if(currentUser.getUserRole().equals("AdminEvaluator"))
			{	model.addAttribute("adminEvaFields", Application.getAllFields());}
			if (evaluator != null)
				model.addAttribute("evaFields", Application.getEvaluatorFields(Integer.valueOf(evaluator)));
			model.addAttribute("interviewFields", Application.getInterviewerFields());
		}
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		model.addAttribute("bean",subApp);
		model.addAttribute("id", subApp.getId());
		model.addAttribute("beanName", "ApplicationTemplate");

		logger.debug("Exiting method ApplicationController.viewSubmittedApp");
		
		Application searchApp = new Application();
		searchApp.setApp_Id(1);
		searchApp.setFirstPage(pageNumber * 1);
		searchApp.setMaxResult(5);
logger.debug("Entering method ApplicationController.viewSubmittedApp");
HashMap<String, Object> criteria = new HashMap<>();
Application search = new Application();
search.setCriteria(criteria);
Application results = appTempService.listApplicationTemplate(search);
List<Application> resultList = results.getResults();
int totalCount = results.getTotalResult();
		Integer nOfRecords = totalCount;
		Integer nOfPages = totalCount;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
			model.addAttribute("beanList", resultList);
			model.addAttribute("currentPage", "1");
		} 
		else
		{
			model.addAttribute("currentPage", currentPage);
			}

		return "JobTemp_viewSubmittedApp";
	}

	


	
	@RequestMapping(value = "/viewSubmittedApp2", method = RequestMethod.GET)
	public String viewSubmittedApp2(HttpServletRequest request,@ModelAttribute("bean") ProgramOld app, @RequestParam("id") String id, Model model)
			throws ParserConfigurationException, SAXException, IOException {
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;


		Application appTemp = new Application();
		app.setId(1);
		app.setFirstPage(pageNumber * 1);
		app.setMaxResult(1);

		logger.debug("Entering method ApplicationController.viewSubmittedApp");
HashMap<String, Object> criteria = new HashMap<>();
criteria.put("talent_Id",id);
Application search = new Application();
search.setCriteria(criteria);
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userRoleType", currentUser.getUserRole());
			String evaluator = currentUser.getUserType();
			if(currentUser.getUserRole().equals("AdminEvaluator"))
			{	model.addAttribute("adminEvaFields", Application.getAllFields());}
			if (evaluator != null)
				model.addAttribute("evaFields", Application.getEvaluatorFields(Integer.valueOf(evaluator)));
			model.addAttribute("interviewFields", Application.getInterviewerFields());
		}
		logger.debug("Inside method ApplicationController.viewSubmittedApp, before get bean with id: " + id);
		Application subApp = appTempService.getApplicationTemplate(search);
		//subApp = temp;
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		model.addAttribute("bean", subApp);
		model.addAttribute("beanName", "ApplicationTemplate");
		Application appList = appTempService.listApplicationTemplate(subApp);
		List<Application> resultList = appList.getResults();
		int totalCount = subApp.getTotalResult();
	
		Integer nOfRecords = totalCount;
		Integer nOfPages = totalCount;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
			model.addAttribute("beanList", resultList);
			model.addAttribute("currentPage", "1");
		} else {

			Integer currentPage = 0;
			if (request.getParameter("left") == null)
			{
				//model.addAttribute("nextId", resultList.get(currentPage+1).getTalent_Id());
				currentPage = new Integer(request.getParameter("currentPage")) + 1;
			}
			else
			{
				//model.addAttribute("nextId", resultList.get(currentPage+1).getTalent_Id());
				currentPage = new Integer(request.getParameter("currentPage")) - 1;
			
			}

			model.addAttribute("currentPage", currentPage.toString());
		}

		
		if(subApp.getAppStatus().equals("Under-Processing") || subApp.getAppStatus().equals("Short-Listed") || subApp.getAppStatus().equals("Rejected"))
			model.addAttribute("disabled", "disabled");
		logger.debug("Exiting method ApplicationController.viewSubmittedApp");
		return "JobTemp_viewSubmittedApp";
	}

	@RequestMapping(value = "/viewOtherApp", method = RequestMethod.GET)
	public String viewOtherApp(HttpServletRequest request, @ModelAttribute("bean") ProgramOld app, Model model)
			throws ParserConfigurationException, SAXException, IOException {
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		Application appTemp = new Application();
		app.setId(1);
		app.setFirstPage(pageNumber * 1);
		app.setMaxResult(1);

		model.addAttribute("controllerName", "ApplicationController");
		logger.debug(
				"Inside method ApplicationController.viewSubmittedApplication , before retrieving submitted applications from database");
		Application appList = appTempService.listApplicationTemplate(appTemp);
		List<Application> resultList = appList.getResults();
		int totalCount = appTemp.getTotalResult();
		logger.debug(
				"Inside method ApplicationController.getSubmittedApplications , after retrieving submitted applications from database, no of records : "
						+ totalCount);
		model.addAttribute("beanList", resultList);
		model.addAttribute("tableFields", Application.getTableFields());
		Integer nOfRecords = totalCount;
		Integer nOfPages = totalCount;
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

		logger.debug("Entering method ApplicationController.viewSubmittedApp");
		Application search = new Application();
		HashMap< String, Object> criteria = new HashMap<>();
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userRoleType", currentUser.getUserRole());
			String evaluator = currentUser.getUserType();
			if(currentUser.getUserRole().equals("AdminEvaluator"))
			{	model.addAttribute("adminEvaFields", Application.getAllFields());}
			if (evaluator != null)
				model.addAttribute("evaFields", Application.getEvaluatorFields(Integer.valueOf(evaluator)));
			model.addAttribute("interviewFields", Application.getInterviewerFields());
		}
		logger.debug("Inside method ApplicationController.viewSubmittedApp, before get bean with id: ");
		Application subApp = appTempService.getApplicationTemplate(search);
		//subApp = temp;
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		model.addAttribute("bean", subApp);
		model.addAttribute("beanName", "ApplicationTemplate");
		if(subApp.getAppStatus().equals("Under-Processing") || subApp.getAppStatus().equals("Short-Listed") || subApp.getAppStatus().equals("Rejected"))
			model.addAttribute("disabled", "disabled");
		logger.debug("Exiting method ApplicationController.viewSubmittedApp");
		return "JobTemp_viewSubmittedApp";
	}

	@RequestMapping(value = "/submitEvaluation1", method = RequestMethod.POST)
	public String submitEvaluation1(@ModelAttribute("bean") Application app, @RequestParam("id") String id,
			Model model) {
		Application subApp = new Application();
		logger.debug("Exiting method ApplicationController.submitEvaluation");
		app.setId(Long.parseLong(id));
		if (app.getEv_1_comment() != null)
			subApp.setEv_1_comment(app.getEv_1_comment());
		if (app.getEv_2_comment() != null)
			subApp.setEv_2_comment(app.getEv_2_comment());
		if (app.getEv_3_comment() != null)
			subApp.setEv_3_comment(app.getEv_3_comment());
		if (app.getEv_4_comment() != null)
			subApp.setEv_4_comment(app.getEv_4_comment());
		if (app.getEv_5_comment() != null)
			subApp.setEv_5_comment(app.getEv_5_comment());

		if (app.getEv_1_score() != null)
			subApp.setEv_1_score(app.getEv_1_score());
		if (app.getEv_2_score() != null)
			subApp.setEv_2_score(app.getEv_2_score());
		if (app.getEv_3_score() != null)
			subApp.setEv_3_score(app.getEv_3_score());
		if (app.getEv_4_score() != null)
			subApp.setEv_4_score(app.getEv_4_score());
		if (app.getEv_5_score() != null)
			subApp.setEv_5_score(app.getEv_5_score());
		if (app.getAppStatus() != null)
			subApp.setAppStatus(app.getAppStatus());
		logger.debug("Exiting method ApplicationController.submitEvaluation, before updating bean : " + subApp);
		appTempService.updateApplicationTemplate(subApp);
		model.addAttribute("message1", "We have successfully recieved your evaluation!");
		logger.debug("Exiting method ApplicationController.submitEvaluation");
		return "JobTemp_submissionCompleted";
	}

	@RequestMapping(value = "/submitAddAppForm", method = RequestMethod.POST)
	public String submitAddAppForm(HttpServletRequest request, @ModelAttribute("bean") Application app, Model model) {
		long appID = Long.valueOf(request.getParameter("appID"));
		app.setTalent_Id(Integer.valueOf(CurrentUser.getUserKey()));
		app.setApp_Id(appID);
		long appId = appTempService.addApplicationTemplate(app);
		if(appId!=0)
		{
			model.addAttribute("message1",
		
				"We have successfully recieved your response");
			//model.addAttribute("message2", "Please use it in any future corespondence");
			logger.debug("Exiting method ApplicationController.submitAddAppForm");
		}
			else
			{
				model.addAttribute("message1",
					
					"Error in saving your application, please try again");
				
				logger.debug("Exiting method ApplicationController.submitAddAppForm error");
			// 	return "redirect:"+"/ApplicationController/viewOppApplicationForm";
				}
			return "JobTemp_submissionCompleted";
		
		}


	@RequestMapping(value = "/updateAppStatus", method = RequestMethod.POST)
	public String updateAppStatus(@RequestParam("currentPage") String currentPage,HttpServletRequest request,@ModelAttribute("bean") Application app, Model model) {
		logger.debug("Entering method ApplicationController.updateAppStatus");
		
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		Application subApp = appTempService.getApplicationTemplate(app);
		subApp.setAppStatus(app.getAppStatus());
		subApp.setEv_avg_score(app.getEv_avg_score());
		subApp.setReviewComment(app.getReviewComment());
		appTempService.updateApplicationTemplate(app);

		try {
			model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userRoleType", currentUser.getUserRole());
			String evaluator = currentUser.getUserType();
			if(currentUser.getUserRole().equals("AdminEvaluator"))
			{	model.addAttribute("adminEvaFields", Application.getAllFields());}
			if (evaluator != null)
				model.addAttribute("evaFields", Application.getEvaluatorFields(Integer.valueOf(evaluator)));
			model.addAttribute("interviewFields", Application.getInterviewerFields());
		}
		try {
			model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("bean",subApp);
		model.addAttribute("id", subApp.getId());
		model.addAttribute("beanName", "ApplicationTemplate");

		logger.debug("Exiting method ApplicationController.viewSubmittedApp");
		
		Application searchApp = new Application();
		searchApp.setApp_Id(1);
		searchApp.setFirstPage(pageNumber * 1);
		searchApp.setMaxResult(5);
logger.debug("Entering method ApplicationController.viewSubmittedApp");
HashMap<String, Object> criteria = new HashMap<>();
Application search = new Application();
search.setCriteria(criteria);
Application results = appTempService.listApplicationTemplate(search);
List<Application> resultList = results.getResults();
int totalCount = results.getTotalResult();
		Integer nOfRecords = totalCount;
		Integer nOfPages = totalCount;
		if (nOfPages == 0)
			nOfPages = 1;
		model.addAttribute("nOfRecords", nOfRecords);
		model.addAttribute("nOfPages", nOfPages);

		if (request.getParameter("currentPage") == null) {
			model.addAttribute("beanList", resultList);
			model.addAttribute("currentPage", "1");
		} 
		else
		{
			model.addAttribute("currentPage", currentPage);
			}
		model.addAttribute("message1", "We have successfully updated application!");
		logger.debug("Exiting method ApplicationController.updateAppStatus");
		return "JobTemp_submissionCompleted";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) throws ParserConfigurationException, SAXException, IOException {
		logger.debug("Entering method ApplicationController.homePage");
		return "JobTemp_homePage";
	}

	@RequestMapping(value = "/viewOppApplicationForm", method = RequestMethod.GET)
	public String viewOppApplicationForm(Model model,Talent talent) throws ParserConfigurationException, SAXException, IOException {
		logger.debug("Entering method ApplicationController.viewOppApplicationForm");

		model.addAttribute("bean", new Application());
		model.addAttribute("xmlFields", getFromXML.getFormFields(1));
		model.addAttribute("searchBean", new Talent());
		model.addAttribute("beanName", "ApplicationTemplate");
		model.addAttribute("addFields", Talent.getAddFields());
		model.addAttribute("controllerName", "TalentController");
		model.addAttribute("talent", talent);
		logger.debug("Exiting method ApplicationController.viewOppApplicationForm");
		return "JobTemp_getOpportunityForm_v_1.1";
	}

}
