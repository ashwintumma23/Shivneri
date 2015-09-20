package centralServer;

import java.util.Map;
import java.util.logging.Level;

import common.EmailServiceLogger;

/**
 * MonitorHeartBeats: Monitor the heart beats that have been received
 * 
 */
public class MonitorHeartBeats implements Runnable {

	/**
	 * Run continuously (3 seconds interval) to monitor the received UDP packets 
	 */
	public void run() {
		EmailServiceLogger emailServiceLogger = EmailServiceLogger.getInstance(); 
		emailServiceLogger.logger.log(Level.INFO, "[MONITOR_HEART_BEATS] Thread Started to monitor"
				+ " heart beats received from underlying Email Services");
		while(true){
			try{
				for(Map.Entry<String, Long> entry:ServerProcess.emailServicesMap.entrySet()){
					if(System.currentTimeMillis() - entry.getValue() > 3000 ){
						System.out.println(entry.getKey() + " Mail Service has gone down");
						emailServiceLogger.logger.log(Level.WARNING, 
								entry.getKey() + " Mail Service has gone down");
										
						
						// Stop monitoring for the service which has gone down
						ServerProcess.emailServicesMap.remove(entry.getKey());
						ServerProcess.emailServicesList.remove(entry.getKey());
					}
				}
				
				// Keep monitoring for every three seconds
				Thread.sleep(3000);
			}
			catch(InterruptedException ie){
			}			
		}
	}
}
