package aisha.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import aisha.bean.BasicBean;
import aisha.bean.Complain;
import aisha.bean.Startup;
import aisha.service.ComplainService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/ComplainController")
public class ComplainController {
private static String thisBean = "Complain";

@Autowired
private ComplainService service;

	@RequestMapping(value = "/addComplain", method = RequestMethod.GET)
	 public String addComplain(Model model) {
		 String thisOperation = "Add";
		 
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Contact Us");
	     try {
			//model = FormFields.fillModel( model,  "Complain",  "ComplainController",  "Add", "platform-body-add", new Complain(), new Complain(),"Add");
			model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, new Complain(), new Complain()); 
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
	 
	
	@RequestMapping(value= "/submitAddComplain", method = RequestMethod.POST)
	 public String submitAddComplain(HttpServletRequest request, @ModelAttribute("bean") Complain bean, BindingResult result, Model model) throws IOException{
		 String thisOperation = "SubmitAdd";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Contact Us");
		 ArrayList<String> messages = new ArrayList<String>();
		 Long beanId = 0L;
		 
		
			  beanId = service.addComplain(bean);	  
			  try {
				model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, bean, bean);
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
			 
					
						
						messages.add("Your Message is recieved, We will contact you soon");
						model.addAttribute("messages", messages);

				
		return "addBean";
			}
	
	 @RequestMapping(value="/getComplain", method=RequestMethod.GET)
	 public String getComplain(Model model, @RequestParam("id") Integer id) {
		 String thisOperation = "Get";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Contact Us");
		 Complain complain = new Complain();
		 BasicBean result = new Complain();
		 List<BasicBean> beanList = new ArrayList<BasicBean>();
		 //complain.setFirstResults(10);
		// complain.setMaxResults(10);
	
		 HashMap<String, Object> searchCriteria = new HashMap<>();
		   searchCriteria.put("id", id);
		   complain.setSearchCriteria(searchCriteria);
		 result = service.getComplain(complain);
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

		 return "addBean";
	
	 }
	 
	 @RequestMapping(value= "/submitUpdateComplain", method = RequestMethod.POST)
	 public String submitUpdateComplain(HttpServletRequest request, @RequestParam("oldBean") String oldBean,@ModelAttribute("bean") Complain bean, @ModelAttribute("id") String id, BindingResult result, Model model) throws IOException{
		 String thisOperation = "View";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Contact Us");
		 ArrayList<String> messages = new ArrayList<String>();
		 Long beanId = 0L;
		 HashMap<String, Object> searchCriteria = new HashMap<>();
		 
		try {
			
				 
				 BasicBean savedBean = updateOldBean(oldBean,bean);
				 
			  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, savedBean, savedBean);
			  

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
		
		messages.add("Updated successfully!");
		model.addAttribute("messages",messages);
		
		return "addBean";


	 	}

	 @RequestMapping(value="/getComplainList", method=RequestMethod.GET)
	 public String getComplainList(Model model, HttpServletRequest request) {
		 String thisOperation = "List";
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

			Complain profile = new Complain();
			profile.setFirstPage(pageNumber * 50);
			profile.setMaxResult(50);
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
			/*if(CurrentUser.getUserType()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
				searchCriteria.put("status", "active");*/
			profile.setSearchCriteria(searchCriteria);
			BasicBean profileList = service.listComplains(profile);
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				//model = FormFields.fillModel( model,  "Complain",  "ComplainController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
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
			
			return "getAllBeans";

		
	 }
	 
	 public BasicBean updateOldBean(String oldBeanString, BasicBean newBean)
	 {
		 ObjectMapper mapper = new ObjectMapper();
		 Complain beanBack = null;
		try {
			beanBack = mapper.readValue(oldBeanString, Complain.class);
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
		service.updateComplain(beanBack);
		return beanBack;
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
