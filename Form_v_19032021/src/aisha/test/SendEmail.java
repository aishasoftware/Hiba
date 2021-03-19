/*package aisha.test;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class SendEmail  
{  
 public static void main(String [] args) throws MessagingException{  
      String to = "abushoosh1992@gmail.com";
      final String from = "aishasoftware@gmail.com";
      final String password = "1351986789";
      String host = "smtp.gmail.com";
  
     //Get the session object  
      //Properties properties = System.getProperties();  
     // properties.setProperty("mail.smtp.host", host);  
     // properties.setProperty("mail.smtp.port", "587");  
      //Session session = Session.getDefaultInstance(properties);  
  
      
      Properties props = new Properties();  
      props.setProperty("mail.transport.protocol", "smtp");     
      props.setProperty("mail.host", "smtp.gmail.com");  
      props.put("mail.smtp.auth", "true");  
      props.put("mail.smtp.port", "25");  
      props.put("mail.debug", "true");  
      props.put("mail.smtp.socketFactory.port", "465");  
      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
      props.put("mail.smtp.socketFactory.fallback", "false"); 
      props.put("mail.smtp.starttls.enable", "true");
      Session session = Session.getDefaultInstance(props,  
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {  
         return new PasswordAuthentication(from,password);  
     }  
     });  

     //session.setDebug(true);  
     Transport transport = session.getTransport();  
     InternetAddress addressFrom = new InternetAddress(from);  

     MimeMessage message = new MimeMessage(session);  
     message.setSender(addressFrom);  
     message.setSubject("249 platform");  
     message.setContent("shishi", "text/plain");  
     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

     transport.connect();  
     Transport.send(message);  
     transport.close();
     }  

     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Ping");  
         message.setText("Hello, this is example of sending email  ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
*/