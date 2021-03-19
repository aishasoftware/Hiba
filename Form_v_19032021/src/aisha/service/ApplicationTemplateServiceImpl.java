package aisha.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Application;
import aisha.bean.BasicBean;
import aisha.bean.Talent;
import aisha.controller.ApplicationController;
import aisha.dao.ApplicationTemplateDAO;
import aisha.dao.BasicDAO;
import aisha.dao.TalentDAO;
import aisha.security.beans.SystemUser;

 
@Service
@Transactional
public class ApplicationTemplateServiceImpl implements ApplicationTemplateService {
 
@Autowired
private BasicDAO appDAO;
protected static Logger logger = Logger.getLogger(ApplicationTemplateServiceImpl.class);


@Override
public long addApplicationTemplate(Application app) {
	
logger.debug("Inside method ApplicationTemplateServiceImpl.addApplicationTemplate");
float eva_avg_score = 0;
float intv_avg_score = 0;
if(app.getNoOfEvaluators() != 0) 
	eva_avg_score = (app.getEv_1_score() + app.getEv_2_score() + app.getEv_3_score() + app.getEv_4_score() + app.getEv_5_score())/app.getNoOfEvaluators();
if(app.getNoOfInterviewers() != 0) 
	intv_avg_score = (app.getIntv_1_score() + app.getIntv_2_score() + app.getIntv_3_score() + app.getIntv_4_score() + app.getIntv_5_score())/Float.valueOf(app.getNoOfInterviewers());
app.setEv_avg_score(eva_avg_score);
app.setIntv_avg_score(intv_avg_score);
app.setSubmTime(new Date());
long result = appDAO.addBean(app);
return result;
	
}


@Override
public Application listApplicationTemplate(Application app)
 {
	logger.debug("Inside method ApplicationTemplateServiceImpl.listApplicationTemplate");
	String x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	HashMap<String, Object> criteria = new HashMap<>();
	/*if (x != "anonymousUser")
	{
		SystemUser currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(currentUser.getUserRole().equals("Evaluator"))
		{
			
			criteria.put("appStatus", "Screened");
		}
		
	}*/
	//app.setCriteria(criteria);
	BasicBean result = this.appDAO.listBeans(app);
	List<BasicBean> resultList = new ArrayList<BasicBean>();
	resultList = result.getResults();

		result.setResults(resultList);
		return (Application) result;	
}

@Override
public Application getApplicationTemplate(Application app) {
	logger.debug("Inside method ApplicationTemplateServiceImpl.getApplicationTemplate");
	return (Application) this.appDAO.getBean(app);
}


@Override
public void updateApplicationTemplate(Application app) {
	logger.debug("Inside method ApplicationTemplateServiceImpl.updateApplicationTemplate");
	int count1 = 0;
	float total1 = 0;
	
	int count2 = 0;
	float total2 = 0;
	
	float eva_avg_score;
	float intv_avg_score;
Float[] evas = new Float[5];
evas[0] = app.getEv_1_score();
evas[1] = app.getEv_2_score();
evas[2] = app.getEv_3_score();
evas[3] = app.getEv_4_score();
evas[4] = app.getEv_5_score();

Float[] intvs = new Float[5];
intvs[0] = app.getIntv_1_score();
intvs[1] = app.getIntv_2_score();
intvs[2] = app.getIntv_3_score();
intvs[3] = app.getIntv_4_score();
intvs[4] = app.getIntv_5_score();
	//Are you sure you want this to start at index 1?
	for (int index = 0; index < evas.length; index++)
	{
	    if (evas[index] > 0)
	    {
	        total1 = total1 + evas[index];
	        count1++;
	    } 
	}
	
	eva_avg_score = total1 / app.getNoOfEvaluators();
	
	for (int index = 0; index < intvs.length; index++)
	{
	    if (intvs[index] > 0)
	    {
	        total2 += intvs[index];
	        count2++;
	    } 
	}
	
	intv_avg_score = total2 / app.getNoOfInterviewers();
	
// float eva_avg_score = (app.getEv_1_score() + app.getEv_2_score() + app.getEv_3_score() + app.getEv_4_score() + app.getEv_5_score())/Float.valueOf(app.getNoOfEvaluators());
// float intv_avg_score = (app.getIntv_1_score() + app.getIntv_2_score() + app.getIntv_3_score() + app.getIntv_4_score() + app.getIntv_5_score())/Float.valueOf(app.getNoOfInterviewers());
app.setEv_avg_score(eva_avg_score);
app.setIntv_avg_score(intv_avg_score);
System.out.println("########## app status : " + app.getId());
/*	if(app.getAppStatus() == "Screened")
	{
		logger.debug("Inside method ApplicationTemplateServiceImpl.updateApplicationTemplate, app status : " + app.getAppStatus());
for(int j = 0; j < app.getNoOfEvaluators() ; j++)
	{
		if(app.getEvaluation(j+1) != null)
		count = count +1;
	if(count == app.getNoOfEvaluators())	
		app.setAppStatus("Evaluated");

	}*/
this.appDAO.updateBean(app);
}
 
}