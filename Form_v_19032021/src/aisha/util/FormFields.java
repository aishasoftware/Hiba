package aisha.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aisha.bean.BasicBean;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Program;
import aisha.bean.Startup;
import aisha.bean.Talent;
import aisha.dao.BasicDAO;
import aisha.service.ConnectionService;
import aisha.util.bean.FieldAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
//@PropertySource(value = "classpath:platform.properties")
public class FormFields {
private static BasicDAO basicDAO;
@Autowired
private  BasicDAO mybasicDAO;

@Value( "${beanPath" )
private static String beanPath;

private static ConnectionService connectionService;
@Autowired
private  ConnectionService myConnectionService;

@PostConstruct
private void init()
{
	basicDAO = this.mybasicDAO;
	connectionService = this.myConnectionService;
}
	
	
	public static List<BasicBean> getOptionsFromBean(BasicBean basic, BasicBean bean)
	{  
		List<BasicBean> beanList = new ArrayList<BasicBean>();
		Iterator itr;
        if(basic.getClass().getSimpleName().equals("Startup") && bean.getClass().getSimpleName().equals("Talent"))
        	//&& CurrentUser.getUserType().equals("Talent"))
        {
        	
		Set<Startup> beanSet = ((Talent) bean).getStartups();
		Startup current = new Startup();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Startup) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }
        
