package aisha.service;
import java.util.List;
import java.util.Map;

import aisha.bean.BasicBean;
import aisha.bean.Investor;

public interface InvestorService {
 
	    public long addBean(BasicBean talent);
	    public BasicBean listBeans(BasicBean talent);
	    public BasicBean getBean(BasicBean talent);
	    public BasicBean getMyBean(BasicBean talent);
	    public void updateBean(BasicBean talent);    
	    public BasicBean listBeansCustom(BasicBean talent);
	    public BasicBean listStartupBasicBeans(BasicBean talent);
	    public Map<String,String> getEvaluators(String id);
	    public Map<String,String> getInterviewers(String id);
		public void deleteBean(BasicBean bean);
	    
    
}
