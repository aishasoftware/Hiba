package aisha.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.Talent;

 
//@Repository
public class TalentDAOImpl implements TalentDAO {
 
 @Autowired
 private SessionFactory sessionFactory;
 protected static Logger logger = Logger.getLogger(TalentDAOImpl.class);
 
 private Session getCurrentSession() {
  return sessionFactory.getCurrentSession();
 }
 
 public long addTalent(Talent talent) {
	 logger.debug("Inside method TalentDAOImpl.addTalent");
	 //talent.setCreationTime(new Date());
  long result = (long) getCurrentSession().save(talent);
  logger.debug("Inside method TalentDAOImpl.addTalent, after add talent with id :" + result);
  return result;
 }
 
 
 
 @Override
	@Transactional
	public Talent listBeans(Talent talent) {
	 logger.debug("Inside method TalentDAOImpl.listBeans");
	 Criteria criteria= sessionFactory.getCurrentSession().createCriteria(Talent.class);

		List<Talent> sList = null;
/*		if (talent.getFilter() != null)
		{
			List<String> keysList = new ArrayList<String>(talent.getFilter().keySet());
			Iterator<String> keyListIterator = keysList.iterator();
			while (keyListIterator.hasNext()) {
				String searchCriteriaKey = keyListIterator.next();
				
				
				Object searchCreteriaValue = talent.getFilter()
						.get(searchCriteriaKey);
				
				
				if(searchCreteriaValue != null && searchCreteriaValue.toString().isEmpty()!= true)
				{	
					criteria.add(Restrictions.eq(searchCriteriaKey,
							searchCreteriaValue));
				}
			}
			
		}*/
/*	
	if (talent.isOrderAsc() == true) {
		criteria.addOrder(Order.asc(talent.getOrderBy()));
	} else {
		criteria.addOrder(Order.desc(talent.getOrderBy()));
	}*/
	
	logger.debug("Inside method TalentDAOImpl.listBeans, criteria :" + criteria);
	criteria.setProjection(null);
	//disabled for testing since no data
	//criteria.setFirstResult(talent.getFirstResults());
	//criteria.setMaxResults(talent.getMaxResults());

		
	
	sList=criteria.list();
	logger.debug("Inside method TalentDAOImpl.listBeans, result size :" + sList.size());
	logger.debug("Inside method TalentDAOImpl.listBeans, result list :" + sList);
	int numerOfRows = sList.size();
//	talent.setTotalResults(numerOfRows);
	
		//talent.setTalentList(sList);
		logger.debug("Inside method TalentDAOImpl.listBeans, criteria :" );
		return talent;
		
	}

 @Override
 @Transactional
	public Talent getTalent(int talentID) {
	 logger.debug("Inside method TalentDAOImpl.getTalent");
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Talent.class);
	
		
		criteria.add(Restrictions.eq("id", Long.valueOf(String.valueOf(talentID))));
		List<Talent> sList = null;
		logger.debug("Inside method TalentDAOImpl.getTalent, get talent with id : "+talentID);
		sList=criteria.list();
		logger.debug("Inside method TalentDAOImpl.getTalent, get talent result : "+talentID);

		
		System.out.println("################# :" + sList.size());
		try
		{logger.debug("Inside method TalentDAOImpl.getTalent, get talent result : " + sList.get(0));
		return sList.get(0);}
		catch(Exception e)
		{System.out.println("#######  exception : "+e.getMessage());return null;}
	}

 @Transactional
	public void updateTalent(Talent talent) {
	 logger.debug("Inside method TalentDAOImpl.updateTalent : " + talent);
		Session session = this.sessionFactory.getCurrentSession();
		session.update(talent);
		logger.debug("Inside method TalentDAOImpl.updateTalent, after update talent");
}

@Override
public Talent listBeansCustom(Talent talent) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Talent listStartupTalents(Talent talent) {
	SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select ID,FIELD1,FIELD2,FIELD3 from talent where FIELD15 = 262144");
	//List<Talent> sList = null;

	
	List<Object[]> sList = query.list();
	List<BasicBean> omg = new ArrayList<BasicBean>();
	//Talent t = new Talent();
	Talent result = new Talent();
	//for(Object[] row: sList)
		Object[] row;
		for(int i = 0 ; i < sList.size() ; i++)
	{
		row = sList.get(i);
		Talent t = new Talent();
		t.setId(Long.valueOf(row[0].toString()));
		t.setField1(row[1].toString());
		t.setField2(row[2].toString());
		t.setField3(row[3].toString());
		
		omg.add(t);
	}
	//Talent result = new Talent();
	//result.setResults(sList);
	result.setResults(omg);
	return result;
}

/*@Override
public Talent listBeansCustom(Talent talent) {
	SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select app.NAME,app.INVESTOR_ID,sub.APP_STATUS,sub.SUBMISSION_TIME, sub.id from APPLICATIONS app join SUBMITTED_APPLICATION sub on (app.id=sub.APP_ID) where sub.TALENT_ID = 5505024");
	//List<Talent> sList = null;

	
	List<Object[]> sList = query.list();
	List<Talent> omg = new ArrayList<>();
	Talent t = new Talent();
	Talent result = new Talent();
	for(Object[] row: sList)
	{
		
		t.setField1(row[0].toString());
		t.setField2(row[1].toString());
		t.setField3(row[2].toString());
		t.setField4(row[3].toString());
		t.setField5(row[4].toString());
		omg.add(t);
	}
	//Talent result = new Talent();
	//result.setResults(sList);
	result.setResults(omg);
	return result;
}*/
}