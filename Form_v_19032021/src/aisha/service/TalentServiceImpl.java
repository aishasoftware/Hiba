package aisha.service;

import java.security.acl.Permission;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
/*import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.BasicBean;
import aisha.bean.Talent;
import aisha.dao.BasicDAO;
import aisha.util.CurrentUser;


 
@Service
@Transactional
public class TalentServiceImpl implements TalentService {
 
/*@Autowired	
private MutableAclService aclService;
*/
@Autowired
private BasicDAO basicDAO;


protected static Logger logger = Logger.getLogger(TalentServiceImpl.class);


@Override
public long addBean(BasicBean talent) {
	//org.springframework.security.acls.AclPermissionEvaluator
	//org.springframework.security.acls.domain.EhCacheBasedAclCache
//	org.springframework.security.acls.jdbc.JdbcAclService 
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, add bean : " + talent);
	talent.setStatus("active");
    talent.setCreationTime(new Timestamp((new Date()).getTime()));
    talent.setCreatedBy(String.valueOf(CurrentUser.getUserId()));
    talent.setLastUpdatedBy(String.valueOf(CurrentUser.getUserId()));
    talent.setLastUpdateTime(new Timestamp((new Date()).getTime()));
	long result = basicDAO.addBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.addBasicBean, after adding bean with id: " + result);
	return result;
}

@Override
public void deleteTalent(Talent talent) {
	logger.debug("Inside method TalentServiceImpl.addTalent, add bean : " + talent);
	/*talent.setStatus("active");
talent.setCreationTime(new Date());*/
	basicDAO.deleteBean(talent);
	logger.debug("Inside method TalentServiceImpl.addTalent, after adding bean with id: ");
	
}


@Override
@PreAuthorize("hasRole('ROLE_USER_PlatformAdmin1')")
public BasicBean listBeans(BasicBean talent)
 {
	logger.debug("Inside method BasicBeanServiceImpl.listBasicBeans");
		BasicBean result = (BasicBean) this.basicDAO.listBeans(talent);
	//	logger.debug("Inside method BasicBeanServiceImpl.listBasicBeans, after listing beans : " + result.getBasicBeanList());
		return result;	
}

public enum Permissions
{ READ, WRITE}

/*@Transactional
public void grant (String principal, Talent t, Permission[] per)
{
ObjectIdentity oi = new ObjectIdentityImpl(Talent.class, t.getId());
Sid sid = new PrincipalSid(principal);
MutableAcl acl = (MutableAcl) aclService.readAclById(oi);
for(Permission persmission : per) {
	switch(persmission.toString()) {
	case "READ":
	acl.insertAce(acl.getEntries().size(), BasePermission.READ, sid, true);
	break;
}
	aclService.updateAcl(acl);
}
}
*/
@Override
@Transactional
//@PostAuthorize("hasPermission(returnObject != null)")
public BasicBean getBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean");
	BasicBean result = (BasicBean) this.basicDAO.getBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean, after get talent : " + result);
	return result;
}

@Override
public BasicBean getMyBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean");
	BasicBean result = (BasicBean) this.basicDAO.getMyBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean, after get talent : " + result);
	return result;
}


@Override
public void updateBean(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean");
	
	this.basicDAO.updateBean(talent);
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean, after update talent : " + talent);
}


@Override
public BasicBean listBeansCustom(BasicBean talent) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public BasicBean listStartupBasicBeans(BasicBean talent) {
	// TODO Auto-generated method stub
	return null;
}

/*@Override
public BasicBean listStartupBasicBeans(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean");
	
	BasicBean result = this.talentDAO.listStartupBasicBeans(talent);
	logger.debug("Inside method BasicBeanServiceImpl.updateBasicBean, after update talent : " + talent);
return result;
}
*/

/*
@Override
public BasicBean listBeansCustom(BasicBean talent) {
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean");
	BasicBean result = this.talentDAO.listBeansCustom(talent);
	logger.debug("Inside method BasicBeanServiceImpl.getBasicBean, after get talent : " + result);
	return result;
}*/

}
 
