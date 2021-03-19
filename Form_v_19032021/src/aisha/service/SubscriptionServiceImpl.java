package aisha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.Subscription;
import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;
import aisha.util.CurrentUser;

 
@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {


@Autowired
private BasicDAO basicDAO;

protected static Logger logger = Logger.getLogger(SubscriptionServiceImpl.class);

@Override
public long addBean(BasicBean subs) {
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, add bean : " + subs);
	subs.setStatus("pending");
   // subs.setCreationTime(new Timestamp((new Date()).getTime()));
   //subs.setLastUpdateTime(new Timestamp((new Date()).getTime()));
    subs.setCreatedBy(CurrentUser.getUserName());
    subs.setLastUpdatedBy(CurrentUser.getUserKey());
	long result = basicDAO.addBean(subs);
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
public BasicBean getBean(BasicBean sub) {
	// TODO Auto-generated method stub
	return (BasicBean) basicDAO.getBean(sub);
}


@Override
public void updateBean(BasicBean sub) {
	// TODO Auto-generated method stub
	basicDAO.updateBean(sub);
}
 
}