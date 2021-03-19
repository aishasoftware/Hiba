package aisha.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aisha.bean.Application;
import aisha.bean.Complain;
import aisha.bean.Connection;
import aisha.bean.PlatformUser;
import aisha.bean.Program;
import aisha.bean.ProgramOld;
import aisha.bean.BasicBean;
import aisha.bean.Investor;
import aisha.bean.Startup;
import aisha.bean.Talent;
//import aisha.controller.StartupController.NullAwareBeanUtilsBean;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;
import aisha.service.ApplicationService;
import aisha.service.ApplicationTemplateService;
import aisha.service.ComplainService;
import aisha.service.ConnectionService;
import aisha.service.InvestorService;
import aisha.test.getFromXML;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

@Controller
@RequestMapping(value = "/InvestorController")
public class InvestorController {
	private static String thisBean = "Investor";
	protected Investor currentProfile = new Investor();
	@Autowired
	private ApplicationService programService;
	@Autowired
	private SystemUserService userService;
	@Autowired
	private ApplicationTemplateService appService;
	@Autowired
	private InvestorService service;
	@Autowired
	private ApplicationTemplateService appTempService;
	@Autowired
	private ConnectionService connService;

	@Autowired
	private ComplainService complainService;
	protected static Logger logger = Logger.getLogger(InvestorController.class);

	@RequestMapping(value = "/addInvestor", method = RequestMethod.GET)
	public String addInvestor(Model model) {

		String thisOperation = "Add";
		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					new Investor(), new Investor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "addBean";
	}

	@RequestMapping(value = "/submitAddInvestor", method = RequestMethod.POST)
	public String submitAddInvestor(HttpServletRequest request,
			@ModelAttribute("bean") Investor bean, BindingResult result,
			Model model) throws IOException, NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		logger.debug("Entering method InvestorController.submitAddInvestor");
		String thisOperation = "SubmitAdd";
		model.addAttribute("role", CurrentUser.getUserRole());
		Investor childBean = (Investor) bean;

		String dateString = request.getParameter("field26");

		Date dateOfFoundation = null;
		try {
			dateOfFoundation = new SimpleDateFormat("yyyy-MM-dd")
					.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		childBean.setField26(dateOfFoundation);
		PlatformUser sysUser = new PlatformUser();
		ArrayList<String> messages = new ArrayList<String>();
		Long beanId = 0L;

		try {
			beanId = service.addBean(bean);

			if (beanId == null || beanId == 0) {
				messages.add("Error occured during adding investor to DB");
				model.addAttribute("messages", messages);
				model = FormFields.fillModelGeneric(model, thisBean, "Add",
						bean, bean);
				return "addBean";
			}
		} catch (Exception e) {
			messages.add("This investor name is already used, please try another investor name");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric(model, thisBean, "Add", bean,
					bean);
			return "addBean";
		}
		model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
				bean, bean);
		sysUser.setUserName(childBean.getField13());
		if (!(childBean.getField14()).equals(childBean.getField15())) {
			messages.add("There is a mismatch between password and confirmed password");
			model.addAttribute("messages", messages);
			return "addBean";
		}

		sysUser.setEmail(childBean.getField2());
		sysUser.setUserType(thisBean);
		sysUser.setUserRole("InvestorAdmin");
		sysUser.setUserKey(beanId.toString());
		sysUser.setPassword(childBean.getField14());
		sysUser.setConfirmedPassword(childBean.getField15());
		// investor admin full name is same as investor name, to be used later
		// in connection
		sysUser.setUserFullName(childBean.getField1());
		try {

			Long userId = userService.addSystemUser(sysUser);
			if (userId == null || userId == 0) {

				messages.add("Error occured during adding user to DB");
				model.addAttribute("messages", messages);
				model = FormFields.fillModelGeneric(model, thisBean, "Add",
						bean, bean);
				service.deleteBean(childBean);
				return "addBean";
			}

		} catch (Exception e) {

			messages.add("This user name is already used, please try another user name");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric(model, thisBean, "Add", bean,
					bean);
			service.deleteBean(childBean);
			return "addBean";
		}

