package aisha.service;
import java.util.List;
import java.util.Map;

import aisha.bean.Connection;

public interface ConnectionService {
 
    public long addConnection(Connection Connection);
    public Connection listConnections(Connection Connection);
    public Connection getConnection(Connection Connection);
    public void updateConnection(Connection Connection);
    public Map<String,String> getConnections(String id, String type);
    
}
