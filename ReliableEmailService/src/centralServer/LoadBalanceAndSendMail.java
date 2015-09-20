package centralServer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
import java.util.logging.Level;

import common.EmailServiceLogger;
import common.EmailTemplate;
import underlyingEmailServices.MailgunEmailService;
import underlyingEmailServices.SendGridEmailService;

/**
 * LoadBalanceAndSendMail: Load Balance the requests which are received, 
 * and dispatch them to underlying email service
 * 
 *
 */
public class LoadBalanceAndSendMail implements Runnable {
	
	private String emailListenPort = "11000"; 

	@SuppressWarnings("static-access")
	public void run() {
		byte[] receiveData;
		
		EmailServiceLogger emailServiceLogger = EmailServiceLogger.getInstance(); 
		emailServiceLogger.logger.log(Level.INFO, "[LOAD_BALANCER] Thread Started to load"
				+ " balance the email requests and send email");
		
		// Keep listening for requests from clients for sending Email
		while(true){
			try{
			DatagramSocket serverSocket = null;
			receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			serverSocket = new DatagramSocket(Integer.parseInt(this.emailListenPort));
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			byte[] receivedDataAsObject = receivePacket.getData();
			ByteArrayInputStream in = new ByteArrayInputStream(receivedDataAsObject);
            ObjectInputStream is = new ObjectInputStream(in);
            EmailTemplate emailTemplate = null;
            emailTemplate = (EmailTemplate) is.readObject();
            serverSocket.close();
            
            int size = ServerProcess.emailServicesMap.size();
            if(size < 1) {
            	System.out.println("No active underlying Email Service. Cannot send email");
        		emailServiceLogger.logger.log(Level.WARNING, "No active underlying Email Service."
        				+ " Cannot send email");
            	continue;            	
            }
            int randomValidIndex = new Random().nextInt(size);
            
            String underlyingEmailServicePointer = 
            		ServerProcess.emailServicesList.get(randomValidIndex);
            
            switch(underlyingEmailServicePointer.toUpperCase()) {
			
				// Email Service: Mailgun
				case "MAILGUN":
					MailgunEmailService mes = new MailgunEmailService(emailTemplate);
					mes.sendMailMailgun();
					emailServiceLogger.logger.log(Level.INFO, "Sent Mail to "
							+ emailTemplate.getTo() + " with MAILGUN");	            		
					break;
					
				// Email Service: Sendgrid
				case "SENDGRID":
					SendGridEmailService ses = new SendGridEmailService(emailTemplate);
					ses.sendMailSendGrid();
					emailServiceLogger.logger.log(Level.INFO, "Sent Mail to "
							+ emailTemplate.getTo() + " with SENDGRID");	
					break;			
					
				// Any new service can be added here	
				default:
					break;
			}
		} catch (Exception e){
				e.printStackTrace();
		} 
		}
							
	}

}
