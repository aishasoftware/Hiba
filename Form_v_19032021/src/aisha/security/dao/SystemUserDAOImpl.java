package aisha.security.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.PlatformUser;
import aisha.security.beans.SystemUser;

public class SystemUserDAOImpl implements SystemUserDAO{

	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 private Session getCurrentSession() {
	  return sessionFactory.getCurrentSession();
	 }
	 
	 public void addTSystemUser(SystemUser sysUser) {
	  getCurrentSession().save(sysUser);
	 }


	   @Transactional
		public PlatformUser getPlatformUser(PlatformUser user) {
			Session session = this.sessionFactory.getCurrentSession();
			HashMap<String,Object> searchCriteria=user.getSearchCriteria();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(user.getClass());
			if(searchCriteria!=null)
			{
			for (String key : searchCriteria.keySet()) 
			{
				criteria.add(Restrictions.eq(key, searchCriteria.get(key)));
			}
			
			}
			List<PlatformUser> sList = null;

			sList=criteria.list();
			

			
			System.out.println("################# :" + sList.size());
			try
			{return sList.get(0);}
			catch(Exception e)
			{System.out.println("#######33  xeception : "+e.getMessage());return null;}
		}

	@Override
	public void updatePlatformUser(PlatformUser user) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
		//@Transactional
		public PlatformUser listPlatformUsers(PlatformUser user) {
		// logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates");
		 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PlatformUser.class);
		

				List<BasicBean> sList = null;
		
				if (user.getSearchCriteria() != null)
				{
					List<String> keysList = new ArrayList<String>(user.getSearchCriteria().keySet());
					Iterator<String> keyListIterator = keysList.iterator();
					
					/*if (keysList.contains("status") == false)
						criteria.add(Restrictions.eq("status",
								"Active"));*/
						
					while (keyListIterator.hasNext()) {
						String searchCriteriaKey = keyListIterator.next();
						Object searchCreteriaValue = user.getSearchCriteria()
								.get(searchCriteriaKey);
						if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)
						{

						if (searchCriteriaKey.equals("creationTime") || searchCriteriaKey.equals("LastUpdateTime")) {
									 HashMap<String, Object> dateFrame = new HashMap<>();
									 dateFrame = (HashMap<String, Object>) searchCreteriaValue;
									 
									 String sDate1 = (String) dateFrame.get("fromDate");
									 String sDate2 = (String) dateFrame.get("toDate");
									    Date date1 = new Date();
									    Date date2 = new Date();
										try {
											if(sDate1!=null)
											date1 = new SimpleDateFormat("dd-MM-yy").parse(sDate1);
											if(sDate2!=null)
											date2 = new SimpleDateFormat("dd-MM-yy").parse(sDate2);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}  
							
									criteria.add(Restrictions.between(searchCriteriaKey,
												date1, date2));
								 }
							
									 if(searchCriteriaKey != "creationTime" && searchCriteriaKey != "LastUpdateTime")
								criteria.add(Restrictions.eq(searchCriteriaKey,
										searchCreteriaValue));
			
				}
					}
					
				}
				//logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, criteria : " + criteria);
				//int totalResult = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				//criteria.setProjection(null);
				/*user.setTotalResult(totalResult);
				if(user.getFirstPage() < user.getTotalResult())
				criteria.setFirstResult(user.getFirstPage());
				else
				criteria.setFirstResult(user.getTotalResult());	
				criteria.setMaxResults(user.getMaxResult());
				
				if (user.isOrderAsc() == true) {
					criteria.addOrder(Order.asc(user.getOrderBy()));
				} else {
					criteria.addOrder(Order.desc(user.getOrderBy()));
				}*/
			
			sList = criteria.list();
			//logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, after get results list of size: " + sList.size());
			//logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, after get results list : " + sList);
			user.setResults(sList);
			System.out.println("######## sList : "+sList.size());
			return user;
			
		}

	@Override
	public void addPlatformUser(PlatformUser sysUser) {
		// TODO Auto-generated method stub
		
	}

	 
}
