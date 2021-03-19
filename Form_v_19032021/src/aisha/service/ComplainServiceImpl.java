package aisha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Complain;
import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;

 
@Service
@Transactional
public class ComplainServiceImpl implements ComplainService {


@Autowired
private BasicDAO basicDAO;

protected static Logger logger = Logger.getLogger(ComplainServiceImpl.class);


@Override
public long addComplain(Complain complain) {
	logger.debug("Inside method ComplainProfileServiceImpl.addComplainProfile, add bean : " + complain);
	// profile.setCreationTime(new Date());
	complain.setStatus("active");
     //SystemUser currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // profile.setCreatedBy(currentUser.getUserName());
	complain.setStatus("inserted");
	long result = basicDAO.addBean(complain);
	logger.debug("Inside method ComplainProfileServiceImpl.addComplainProfile, after adding bean with id: " + result);
	return result;
	
}


@Override
public Complain listComplains(Complain complain)
 {
	logger.debug("Inside method ComplainProfileServiceImpl.listComplainProfiles");
		Complain result =  (Complain) this.basicDAO.listBeans(complain);
		logger.debug("Inside method ComplainProfileServiceImpl.listComplainProfiles, after listing beans : " + result.getResults());
		return result;
	
}


@Override
public Complain getComplain(Complain complain) {
	logger.debug("Inside method ComplainProfileServiceImpl.getComplainProfile");
	Complain result = (Complain) this.basicDAO.getBean(complain);
	logger.debug("Inside method ComplainProfileServiceImpl.getComplainProfile, after get ComplainProfile : " + result);
	return result;
}


@Override
public void updateComplain(Complain complain) {
	logger.debug("Inside method ComplainProfileServiceImpl.updateComplainProfile");
	//profile.setLastModificationTime(new Date());
    PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //profile.setLastModifiedBy(currentUser.getUserName());
	this.basicDAO.updateBean(complain);
	logger.debug("Inside method ComplainProfileServiceImpl.updateComplainProfile, after update ComplainProfile : " + complain);
}
 
}