package aisha.service;



import aisha.bean.Talent;

 
public interface BasicService {
 
    public long addTalent(Talent talent);
    public void deleteTalent(Talent talent);
    public Talent listTalents(Talent talent);
    public Talent getTalent(int id);
    public void updateTalent(Talent talent);
   /*
  
    public void deleteStrategy(int id);
    */
 
}
