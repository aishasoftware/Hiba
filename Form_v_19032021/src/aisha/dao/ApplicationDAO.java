package aisha.dao;

import java.util.List;

import aisha.bean.ProgramOld;


 
public interface ApplicationDAO {
 
    public void addApplication(ProgramOld app);
    public ProgramOld getApplication(int id);
    public void updateApplication(ProgramOld app);
/*    
    
    public void deleteStrategy(int id);
    public List<Strategy> getStrategies();*/

   

		
	public List<ProgramOld> listApplications(ProgramOld app);
     
}