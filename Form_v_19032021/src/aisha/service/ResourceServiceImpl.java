package aisha.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Resource;
import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;

 
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {


@Autowired
private BasicDAO basicDAO;




@Override
public long addResource(Resource resource) {
	
	resource.setCreationTime(new Timestamp(new Date().getTime()));
	resource.setStatus("active");
   // PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   // resource.setCreatedBy(currentUser.getUserName());

	long result = basicDAO.addBean(resource);

	return result;
	
}


@Override
public Resource listResources(Resource resource)
 {

		Resource result =  (Resource) this.basicDAO.listBeans(resource);
	
		return result;
	
}


@Override
public Resource getResource(Resource resource) {
	
	Resource result = (Resource) this.basicDAO.getBean(resource);

	return result;
}


@Override
public void updateResource(Resource resource) {
	
	resource.setLastUpdateTime(new Timestamp(new Date().getTime()));
    PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    resource.setLastUpdatedBy(currentUser.getUserName());
	this.basicDAO.updateBean(resource);
	
}
 
}