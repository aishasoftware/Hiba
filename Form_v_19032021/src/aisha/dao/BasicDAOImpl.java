package aisha.dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.PlatformUser;
import aisha.bean.Talent;
import aisha.util.CurrentUser;

 
//@Repository
public class BasicDAOImpl implements BasicDAO {
 
 @Autowired
 private SessionFactory sessionFactory;
 protected static Logger logger = Logger.getLogger(BasicDAOImpl.class);
 
 private Session getCurrentSession() {
  return sessionFactory.getCurrentSession();
 }

 @Override
 public long addBean(BasicBean basic) {
	 basic.setCreationTime(new Timestamp(System.currentTimeMillis()));
	 logger.debug("Inside method BasicDAOImpl.addBean");
	 logger.debug("inserting bean into db : " + basic);
	 basic.setId(1);
  long result = (long) getCurrentSession().save(basic);
  logger.debug("Inside method BasicDAOImpl.addBean, after add bean with id :" + result);
  return result;
 }
 
 @Override
 public void deleteBean(BasicBean basic) {
	 basic.setCreationTime(new Timestamp(System.currentTimeMillis()));
	 logger.debug("Inside method BasicDAOImpl.addBean");
	 logger.debug("inserting bean into db : " + basic);
   getCurrentSession().delete(basic);
  logger.debug("Inside method BasicDAOImpl.addBean, after add bean with id :");
 }
 
 @Override
 //@Transactional
 //@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public BasicBean getBean(BasicBean bean) {
	 logger.debug("Inside method BasicDAOImpl.getBean");
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(bean.getClass());
	String name = bean.getClass().getSimpleName();
	
