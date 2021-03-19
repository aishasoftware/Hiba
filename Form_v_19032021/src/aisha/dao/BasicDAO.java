package aisha.dao;

import java.util.List;
import java.util.Map;

import aisha.bean.BasicBean;
import aisha.bean.Talent;


 
public interface BasicDAO {
 
    public long addBean(BasicBean basic);
    public BasicBean getBean(BasicBean basic);
    public void deleteBean(BasicBean basic) ;
    public BasicBean getMyBean(BasicBean basic);
    public void updateBean(BasicBean basic);		
	public BasicBean listBeans(BasicBean basic);
	public List<String> getPriviliges(String packId);
	public Map<String,String> getConnections(String id, String type);
	public Map<String,String> getEvaluators(String id);
	public Map<String,String> getInterviewers(String id);
	
	
     
}