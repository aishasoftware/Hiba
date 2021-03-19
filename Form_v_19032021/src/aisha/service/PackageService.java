package aisha.service;
import java.util.List;

import aisha.bean.Package;

public interface PackageService {
 
    public long addPackage(Package pack);
    public Package listPackages(Package pack);
    public Package getPackage(Package pack);
    public void updatePackage(Package pack);
    public List<String> getPriviliges(String packId);
    
}