	if (bean.getSearchCriteria() != null)
	{
		List<String> keysList = new ArrayList<String>(bean.getSearchCriteria().keySet());
		Iterator<String> keyListIterator = keysList.iterator();
		while (keyListIterator.hasNext()) {
			String searchCriteriaKey = keyListIterator.next();
			
			
			Object searchCreteriaValue = bean.getSearchCriteria()
					.get(searchCriteriaKey);
			
			
			if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)

			{	

				if(searchCriteriaKey == "id" || searchCriteriaKey == "outerId")
				{
					System.out.println("%%%%%%%%%%%%%%%%%%%%5 outer");
					criteria.add(Restrictions.eq(searchCriteriaKey, Long.valueOf(String.valueOf(searchCreteriaValue))));
				}
				else
					criteria.add(Restrictions.eq(searchCriteriaKey, searchCreteriaValue));
					
			}
			

		}
		
	}
	

		List<BasicBean> sList = null;
		logger.debug("Inside method BasicDAOImpl.getBean, get basic with id : "+bean.getId());
		sList=criteria.list();
		logger.debug("Inside method BasicDAOImpl.getBean, get basic result : "+bean.getId());

		
		System.out.println("################# :" + sList.size());
		try
		{logger.debug("Inside method BasicDAOImpl.getBean, get talent result : " + sList.get(0));
		return sList.get(0);}
		catch(Exception e)
		{System.out.println("#######  exception : "+e.getMessage());return null;}
	}

 
 @Override
 //@Transactional
 //@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public BasicBean getMyBean(BasicBean basic) {
	 logger.debug("Inside method BasicDAOImpl.getBean");
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(basic.getClass());
	String name = basic.getClass().getSimpleName();
		if(basic.getClass().getSimpleName().equals("PlatformUser"))
		{
		if(((PlatformUser)basic).getUserName() != null )
			criteria.add(Restrictions.eq("userName", ((PlatformUser)basic).getUserName()));
		else
			criteria.add(Restrictions.eq("id", ((PlatformUser)basic).getId()));
		}
		else if(basic.getClass().getSimpleName().equals("Talent"))
		criteria.add(Restrictions.eq("userId", ((Talent)basic).getUserId()));
		
		else if(basic.getClass().getSimpleName().equals("Talent") 
				|| basic.getClass().getSimpleName().equals("Startup")
				|| basic.getClass().getSimpleName().equals("Investor"))
		criteria.add(Restrictions.eq("userId", basic.getUserId()));
		
		else
		criteria.add(Restrictions.eq("id", Long.valueOf(String.valueOf(basic.getId()))));
		List<Talent> sList = null;
		logger.debug("Inside method BasicDAOImpl.getBean, get basic with id : "+basic.getId());
		sList=criteria.list();
		logger.debug("Inside method BasicDAOImpl.getBean, get basic result : "+basic.getId());

		
		System.out.println("################# :" + sList.size());
		System.out.println("################# :" + sList);
		try
		{logger.debug("Inside method BasicDAOImpl.getBean, get talent result : " + sList.get(0));
		return sList.get(0);}
		catch(Exception e)
		{System.out.println("#######  exception : "+e.getMessage());return null;}
	}

 
 @Override
 @Transactional
	public BasicBean listBeans(BasicBean bean) {
	 logger.debug("Inside method TalentDAOImpl.listBeans");
	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(bean.getClass());
	 Criteria extraCriteria;
	 String sDate1;
	 String sDate2;
	 
	 Time sDate3;
	 Time sDate4;
	 
	 int evaluator = 0;
		List<BasicBean> sList = null;
		if (bean.getSearchCriteria() != null)
		{
			List<String> keysList = new ArrayList<String>(bean.getSearchCriteria().keySet());
			Iterator<String> keyListIterator = keysList.iterator();
			while (keyListIterator.hasNext()) {
				String searchCriteriaKey = keyListIterator.next();
				
				
				Object searchCreteriaValue = bean.getSearchCriteria()
						.get(searchCriteriaKey);
				
				
				if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)

				{	

					if(searchCriteriaKey=="startTime" && bean.getClass().getSimpleName().equals("Event"))
					{
						 HashMap<String, Object> dateFrame = new HashMap<>();
						 dateFrame = (HashMap<String, Object>) searchCreteriaValue;
						  sDate3 = (Time) dateFrame.get("startTime");
						  sDate4 = (Time) dateFrame.get("endTime");
						criteria.add(Restrictions.between("startTime", sDate3, sDate4));
					/*	criteria.add(Restrictions.eq("eventDate", bean.getSearchCriteria()
						.get("eventDate")));*/
						criteria.add(Restrictions.eq("location", bean.getSearchCriteria()
						.get("location")));
					}
					
					if(searchCriteriaKey=="evaluator")
					{
						Disjunction or = Restrictions.disjunction();
						      or.add(Restrictions.eq("eva_1", searchCreteriaValue))
						      .add(Restrictions.eq("eva_2",searchCreteriaValue)) 
						      .add(Restrictions.eq("eva_3",searchCreteriaValue)) 
						      .add(Restrictions.eq("eva_4",searchCreteriaValue))
						   .add(Restrictions.eq("eva_5",searchCreteriaValue));
						criteria.add(or);
						/*evaluator = 1;
						Criterion cri1 = Restrictions.eq("eva_1", searchCreteriaValue);
						Criterion cri2 = Restrictions.eq("eva_2",searchCreteriaValue);
						Criterion cri3 = Restrictions.eq("eva_3",searchCreteriaValue); 
						Criterion cri4 = Restrictions.eq("eva_4",searchCreteriaValue); 
						// Fetch records with the OR condition
						LogicalExpression expression1 = Restrictions.or(cri1, cri2);
						LogicalExpression expression2 = Restrictions.or(cri3, cri4);
						criteria.add(expression1);
						criteria.add(expression2);*/
					   /*  evaluators.add(Restrictions.or(Restrictions.eq("eva_1", searchCreteriaValue),  
					      Restrictions.or(Restrictions.eq("eva_2", searchCreteriaValue),  
					      Restrictions.or(Restrictions.eq("eva_3", searchCreteriaValue),  
					      Restrictions.or(Restrictions.eq("eva_4", searchCreteriaValue),Restrictions.eq("eva_5", searchCreteriaValue))  
					      ))));  */
					}
					 if (searchCriteriaKey.contains("Time") && !bean.getClass().getSimpleName().equals("Event")) {
						 HashMap<String, Object> dateFrame = new HashMap<>();
						 dateFrame = (HashMap<String, Object>) searchCreteriaValue;
						 if(searchCriteriaKey=="LastUpdateTime")
						 {
						  sDate1 = (String) dateFrame.get("fromModify");
						  sDate2 = (String) dateFrame.get("toModify");
						 }
						 else
						 {
							  sDate1 = (String) dateFrame.get("fromCreate");
							  sDate2 = (String) dateFrame.get("toCreate");
							 
						 }
						    Date date1 = new Date();
						    Date date2 = new Date();
							try {
								if(sDate1!=null)
								date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
								if(sDate2!=null)
								date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
						    System.out.println(sDate1+"\t"+date1);  

						criteria.add(Restrictions.between(searchCriteriaKey,
								date1, date2));
					 }
										if(searchCriteriaKey == "id" || searchCriteriaKey == "applicantId" || searchCriteriaKey == "app_Id")
							{
								System.out.println("%%%%%%%%%%%%%%%%%%%%5 outer");
								criteria.add(Restrictions.eq(searchCriteriaKey, Long.valueOf(String.valueOf(searchCreteriaValue))));
							}
										if(searchCriteriaKey == "outerId" || searchCriteriaKey == "innerId" )
							{
								System.out.println("%%%%%%%%%%%%%%%%%%%%5 outer");
								criteria.add(Restrictions.eq(searchCriteriaKey, Integer.valueOf(String.valueOf(searchCreteriaValue))));
							}
										if(!searchCriteriaKey.contains("Time") && !searchCriteriaKey.contains("evaluator") && !searchCriteriaKey.contains("applicantId") && !searchCriteriaKey.contains("outerId") &&  !searchCriteriaKey.contains("innerId") && !searchCriteriaKey.contains("app_Id") && !bean.getClass().getSimpleName().equals("Event"))
												criteria.add(Restrictions.like(searchCriteriaKey,
														searchCreteriaValue.toString(),MatchMode.ANYWHERE));

						}
			}
			
		}
		System.out.println("^^^^^^^^^^^^^ criteria : "+criteria);
/*	
	if (talent.isOrderAsc() == true) {
		criteria.addOrder(Order.asc(talent.getOrderBy()));
	} else {
		criteria.addOrder(Order.desc(talent.getOrderBy()));
	}*/
	
	logger.debug("Inside method TalentDAOImpl.listBeans, criteria :" + criteria);
	int numerOfRows = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	criteria.setProjection(null);
	//disabled for testing since no data
	criteria.setFirstResult(bean.getFirstPage());
	criteria.setMaxResults(bean.getMaxResult());
	sList=criteria.list();
	logger.debug("Inside method TalentDAOImpl.listBeans, result size :" + sList.size());
	logger.debug("Inside method TalentDAOImpl.listBeans, result list :" + sList);
	//int numerOfRows = sList.size();
	//bean.setTotalResult(numerOfRows);
	System.out.println("$$$$$$$$$$$$$$$$$ list bean result: " + sList);
		bean.setResults(sList);
		bean.setTotalResult(numerOfRows);
		logger.debug("Inside method TalentDAOImpl.listBeans, criteria :" );
		return bean;
		
	}


 @Override
	public void updateBean(BasicBean basic) {
	 basic.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
	 basic.setLastUpdatedBy(String.valueOf(CurrentUser.getUserId()));
	 logger.debug("Inside method TalentDAOImpl.updateTalent : " + basic);
		Session session = this.sessionFactory.getCurrentSession();
		session.update(basic);
		logger.debug("Inside method TalentDAOImpl.updateTalent, after update basic");
}

 @Override
 public List<String> getPriviliges(String packId)
 {
 SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT RESOURCE_ID FROM PACKAGE_RESOURCE WHERE PACKAGE_ID =" + packId);
	//List<Talent> sList = null;

	
	List<Object[]> sList = query.list();
	List<String> omg = new ArrayList<String>();
		Object[] row;
		for(int i = 0 ; i < sList.size() ; i++)
	{
			omg.add(String.valueOf(sList.get(i)));
		
	}

	return omg;
 }
 
 
 @Override
 public Map<String,String> getConnections(String id, String type)
 {
	 SQLQuery query =  null ;
	 if(type.equals("Entity"))
		 query = sessionFactory.getCurrentSession().createSQLQuery("SELECT INNER_ID,STATUS FROM CONNECTION  WHERE OUTER_ID =" + id );
	 if(type.equals("User"))
		 query = sessionFactory.getCurrentSession().createSQLQuery("SELECT OUTER_ID,STATUS FROM CONNECTION  WHERE INNER_ID =" + id );
	//List<Talent> sList = null;
	 //List<Talent> sList = null;

	
	List<Object[]> sList = query.list();
	Map<String,String> omg = new HashMap();
		Object[] row;
		for(int i = 0 ; i < sList.size() ; i++)
	{
			row = sList.get(i);
			omg.put(String.valueOf(row[0]) , String.valueOf(row[1]));
		
	}
System.out.println("%%%%%%%%%%%%%%% omg : " + omg);
	return omg;
 }


 @Override
 public Map<String,String> getEvaluators(String id)
 {
 SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT ID,USERNAME FROM PLATFORM_USER  WHERE USERKEY =" + id + " AND USERROLE = 'Evaluator'");
	//List<Talent> sList = null;

	
 List<Object[]> sList = query.list();
	Map<String,String> omg = new HashMap();
		Object[] row;
		for(int i = 0 ; i < sList.size() ; i++)
	{
			row = sList.get(i);
			omg.put(String.valueOf(row[0]) , String.valueOf(row[1]));
		
	}
System.out.println("%%%%%%%%%%%%%%% omg : " + omg);
	return omg;
 }
 
 @Override
 public Map<String,String> getInterviewers(String id)
 {
 SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT ID,USERNAME FROM PLATFORM_USER  WHERE USERKEY =" + id + " AND USERROLE = 'Interviewer'");
	//List<Talent> sList = null;

	
 List<Object[]> sList = query.list();
	Map<String,String> omg = new HashMap();
		Object[] row;
		for(int i = 0 ; i < sList.size() ; i++)
	{
			row = sList.get(i);
			omg.put(String.valueOf(row[0]) , String.valueOf(row[1]));
		
	}
System.out.println("%%%%%%%%%%%%%%% omg : " + omg);
	return omg;
 }
 }