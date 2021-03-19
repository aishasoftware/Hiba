package aisha.dao;

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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.ProgramOld;
import aisha.bean.Strategy;
import aisha.bean.TalentOld;
import aisha.test.DateHelper;

 
//@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
 
 @Autowired
 private SessionFactory sessionFactory;
 
 private Session getCurrentSession() {
  return sessionFactory.getCurrentSession();
 }
 
 public void addApplication(ProgramOld app) {
  getCurrentSession().save(app);
 }
 
 
 
 @SuppressWarnings("unchecked")
@Override
	@Transactional
	public List<ProgramOld> listApplications(ProgramOld app) {

	 Criteria criteria= sessionFactory.getCurrentSession().createCriteria(ProgramOld.class);

	 
	 
	 if (app.getSearchCriteria() != null)
		{
			List<String> keysList = new ArrayList<String>(app.getSearchCriteria().keySet());
			Iterator<String> keyListIterator = keysList.iterator();
			while (keyListIterator.hasNext()) {
				String searchCriteriaKey = keyListIterator.next();
				
				
				Object searchCreteriaValue = app.getSearchCriteria()
						.get(searchCriteriaKey);
				
				
				if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)
				{	
					
					 if (searchCriteriaKey.equals("CREATION_TIME")) {
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
						    System.out.println(sDate1+"\t"+date1);  

						criteria.add(Restrictions.between("CREATION_TIME",
									date1, date2));
					 }
					 else
					criteria.add(Restrictions.eq(searchCriteriaKey,
							searchCreteriaValue));
				}
			}
			
		}
	 
	 

			
			List<ProgramOld> sList = null;
		
		//criteria.setProjection(null);
		criteria.setFirstResult(app.getFirstPage());
		criteria.setMaxResults(app.getMaxResult());
		
	//	session.
		
		sList=criteria.list();
		
		int resultsCount=sList.size();
		System.out.println("################# Size of List Bean Result in dbAdapter: "+resultsCount);
		System.out.println("################# Data of List Bean Result in dbAdapter: "+sList);
	//	tx.commit();
		
		return sList;
		
	}

 @Override
 @Transactional
	public ProgramOld getApplication(int appID) {
		
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(ProgramOld.class);
	
		
		criteria.add(Restrictions.eq("id", appID));
		List<ProgramOld> sList = null;

		sList=criteria.list();
	

		
		System.out.println("################# :" + sList.size());
		try
		{return sList.get(0);}
		catch(Exception e)
		{System.out.println("#######  exception : "+e.getMessage());return null;}
	}

 @Transactional
	public void updateApplication(ProgramOld app) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("#### talent update : "+app);
		session.update(app);
		
}
}