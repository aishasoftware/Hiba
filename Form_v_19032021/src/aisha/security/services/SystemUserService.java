package aisha.security.services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.security.core.userdetails.UserDetailsService;

import aisha.bean.PlatformUser;
import aisha.bean.Strategy;
import aisha.security.beans.SystemUser;

public interface SystemUserService{
	   
	    public long addSystemUser(PlatformUser sysUser);
	    public PlatformUser getSystemUser(PlatformUser user);
	    public void updateSystemUser(PlatformUser user);
		public PlatformUser listSystemUsers(PlatformUser user);
		public PlatformUser resetPassword(String username) throws AddressException, MessagingException ;
		public PlatformUser sendPasswordEmail(PlatformUser user) throws AddressException, MessagingException ;
	 
}
