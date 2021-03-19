package aisha.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;
import aisha.security.beans.SystemUser;
import aisha.service.ConnectionService;
import aisha.service.InvestorService;


public class CurrentUser {


@Autowired

private static BasicDAO basicDAO;
@Autowired
private  BasicDAO mybasicDAO;

private static ConnectionService connectionService;
@Autowired
private  ConnectionService myConnectionService;

private static InvestorService invService;
@Autowired
private  InvestorService myInvService;

@PostConstruct
private void init()
{
	basicDAO = this.mybasicDAO;
	invService = this.myInvService;
	connectionService = this.myConnectionService;
}
	public static Map<String,String> getConnections(String user, String type)
	{
		Map<String,String> conns = null;
	//	PlatformUser currentUser = new PlatformUser();
	//	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		if (user != "anonymousUser")
		    {
			// currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			conns = connectionService.getConnections(user, type);
			System.out.println("$$$$$$$$$$$$$$$$$$ conns : " + conns);
		return conns;
		    }
		else
		return null;
	}
	
	public static Map<String,String> getEvaluators()
	{
		Map<String,String> evas = null;
		PlatformUser currentUser = new PlatformUser();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		if (user != "anonymousUser")
		    {
			currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			evas = invService.getEvaluators(String.valueOf(CurrentUser.getEntityId()));
			System.out.println("$$$$$$$$$$$$$$$$$$ conns : " + evas);
		return evas;
		    }
		else
		return null;
	}
	
	public static Map<String,String> getInterviewers()
	{
		Map<String,String> intv = null;
		PlatformUser currentUser = new PlatformUser();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		if (user != "anonymousUser")
		    {
			currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			intv = invService.getInterviewers(String.valueOf(currentUser.getUserKey()));
			System.out.println("$$$$$$$$$$$$$$$$$$ conns : " + intv);
		return intv;
		    }
		else
		return null;
	}
	
	public static String getUserFullName()
	{
		PlatformUser currentUser = new PlatformUser();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		if (user != "anonymousUser")
		    {
			currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String fullName = currentUser.getUserFullName();
		return fullName;
		    }
		else
		return null;
	}
	
	
	public static String getUserStatus()
	{
		PlatformUser currentUser = new PlatformUser();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		if (user != "anonymousUser")
		    {
			currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String status = currentUser.getStatus();
		return status;
		    }
		else
		return "anonymousUser";
	}
	
	
public static int getUserId()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
	    {
		currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	int userId = (int) currentUser.getId();
	return userId;
	    }
	else
	return 0;
}


public static String getLastLogin()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
	    {
		currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = null;
		if(currentUser.getLastLoginDate() != null)
			login = currentUser.getLastLoginDate().toString();
	return login;
	    }
	else
	return null;
}

public static String getPriviliges()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
	    {
		Collection<SimpleGrantedAuthority> Authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	return Authorities.toString();
	    }
	else
	return "0";
}


public static String getUserRole()
{
	PlatformUser currentUser = new PlatformUser();
String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
if (user != "anonymousUser")
    {
	currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
	String userRole =  currentUser.getUserRole();
	return userRole;
}
else
	currentUser.setUserRole("anonymousUser");	
return user;
}

public static String getUserName()
{
	PlatformUser currentUser = new PlatformUser();
String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
if (user != "anonymousUser")
    {
	currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
	String userName =  currentUser.getUserName();
	return userName;
}
return user;
}

public static String getUserType()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
        {
		currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		String userType =  currentUser.getUserType();
		return userType;
}
	return user;
}

public static String getUserKey()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
        {
		currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		String userKey =  currentUser.getUserKey();
		return userKey;
}
	return user;
}
public static String getEntityId()
{
	PlatformUser currentUser = new PlatformUser();
	String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
	if (user != "anonymousUser")
        {
		currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		String userKey =  currentUser.getUserKey();
		return userKey;
}
	return user;
}

public static String getEntityType()
{
	PlatformUser currentUser =  (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
	String entityType = currentUser.getUserType();
	return entityType;
}


}
