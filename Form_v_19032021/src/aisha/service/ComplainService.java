package aisha.service;
import aisha.bean.Complain;

public interface ComplainService {
 
    public long addComplain(Complain complain);
    public Complain listComplains(Complain complain);
    public Complain getComplain(Complain complain);
    public void updateComplain(Complain complain);
    
}
