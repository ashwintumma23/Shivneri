package centralServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import common.EmailServiceLogger;

/**
 * 
 * ServerProcess: Class to start the main Server Process
 * Usage: 
 * $ java ServerProcess
 * 
 */
public class ServerProcess {

	public static ArrayList<String> emailServicesList = new ArrayList<String>();
	public static HashMap<String,Long> emailServicesMap = new HashMap<String,Long>();
	public static int emailServicesCount = 0;
	
	/**
	 * Main function to start the threads of Server Process
	 */
	public static void main(String[] args) {		
	
		EmailServiceLogger emailServiceLogger = EmailServiceLogger.getInstance();
		emailServiceLogger.logger.log(Level.INFO, "[SERVER_PROCESS] Started Main Server");
		
		/*
		 *  Start threads for listening heart beats from Underlying email services,
		 *  for monitoring those heart beats, and
		 *  load balancing and sending mail
		 */
		new Thread(new ListenHeartBeats()).start();
		
		new Thread(new MonitorHeartBeats()).start();
		
		new Thread(new LoadBalanceAndSendMail()).start();

		
	}
}
