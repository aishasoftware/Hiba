package aisha.security.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Connection;
import aisha.bean.PlatformUser;
import aisha.security.beans.SystemUser;
import aisha.security.services.SystemUserService;
import aisha.service.ConnectionService;
import aisha.service.PackageService;
import aisha.service.SubscriptionService;
import aisha.util.CurrentUser;



public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {


	@Autowired
	private PackageService packService;


	@Autowired
	private SystemUserService sysService;
	
	@Autowired
	private ConnectionService conService;
	
	
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
		System.out.println("###### SecurityContextHolder : " +	SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal());
		
		PlatformUser col = 	 (PlatformUser)  SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
		
		//List<String> priv = packService.getPriviliges(col.getSubscriptionId());
		
/*if(priv!=null && !priv.isEmpty())
{
	Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		 for(int i=0;i<priv.size();i++)
		 {
			 SimpleGrantedAuthority authority = new SimpleGrantedAuthority(priv.get(i));
		 
		 
		 updatedAuthorities.add(authority);
		 }
		 SimpleGrantedAuthority userkey = new SimpleGrantedAuthority(CurrentUser.getUserKey());
		 updatedAuthorities.add(userkey);
		 updatedAuthorities.addAll(oldAuthorities);

		 SecurityContextHolder.getContext().setAuthentication(
		         new UsernamePasswordAuthenticationToken(
		                 SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
		                 SecurityContextHolder.getContext().getAuthentication().getCredentials(),
		                 updatedAuthorities)
		 );
		 System.out.println("@@@@@@@@@@@@@@@@@@@ author : " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
}*/
		System.out.println("### i am authentication : "+authentication);
		
		col.setLastLoginDate(new Date());
		col.setLoginTryCount(0);
		//sysService.updateSystemUser(col);
		super.onAuthenticationSuccess(request, response, authentication);
		
		System.out.println("################ i am collection : "+col);
/*		
		Connection con = new Connection();
		HashMap<String, Object> searchCriteria = new HashMap<>();
		searchCriteria.put("innerId", Integer.valueOf(CurrentUser.getEntityId()));
		con.setSearchCriteria(searchCriteria);
		conService.listConnections(con);*/
		//List<String> conns = conService.getConnections((CurrentUser.getEntityId()));
		//System.out.println("################ connections are : " + conns );
		
	}

}
