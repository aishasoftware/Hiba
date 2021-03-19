/*package aisha.test;


import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class SendEmail1 {

    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "";
    private static final String PASSWORD = "1351986789";

    private static final String EMAIL_FROM = "a.mohamed@ebs-sd.com";
    private static final String EMAIL_TO = "abushoosh1992@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP (HTML)";
    private static final String EMAIL_TEXT = "<h1>Hello Java Mail \n ABC123</h1>";

    public static void main(String[] args) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");

       
        prop.put("mail.smtp.host", "smtp.gmail.com"); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "");
        prop.put("mail.smtp.port", "587"); // default port 25

        prop.setProperty("mail.smtp.socketFactory.port", "587");
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
		
			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);
			
			// content 
            msg.setText(EMAIL_TEXT);
			
            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}*/