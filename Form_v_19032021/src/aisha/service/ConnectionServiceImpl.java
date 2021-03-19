package aisha.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.Connection;
import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;

 
@Service
@Transactional
public class ConnectionServiceImpl implements ConnectionService {


@Autowired
private BasicDAO basicDAO;

protected static Logger logger = Logger.getLogger(ConnectionServiceImpl.class);


@Override
public long addConnection(Connection connection) {
	logger.debug("Inside method ConnectionProfileServiceImpl.addConnectionProfile, add bean : " + connection);
	// profile.setCreationTime(new Date());
	connection.setStatus("pending");
	connection.setCreationTime(new Timestamp((new Date()).getTime()));
     //SystemUser currentUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // profile.setCreatedBy(currentUser.getUserName());
	long result = basicDAO.addBean(connection);
	logger.debug("Inside method ConnectionProfileServiceImpl.addConnectionProfile, after adding bean with id: " + result);
	return result;
	
}


@Override
public Connection listConnections(Connection Connection)
 {
	logger.debug("Inside method ConnectionProfileServiceImpl.listConnectionProfiles");
		Connection result =  (Connection) this.basicDAO.listBeans(Connection);
		logger.debug("Inside method ConnectionProfileServiceImpl.listConnectionProfiles, after listing beans : " + result.getResults());
		return result;
	
}


@Override
public Connection getConnection(Connection Connection) {
	logger.debug("Inside method ConnectionProfileServiceImpl.getConnectionProfile");
	Connection result = (Connection) this.basicDAO.getBean(Connection);
	logger.debug("Inside method ConnectionProfileServiceImpl.getConnectionProfile, after get ConnectionProfile : " + result);
	return result;
}


@Override
public void updateConnection(Connection Connection) {
	logger.debug("Inside method ConnectionProfileServiceImpl.updateConnectionProfile");
	//profile.setLastModificationTime(new Date());
   // PlatformUser currentUser = (PlatformUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //profile.setLastModifiedBy(currentUser.getUserName());
	this.basicDAO.updateBean(Connection);
	logger.debug("Inside method ConnectionProfileServiceImpl.updateConnectionProfile, after update ConnectionProfile : " + Connection);
}


@Override
public  Map<String,String> getConnections(String id, String type)
{
	Map<String,String>  result = null ;
	result =  this.basicDAO.getConnections(id, type);
	return result;
	
	}
 
}