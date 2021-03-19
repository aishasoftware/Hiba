package aisha.service;

import java.util.List;

import aisha.bean.Program;

 
public interface ApplicationService {
 
    public long addApplication(Program app);
    public Program listApplications(Program app);
    public Program getApplication(int id);
    public void updateApplication(Program app);
   /*
  
    public void deleteStrategy(int id);
    */
 
}
