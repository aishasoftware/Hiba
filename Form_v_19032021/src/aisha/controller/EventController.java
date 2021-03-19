package aisha.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
import aisha.bean.Event;
import aisha.bean.PlatformUser;
import aisha.bean.Talent;
import aisha.service.EventService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(value = "/EventController")
public class EventController {
private static String thisBean = "Event";

@Autowired
private EventService service;


@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
public String addEvent(Model model) {
	//logger.debug("Entering method EventController.addEvent");
	String thisOperation = "Add";

    model.addAttribute("role", CurrentUser.getUserRole());
	
    try {
		
		model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, new Event(), new Event()); 
			} catch (Exception e) {
		// TODO Auto-generated catch block
	//			logger.debug("Inside method EventController.addEvent : throwing excpetion " + e.getMessage());
		e.printStackTrace();
	}
  //  logger.debug("Existing method EventController.addEvent");
	return "addBean";
}

	@RequestMapping(value= "/submitAddEvent", method = RequestMethod.POST)
	 public String submitAddEvent(HttpServletRequest request, @ModelAttribute("bean") Event bean, BindingResult result, Model model) throws IOException, ParseException{
		 String thisOperation = "SubmitAdd";
		 
		 
		    String startTimeString = request.getParameter("startTime");
			String endTimeString = request.getParameter("endTime");
			String eventDateString = request.getParameter("eventDate");
			Time startTime = null;
			Time endTime = null;
			Date eventDate = null;
				startTime = Time.valueOf(startTimeString + ":00");
				endTime =  Time.valueOf(endTimeString + ":00");
				
				eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(eventDateString);
				
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Create Event");

	     
		 PlatformUser sysUser = new PlatformUser();
		 ArrayList<String> messages = new ArrayList<String>();
		 Long beanId = 0L;
		 
		try {
			  BasicBean emptyBean = (BasicBean) Class.forName("aisha.bean."+ thisBean).newInstance();
			  Event serachEvent =  new Event();
				 HashMap<String, Object> searchCriteria = new HashMap<>();
				 HashMap<String, Object> dateFilter = new HashMap<>();
				   searchCriteria.put("location", ((Event)bean).getLocation());
				   searchCriteria.put("eventDate", (eventDate));
				   
				   dateFilter.put("startTime", (startTime));
				   dateFilter.put("endTime", (endTime));
				   searchCriteria.put("startTime", dateFilter);
				   serachEvent.setSearchCriteria(searchCriteria);
				   serachEvent = (Event) service.listBeans(serachEvent);
				   if(serachEvent.getResults().size()>0)
				   {
					  
						  
						
						messages.add("Sorry, occupied!");
						model.addAttribute("messages", messages);
						model = FormFields.fillModelGeneric( model, thisBean, thisOperation, bean, bean);  
						return addEvent(model);
				   }
				   bean.setStartTime(startTime);
				   bean.setEndTime(endTime);
				   beanId = service.addBean(bean);	  
				   
			  model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, bean, bean);
			  
						
						messages.add("Your prrofile creation request is recieved successfully, please wait 249 feedback");
						model.addAttribute("messages", messages);
						model = FormFields.fillModelGeneric( model, thisBean,   "View", bean, bean);
					
						
						}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchMethodException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (SecurityException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
			
	      catch (InstantiationException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (IllegalAccessException e4) {
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
		


	 @RequestMapping(value="/getEvent", method=RequestMethod.GET)
	 public String getEvent(Model model, @RequestParam("id") Integer id) {
		 String thisOperation = "Get";
		 
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "View Event");

	     
		 Event event = new Event();
		 BasicBean result = new Event();
		 List<BasicBean> beanList = new ArrayList<BasicBean>();
		 //event.setFirstResults(10);
		// event.setMaxResults(10);
	
		 HashMap<String, Object> searchCriteria = new HashMap<>();
		   searchCriteria.put("id", id);
		   event.setSearchCriteria(searchCriteria);
		 result = service.getBean(event);
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
		// logger.debug("Inside method EventController.getEventList, before list events with criteria : " + event.getFilter());

		
		 return "addBean";
	
	 }

	 @RequestMapping(value= "/submitUpdateEvent", method = RequestMethod.POST)
	 public String submitUpdateEvent(HttpServletRequest request,@ModelAttribute("bean") Event bean, @ModelAttribute("id") String id, BindingResult result, Model model) throws IOException{
		 String thisOperation = "View";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "View Event");

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
		
		messages.add("Profile updated successfully!");
		model.addAttribute("messages",messages);
		
		return "addBean";

		

	 	}

	 @RequestMapping(value="/getEventList", method=RequestMethod.GET)
	 public String getEventList(Model model, HttpServletRequest request) {
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

			BasicBean profile = new Event();
			profile.setFirstPage(pageNumber * 50);
			profile.setMaxResult(50);
			HashMap<String, Object> criteria = new HashMap<>();
			HashMap<String, Object> createDateFilter = new HashMap<>();
			HashMap<String, Object> modifyDateFilter = new HashMap<>();
			ArrayList<String> searchFields = Event.getSearchFields();

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
			if(CurrentUser.getUserType()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
				searchCriteria.put("status", "active");
			profile.setSearchCriteria(searchCriteria);
			BasicBean profileList = service.listBeans(profile);
			List<BasicBean> resultList = profileList.getResults();
			
			try {
				//model = FormFields.fillModel( model,  "Event",  "EventController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
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
		service.updateBean(beanBack);
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