        if(basic.getClass().getSimpleName().equals("Startup") && bean.getClass().getSimpleName().equals("Investor"))
        	//&& CurrentUser.getUserType().equals("Investor"))
        {
		Set<Startup> beanSet = ((Investor) bean).getStartups();
		Startup current = new Startup();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Startup) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }
        
        if(basic.getClass().getSimpleName().equals("Investor") && bean.getClass().getSimpleName().equals("Startup"))
        	//&& CurrentUser.getUserType().equals("Investor"))
        {
		Set<Investor> beanSet = ((Startup) bean).getInvestors();
		Investor current = new Investor();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Investor) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }
        
        if(basic.getClass().getSimpleName().equals("Talent") && bean.getClass().getSimpleName().equals("Startup"))
        	//&& CurrentUser.getUserType().equals("Investor"))
        {
		Set<Talent> beanSet = ((Startup) bean).getTalents();
		Talent current = new Talent();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Talent) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }
        
      /*  if(basic.getClass().getSimpleName().equals("Talent"))
        {
		Set<Talent> beanSet = ((Startup) bean).getTalents();
		Talent current = new Talent();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Talent) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }*/
        
      /*  if(basic.getClass().getSimpleName().equals("Investor"))
        {
		Set<Investor> beanSet = ((Startup) basic).getInvestors();
		Investor current = new Investor();
		itr = beanSet.iterator();
		while(itr.hasNext())
		{
			current = (Investor) itr.next();
			//startupList.add((BasicBean)current);
			beanList.add(current);
		}
       		
        }*/
		
		return beanList;
	}
		public static List<BasicBean> getOptionsFromDB(BasicBean basic)
		{  
	  if(basic.getClass().getSimpleName().equals("PlatformUser") && 
			  CurrentUser.getUserRole().equals("InvestorAdmin"))
	  {
	     
	HashMap<String, Object> searchCriteria = new HashMap<>();
	searchCriteria.put("userKey", CurrentUser.getUserId());
	basic.setSearchCriteria(searchCriteria);
	  }
	BasicBean result = basicDAO.listBeans(basic);
	  
	List<BasicBean> results = result.getResults();
	  
	return results;

	}
	public static List<FieldAttributes> getFormFields(String beanName, BasicBean bean) throws ParserConfigurationException, SAXException, IOException {
		
		ArrayList<FieldAttributes> result = new ArrayList<>();

		/*File fXmlFile = new File(
				Thread.currentThread().getContextClassLoader().getResource(beanName + ".xml").getFile());
		*/
		File fXmlFile = null;
		//System.out.println("########### beanName beanName beanName : " + beanName + ", " + beanName.substring(12) );
		if(beanName.contains("_"))
			//fXmlFile = new File("//home//programs//" + beanName.substring(1) + ".xml");
		{
			 System.out.println("########### beanName beanName beanName : " + beanName );
			 fXmlFile = new File(Thread.currentThread().getContextClassLoader().getResource("resources/"+ beanName.substring(12) + ".xml").getFile());
		   
		    }
		else
			fXmlFile = new File(Thread.currentThread().getContextClassLoader().getResource("resources/"+ beanName + ".xml").getFile());
		//fXmlFile = new File("//innovation//apache-tomcat-8.5.43//webapps//Form_v_1.0//WEB-INF//classes//resources//" + beanName + ".xml");
			//final File fXmlFile = new File("C://Users//hp//Desktop//DS//" + beanName + ".xml");
		//File 
			//fXmlFile = new File(Thread.currentThread().getContextClassLoader().getResource("resources/" + beanName + ".xml").getFile());
		
		String userRole;
		String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (x != "anonymousUser")
		{
			PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userRole = currentUser.getUserRole();
		}
		else
			userRole = x;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList ParentTag = doc.getElementsByTagName("fields");

		for (int temp = 0; temp < ParentTag.getLength(); temp++) {

			Node childFieldTag = ParentTag.item(temp);
			Element childFieldElement = (Element) childFieldTag;
			if (childFieldTag.getNodeType() == Node.ELEMENT_NODE) {
				NodeList FieldsNodeList = childFieldElement.getElementsByTagName("field");

				for (int temp1 = 0; temp1 < FieldsNodeList.getLength(); temp1++) {

					Node singleFieldNode = FieldsNodeList.item(temp1);
					if (singleFieldNode.getNodeType() == Node.ELEMENT_NODE) {
						FieldAttributes myField = new FieldAttributes();
						Element fieldElement = (Element) FieldsNodeList.item(temp1);
						// List method
						if (fieldElement.getElementsByTagName("List").item(
								0) != null) {
							String beanClassPath = fieldElement
									.getElementsByTagName("List")
									.item(0).getTextContent();
							
							myField.setList(beanClassPath);
							try {
								if (fieldElement.getElementsByTagName("type").item(0).getTextContent().equals("link")) 
								{
									BasicBean object = (BasicBean) Class.forName("aisha.bean."+beanClassPath)
								
										.newInstance();
					
							
									//Set<Startup> optionsList =  new HashSet<Startup>();
									//optionsList = ((Talent)bean).getStartups();
									myField.setOptions(getOptionsFromBean(object,bean));
									

								}
								

									if (fieldElement.getElementsByTagName("items").item(
											0) != null) 
									{
										BasicBean object = (BasicBean) Class.forName("aisha.bean."+beanClassPath)
									
											.newInstance();
						
									if(bean.getClass().getSimpleName().equals("Startup"))
										{
										//Set<Startup> optionsList =  new HashSet<Startup>();
										//optionsList = ((Talent)bean).getStartups();
										myField.setOptions(getOptionsFromBean(object,bean));
										}

									}
								if (fieldElement.getElementsByTagName("type").item(0).getTextContent().equals("select")) 
									{
								
									
									BasicBean object = (BasicBean) Class.forName("aisha.bean."+beanClassPath)
											
											.newInstance();
									List<BasicBean> options = new ArrayList<BasicBean>();
									options = getOptionsFromDB(object);
									myField.setOptions(options);
									}
								
								//myField.setOptions(getOptions(object));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						/*	myField.setOptionsObject(basicDAO.get(object,
									beanClassPath, commonService));*/
						}
					//// List Method	
						
						if (fieldElement.getElementsByTagName("name").item(0) != null)
							myField.setName(fieldElement.getElementsByTagName("name").item(0).getTextContent());
						
						if (fieldElement.getElementsByTagName("nameEng").item(0) != null)
							myField.setNameEng(fieldElement.getElementsByTagName("nameEng").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("nameArb").item(0) != null)
							myField.setNameArb(
									fieldElement.getElementsByTagName("nameArb").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("regExpr").item(0) != null)
							myField.setRegExpr(fieldElement.getElementsByTagName("regExpr").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("type").item(0) != null)
							myField.setType(
									fieldElement.getElementsByTagName("type").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("required").item(0) != null)
							myField.setRequired(
									fieldElement.getElementsByTagName("required").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("boxSize").item(0) != null)
							myField.setBoxSize(fieldElement.getElementsByTagName("boxSize").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("multiple").item(0) != null)
							myField.setMultiple(fieldElement.getElementsByTagName("multiple").item(0).getTextContent());

						
						if (fieldElement.getElementsByTagName("maxLength").item(0) != null)
							myField.setMaxLength(
									fieldElement.getElementsByTagName("maxLength").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("saveInField").item(0) != null)
							myField.setSaveInField(fieldElement.getElementsByTagName("saveInField").item(0).getTextContent());

					// Options method	
						if (fieldElement.getElementsByTagName("options").item(0) != null)
						{
							Set<String> itemsList =  new HashSet<String>();	
							NodeList optionssNodeList = fieldElement.getElementsByTagName("item");

							for (int temp2 = 0; temp2 < optionssNodeList.getLength(); temp2++) {

								Node singleOtionNode = optionssNodeList.item(temp2);
								Element optionElement = (Element) singleFieldNode;
								if (optionElement.getNodeType() == Node.ELEMENT_NODE) {
							
									//Element optionElement = (Element) optionssNodeList.item(temp2);
									if (optionElement.getElementsByTagName("item").item(0) != null)
									//if (option.getElementsByTagName("option").item(0) != null)
									{
										
										itemsList.add(fieldElement.getElementsByTagName("item").item(temp2).getTextContent());
									
									}
									
									}
							}
							myField.setItems(itemsList);
							System.out.println("################# here are my items:"+itemsList);
						}
					// Options method
						    result.add(myField);
					}

				}

			}

		}
