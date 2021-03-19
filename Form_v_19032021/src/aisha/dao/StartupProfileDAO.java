package aisha.dao;

import aisha.bean.StartupProfile;


 
public interface StartupProfileDAO {
 
    public long addStartupProfile(StartupProfile profile);
    public StartupProfile getStartupProfile(int id);
    public void updateStartupProfile(StartupProfile profile);
	public StartupProfile listStartupProfiles(StartupProfile profile);
     
}