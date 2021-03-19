
package aisha.service;



import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import aisha.bean.BasicBean;
import aisha.bean.Talent;

 
public interface TalentService {
 
    public long addBean(BasicBean talent);
    @PreAuthorize("hasRole('ROLE_USER_PlatformAdmin1')")
    public BasicBean listBeans(BasicBean talent);
    //@PostAuthorize("hasPermission(returnObject != null)")
    public BasicBean getBean(BasicBean talent);
    public BasicBean getMyBean(BasicBean talent);
    public void updateBean(BasicBean talent);    
    public BasicBean listBeansCustom(BasicBean talent);
    public BasicBean listStartupBasicBeans(BasicBean talent);
    public void deleteTalent(Talent talent) ;
 
}
