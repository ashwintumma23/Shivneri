package centralServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.logging.Level;

import common.EmailServiceLogger;

/**
 * 
 * ListenHeartBeats: Thread for listening the heart beats that 
 * underlying email services send
 *
 */
public class ListenHeartBeats implements Runnable {
	private String heartBeatListenPort; 
	
	/**
	 * Constructor which initiates the 
	 */
	public ListenHeartBeats() {
		this.heartBeatListenPort = "10000";
	}
	
	/**
	 * Run method continues to listen the heart beats by receiving the UDP packets
	 */
	public void run(){
		EmailServiceLogger emailServiceLogger = EmailServiceLogger.getInstance(); 
		emailServiceLogger.logger.log(Level.INFO, "[LISTEN_HEART_BEATS] Thread Started to listen from"
				+ " underlying Email Services");
		
		byte [] receiveData;
		try{
			DatagramSocket masterListenSocket = null;
			masterListenSocket = new DatagramSocket(Integer.parseInt(this.heartBeatListenPort));

				while(true){
				
					receiveData= new byte[1024];
					DatagramPacket receiveAliveDataPacket = new DatagramPacket(receiveData, receiveData.length);
					masterListenSocket.receive(receiveAliveDataPacket);
					String messageReceived = new String (receiveAliveDataPacket.getData());
					
					String emailServiceName = messageReceived.trim().split("_")[0];
					
					ServerProcess.emailServicesMap.put(emailServiceName, 
								System.currentTimeMillis());
					
					if(!ServerProcess.emailServicesList.contains(emailServiceName)){
						ServerProcess.emailServicesList.add(emailServiceName);
						emailServiceLogger.logger.log(Level.INFO, 
								emailServiceName + " Mail Service is now being monitored");
					}
				}   
					            
			} 
		catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