		messages.add("Your profile creation request is recieved successfully, please wait 249 feedback");
		model.addAttribute("messages", messages);
		model = FormFields
				.fillModelGeneric(model, thisBean, "View", bean, bean);
		return "addBean";
	}

	@RequestMapping(value = "/getInvestor", method = RequestMethod.GET)
	public String getInvestor(Model model, @RequestParam("id") Integer id) {
		model.addAttribute("title", "Investor Info");
		String thisOperation = "View";
		Investor investor = new Investor();
		BasicBean result = new Investor();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("id", id);
		investor.setSearchCriteria(searchCriteria);
		result = service.getBean(investor);
		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					result, result);

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

		return "addBean";
	}

	@RequestMapping(value = "/submitUpdateInvestor", method = RequestMethod.POST)
	public String submitUpdateInvestor(HttpServletRequest request,
			@RequestParam("oldBean") String oldBean,
			@ModelAttribute("bean") Investor bean,
			@ModelAttribute("id") String id, BindingResult result, Model model)
			throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String thisOperation = "View";
		Investor childBean = (Investor) bean;
		ObjectMapper mapper = new ObjectMapper();
		Investor beanBack = null;

		try {
			beanBack = mapper.readValue(oldBean, Investor.class);

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
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
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
	/*	PlatformUser oldUser = userService.listSystemUsers(sysUser);

		if (oldUser.getResults() != null && oldUser.getResults().size() > 0)
			sysUser = (PlatformUser) oldUser.getResults().get(0);
		else {
			messages.add("Error occured during get investor from DB");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric(model, thisBean, "Add", bean,
					bean);
			return "addBean";
		}
		if (!sysUser.getEmail().equals(beanBack.getField2())) {
			sysUser.setEmail(beanBack.getField2());
			userService.updateSystemUser((sysUser));
		}*/

		BasicBean savedBean = updateOldBean(oldBean, bean);
		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					savedBean, savedBean);
		} catch (Exception e) {
			messages.add("An error occured");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric(model, thisBean, "Add", bean,
					bean);
			return "addBean";
		}
		messages.add("Profile updated successfully!");
		model.addAttribute("messages", messages);
		return "addBean";
	}

	@RequestMapping(value = "/viewMyProfile", method = RequestMethod.GET)
	public String viewMyProfile(Model model) throws IOException {
		logger.debug("Entering method InvestorController.viewMyProfile");
		String thisOperation = null;

		Investor searchBean = new Investor();
		BasicBean resultBean = new Investor();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		try {

			searchCriteria.put("id", CurrentUser.getUserKey());
			searchBean.setSearchCriteria(searchCriteria);
			resultBean = service.getBean(searchBean);
			if (resultBean.getStatus().equals("closed"))
				thisOperation = "View";
			else
				thisOperation = "Update";
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					resultBean, resultBean);
		} catch (Exception e) {
			logger.debug("Inside method InvestorController.viewMyProfile: an errror occured");
		}

		logger.debug("Existing method InvestorController.viewMyProfile: an errror occured");
		return "addBean";
	}

	@RequestMapping(value = "/submitUpdateProgram", method = RequestMethod.POST)
	public String submitUpdateProgram(HttpServletRequest request,
			@RequestParam("oldBean") String oldBean,
			@ModelAttribute("bean") Program bean, BindingResult result,
			Model model) throws IOException {
		String thisOperation = "View";

		ArrayList<String> messages = new ArrayList<String>();
		BasicBean savedBean = updateOldBean(oldBean, bean);

		try {
			model = FormFields.fillModelGeneric(model, "Program",
					thisOperation, savedBean, savedBean);
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

		messages.add("Profile updated successfully!");
		model.addAttribute("messages", messages);
		return "addBean";

	}

	@RequestMapping(value = "/getInvestorList", method = RequestMethod.GET)
	public String getInvestorList(Model model, HttpServletRequest request) {
		logger.debug("Entering method InvestorController.getInvestorList");
		String thisOperation = "List";
		model.addAttribute("role", CurrentUser.getUserRole());
		model.addAttribute("userName", CurrentUser.getUserName());
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request
					.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		BasicBean profile = new Investor();
		profile.setFirstPage(pageNumber * 5);
		profile.setMaxResult(5);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> createDateFilter = new HashMap<>();
		HashMap<String, Object> modifyDateFilter = new HashMap<>();
		ArrayList<String> searchFields = Investor.getSearchFields();

		for (int i = 0; i < searchFields.size(); i++) {
			if (searchFields.get(i).equals("fromCreate")
					|| searchFields.get(i).equals("toCreate")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					createDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (searchFields.get(i).equals("fromModify")
					|| searchFields.get(i).equals("toModify")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					modifyDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (!searchFields.get(i).equals("fromDate")
					&& !searchFields.get(i).equals("toDate")
					&& request.getParameter(searchFields.get(i)) != null)
				criteria.put(searchFields.get(i),
						request.getParameter(searchFields.get(i)));

		}

		if (!createDateFilter.isEmpty()) {
			criteria.put("creationTime", createDateFilter);
			criteria.remove("toCreate");
			criteria.remove("fromCreate");
		}
		if (!modifyDateFilter.isEmpty()) {
			criteria.put("LastUpdateTime", modifyDateFilter);
		}
		HashMap<String, Object> searchCriteria = new HashMap<>();
		if (CurrentUser.getUserType() != null
				&& !CurrentUser.getUserRole().equals("PlatformAdmin"))
			criteria.put("status", "active");
		profile.setSearchCriteria(criteria);

		BasicBean profileList = service.listBeans(profile);

		List<BasicBean> resultList = profileList.getResults();

		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					profileList, profileList);
		} catch (Exception e) {
			// TODO Auto-generated catch block

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

		Map<String, String> x = new HashMap<String, String>();
		x = CurrentUser.getConnections(CurrentUser.getUserKey(), "User");
		if (x == null || x.isEmpty())
			model.addAttribute("myConnections", null);
		else
			model.addAttribute("myConnections", x);

		return "getAllBeans";
	}


    @RequestMapping(value="/getPage", method=RequestMethod.GET)
    public String getPage(Model model, HttpServletRequest request,
    		@RequestParam("id") Integer pageNumber) {


    	
    	 logger.debug("Entering method InvestorController.getInvestorList");
		 String thisOperation = "List";
		 String thisBean = "Investor";
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

			BasicBean profile = new Investor();
			profile.setFirstPage(pageNumber * 5);
			profile.setMaxResult(5);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Investor.getSearchFields();

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
			logger.debug("Inside method InvestorController.getInvestorList: after getting beans from DB");
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, profileList, profileList);
				 } catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug("Inside method InvestorController.getInvestorList: throwing exception " + e.getMessage());
				
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
			logger.debug("Existing method InvestorController.getInvestorList");
			return "getAllBeans";	

      
    }
    

	@RequestMapping(value = "/getConnectionList", method = RequestMethod.GET)
	public String getConnectionList(Model model, HttpServletRequest request) {
		String thisOperation = "List";
		String thisBean = "Connection";

		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request
					.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		BasicBean profile = new Program();
		profile.setFirstPage(pageNumber * 2);
		profile.setMaxResult(2);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> createDateFilter = new HashMap<>();
		HashMap<String, Object> modifyDateFilter = new HashMap<>();
		ArrayList<String> searchFields = Program.getSearchFields();

		for (int i = 0; i < searchFields.size(); i++) {
			if (searchFields.get(i).equals("fromCreate")
					|| searchFields.get(i).equals("toCreate")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					createDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (searchFields.get(i).equals("fromModify")
					|| searchFields.get(i).equals("toModify")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					modifyDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (!searchFields.get(i).equals("fromDate")
					&& !searchFields.get(i).equals("toDate")
					&& request.getParameter(searchFields.get(i)) != null)
				criteria.put(searchFields.get(i),
						request.getParameter(searchFields.get(i)));

		}

		if (!createDateFilter.isEmpty()) {
			criteria.put("creationTime", createDateFilter);
		}
		if (!modifyDateFilter.isEmpty()) {
			criteria.put("LastUpdateTime", modifyDateFilter);
		}
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("innerId",
				Integer.valueOf(CurrentUser.getEntityId()));
		Connection conn = new Connection();
		conn.setSearchCriteria(searchCriteria);
		BasicBean profileList = connService.listConnections(conn);
		List<BasicBean> resultList = profileList.getResults();

		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					profileList, profileList);
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

		Map<String, String> x = new HashMap<String, String>();
		x = CurrentUser.getConnections(CurrentUser.getUserKey(), "User");
		if (x == null || x.isEmpty())
			model.addAttribute("myConnections", null);
		else
			model.addAttribute("myConnections", x);

		return "getAllBeans";
	}

	@RequestMapping(value = "/getProgramList", method = RequestMethod.GET)
	public String getProgramList(Model model, HttpServletRequest request) {
		String thisOperation = "List";
		String thisBean = "Program";

		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request
					.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		BasicBean profile = new Program();
		profile.setFirstPage(pageNumber * 2);
		profile.setMaxResult(2);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> createDateFilter = new HashMap<>();
		HashMap<String, Object> modifyDateFilter = new HashMap<>();
		ArrayList<String> searchFields = Program.getSearchFields();

		for (int i = 0; i < searchFields.size(); i++) {
			if (searchFields.get(i).equals("fromCreate")
					|| searchFields.get(i).equals("toCreate")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					createDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (searchFields.get(i).equals("fromModify")
					|| searchFields.get(i).equals("toModify")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					modifyDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}
			if (!searchFields.get(i).equals("fromDate")
					&& !searchFields.get(i).equals("toDate")
					&& request.getParameter(searchFields.get(i)) != null)
				criteria.put(searchFields.get(i),
						request.getParameter(searchFields.get(i)));

		}

		if (!createDateFilter.isEmpty()) {
			criteria.put("creationTime", createDateFilter);
			criteria.remove("fromCreate");
			criteria.remove("toCreate");
		}
		if (!modifyDateFilter.isEmpty()) {
			criteria.put("LastUpdateTime", modifyDateFilter);
		}
		HashMap<String, Object> searchCriteria = new HashMap<>();
		if (CurrentUser.getUserType() != null
				&& !CurrentUser.getUserRole().equals("PlatformAdmin")) {
			criteria.put("status", "active");
		}

		profile.setSearchCriteria(criteria);
		BasicBean profileList = service.listBeans(profile);
		List<BasicBean> resultList = profileList.getResults();

		try {

			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					profileList, profileList);
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

		return "getAllBeans";
	}

	@RequestMapping(value = "/getMyProgramList", method = RequestMethod.GET)
	public String getMyProgramList(Model model, HttpServletRequest request) {
		String thisOperation = "List";
		String thisBean = "Program";
		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request
					.getParameter("currentPage"));
			if (request.getParameter("left") != null)
				pageNumber = currentPageNumber - 1;
			else if (request.getParameter("right") != null)
				pageNumber = currentPageNumber + 1;
		}
		pageNumber--;

		BasicBean profile = new Program();
		profile.setFirstPage(pageNumber * 5);
		profile.setMaxResult(5);
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> createDateFilter = new HashMap<>();
		HashMap<String, Object> modifyDateFilter = new HashMap<>();
		ArrayList<String> searchFields = Program.getSearchFields();

		for (int i = 0; i < searchFields.size(); i++) {
			if (searchFields.get(i).equals("fromCreate")
					|| searchFields.get(i).equals("toCreate")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					createDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (searchFields.get(i).equals("fromModify")
					|| searchFields.get(i).equals("toModify")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					modifyDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}
			if (!searchFields.get(i).equals("fromDate")
					&& !searchFields.get(i).equals("toDate")
					&& request.getParameter(searchFields.get(i)) != null)
				criteria.put(searchFields.get(i),
						request.getParameter(searchFields.get(i)));

		}

		if (!createDateFilter.isEmpty()) {
			criteria.put("creationTime", createDateFilter);
			criteria.remove("fromCreate");
			criteria.remove("toCreate");
		}
		if (!modifyDateFilter.isEmpty()) {
			criteria.put("LastUpdateTime", modifyDateFilter);
		}
		HashMap<String, Object> searchCriteria = new HashMap<>();
		if (CurrentUser.getUserType() != null
				&& CurrentUser.getUserType().equals("Investor")) {
			criteria.put("investorId",
					Integer.valueOf(CurrentUser.getEntityId()));
		}
		if (CurrentUser.getUserRole().equals("Evaluator")) {
			criteria.put("evaluator", String.valueOf(CurrentUser.getUserId()));
		}

		profile.setSearchCriteria(criteria);
		BasicBean profileList = service.listBeans(profile);
		List<BasicBean> resultList = profileList.getResults();

		try {

			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					profileList, profileList);
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
		
		return "getAllBeans";
	}

		@RequestMapping(value = "/submitAddComplain", method = RequestMethod.POST)
	public String submitAddComplain(HttpServletRequest request,
			@ModelAttribute("bean") Complain complain, BindingResult result,
			Model model) throws IOException {

		Long complainId = complainService.addComplain(complain);
		// PlatformUser sysUser = new PlatformUser();
		// sysUser.setUserName(investor.getField1());
		// sysUser.setUserRole("Investor");
		// sysUser.setUserKey(talentId.toString());

		logger.debug("Entering method TalentController.submitAddTalent");

		logger.debug("Inside method TalentController.submitAddTalent, before add bean : "
				+ complainId);

		// model.addAttribute("message1",
		// "Your profile has been created successfully");
		// model.addAttribute("message2","An email containing your crednetials is sent to you, check and login");
		logger.debug("Exiting method TalentController.submitAddTalent");
		// return "succCompleted";

		model.addAttribute("beanName", "InvestorUser");
		model.addAttribute("controllerName", "InvestorController");
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Your message recieved successfully!");

		model.addAttribute("messages", messages);
		return "addProfile";
	}

	@RequestMapping(value = "/addProgram", method = RequestMethod.GET)
	public String addProgram(Model model) {
		String thisOperation = "Add";
		String thisBean = "Program";

		try {

			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					new Program(), new Program());
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

		return "addBean";
	}

	@RequestMapping(value = "/submitAddProgram", method = RequestMethod.POST)
	public String submitAddProgram(HttpServletRequest request,
			@ModelAttribute("bean") Program bean, BindingResult result,
			Model model,
			@RequestParam("uploadedFileName") MultipartFile[] multipart)
			throws IOException, URISyntaxException {
		String thisOperation = "SubmitAdd";
		String thisBean = "Program";
		ArrayList<String> messages = new ArrayList<String>();

		HashMap<String, Object> searchCriteria = new HashMap<>();

		int i = 0;
		int j = 0;
		if (bean.getEva_1() != null)
			i++;
		if (bean.getEva_2() != null)
			i++;
		if (bean.getEva_3() != null)
			i++;
		if (bean.getEva_4() != null)
			i++;
		if (bean.getEva_5() != null)
			i++;

		if (bean.getInterv_1() != null)
			j++;
		if (bean.getInterv_2() != null)
			j++;
		if (bean.getInterv_3() != null)
			j++;
		if (bean.getInterv_4() != null)
			j++;
		if (bean.getInterv_5() != null)
			j++;

		if (i != Integer.valueOf(bean.getNoOfEvaluators())) {
			messages.add("Error in Number of Evaluators");
			model.addAttribute("messages", messages);
			try {
				model = FormFields.fillModelGeneric(model, thisBean,
						thisOperation, bean, bean);
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
			return addProgram(model);
		}
		if (j != Integer.valueOf(bean.getNoOfInterviewers())) {
			messages.add("Error in Number of Interviewers");
			model.addAttribute("messages", messages);
			try {
				model = FormFields.fillModelGeneric(model, thisBean,
						thisOperation, bean, bean);
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
			return addProgram(model);
		}
		Map<String, String[]> evasMap = request.getParameterMap();
		String evaString = request.getParameter("evaString");
		String intvString = request.getParameter("intvString");
		Map<String, String[]> evasBack = null;
		Map<String, String[]> intvsBack = null;
		ObjectMapper mapper = new ObjectMapper();
		evasBack = mapper.readValue(evaString, Map.class);
		intvsBack = mapper.readValue(intvString, Map.class);
		String startTimeString = request.getParameter("startTime");
		String endTimeString = request.getParameter("endTime");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd")
					.parse(startTimeString);
			endTime = new SimpleDateFormat("yyyy-MM-dd").parse(endTimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setStartTime(startTime);
		bean.setEndTime(endTime);
		Method method = null;

		String investorPath = CurrentUser.getEntityId();
		Investor investor = new Investor();
		searchCriteria.put("id", Long.valueOf(CurrentUser.getEntityId()));
		investor.setSearchCriteria(searchCriteria);
		investor = (Investor) service.getBean(investor);
		((Program) bean).setInvestorName(investor.getField1());
		Long beanId = 0L;
		beanId = programService.addApplication(bean);
		try {

			for (int i1 = 0; i1 < multipart.length; i1++) {
				try {
					int j1 = i1 + 1;
					method = bean.getClass().getMethod("setAppFile" + j1,
							String.class);

				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (multipart[i1].getSize() != 0) {
					String fileName = multipart[i1].getOriginalFilename();
					byte[] bytes = multipart[i1].getBytes();
					// String relativePath = "C://Users//hp//Desktop//DS//CVs//"
					// + beanId + ".xml";
					Path path = Paths.get("C://Users//hp//Desktop//DS//test//" + beanId
							+ ".xml");
					File file = new File(beanId + ".xml");

					file.createNewFile();

					Files.write(path, bytes);

					try {
						method.invoke(bean, beanId + ".xml");
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

					programService.updateApplication(bean);
				}
			}

			messages.add("Program created successfully");
			model.addAttribute("messages", messages);
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					bean, bean);
			model.addAttribute("evasBack", evasBack);
			model.addAttribute("intvsBack", intvsBack);

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

		return "addBean";
	}

	@RequestMapping(value = "/getProgram", method = RequestMethod.GET)
	public String getProgram(Model model, @RequestParam("id") Integer id) {
		String thisOperation = "View";
		String thisBean = "Program";
		Program program = new Program();
		BasicBean result = new Program();
		List<BasicBean> beanList = new ArrayList<BasicBean>();

		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("id", id);
		program.setSearchCriteria(searchCriteria);
		result = service.getBean(program);

		try {
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					result, result);

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

		return "addBean";

	}

	@RequestMapping(value = "/getApplicationForm", method = RequestMethod.GET)
	public String getApplicationForm(Model model, HttpServletRequest request,
			@RequestParam("id") String app_Id) {

		String x = CurrentUser.getUserStatus();
		if (!x.equals("active")) {
			model.addAttribute("role", CurrentUser.getUserRole());
			ArrayList<String> messages = new ArrayList<String>();
			messages.add("Sorry, you need to Sign-Up or Log-In before you can apply");
			model.addAttribute("messages", messages);
			return getProgramList(model, request);
		}
		String thisOperation = "Add";
		String thisBean = "Application";
		model.addAttribute("role", CurrentUser.getUserRole());
		Application application = new Application();
		BasicBean result = new Application();
		List<BasicBean> beanList = new ArrayList<BasicBean>();
		BasicBean prog = new Program();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("id", app_Id);
		prog.setSearchCriteria(searchCriteria);
		prog = (Program) service.getBean(prog);

		try {
			model = FormFields.fillModelGeneric(model, thisBean + "_" + app_Id,
					thisOperation, prog, result);

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

		String beanString = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			beanString = mapper.writeValueAsString(prog);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "JobTemp_getOpportunityForm_v_1.1";
	}

	@RequestMapping(value = "/getApplication", method = RequestMethod.GET)
	public String getApplication(Model model,
			@RequestParam("id") String idsArrayy) {
		String[] ids = idsArrayy.split(" ");
		String id = ids[0];
		Integer app_Id = Integer.valueOf(ids[1]);
		String thisOperation = null;
		if (CurrentUser.getUserRole().equals("Reviewer")
				|| CurrentUser.getUserRole().equals("PlatformAdmin"))
			thisOperation = "Reviewer";
		else if (CurrentUser.getUserRole().equals("InvestorAdmin"))
			thisOperation = "AdminView";
		else
			thisOperation = "View";
		String thisBean = "Application";
		model.addAttribute("role", CurrentUser.getUserRole());
		Application application = new Application();
		BasicBean result = new Application();
		List<BasicBean> beanList = new ArrayList<BasicBean>();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("id", id);
		application.setSearchCriteria(searchCriteria);
		result = appService.getApplicationTemplate(application);

		try {
			model = FormFields.fillModelGeneric(model, thisBean + "_" + app_Id,
					thisOperation, result, result);

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

		String beanString = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			beanString = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (!((Application) result).getAppStatus().equals("Submitted"))
			model.addAttribute("disabled", "disabled");
		else
			model.addAttribute("operation", "updateStatus");
		if (CurrentUser.getUserRole().equals("Reviewer")
				|| CurrentUser.getUserRole().equals("PlatformAdmin")) {
			model.addAttribute("updateStatus", "yes");
			model.addAttribute("operation", "updateStatus");
		}
		model.addAttribute("action", "updateStatus");
		model.addAttribute("progName", ((Application) result).getProgName());
		return "JobTemp_getOpportunityForm_v_1.1";

	}

	@RequestMapping(value = "/getApplicationList", method = RequestMethod.GET)
	public String getApplicationList(Model model, HttpServletRequest request)
			throws JsonProcessingException {
		logger.debug("Entering method ApplicationController.getSubmittedApplications");
		String thisOperation = "ListApps";
		String thisBean = "Application";
		model.addAttribute("role", CurrentUser.getUserRole());

		Integer appId = null;
		appId = Integer.valueOf(request.getParameter("id"));

		int pageNumber = 1;
		if (request.getParameter("currentPage") != null) {
			int currentPageNumber = Integer.parseInt(request
					.getParameter("currentPage"));
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
		HashMap<String, Object> criteria = new HashMap<>();
		HashMap<String, Object> createDateFilter = new HashMap<>();
		HashMap<String, Object> modifyDateFilter = new HashMap<>();
		ArrayList<String> searchFields = Application.getSearchFields();

		for (int i = 0; i < searchFields.size(); i++) {
			if (searchFields.get(i).equals("fromCreate")
					|| searchFields.get(i).equals("toCreate")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					createDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}

			if (searchFields.get(i).equals("fromModify")
					|| searchFields.get(i).equals("toModify")) {
				if (request.getParameter(searchFields.get(i)) != null
						&& !request.getParameter(searchFields.get(i)).isEmpty())

				{
					modifyDateFilter.put(searchFields.get(i),
							request.getParameter(searchFields.get(i)));

				}
			}
			if (!searchFields.get(i).equals("fromDate")
					&& !searchFields.get(i).equals("toDate")
					&& request.getParameter(searchFields.get(i)) != null)
				criteria.put(searchFields.get(i),
						request.getParameter(searchFields.get(i)));

		}

		if (!createDateFilter.isEmpty()) {
			criteria.put("submTime", createDateFilter);
			criteria.remove("fromCreate");
			criteria.remove("toCreate");
		}
		if (!modifyDateFilter.isEmpty()) {
			criteria.put("LastUpdateTime", modifyDateFilter);
		}

		criteria.put("app_Id", Long.valueOf(appId));
		app.setSearchCriteria(criteria);

		app.setSearchCriteria(criteria);
		logger.debug("Inside method ApplicationController.getSubmittedApplications , before retrieving submitted applications from database");
		Application appList = appService.listApplicationTemplate(app);
		List<BasicBean> resultList = appList.getResults();
		try {
			// model = FormFields.fillModel( model, "Investor",
			// "InvestorController", "Summary", "platform-body-view-get",
			// profileList, profileList ,"anonymousUser");
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					appList, appList);
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
		logger.debug("Inside method ApplicationController.getSubmittedApplications , after retrieving submitted applications from database, no of records : "
				+ totalCount);

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

		model.addAttribute("progId", appId);
		addLinks(model);
		logger.debug("Exiting method ApplicationController.getSubmittedApplications");
		return "getAllBeans";

	}

	@RequestMapping(value = "/submitAddAppForm", method = RequestMethod.POST)
	public String submitAddAppForm(HttpServletRequest request,
			@RequestParam("oldBean") String oldBean,
			@ModelAttribute("bean") Application app, Model model) {

		ObjectMapper mapper = new ObjectMapper();
		Program prog = null;
		try {
			prog = mapper.readValue(oldBean, Program.class);
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

		String thisOperation = "View";
		long appID = Long.valueOf(request.getParameter("appID"));
		String progName = String.valueOf(request.getParameter("progName"));

		model.addAttribute("role", CurrentUser.getUserRole());
		String user = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal().toString();
		/*
		 * if (user == "anonymousUser") { model.addAttribute("role",
		 * CurrentUser.getUserRole()); ArrayList<String> messages = new
		 * ArrayList<String>();
		 * messages.add("Sorry, you need to Log-In before you can apply");
		 * model.addAttribute("messages", messages); return
		 * getApplicationForm(model,String.valueOf(appID)); //return
		 * "JobTemp_submissionCompleted"; }
		 */
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ progName : " + progName);
		app.setProgName(progName);
		app.setApplicantId(Long.valueOf(CurrentUser.getEntityId()));
		app.setApplicantType(CurrentUser.getUserType());
		app.setApp_Id(Long.valueOf(String.valueOf(appID)));
		app.setEva_1(prog.getEva_1());
		app.setEva_2(prog.getEva_2());
		app.setEva_3(prog.getEva_3());
		app.setEva_4(prog.getEva_4());
		app.setEva_5(prog.getEva_5());

		app.setIntv_1(prog.getInterv_1());
		app.setIntv_2(prog.getInterv_2());
		app.setIntv_3(prog.getInterv_3());
		app.setIntv_4(prog.getInterv_4());
		app.setIntv_5(prog.getInterv_5());

		if (CurrentUser.getUserType().equals("Investor")
				|| CurrentUser.getUserType().equals("PlatformAdmin"))
			app.setAppStatus("mock");
		else
			app.setAppStatus("submitted");
		/*
		 * Program prog = new Program(); HashMap<String, Object> searchCriteria
		 * = new HashMap<>(); searchCriteria.put("id", appID);
		 * prog.setSearchCriteria(searchCriteria); prog = (Program)
		 * service.getBean(prog);
		 */
		app.setNoOfEvaluators(Integer.valueOf(prog.getNoOfEvaluators()));
		app.setNoOfInterviewers(Integer.valueOf(prog.getNoOfInterviewers()));

		// long appId =
		appTempService.addApplicationTemplate(app);

		try {
			// model = FormFields.fillModel( model, "Investor",
			// "InvestorController", "Add", "platform-body-add", new Investor(),
			// new Investor(),"Add");
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					new Investor(), new Investor());
			ArrayList<String> messages = new ArrayList<String>();
			messages.add("Your application created successfully!");
			model.addAttribute("messages", messages);
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
		// return "addBean";
		return "JobTemp_submissionCompleted";
	}

	@RequestMapping(value = "/submitEvaluation", method = RequestMethod.POST)
	public String submitEvaluation(HttpServletRequest request,
			@RequestParam("oldBean") String oldBean,
			@RequestParam("id") String id,
			@RequestParam("intv_score") String intv_score,
			@RequestParam("intv_comment") String intv_comment,
			@RequestParam("eva_score") String eva_score,
			@RequestParam("eva_comment") String eva_comment,
			@ModelAttribute("bean") Application app, Model model) {

		ObjectMapper mapper = new ObjectMapper();
		Application oldApp = null;
		try {
			oldApp = mapper.readValue(oldBean, Application.class);
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

		String thisOperation = "View";
		long appID = Long.valueOf(request.getParameter("appID"));
		Float eva_score_float = null;
		Float intv_score_float = null;
		model.addAttribute("role", CurrentUser.getUserRole());
		String user = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal().toString();
		System.out.println("#################### getEva_1 : "
				+ oldApp.getEva_1().equals(
						String.valueOf(CurrentUser.getUserId())));
		if (CurrentUser.getUserRole().equals("Evaluator")) {
			eva_score_float = Float.valueOf(eva_score);
			if (oldApp.getEva_1() != null
					&& oldApp.getEva_1().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				System.out.println("#################### eva 1 : ");
				oldApp.setEv_1_score(eva_score_float);
				oldApp.setEv_1_comment(eva_comment);
			}
			if (oldApp.getEva_2() != null
					&& oldApp.getEva_2().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				System.out.println("#################### eva 2 : ");
				oldApp.setEv_2_score(eva_score_float);
				oldApp.setEv_2_comment(eva_comment);
			}
			if (oldApp.getEva_3() != null
					&& oldApp.getEva_3().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				System.out.println("#################### eva 3 : ");
				oldApp.setEv_3_score(eva_score_float);
				oldApp.setEv_3_comment(eva_comment);
			}
			if (oldApp.getEva_4() != null
					&& oldApp.getEva_4().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				System.out.println("#################### eva 4 : ");
				oldApp.setEv_4_score(eva_score_float);
				oldApp.setEv_4_comment(eva_comment);
			}
			if (oldApp.getEva_5() != null
					&& oldApp.getEva_5().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				System.out.println("#################### eva 5 : ");
				oldApp.setEv_5_score(eva_score_float);
				oldApp.setEv_5_comment(eva_comment);
			}
		}

		if (CurrentUser.getUserRole().equals("Interviewer")) {
			intv_score_float = Float.valueOf(intv_score);

			if (oldApp.getIntv_1() != null
					&& oldApp.getIntv_1().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				oldApp.setIntv_1_score(intv_score_float);
				oldApp.setIntv_1_comment(intv_comment);
			}
			if (oldApp.getIntv_2() != null
					&& oldApp.getIntv_2().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				oldApp.setIntv_2_score(intv_score_float);
				oldApp.setIntv_2_comment(intv_comment);
			}
			if (oldApp.getIntv_3() != null
					&& oldApp.getIntv_3().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				oldApp.setIntv_3_score(intv_score_float);
				oldApp.setIntv_3_comment(intv_comment);
			}
			if (oldApp.getIntv_4() != null
					&& oldApp.getIntv_4().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				oldApp.setIntv_4_score(intv_score_float);
				oldApp.setIntv_4_comment(intv_comment);
			}
			if (oldApp.getIntv_5() != null
					&& oldApp.getIntv_5().equals(
							String.valueOf(CurrentUser.getUserId()))) {
				oldApp.setIntv_5_score(intv_score_float);
				oldApp.setIntv_5_comment(intv_comment);
			}

		}
		if (CurrentUser.getUserType().equals("Investor")
				|| CurrentUser.getUserType().equals("PlatformAdmin"))
			app.setAppStatus("mock");
		else
			app.setAppStatus("submitted");

		oldApp.setId(Long.valueOf(id));
		appTempService.updateApplicationTemplate(oldApp);

		try {
			// model = FormFields.fillModel( model, "Investor",
			// "InvestorController", "Add", "platform-body-add", new Investor(),
			// new Investor(),"Add");
			model = FormFields.fillModelGeneric(model, thisBean, thisOperation,
					new Investor(), new Investor());
			ArrayList<String> messages = new ArrayList<String>();
			messages.add("Your evaluation done successfully!");
			model.addAttribute("messages", messages);
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
		return "JobTemp_submissionCompleted";

	}

		@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public String updateAppStatus(HttpServletRequest request,
			@RequestParam("appID") String appID,
			@RequestParam("progName") String progName,
			@RequestParam("oldBean") String oldBean,
			@ModelAttribute("bean") Application bean,
			@ModelAttribute("id") String id, BindingResult result, Model model)
			throws IOException {
		String thisOperation = "View";
		model.addAttribute("role", CurrentUser.getUserRole());
		Class cls = null;
		Application childBean = (Application) bean;
		String appStatus = request.getParameter("appStatus");
		System.out.println("################### appStatus : " + appStatus);
		ArrayList<String> messages = new ArrayList<String>();

		// bean.setStatus(appStatus);

		// bean.setReviewComment(reviewer_comment);
		BasicBean beanBack = null;
		ObjectMapper mapper = new ObjectMapper();

		beanBack = mapper.readValue(oldBean, Application.class);
		System.out.println("$$$$$$$$$$$$ beanBack : " + progName);
		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(beanBack, childBean);
			// ((Investor)beanBack).setVersion((oldInvestor.getVersion()));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		service.updateBean(beanBack);

		/*
		 * try { model = FormFields.fillModelGeneric( model, thisBean,
		 * thisOperation, savedBean, savedBean); } catch (NoSuchMethodException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (SecurityException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalArgumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		messages.add("Applicatin updated successfully!");
		// try {
		// model.addAttribute("xmlFields",
		// getFromXML.getFormFields(Integer.valueOf(appID)));
		// model.addAttribute("bean", beanBack);

		/*
		 * String beanString = null; ObjectMapper mapper = new ObjectMapper();
		 * try { beanString = mapper.writeValueAsString(savedBean); } catch
		 * (JsonProcessingException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// model.addAttribute("oldBean", beanString );
		try {
			model = FormFields.fillModelGeneric(model, thisBean + "_" + appID,
					thisOperation, beanBack, beanBack);

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
		logger.debug("Exiting method ApplicationController.viewOppApplicationForm");
		return "JobTemp_getOpportunityForm_v_1.1";
	}

	@RequestMapping(value = "/evaluate", method = RequestMethod.GET)
	public String evaluate(Model model, @RequestParam("id") String mergID) {
		String[] ids = mergID.split(" ");
		String id = ids[0];
		Integer app_Id = Integer.valueOf(ids[1]);

		String eva_comment = null;
		Float eva_score = null;

		String Intv_comment = null;
		Float Intv_score = null;

		String thisOperation = null;
		String thisBean = "Application";
		String x = String.valueOf(CurrentUser.getUserId());
		model.addAttribute("role", CurrentUser.getUserRole());
		Map<String, String> evaluators = CurrentUser.getEvaluators();
		Map<String, String> interviewers = CurrentUser.getInterviewers();
		if (evaluators.containsKey(x) || interviewers.containsKey(x))
			thisOperation = "Evaluate";

		if (interviewers.containsKey(x))
			thisOperation = "Interview";

		if (CurrentUser.getUserRole().equals("PlatformAdmin")
				|| CurrentUser.getUserRole().equals("InvestorAdmin"))
			thisOperation = "AdminView";

		BasicBean result = new Application();
		Application application = new Application();
		List<BasicBean> beanList = new ArrayList<BasicBean>();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("id", id);
		application.setSearchCriteria(searchCriteria);
		String hi = ((Application) result).getEva_1();
		result = appService.getApplicationTemplate(application);
		if (((Application) result).getEva_1().equals(x)) {
			eva_score = ((Application) result).getEv_1_score();
			eva_comment = ((Application) result).getEv_1_comment();
		}

		if (((Application) result).getEva_2().equals(x)) {
			eva_score = ((Application) result).getEv_2_score();
			eva_comment = ((Application) result).getEv_2_comment();
		}

		if (((Application) result).getEva_3().equals(x)) {
			eva_score = ((Application) result).getEv_3_score();
			eva_comment = ((Application) result).getEv_3_comment();
		}

		if (((Application) result).getEva_4().equals(x)) {
			eva_score = ((Application) result).getEv_4_score();
			eva_comment = ((Application) result).getEv_4_comment();
		}

		if (((Application) result).getEva_5().equals(x)) {
			eva_score = ((Application) result).getEv_5_score();
			eva_comment = ((Application) result).getEv_5_comment();
		}

		if (((Application) result).getIntv_1().equals(x)) {
			Intv_score = ((Application) result).getIntv_1_score();
			Intv_comment = ((Application) result).getIntv_1_comment();
		}

		if (((Application) result).getIntv_2().equals(x)) {
			Intv_score = ((Application) result).getIntv_2_score();
			Intv_comment = ((Application) result).getIntv_2_comment();
		}

		if (((Application) result).getIntv_3().equals(x)) {
			Intv_score = ((Application) result).getIntv_3_score();
			Intv_comment = ((Application) result).getIntv_3_comment();
		}

		if (((Application) result).getIntv_4().equals(x)) {
			Intv_score = ((Application) result).getIntv_4_score();
			Intv_comment = ((Application) result).getIntv_4_comment();
		}

		if (((Application) result).getIntv_5().equals(x)) {
			Intv_score = ((Application) result).getIntv_5_score();
			Intv_comment = ((Application) result).getIntv_5_comment();
		}

		try {
			model = FormFields.fillModelGeneric(model, thisBean + "_" + app_Id,
					thisOperation, result, result);

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

		model.addAttribute("eva_score", eva_score);
		model.addAttribute("eva_comment", eva_comment);
		model.addAttribute("intv_score", Intv_score);
		model.addAttribute("intv_comment", Intv_comment);
		if (((Application) result).getAppStatus().equals("Evaluated")
				|| ((Application) result).getAppStatus().equals("Reviewed"))
			model.addAttribute("disabled", "disabled");
		model.addAttribute("controllerName", "InvestorController");
		model.addAttribute("action", "submitEvaluation");
		addLinks(model);
		logger.debug("Exiting method ApplicationController.viewOppApplicationForm");
		return "JobTemp_getOpportunityForm_v_1.1";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletResponse response, Model model,
			@RequestParam("fileName") String fileName) {
		// System.out.println("Calling Download:- " + fileName);
		// ClassPathResource pdfFile = new ClassPathResource("downloads/" +
		// fileName);
		// File file = new File("C://Users//hp//Desktop//DS//CVs//" + fileName);
		Path file = Paths.get("C://Users//hp//Desktop//DS//CVs//", fileName);
		// response.setContentType("application/pdf");
		response.setContentType("text/xml");
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// response.addHeader("Content-Disposition",
		// "attachment; filename="+fileName + ".pdf");
		try {
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, Model model,
			@RequestParam("fileName") String fileName) {
		File file = new File("C://Users//hp//Desktop//husam//" + fileName);

		try {
			InputStreamResource resource = new InputStreamResource(
					new FileInputStream(file));
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ file.getName());
			BufferedInputStream inStrem = null;

			inStrem = new BufferedInputStream(new FileInputStream(file));

			BufferedOutputStream outStream = new BufferedOutputStream(
					response.getOutputStream());

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inStrem.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);

				outStream.flush();
				inStrem.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BasicBean updateOldBean(String oldBeanString, BasicBean newBean) {
		BasicBean beanBack = null;
		ObjectMapper mapper = new ObjectMapper();
		Investor oldInvestor = null;

		try {
			if (newBean.getClass().getSimpleName().equals("Investor")) {

				beanBack = mapper.readValue(oldBeanString, Investor.class);
				oldInvestor = (Investor) beanBack;

			}

			else if (newBean.getClass().getSimpleName().equals("Application")) {

				beanBack = mapper.readValue(oldBeanString, Application.class);
			} else {

				beanBack = mapper.readValue(oldBeanString, Program.class);
			}

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

		BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
		try {
			notNull.copyProperties(beanBack, newBean);
			// ((Investor)beanBack).setVersion((oldInvestor.getVersion()));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (beanBack.getClass().getSimpleName().equals("Application")) {
			programService.updateApplication((Program) beanBack);
			/*
			 * HashMap<String, Object> criteria = new HashMap<>();
			 * criteria.put("appID", beanBack.getApp_Id());
			 */
			/*
			 * beanBack.setEv_1_score(((Application)newBean).getEv_1_score());
			 * beanBack.setEv_2_score(((Application)newBean).getEv_2_score());
			 * beanBack.setEv_3_score(((Application)newBean).getEv_3_score());
			 * beanBack
			 * .setEv_1_comment(((Application)newBean).getEv_1_comment());
			 * beanBack
			 * .setEv_2_comment(((Application)newBean).getEv_2_comment());
			 * beanBack
			 * .setEv_3_comment(((Application)newBean).getEv_3_comment());
			 * beanBack
			 * .setInterview_score(((Application)newBean).getInterview_score());
			 * beanBack
			 * .setInterviewComment(((Application)newBean).getInterviewComment
			 * ()); beanBack.setAppStatus(((Application)newBean).getStatus());
			 * appService.updateApplicationTemplate(beanBack);
			 */
		} else
			service.updateBean(beanBack);
		return beanBack;
	}

	public class NullAwareBeanUtilsBean extends BeanUtilsBean {

		@Override
		public void copyProperty(Object dest, String name, Object value)
				throws IllegalAccessException, InvocationTargetException {
			if (value == null)
				return;
			super.copyProperty(dest, name, value);
		}

	}

	  public static void addLinks(Model model)
			{
				if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Startup"))
				{
					model.addAttribute("linkKeys",  Startup.getLinks().keySet());
					model.addAttribute("links",  Startup.getLinks());
				}
				
		}
	  
}
