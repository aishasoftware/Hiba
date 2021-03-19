package aisha.security.controllers;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;



public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
/*
	@Autowired
	@Qualifier(value = "systemUserDBAdapter")
	IBasicbdAdapter systemUserDBAdapter;*/

	private int loginAttemptsThreshold=3;
	private int loginTryCount;

	@SuppressWarnings("deprecation")
	@Transactional
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		final String thisMethod = "onAuthenticationFailure: ";
		logger.debug(thisMethod + "Entering.............. ");
/*		System.out.println("###### SecurityContextHolder : " +	SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal().toString());*/
System.out.println("###### login error"+exception.getMessage());
	
		super.onAuthenticationFailure(request, response, exception);
	}
	
	
}
