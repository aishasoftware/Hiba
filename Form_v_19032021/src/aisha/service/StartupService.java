package aisha.service;
import aisha.bean.BasicBean;
import aisha.bean.Startup;
import aisha.bean.Talent;

public interface StartupService {
 
	  public long addBean(BasicBean talent);
	    public BasicBean listBeans(BasicBean talent);
	    public BasicBean getBean(BasicBean talent);
	    public BasicBean getMyBean(BasicBean talent);
	    public void updateBean(BasicBean talent);    
	    public BasicBean listBeansCustom(BasicBean talent);
	    public BasicBean listStartupBasicBeans(BasicBean talent);
		void deleteBean(Startup startup);
    
}
