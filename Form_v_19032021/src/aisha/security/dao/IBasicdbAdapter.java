package aisha.security.dao;

import java.util.List;

import org.hibernate.Session;

import aisha.security.beans.SystemUser;


public interface IBasicdbAdapter {
	//public List<SystemUser> listBeans(SystemUser bean) ;
	public void addSystemUser(SystemUser bean);
	//public void deleteBean(SystemUser bean) ;
	public void updateSystemUser(SystemUser bean) ;
	public SystemUser getSystemUser(SystemUser bean) ;
	//public List<SystemUser> listUser(SystemUser bean) ;
	//public void increaseHours(List<Integer> studentList,int stepValue);
	
}
