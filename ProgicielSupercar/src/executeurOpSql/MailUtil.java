package executeurOpSql;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;


public class MailUtil {

	public static void sendMail(String recepient,String msg) throws Exception {
		System.out.println("preparing to sent email");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String email = "testingsentmail@gmail.com";
		String pass = "testingemailsent";

		Session session = Session.getInstance(properties, new Authenticator()
		{

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email,pass);
			}
		});

		Message message = prepareMessage(session,email,recepient,msg);

		Transport.send(message);
		System.out.println("message sent successfully");

	}
	private static Message prepareMessage(Session session, String email, String recipient, String msg) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Vente Effectuer");
			
			message.setContent(msg, "text/html");

			return message;
		}
		catch(Exception ex){

		}

		return null;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		  
		MailUtil.sendMail("trileshneermul@gmail.com","msg");

		
	}
	


}