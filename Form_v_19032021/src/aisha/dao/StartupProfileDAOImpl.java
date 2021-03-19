package aisha.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.StartupProfile;
import aisha.security.beans.SystemUser;

 
//@Repository
public class StartupProfileDAOImpl implements StartupProfileDAO {
 
 @Autowired
 private SessionFactory sessionFactory;
 protected static Logger logger = Logger.getLogger(StartupProfileDAOImpl.class);
 
 private Session getCurrentSession() {
  return sessionFactory.getCurrentSession();
 }
 
 @Override
	@Transactional
 public long addStartupProfile(StartupProfile profile) {
	 logger.debug("Inside method StartupProfileDAOImpl.addStartupProfile");
  long result = (long) getCurrentSession().save(profile);
  logger.debug("Inside method StartupProfileDAOImpl.addStartupProfile, after add StartupProfile with id :" + result);
  return result;
 }
 
 
 
 @Override
	@Transactional
	public StartupProfile listStartupProfiles(StartupProfile profile) {
	 logger.debug("Inside method StartupProfileDAOImpl.listStartupProfiles");
	 Criteria criteria= sessionFactory.getCurrentSession().createCriteria(StartupProfile.class);

		List<BasicBean> sList = null;
		if (profile.getSearchCriteria() != null && !profile.getSearchCriteria().isEmpty())
		{
			List<String> keysList = new ArrayList<String>(profile.getSearchCriteria().keySet());
			Iterator<String> keyListIterator = keysList.iterator();
			while (keyListIterator.hasNext()) {
				String searchCriteriaKey = keyListIterator.next();
				
				
				Object searchCreteriaValue = profile.getSearchCriteria()
						.get(searchCriteriaKey);
				
				
				if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)
				{	
					criteria.add(Restrictions.eq(searchCriteriaKey,
							searchCreteriaValue));
				}
			}
			
		}
	
/*	if (profile.isOrderAsc() == true) {
		criteria.addOrder(Order.asc(profile.getOrderBy()));
	} else {
		criteria.addOrder(Order.desc(profile.getOrderBy()));
	}*/
	
	logger.debug("Inside method StartupProfileDAO6Impl.listStartupProfiles, criteria :" + criteria);
	//criteria.setProjection(null);
	
	//disabled for testing since no data
	//criteria.setFirstResult(StartupProfile.getFirstPage());
	//criteria.setMaxResults(StartupProfile.getMaxResult());

		
	
	sList=criteria.list();
	logger.debug("Inside method StartupProfileDAOImpl.listStartupProfiles, result size :" + sList.size());
	logger.debug("Inside method StartupProfileDAOImpl.listStartupProfiles, result list :" + sList);
	int numerOfRows = sList.size();
	profile.setTotalResult(numerOfRows);
	
	profile.setResults(sList);
		logger.debug("Inside method StartupProfileDAOImpl.listStartupProfiles, criteria :" + criteria );
		return profile;
		
	}

 @Override
 @Transactional
	public StartupProfile getStartupProfile(int profileID) {
	 logger.debug("Inside method StartupProfileDAOImpl.getStartupProfile");
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(StartupProfile.class);
	
		
		criteria.add(Restrictions.eq("id", Long.valueOf(String.valueOf(profileID))));
		List<StartupProfile> sList = null;
		logger.debug("Inside method StartupProfileDAOImpl.getStartupProfile, get StartupProfile with id : "+profileID);
		sList=criteria.list();
		logger.debug("Inside method StartupProfileDAOImpl.getStartupProfile, get StartupProfile result : "+profileID);

		
		System.out.println("################# :" + sList.size());
		try
		{
		logger.debug("Inside method StartupProfileDAOImpl.getStartupProfile, get StartupProfile result : " + sList.get(0));
		return sList.get(0);}
		catch(Exception e)
		{System.out.println("#######  exception : "+e.getMessage());return null;}
	}

 @Override
 @Transactional
	public void updateStartupProfile(StartupProfile profile) {
	 logger.debug("Inside method StartupProfileDAOImpl.updateStartupProfile : " + profile);
		Session session = this.sessionFactory.getCurrentSession();
		session.update(profile);
		logger.debug("Inside method StartupProfileDAOImpl.updateStartupProfile, after update StartupProfile");
}
}