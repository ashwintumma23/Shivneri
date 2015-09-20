package underlyingEmailServices;

import java.util.logging.Level;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import common.EmailServiceLogger;
import common.EmailTemplate;

/**
 * MailgunEmailService: Main class for Mailgun Email Service
 */
public class MailgunEmailService {

	public static String sendHeartBeatPort = "12000";
	public static String serverHeartBeatListenPort = "10000";
	EmailServiceLogger emailServiceLogger = EmailServiceLogger.getInstance();

	private String to = "";
	private String from = "";
	private String cc = "";
	private String bcc = "";
	private String subject = "";
	private String body = "";
	
	public MailgunEmailService(EmailTemplate et){
		this.to = et.getTo();
		this.from = et.getFrom();
		this.cc = et.getCc();
		this.bcc = et.getBcc();
		this.subject = et.getSubject();
		this.body = et.getBody();		
	}
	
	public void sendMailMailgun(){
		ClientResponse clientResponse =  SendSimpleMessage(this.to,this.from,
				this.cc,this.bcc,this.subject,this.body);
		if(clientResponse.getStatus() == 200){
			emailServiceLogger.logger.log(Level.INFO, "[MAILGUN SUCCESS] Sent Mail Successfully to " + this.to);
		} else {
			emailServiceLogger.logger.log(Level.WARNING, "[MAILGUN FAILURE] Failed to Send Mail to " + this.to);
		}		
	}
	
	public static ClientResponse SendSimpleMessage(String to, String from, String cc, String bcc,
			String subject, String body) {
	       Client client = Client.create();
	       client.addFilter(new HTTPBasicAuthFilter("api",
	                       "key-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"));
	       WebResource webResource =
	               client.resource("https://api.mailgun.net/v3/sandboxe69axxxxxxxxxxxxxxxxxxxxxxxxxxxxxmailgun.org" +
	                               "/messages");
	       MultivaluedMapImpl formData = new MultivaluedMapImpl();
	       formData.add("from",from);
	       formData.add("to",to);
	       formData.add("subject", subject);
	       formData.add("text", body);
	       return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	               post(ClientResponse.class, formData);
	}
	
	
	public static void main(String[] args) {
		/* 
		 * Start the thread for registering Email Service with the Server,
		 * and also for sending heart beats to the server 
		 */
		new Thread(new MailgunSendHeartBeat()).start();
		System.out.println("Mailgun : Started Thread for sending heart beats");
		
	}

}
