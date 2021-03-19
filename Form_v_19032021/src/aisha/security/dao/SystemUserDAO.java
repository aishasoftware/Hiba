package aisha.security.dao;

import aisha.bean.PlatformUser;
import aisha.security.beans.SystemUser;

	 
	public interface SystemUserDAO {
	 
	    public void addPlatformUser(PlatformUser sysUser);
	    public PlatformUser getPlatformUser(PlatformUser user);
	    public void updatePlatformUser(PlatformUser user);
		public PlatformUser listPlatformUsers(PlatformUser user);
	}