System.out.println("$$$$$$$$$$$$$$$$ result : " + result);
		return result;
}

	
	public static Model fillModelGeneric(Model model, String beanName, String operation, BasicBean origBean, BasicBean newBean) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Boolean isConnection;
		String beanToString = beanToString(origBean);
		List<FieldAttributes> addFormFields = new ArrayList<FieldAttributes>();
		
		isConnection = isConnection(CurrentUser.getConnections(String.valueOf(origBean.getId()), "Entity"), CurrentUser.getUserKey());
		//addFormFields = addFormFields(beanName, isConnection, operation);
		model = addFormFields(model, beanName, isConnection, operation);
		String controllerName = null;
		if(beanName.equals("Resource") || beanName.equals("Package"))
			controllerName = "SubscriptionController";
		else
			controllerName = beanName + "Controller";
		return fillModel(model, beanName,  controllerName, operation, "platform-body-view-get", origBean, newBean, beanToString);
	}
	
	
	public static Boolean isConnection(Map<String,String> connections, String userKey)
	{
		Boolean isConnection = false;
		if (connections.containsKey(userKey))
		{   
			String status = connections.get(userKey);
			if(status.equals("active"))
			   isConnection = true;
		}
		else
			isConnection = false;
		return isConnection;
	}
	
//to be cached	
	public static String beanToString(BasicBean origBean)
	{
		
String methResult = null ;
if(origBean!=null)
 {
	ObjectMapper mapper = new ObjectMapper();
 
   try {
	   methResult = mapper.writeValueAsString(origBean);
} catch (JsonProcessingException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
 }
	
	return methResult;
	}
		
	public static Model fillModel(Model model, String beanName, String controllerName, String operation,String section,BasicBean origBean, BasicBean newBean, String oldBeanString)
	{
		if(beanName.contains("_"))
			beanName = "Application";
	    model.addAttribute("beanName",  beanName);
	    if(controllerName.equals("ProgramController") && beanName.equals("Program"))
		model.addAttribute("controllerName", "InvestorController");
	    if(controllerName.equals("ProgramController") && beanName.equals("Program"))
			model.addAttribute("controllerName", "InvestorController");
	    else
	    	model.addAttribute("controllerName", controllerName);
	    model.addAttribute("operation", operation);	
		model.addAttribute("section", section);
		model.addAttribute("oldBean", oldBeanString);
		model.addAttribute("bean", origBean);
		
		if(operation.equals("List"))
		{
		model.addAttribute("beanList", newBean.getResults());
		}
		
		 if(operation.equals("Add") && beanName.equals("Program"))
		 {
				Map<String, String> eva = new HashMap<String, String>();
				Map<String, String> intv = new HashMap<String, String>();
				/*eva = CurrentUser.getEvaluators();
				intv = CurrentUser.getInterviewers();
				model.addAttribute("myEvaluators", eva);
				model.addAttribute("myInterviewers", intv);*/
				String evaString = null;
				String intvString = null;
				ObjectMapper mapper = new ObjectMapper();
				try {
					evaString = mapper.writeValueAsString(eva);
					intvString = mapper.writeValueAsString(intv);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("evaString", evaString);
				model.addAttribute("intvString", intvString);
		 }
		 
		 if(operation.equals("View") && beanName.equals("Program"))
		 {
			 Map<String, String> evasBack = new HashMap<String, String>();
				Map<String, String> intvsBack = new HashMap<String, String>();
				/*evasBack = CurrentUser.getEvaluators();
				intvsBack = CurrentUser.getInterviewers();
				model.addAttribute("myEvaluators", evasBack);
				model.addAttribute("myInterviewers", intvsBack);

				model.addAttribute("evasBack", evasBack);
				model.addAttribute("intvsBack", intvsBack);*/

		 }
		 if(operation.equals("Add") && beanName.equals("Application"))
		 {
				
				model.addAttribute("operation", "Apply");
		 }
		model.addAttribute("userName", CurrentUser.getUserName());
	    model.addAttribute("role", CurrentUser.getUserRole());
	     
	  //Add Links
	    switch (beanName)
		{
			case "Investor":
				model.addAttribute("linkKeys",  Investor.getLinks().keySet());
				model.addAttribute("links",  Investor.getLinks());
				break;

			case "Startup":
				model.addAttribute("linkKeys",  Startup.getLinks().keySet());
				model.addAttribute("links",  Startup.getLinks());
				break;
				
			case "Talent":
				model.addAttribute("linkKeys",  Talent.getLinks().keySet());
				model.addAttribute("links",  Talent.getLinks());
				break;
		}
		return model;
	}

			// to be cached
	//public static List<FieldAttributes> addFormFields(String beanName, Boolean isConnection, String operation)
	public static Model addFormFields(Model model, String beanName, Boolean isConnection, String operation)
	{
		List<FieldAttributes> methodResults =  new ArrayList<FieldAttributes>();
		Class cls = null;
		
		try {
			if(beanName.contains("_"))
			cls = Class.forName("aisha.bean.Application");
			else
				cls = Class.forName("aisha.bean." + beanName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	List<String> publicFields = new ArrayList<String>();
	List<String> privateFields = new ArrayList<String>();
	List<String> protectedFields = new ArrayList<String>();
	
	List<String> addFields = new ArrayList<String>();
	List<String> viewFields = new ArrayList<String>();
	List<String> tableFields = new ArrayList<String>();
	List<String> evaluatorFields = new ArrayList<String>();
	List<String> interviewerFields = new ArrayList<String>();
	List<String> adminFields = new ArrayList<String>();
	
	List<String> enabledFieldsNames = new ArrayList<String>();
	List<String> disabledFieldsNames = new ArrayList<String>();

	
	List<FieldAttributes> allFields = new ArrayList<FieldAttributes>();
	List<FieldAttributes> enabledFields = new ArrayList<FieldAttributes>();
	List<FieldAttributes> disabledFields = new ArrayList<FieldAttributes>();
	
	FieldAttributes field = new FieldAttributes();
	//View Fields
    Method getPublicFields = null ;
	Method getPrivateFields = null ;
	Method getProtectedFields = null ;
	
	//Add Fields
	Method getAddFields = null ;
	
	//View Fields
	Method getViewFields = null ;
	
	//Table Fields
	Method getTableFields = null ;
	Method getAdminTableFields = null ;	
	//Evaluation Fields
	Method getEvaluatorFields = null ;
	
	//Interviewer Fields
	Method getInterviewerFields = null ;
	
	//Admin Fields
	Method getAdminFields = null ;
	
	try
	{
	    getPublicFields = cls.getDeclaredMethod("getPublicFields");
		getPrivateFields = cls.getDeclaredMethod("getPrivateFields");
		getProtectedFields = cls.getDeclaredMethod("getProtectedFields");	
		
		getAddFields = cls.getDeclaredMethod("getAddFields");	
		getViewFields = cls.getDeclaredMethod("getViewFields");	
		getTableFields = cls.getDeclaredMethod("getTableFields");
		getAdminTableFields = cls.getDeclaredMethod("getAdminTableFields");
		
		if(beanName.contains("_"))
		{
		getEvaluatorFields = cls.getDeclaredMethod("getEvaluatorFields");
		getInterviewerFields = cls.getDeclaredMethod("getInterviewerFields");
		getAdminFields = cls.getDeclaredMethod("getAdminFields");
		}
	}
	catch(Exception e)
	{
		System.out.println(" Exception !!!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
	}
	
	    	try {
	    		allFields = getFormFields(beanName, new BasicBean());
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
	    	

	    	
			try {
		    		if(operation.equals("Update"))
					{
		    			publicFields = (ArrayList<String>) getPublicFields.invoke(null);//enabled
				    	protectedFields = (ArrayList<String>) getProtectedFields.invoke(null);//enabled
				    	privateFields = (ArrayList<String>) getPrivateFields.invoke(null);//enabled
					
	 	   	    	enabledFieldsNames.addAll(publicFields);
	 	   	    	enabledFieldsNames.addAll(protectedFields);
	 	   	    	enabledFieldsNames.addAll(privateFields);
	 	   	    	
					}
		    		
		    		if(operation.equals("View"))//always disabled
				{
	    			if(beanName.contains("_"))
	    				{
	    				viewFields = (ArrayList<String>) getViewFields.invoke(null);//disabled
	    				disabledFieldsNames.addAll(viewFields);
	    				}
	    			
	    			else 
	    				{
	    				publicFields = (ArrayList<String>) getPublicFields.invoke(null);//disabled
	    				
				
				if(isConnection)
			    	protectedFields = (ArrayList<String>) getProtectedFields.invoke(null);//disabled
				
				disabledFieldsNames.addAll(publicFields);
				disabledFieldsNames.addAll(protectedFields);
	    				}
				}
		    		
		    		if(operation.equals("Evaluate"))
					{
		    	
		    				viewFields = (ArrayList<String>) getViewFields.invoke(null);//always disabled
		    				evaluatorFields = (ArrayList<String>) getEvaluatorFields.invoke(null);//enabled
		    				disabledFieldsNames.addAll(viewFields);
		    				enabledFieldsNames.addAll(evaluatorFields);
		    			
					}
	    		
		    		
		    		if(operation.equals("Interview"))
					{
		    	
		    				viewFields = (ArrayList<String>) getViewFields.invoke(null);//always disabled
		    				interviewerFields = (ArrayList<String>) getInterviewerFields.invoke(null);//enabled
		    				disabledFieldsNames.addAll(viewFields);
		    				enabledFieldsNames.addAll(interviewerFields);
		    			
					}
		    		
		    		if(operation.equals("AdminView"))
					{
	    				viewFields = (ArrayList<String>) getViewFields.invoke(null);//always disabled
	    				adminFields = (ArrayList<String>) getAdminFields.invoke(null);//always disabled
	    				disabledFieldsNames.addAll(viewFields);
	    				disabledFieldsNames.addAll(adminFields);
	 	   	    	
					}
		    		
		    		if(operation.equals("Add"))
	 	    		{
	 	    			addFields = (ArrayList<String>) getAddFields.invoke(null);//always enabled
	 	   	    	
	 	    			enabledFieldsNames.addAll(addFields);
	 	   	    
	 	    		}

	 	    		if(operation.equals("List"))
	 	    		{
// table and search fields are different for users other than admin
	 	    			if(CurrentUser.getUserType().equals("Admin"))
	 	    				tableFields = (ArrayList<String>) getAdminTableFields.invoke(null);
	 	    			else
	 	    				{
	 	    				tableFields = (ArrayList<String>) getTableFields.invoke(null);
	 	    				model.addAttribute("subscribe", "yes");
	 	    				}
						disabledFieldsNames.addAll(tableFields);
	 	    		}
	 	    		
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
		
			
			 for(int i= 0 ; i < allFields.size() ; i++)
			 {
				 if(enabledFieldsNames.contains(allFields.get(i).getName()))
					 enabledFields.add(allFields.get(i));
			 }
			 
			 for(int i= 0 ; i < allFields.size() ; i++)
			 {
				 if(disabledFieldsNames.contains(allFields.get(i).getName()))
					 disabledFields.add(allFields.get(i));
			 }
if(operation.equals("List"))
	model.addAttribute("tableFields", disabledFields);
else
{
model.addAttribute("enabledFields", enabledFields);
model.addAttribute("disabledFields", disabledFields);
}
return model;
	}
	

}

