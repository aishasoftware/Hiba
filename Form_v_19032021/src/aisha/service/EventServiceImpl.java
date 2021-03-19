package aisha.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.dao.BasicDAO;
import aisha.dao.StartupProfileDAO;

 
@Service
@Transactional
public class EventServiceImpl implements EventService {
 
@Autowired
private StartupProfileDAO StartupProfileDAO;

@Autowired
private BasicDAO basicDAO;

protected static Logger logger = Logger.getLogger(EventServiceImpl.class);

@Override
public long addBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, add bean : " + talent);
	talent.setStatus("pending");
    talent.setCreationTime(new Timestamp((new Date()).getTime()));
	long result = basicDAO.addBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, after adding bean with id: " + result);
	return result;
	
}


@Override
public BasicBean listBeans(BasicBean talent)
 {
	logger.debug("Inside method BasicBeanServiceImpl.listBasicBeans");
		BasicBean result = (BasicBean) this.basicDAO.listBeans(talent);
	//	logger.debug("Inside method BasicBeanServiceImpl.listBasicBeans, after listing beans : " + result.getBasicBeanList());
		return result;	
}

@Override
@Transactional
public BasicBean getBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean");
	BasicBean result = (BasicBean) this.basicDAO.getBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean, after get talent : " + result);
	return result;
}

@Override
public BasicBean getMyBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean");
	BasicBean result = (BasicBean) this.basicDAO.getMyBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean, after get talent : " + result);
	return result;
}


@Override
public void updateBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean");
	
	this.basicDAO.updateBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean, after update talent : " + talent);
}


@Override
public BasicBean listBeansCustom(BasicBean talent) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public BasicBean listStartupBasicBeans(BasicBean talent) {
	// TODO Auto-generated method stub
	return null;
}

/*@Override
public long addStartup(Startup startup) {
	logger.debug("Inside method StartupProfileServiceImpl.addStartupProfile, add bean : " + startup);
	// profile.setCreationTime(new Date());
     //SystemUser currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // profile.setCreatedBy(currentUser.getUserName());
	startup.setStatus("pending");
	startup.setCreationTime(new Timestamp((new Date()).getTime()));
	long result = basicDAO.addBean(startup);
	logger.debug("Inside method StartupProfileServiceImpl.addStartupProfile, after adding bean with id: " + result);
	return result;
	
}


@Override
public Startup listStartups(Startup startup)
 {
	logger.debug("Inside method StartupProfileServiceImpl.listStartupProfiles");
		Startup result =  (Startup) this.basicDAO.listBeans(startup);
		logger.debug("Inside method StartupProfileServiceImpl.listStartupProfiles, after listing beans : " + result.getResults());
		return result;
	
}


@Override
public Startup getStartup(Startup startup) {
	logger.debug("Inside method StartupProfileServiceImpl.getStartupProfile");
	Startup result = (Startup) this.basicDAO.getBean(startup);
	logger.debug("Inside method StartupProfileServiceImpl.getStartupProfile, after get StartupProfile : " + result);
	return result;
}

@Override
public Startup getMyStartup(Startup startup) {
	logger.debug("Inside method StartupProfileServiceImpl.getStartupProfile");
	Startup result = (Startup) this.basicDAO.getMyBean(startup);
	logger.debug("Inside method StartupProfileServiceImpl.getStartupProfile, after get StartupProfile : " + result);
	return result;
}

@Override
public void updateStartup(Startup startup) {
	logger.debug("Inside method StartupProfileServiceImpl.updateStartupProfile");
	//profile.setLastModificationTime(new Date());
    PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //profile.setLastModifiedBy(currentUser.getUserName());
	this.basicDAO.updateBean(startup);
	logger.debug("Inside method StartupProfileServiceImpl.updateStartupProfile, after update StartupProfile : " + startup);
}
 */
}