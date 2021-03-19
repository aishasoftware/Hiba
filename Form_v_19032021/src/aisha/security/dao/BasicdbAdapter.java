package aisha.security.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import aisha.security.beans.SystemUser;


public class BasicdbAdapter implements IBasicdbAdapter{

		
	//	private static final Logger logger = LoggerFactory.getLogger(DAOImpl.class);
	//@Autowired
//	protected SessionFactory sessionFactory;
	@Autowired
	public SessionFactory sessionFactory;
	public Session currentSession;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Transactional
		public void addSystemUser(SystemUser bean) {
		
		bean.setCreationTime(new Timestamp(System.currentTimeMillis()));
		bean.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		bean.setStatus("active");
			Session session = this.sessionFactory.getCurrentSession();
		
			try
			{session.save(bean);
			//session.persist(bean);
			}
			catch(Exception e)
			{
				System.err.println("######### Exception in dbAdapter : "+e.getMessage());}
	

			}
	
   @Transactional
		public void updateSystemUser(SystemUser bean) {
			Session session = this.sessionFactory.getCurrentSession();
			bean.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
			session.update(bean);
			
}

	   
	   @Transactional
		public SystemUser getSystemUser(SystemUser bean) {
			Session session = this.sessionFactory.getCurrentSession();
			HashMap<String,Object> searchCriteria=bean.getSearchCriteria();
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(bean.getClass());
			if(searchCriteria!=null)
			{
			for (String key : searchCriteria.keySet()) 
			{
				criteria.add(Restrictions.eq(key, searchCriteria.get(key)));
			}
			
			
			if(!(searchCriteria.get("status")!=null&&!searchCriteria.get("status").equals("active")))
			criteria.add(Restrictions.eq("status","active"));
			}
			
			criteria.add(Restrictions.eq("userName", ((SystemUser)bean).getUserName()));
			
			if(bean.getId()!=0)
			criteria.add(Restrictions.eq("id", 1605632));
			List<SystemUser> sList = null;

			sList=criteria.list();
			

			
			System.out.println("################# :" + sList.size());
			try
			{return sList.get(0);}
			catch(Exception e)
			{System.out.println("#######33  xeception : "+e.getMessage());return null;}
		}
	   
		}