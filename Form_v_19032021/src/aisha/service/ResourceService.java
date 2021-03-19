package aisha.service;
import aisha.bean.Resource;

public interface ResourceService {
 
    public long addResource(Resource resource);
    public Resource listResources(Resource resource);
    public Resource getResource(Resource resource);
    public void updateResource(Resource resource);
    
}
