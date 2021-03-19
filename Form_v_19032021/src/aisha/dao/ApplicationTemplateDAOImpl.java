package aisha.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Application;
import aisha.security.beans.SystemUser;

 
//@Repository
public class ApplicationTemplateDAOImpl implements ApplicationTemplateDAO {
 
 @Autowired
 private SessionFactory sessionFactory;
 protected static Logger logger = Logger.getLogger(ApplicationTemplateDAOImpl.class);
 
 private Session getCurrentSession() {
  return sessionFactory.getCurrentSession();
 }
 
 //finish
 public long addApplicationTemplate(Application app) {
	
	 app.setSubmTime(new Date());
	 app.setAppStatus("Submitted");
	 app.setNoOfEvaluators(3);
	 logger.debug("Inside method ApplicationTemplateDAOImpl.addApplicationTemplate, before add bean : " + app);
	 long result =  (long) getCurrentSession().save(app);
	 logger.debug("Inside method ApplicationTemplateDAOImpl.addApplicationTemplate, after add bean with id :" + result);
	 return result;
 }
 
 
 
@SuppressWarnings("unchecked")
@Override
	@Transactional
	public Application listApplicationTemplates(Application app) {
	 logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates");
	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Application.class);
	 app.setApp_Id(1);

			List<Application> sList = null;
	
			if (app.getCriteria() != null)
			{
				List<String> keysList = new ArrayList<String>(app.getCriteria().keySet());
				Iterator<String> keyListIterator = keysList.iterator();
				
				if (keysList.contains("appStatus") == false)
					criteria.add(Restrictions.ne("appStatus",
							"Rejected"));
					
				while (keyListIterator.hasNext()) {
					String searchCriteriaKey = keyListIterator.next();
					Object searchCreteriaValue = app.getCriteria()
							.get(searchCriteriaKey);
					if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)
					{
					if(searchCriteriaKey=="talent_Id")
					{
						Long searchCreteriaValueString = Long.valueOf(app.getCriteria().get(searchCriteriaKey).toString());
					criteria.add(Restrictions.eq(searchCriteriaKey,
							searchCreteriaValueString));
					}
					
					if (searchCriteriaKey.equals("submTime")) {
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
						
								criteria.add(Restrictions.between("submTime",
											date1, date2));
							 }
						
								 if(searchCriteriaKey != "talent_Id" && searchCriteriaKey != "submTime")
							criteria.add(Restrictions.eq(searchCriteriaKey,
									searchCreteriaValue));
		
			}
				}
				
			}
			logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, criteria : " + criteria);
			int totalResult = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			app.setTotalResult(totalResult);
			if(app.getFirstPage() < app.getTotalResult())
			criteria.setFirstResult(app.getFirstPage());
			else
			criteria.setFirstResult(app.getTotalResult());	
			criteria.setMaxResults(app.getMaxResult());
			
			if (app.isOrderAsc() == true) {
				criteria.addOrder(Order.asc(app.getOrderBy()));
			} else {
				criteria.addOrder(Order.desc(app.getOrderBy()));
			}
		
		sList = criteria.list();
		logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, after get results list of size: " + sList.size());
		logger.debug("Inside method ApplicationTemplateDAOImpl.listApplicationTemplates, after get results list : " + sList);
		app.setResults(sList);
		System.out.println("######## sList : "+sList.size());
		return app;
		
	}

 @Override
 @Transactional
	public Application getApplicationTemplate(Application app) {
		
	 logger.debug("Inside method ApplicationTemplateDAOImpl.getApplicationTemplate");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Application.class);
	
		HashMap<String, Object> searchCriteria = app.getCriteria();
		if(searchCriteria.containsKey("talent_Id"))
		criteria.add(Restrictions.eq("talent_Id",Long.parseLong(searchCriteria.get("talent_Id").toString())));
			
		else
		criteria.add(Restrictions.eq("id",app.getId()));
		logger.debug("Inside method ApplicationTemplateDAOImpl.getApplicationTemplate, criteria :" + criteria);
		List<Application> sList = null;

		sList = criteria.list();
		logger.debug("Inside method ApplicationTemplateDAOImpl.getApplicationTemplate, after get results list of size: " + sList.size());
		logger.debug("Inside method ApplicationTemplateDAOImpl.getApplicationTemplate, after get results list : " + sList);
		
		try
		{
			return sList.get(0);
			}
		catch(Exception e)
		{
			System.out.println("#######  exception : "+e.getMessage());
			return null;
			}
	}

 @Transactional
	public void updateApplicationTemplate(Application app) {
	    logger.debug("Inside method ApplicationTemplateDAOImpl.updateApplicationTemplate");
	    SystemUser currentUserName = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if(app.getEv_1_score()!=null && app.getEv_2_score()!=null && app.getEv_3_score()!=null)
	    	{
	    	float total_score = app.getEv_1_score() + app.getEv_2_score() + app.getEv_3_score();
	    	app.setEv_avg_score(total_score/3);
	    	}
	    app.setLastModificationTime(new Date());
	    app.setLastModifiedBy(currentUserName.getUsername());
		Session session = this.sessionFactory.getCurrentSession();
		session.update(app);
		
}
}