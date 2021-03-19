package aisha.dao;

import java.util.List;

import aisha.bean.Application;


 
public interface ApplicationTemplateDAO {
 
    public long addApplicationTemplate(Application app);
    public Application getApplicationTemplate(Application app);
    public void updateApplicationTemplate(Application app);
/*    
    
    public void deleteStrategy(int id);
    public List<Strategy> getStrategies();*/

   

		
	public Application listApplicationTemplates(Application app);
     
}