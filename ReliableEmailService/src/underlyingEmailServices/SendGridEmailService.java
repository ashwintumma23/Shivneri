package underlyingEmailServices;

import java.util.logging.Level;

import com.sendgrid.*;

import common.EmailServiceLogger;
import common.EmailTemplate;
public class SendGridEmailService {

	EmailServiceLogger sendGridLogger= EmailServiceLogger.getInstance();;

	private String to = "";
	private String from = "";
	private String cc = "";
	private String bcc = "";
	private String subject = "";
	private String body = "";
	
	public SendGridEmailService(EmailTemplate et){
		this.to = et.getTo();
		this.from = et.getFrom();
		this.cc = et.getCc();
		this.bcc = et.getBcc();
		this.subject = et.getSubject();
		this.body = et.getBody();		
	}
	
	public void sendMailSendGrid(){
		SendGrid sendgrid = new SendGrid("SENDGRID_USERNAME", "PASSWORD");

	    SendGrid.Email email = new SendGrid.Email();
	    email.addTo(this.to); 
	    email.setFrom(this.from);
	    email.setSubject(this.subject);
	    email.setText(this.body);

	    try {
			SendGrid.Response response = sendgrid.send(email);
			
			if(response.getMessage().contains("success")){
				sendGridLogger.logger.log(Level.INFO, "[SENDGRID SUCCESS] Sent Mail Successfully to " + this.to);
			} else {
				sendGridLogger.logger.log(Level.WARNING, "[SENDGRID FAILURE] Failed to Send Mail to " + this.to);
			}
	    }
	    catch (SendGridException e) {
	      System.err.println(e);
	    }
		
	}

	public static void main(String[] args) {
		/* 
		 * Start the thread for registering Email Service with the Server,
		 * and also for sending heart beats to the server 
		 */
		
		new Thread(new SendGridSendHeartBeat()).start();
		System.out.println("SendGrid : Started Thread for sending heart beats");
		
	}

}
