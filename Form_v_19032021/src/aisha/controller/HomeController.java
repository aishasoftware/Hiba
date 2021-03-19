package aisha.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import aisha.bean.Complain;
import aisha.bean.Investor;
import aisha.bean.PlatformUser;
import aisha.bean.Startup;
import aisha.bean.Talent;
import aisha.security.beans.SystemUser;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) throws ParserConfigurationException, SAXException, IOException {
		model.addAttribute("controllerName", "ApplicationController");
			model.addAttribute("role", CurrentUser.getUserRole());
			model.addAttribute("userName", CurrentUser.getUserName());
			
			if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Investor"))
			{
				model.addAttribute("linkKeys",  Investor.getLinks().keySet());
				model.addAttribute("links",  Investor.getLinks());
			}
			
			if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Startup"))
			{
				model.addAttribute("linkKeys",  Startup.getLinks().keySet());
				model.addAttribute("links",  Startup.getLinks());
			}
			
			if(CurrentUser.getUserType() != null && CurrentUser.getUserType().equals("Talent"))
			{
				model.addAttribute("linkKeys",  Talent.getLinks().keySet());
			    model.addAttribute("links",  Talent.getLinks());
	}
		//return "redirect:"+"/TalentController/addTalent";
	 return "JobTemp_homePage";

	}

	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public String loginError(Model model) throws ParserConfigurationException, SAXException, IOException {
		model.addAttribute("error", "Wrong User Name or Password, Please try again ");
		return "JobTemp_login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% HHHHHHHHHHHHHHHHHHHHHHHHHHHH ");
		return "JobTemp_login";
	}

	

	@RequestMapping(value = "/addComplain", method = RequestMethod.GET)
	 public String addComplain(Model model) {
		 String thisOperation = "Add";
		 String thisBean = "Complain";
	     model.addAttribute("role", CurrentUser.getUserRole());
		
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
	 
}
