package aisha.service;
import aisha.bean.BasicBean;

public interface SubscriptionService {
 
    public long addBean(BasicBean sub);
    public BasicBean listBeans(BasicBean sub);
    public BasicBean getBean(BasicBean sub);
    public void updateBean(BasicBean sub);
    
}
