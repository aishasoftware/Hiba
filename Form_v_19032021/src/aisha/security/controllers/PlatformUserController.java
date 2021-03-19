package aisha.security.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.json.JSONException;
import org.json.JSONObject;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Startup;
import aisha.controller.StartupController.NullAwareBeanUtilsBean;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

@Controller
@RequestMapping(value="/PlatformUserController")
public class PlatformUserController {
	
	private static String thisBean = "PlatformUser";
	@Autowired
	private SystemUserService service; 
	
	protected SystemUser currentSystemUser;
	public void setCurrentBean(SystemUser user)
	{
		this.currentSystemUser = user;
		}
	
	public SystemUser getCurrentBean()
	{return this.currentSystemUser;}

    
	 @RequestMapping(value = "/addPlatformUser", method = RequestMethod.GET)
	 public String addPlatformUser(Model model) {
		 String thisOperation = "Add";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Create PlatformUser Profile");

	
	     try {
			//model = FormFields.fillModel( model,  "PlatformUser",  "PlatformUserController",  "Add", "platform-body-add", new PlatformUser(), new PlatformUser(),"Add");
			model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, new PlatformUser(), new PlatformUser()); 
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
	 	return "addBean";
	 }
	 
	 @RequestMapping(value = "/generatePassword", method = RequestMethod.GET)
	 public String generatePassword(HttpServletRequest request, @ModelAttribute("bean") PlatformUser bean, BindingResult result, Model model) {
		 String thisOperation = "Add";
		 model.addAttribute("userName", CurrentUser.getUserName());
	     model.addAttribute("role", CurrentUser.getUserRole());
	     model.addAttribute("title", "Create PlatformUser Profile");
	     PlatformUser after = null;
		try {
			after = service.sendPasswordEmail(bean);
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     service.updateSystemUser(after);
	     try {
			//model = FormFields.fillModel( model,  "PlatformUser",  "PlatformUserController",  "Add", "platform-body-add", new PlatformUser(), new PlatformUser(),"Add");
			model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, new PlatformUser(), new PlatformUser()); 
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
	 	return "addBean";
	 }
	 
	 
		@RequestMapping(value= "/submitAddPlatformUser", method = RequestMethod.POST)
		 public String submitAddPlatformUser(HttpServletRequest request, @ModelAttribute("bean") PlatformUser bean, BindingResult result, Model model) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
			//, @RequestParam("uploadedFileName") MultipartFile multipart
			String thisOperation = "SubmitAdd";
			
			 PlatformUser sysUser = new PlatformUser();
			 sysUser = bean;
			 ArrayList<String> messages = new ArrayList<String>();
			 Long beanId = 0L;
			 
			try {
				if(CurrentUser.getUserType().equals("Investor"))
				{
					sysUser.setUserType(CurrentUser.getUserType());
				
				sysUser.setUserKey(CurrentUser.getEntityId());
				}
				else
				{
					sysUser.setUserType("Platform");
					sysUser.setUserRole("PlatformAdmin");
				}
				  beanId = service.addSystemUser(sysUser);	  
				  
				  try {
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, bean, bean);
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
							if(beanId == null)
						    {
								messages.add("This user name is already used, please try another user name");
								model.addAttribute("messages", messages);
								try {
									model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, bean, bean);
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								return "addBean";
						    }
						
							
							messages.add("Your user created successfully");
							model.addAttribute("messages", messages);
							try {
								model = FormFields.fillModelGeneric( model, thisBean,   "View", bean, bean);
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							
							}
		
		 catch (SecurityException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
				
		   catch (IllegalAccessException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} 
			
			catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 model.addAttribute("userName", CurrentUser.getUserName());
		     model.addAttribute("role", CurrentUser.getUserRole());
		     model.addAttribute("title", "Create PlatformUser Profile");
			return "addBean";
				}
			

	 
		@RequestMapping(value = "/getPlatformUserList", method = RequestMethod.GET)
			 public String getPlatformUserList(Model model, HttpServletRequest request) throws JsonProcessingException {
				 String thisOperation = "List";
				 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%% way : " + request.getParameter("criteria"));
						model.addAttribute("role", CurrentUser.getUserRole());
					String oldCriteria = null;
					oldCriteria = request.getParameter("criteria");
				
					HashMap<String,Object> resultMap = new HashMap<String,Object>();
				        ObjectMapper mapperObj = new ObjectMapper();
				         
				        if(oldCriteria != null)
				        {
				        	try {
				        
				            resultMap = mapperObj.readValue(oldCriteria, 
				                            new TypeReference<HashMap<String,String>>(){});
				            System.out.println("Output Map: "+resultMap);
				        } catch (IOException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				        }
				        }
					int pageNumber = 1;
					if (request.getParameter("currentPage") != null) {
						int currentPageNumber = Integer.parseInt(request.getParameter("currentPage"));
						if (request.getParameter("left") != null)
							pageNumber = currentPageNumber - 1;
						else if (request.getParameter("right") != null)
							pageNumber = currentPageNumber + 1;
					}
					pageNumber--;

					PlatformUser profile = new PlatformUser();
					profile.setFirstPage(pageNumber * 5);
					profile.setMaxResult(5);
					HashMap<String, Object> criteria = new HashMap<>();
					HashMap<String, Object> createDateFilter = new HashMap<>();
					HashMap<String, Object> modifyDateFilter = new HashMap<>();
					ArrayList<String> searchFields = PlatformUser.getSearchFields();

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
					criteria.remove("fromCreate");
					criteria.remove("toCreate");
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
					//HashMap<String, Object> searchCriteria = new HashMap<>();
					if(CurrentUser.getUserRole()!=null && !CurrentUser.getUserRole().equals("PlatformAdmin"))
					{
						criteria.put("status", "active");
						criteria.put("userKey", CurrentUser.getEntityId());
					}
					if(resultMap.size() > 0)
						profile.setSearchCriteria(resultMap);
					else
						profile.setSearchCriteria(criteria);
					BasicBean profileList = service.listSystemUsers(profile);
					List<BasicBean> resultList = profileList.getResults();
					
					try {
						//model = FormFields.fillModel( model,  "PlatformUser",  "PlatformUserController",  "Summary", "platform-body-view-get", profileList,  profileList ,"anonymousUser");
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
					Integer nOfPages = (totalCount + 1) / 5;
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
if(CurrentUser.getUserRole().equals("InvestorAdmin") || CurrentUser.getUserRole().equals("StartupAdmin"))
					model.addAttribute("view", "yes");
					model.addAttribute("myConnections", null);
					if(criteria.size() > 0)
					{
						 ObjectMapper mapper = new ObjectMapper();
						 String criteriaString = mapper.writeValueAsString(criteria);
						 model.addAttribute("criteria", criteriaString);
					}
					else
						model.addAttribute("criteria", oldCriteria);
					addLinks(model); 
					return "getAllBeans";


		}

		 @RequestMapping(value="/getPlatformUser", method=RequestMethod.GET)
		 public String getPlatformUser(Model model, @RequestParam("id") Integer id) {
			 String thisOperation = "Get";
			 model.addAttribute("role", CurrentUser.getUserRole());
			 PlatformUser user = new PlatformUser();
			 BasicBean result = new PlatformUser();
			 List<BasicBean> beanList = new ArrayList<BasicBean>();
			 //user.setFirstResults(10);
			// user.setMaxResults(10);
		
			 HashMap<String, Object> searchCriteria = new HashMap<>();
			   searchCriteria.put("id", id);
			   user.setSearchCriteria(searchCriteria);
			 result = service.getSystemUser(user);
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
			// logger.debug("Inside method UserController.getUserList, before list users with criteria : " + user.getFilter());

			
			 addLinks(model); 
			 return "addBean";
		
		 }
	 
	 
		 @RequestMapping(value= "/submitUpdatePlatformUser", method = RequestMethod.POST)
			 public String submitUpdatePlatformUser(HttpServletRequest request, @RequestParam("oldBean") String oldBean,@ModelAttribute("bean") PlatformUser bean, @ModelAttribute("id") String id, BindingResult result, Model model) throws IOException
			 {
				 String thisOperation = "View";
				 model.addAttribute("role", CurrentUser.getUserRole());
				
				PlatformUser sysUser = new PlatformUser();
			    ArrayList<String> messages = new ArrayList<String>();
				Long beanId = 0L;
				HashMap<String, Object> searchCriteria = new HashMap<>();
				BasicBean savedBean = updateOldBean(oldBean,bean);	 
			    try {
					model = FormFields.fillModelGeneric( model, thisBean,   thisOperation, savedBean, savedBean);
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
				model.addAttribute("messages",messages);
				addLinks(model); 
				return "addBean";
			 	}

		 	
		 
			 public BasicBean updateOldBean(String oldBeanString, BasicBean newBean)
			 {
				 ObjectMapper mapper = new ObjectMapper();
				 PlatformUser beanBack = null;
				try {
					beanBack = mapper.readValue(oldBeanString, PlatformUser.class);
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
								service.updateSystemUser(beanBack);
				return beanBack;
				}
			 
			 public class NullAwareBeanUtilsBean extends BeanUtilsBean{

				    @Override
				    public void copyProperty(Object dest, String name, Object value)
				            throws IllegalAccessException, InvocationTargetException {
				        if(value==null)return;
				        super.copyProperty(dest, name, value);
				    }

				}
			 

		 
			public static void addLinks(Model model)
			{
				if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Investor"))
				{
					model.addAttribute("linkKeys",  Investor.getLinks().keySet());
					model.addAttribute("links",  Investor.getLinks());
				}
				
			}		
			
			public  HashMap<String, String> jsonToMap(String t) throws JSONException {

		        HashMap<String, String> map = new HashMap<String, String>();
		        JSONObject jObject = new JSONObject(t);
		        Iterator<?> keys = jObject.keys();

		        while( keys.hasNext() ){
		            String key = (String)keys.next();
		            String value = jObject.getString(key); 
		            map.put(key, value);

		        }

		        System.out.println("json : "+jObject);
		        System.out.println("map : "+map);
		    return map;
		    }
		 
}
