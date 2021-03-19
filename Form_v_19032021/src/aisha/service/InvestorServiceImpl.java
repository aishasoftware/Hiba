package aisha.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.PlatformUser;
import aisha.bean.Investor;
import aisha.bean.Investor;
import aisha.bean.Talent;
import aisha.dao.BasicDAO;
import aisha.security.beans.SystemUser;
import aisha.util.CurrentUser;

 
@Service
@Transactional
public class InvestorServiceImpl implements InvestorService {


@Autowired
private BasicDAO basicDAO;

protected static Logger logger = Logger.getLogger(InvestorServiceImpl.class);

@Override
public long addBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, add bean : " + talent);
	talent.setStatus("pending");
    talent.setCreationTime(new Timestamp((new Date()).getTime()));
    talent.setLastUpdateTime(new Timestamp((new Date()).getTime()));
    talent.setCreatedBy(CurrentUser.getUserName());
    talent.setLastUpdatedBy(CurrentUser.getUserKey());
	long result = basicDAO.addBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, after adding bean with id: " + result);
	return result;
	
}

@Override
public void deleteBean(BasicBean investor) {
	logger.debug("Inside method InvestorServiceImpl.deleteTalent, delete bean : " + investor);
	/*talent.setStatus("active");
talent.setCreationTime(new Date());*/
	basicDAO.deleteBean(investor);
	logger.debug("Inside method InvestorServiceImpl.deleteTalent, after deleting bean");
	
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
@Transactional
public void updateBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean");
	
    talent.setLastUpdateTime(new Timestamp((new Date()).getTime()));
    talent.setLastUpdatedBy(CurrentUser.getUserKey());
	
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

@Override
public  Map<String,String> getEvaluators(String id)
{
	Map<String,String>  result = null ;
	result =  this.basicDAO.getEvaluators(id);
	return result;
	
	}

@Override
public  Map<String,String> getInterviewers(String id)
{
	Map<String,String>  result = null ;
	result =  this.basicDAO.getInterviewers(id);
	return result;
	
	}
/*@Override
public long addInvestor(Investor investor) {
	logger.debug("Inside method InvestorProfileServiceImpl.addInvestorProfile, add bean : " + investor);
	// profile.setCreationTime(new Date());
	
     //SystemUser currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // profile.setCreatedBy(currentUser.getUserName());
	investor.setStatus("pending");
	investor.setCreationTime(new Timestamp((new Date()).getTime()));
	long result = basicDAO.addBean(investor);
	logger.debug("Inside method InvestorProfileServiceImpl.addInvestorProfile, after adding bean with id: " + result);
	return result;
	
}


@Override
public Investor listInvestors(Investor investor)
 {
	logger.debug("Inside method InvestorProfileServiceImpl.listInvestorProfiles");
		Investor result =  (Investor) this.basicDAO.listBeans(investor);
		logger.debug("Inside method InvestorProfileServiceImpl.listInvestorProfiles, after listing beans : " + result.getResults());
		return result;
	
}


@Override
public Investor getInvestor(Investor investor) {
	logger.debug("Inside method InvestorProfileServiceImpl.getInvestorProfile");
	Investor result = (Investor) this.basicDAO.getBean(investor);
	logger.debug("Inside method InvestorProfileServiceImpl.getInvestorProfile, after get InvestorProfile : " + result);
	return result;
}


@Override
public void updateInvestor(Investor investor) {
	logger.debug("Inside method InvestorProfileServiceImpl.updateInvestorProfile");
	//profile.setLastModificationTime(new Date());
    PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //profile.setLastModifiedBy(currentUser.getUserName());
	this.basicDAO.updateBean(investor);
	logger.debug("Inside method InvestorProfileServiceImpl.updateInvestorProfile, after update InvestorProfile : " + investor);
}
 */
}