package aisha.service;

import java.util.List;

import aisha.bean.ProgramOld;
import aisha.bean.Application;

 
public interface ApplicationTemplateService {
 
    public long addApplicationTemplate(Application app);
    public Application listApplicationTemplate(Application app);
    public Application getApplicationTemplate(Application app);
    public void updateApplicationTemplate(Application app);
   /*
  
    public void deleteStrategy(int id);
    */
 
}
