package aisha.security.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import aisha.bean.PlatformUser;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;



@Controller
@RequestMapping("/LogoutController")
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Autowired
	@Qualifier("systemUserService")
	protected SystemUserService systemUserService;
	
	@Override
	@RequestMapping("/logout")
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		final String thisMethod = "onLogoutSuccessHandler: ";
		logger.debug(thisMethod + "Entering.............. ");
		try{
		PlatformUser currentSystemUser = (PlatformUser) authentication
				.getPrincipal();
		currentSystemUser.setLastLoginDate(new Date());
		currentSystemUser.setHasLoggedOut(true);
		systemUserService.updateSystemUser(currentSystemUser);
		setDefaultTargetUrl("/");
		super.onLogoutSuccess(request, response, authentication);
		}
		catch(Exception e){
			super.onLogoutSuccess(request, response, authentication);
		}
	}

}