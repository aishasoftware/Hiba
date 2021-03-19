package aisha.security.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import aisha.bean.PlatformUser;
import aisha.dao.BasicDAO;
import aisha.security.dao.SystemUserDAO;
import aisha.util.CurrentUser;
import aisha.util.PasswordGenerator;


public class SystemUserServiceImpl implements UserDetailsService, SystemUserService {

	@Autowired
	SystemUserDAO systemUserDBAdapter;
	@Autowired
	BasicDAO basicDBAdapter;	

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Transactional
	public PlatformUser getSystemUser(PlatformUser user)
	{
		user = (PlatformUser) basicDBAdapter.getBean(user);
	
System.out.println("############# shishi : "+user);
		return user;
		
	}
	/* this method add new system User after check it is not exist on dataBase
	*//* (non-Javadoc)
	 * @see com.ebs.commons.services.CommonService#addBean(com.ebs.commons.beans.BaseRequest)
	 */

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		System.out.println("######### loadUserByUsername");// TODO Auto-generated method stub
		
		PlatformUser user = new PlatformUser();
		//user.setUserName(arg0);
		//user.setUserName(arg0);
		//user.setPassword("123456");
		//user.setUserRole("Engineer");

		//user = cypherPassword(user);
		//systemUserDBAdapter.addBean(user);
	    HashMap<String, Object> searchCriteria = new HashMap<>();
	    
	    searchCriteria.put("userName", arg0);
	    user.setSearchCriteria(searchCriteria);
		user = (PlatformUser) basicDBAdapter.getBean(user);
		user.setEnabled(true);
		user.setLoginTryCount(0);
		//user.setUserFullName("aisha");
		//user.setPassword("aisha");
		//user.setPassword("aisha");
		//user = cypherPassword(user);
		System.out.println("############# after cypher : "+user);
		boolean matched = BCrypt.checkpw(arg0, user.getPassword());
		System.out.println("############# matched : "+ matched);
		//user.setUserRole("ROLE_ADMIN");
		//user.setPassword("aisha");
		
		System.out.println("############# shishi : "+user);
		/*ApplicationController.setCurrentUser(user);*/
				return user;
	}
	protected PlatformUser cypherPassword(PlatformUser user) {
		final String thisMethod = "SystemUserController.addBean: ";
	
		PlatformUser newUser = user;
		String password = user.getPassword();
		try {
			 
		        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
		       // System.out.println(generatedSecuredPasswordHash);
		         
		      //  boolean matched = BCrypt.checkpw(password, generatedSecuredPasswordHash);
		        //System.out.println(matched);
		        
		        /*			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes(), 0, password.length());
			String hashedPass = new BigInteger(1, messageDigest.digest())
					.toString(16);
			if (hashedPass.length() < 32) {
				hashedPass = "0" + hashedPass;
			}
*/
			newUser.setPassword(hashedPass);
			newUser.setConfirmedPassword(hashedPass);
		} catch (Exception e) {
			
			
		}

		return newUser;
	}
	
	
	@Transactional
	public long addSystemUser(PlatformUser user) {
		
		user.setCreationTime(new Timestamp(System.currentTimeMillis()));
		user.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		user.setCreatedBy(CurrentUser.getUserName());
		user.setLastUpdatedBy(CurrentUser.getUserName());
		PlatformUser cyper = null;
		if(user.getPassword() == null || user.getPassword().isEmpty())
		{

			try {
				cyper = sendPasswordEmail(user) ;
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		cyper = cypherPassword(user);
		
		
		cyper.setStatus("active");
		cyper.setCreationTime(new Timestamp((new Date()).getTime()));
		cyper.setLastUpdateTime(new Timestamp((new Date()).getTime()));
		cyper.setCreatedBy(CurrentUser.getUserName());
		cyper.setLastUpdatedBy(String.valueOf(CurrentUser.getUserId()));
		long userId = basicDBAdapter.addBean(cyper);
		
		return userId;
		
	}
	

/*	public String generatePassayPassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);
	 
	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return "505";
	        }
	 
	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);
	 
	    String password = gen.generatePassword(10, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}
*/

	@Transactional
	public void updateSystemUser(PlatformUser user) {
		// TODO Auto-generated method stub

		user.setLastUpdateTime(new Timestamp((new Date()).getTime()));
		user.setLastUpdatedBy(CurrentUser.getUserKey());
		
		basicDBAdapter.updateBean(user);
	}

	@Transactional
	public PlatformUser listSystemUsers(PlatformUser user) {
		//PlatformUser result = systemUserDBAdapter.listPlatformUsers(user);
		PlatformUser result = (PlatformUser) basicDBAdapter.listBeans(user);
		return result;
	}
	
	
	@Override
	public PlatformUser resetPassword(String username) throws AddressException, MessagingException {
		PlatformUser user = new PlatformUser();
		user.setUserName(username);

		user = systemUserDBAdapter.getPlatformUser(user);
		if (user == null)
			return null;
		user =	sendPasswordEmail(user);	
		return user;
	}

	private PlatformUser generatePassword(PlatformUser user) {
		String pass = PasswordGenerator.generateRandomPassword(10);
		user.setPassword(pass);
		user.setConfirmedPassword(pass);
		return user;
	}

	public PlatformUser sendPasswordEmail(PlatformUser user) throws AddressException, MessagingException 
	{
		  String host="smtp.gmail.com";  
		  final String x ="hellodears249@gmail.com";//change accordingly  
		  final String password="Aisha@1992";//change accordingly  
		  PlatformUser email = generatePassword(user);
	
		  String to = email.getEmail();//change accordingly  
		  
		   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.auth", "true");  
		   props.put("mail.smtp.port", "587");    
		   props.put("mail.smtp.starttls.enable", "true");
		// *** BEGIN CHANGE
		   props.put("mail.smtp.user", x);

	        // creates a new session, no Authenticator (will connect() later)
	        Session session = Session.getDefaultInstance(props);
	// *** END CHANGE

	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);

	        msg.setFrom(new InternetAddress(x));
	        InternetAddress[] toAddresses = { new InternetAddress(to) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject("249 Platform");
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setText("Platform user has been created for you, please use these credentials to access it: user name : " + email.getUserName() + " , password : " + email.getPassword());
	// *** BEGIN CHANGE
	        // sends the e-mail
	        Transport t = session.getTransport("smtp");
	        t.connect(x, password);
	        t.sendMessage(msg, msg.getAllRecipients());
	        t.close();
	// *** END CHANGE
			System.out.println("############ generated password : " + email.getPassword());
	        PlatformUser cyper = cypherPassword(email);
			System.out.println("############ generated password : " + email.getPassword());
	        return cyper;
	}
	}