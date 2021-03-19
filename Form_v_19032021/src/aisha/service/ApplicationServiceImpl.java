package aisha.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Program;
import aisha.dao.ApplicationDAO;
import aisha.dao.BasicDAO;
import aisha.util.CurrentUser;

 
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
 
@Autowired
 private BasicDAO basicDAO;
 
@Autowired
private ApplicationDAO appDAO;


@Override
public long addApplication(Program app) {
	app.setStatus("create");
	app.setCreationTime(new Timestamp((new Date().getTime())));
	app.setLastUpdateTime(new Timestamp((new Date().getTime())));
	app.setCreatedBy(String.valueOf(CurrentUser.getUserId()));
	app.setLastUpdatedBy(String.valueOf(CurrentUser.getUserId()));
	app.setInvestorId(Integer.valueOf(CurrentUser.getEntityId()));
	

	long x = basicDAO.addBean(app);
	return x;
	
}


@Override
public Program listApplications(Program app)
 {
	
		return (Program) this.basicDAO.listBeans(app);
	
}


@Override
public Program getApplication(int id) {
	Program x = new Program();
	x.setId(id);
	x = (Program) basicDAO.getBean(x);
	return x;
}


@Override
public void updateApplication(Program app) {
	// TODO Auto-generated method stub
	int i = 0 ;
	int j = 0 ;
	if(app.getEva_1() != null)
			i++;
	if(app.getEva_2() != null)
		i++;
	if(app.getEva_3() != null)
		i++;
	if(app.getEva_4() != null)
		i++;
	if(app.getEva_5() != null)
		i++;
	
	if(app.getInterv_1() != null)
		j++;
	if(app.getInterv_2() != null)
		j++;
	if(app.getInterv_3() != null)
		j++;
	if(app.getInterv_4() != null)
		j++;
	if(app.getInterv_5() != null)
		j++;
	
	 if (i < Integer.valueOf(app.getNoOfEvaluators()))
			;
	 else if (i < Integer.valueOf(app.getNoOfInterviewers()))
		;
	 else
basicDAO.updateBean(app);
}



/*@Override
public Program getApplication(int id) {
	// TODO Auto-generated method stub
	return this.appDAO.getApplication(id);
}


@Override
public void updateApplication(Program app) {
	// TODO Auto-generated method stub
	this.appDAO.updateApplication(app);
}*/
 
}