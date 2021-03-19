

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
import aisha.bean.PlatformUser;
import aisha.security.beans.SystemUser;
import aisha.util.CurrentUser;
import aisha.util.FormFields;

@Controller
@RequestMapping(value = "/ComplainController")
public class ComplainController {
private static String thisBean = "Complain";
	@RequestMapping(value = "/addComplain", method = RequestMethod.GET)
	 public String addComplain(Model model) {
		 String thisOperation = "Add";

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
