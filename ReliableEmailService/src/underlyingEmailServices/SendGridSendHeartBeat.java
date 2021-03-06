package underlyingEmailServices;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * SendGridSendHeartBeat: Class for sending heart beats to Main Server 
 */
public class SendGridSendHeartBeat implements Runnable{
	private DatagramSocket sendHeartBeat;
	public void run(){
		
		/*
		 * Every second, send heart beat to server announcing the health of the server.
		 * If the process for Sendgrid goes down, then the server will detect this aspect, and will
		 * fail over to the other Email Services.
		 */
		while(true){
			try
			{
				sendHeartBeat = new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
			    String aliveNotification = "SENDGRID_HEARTBEAT"; 
			    
			    DatagramPacket sendPacket = new DatagramPacket(aliveNotification.getBytes(), 
			    		aliveNotification.getBytes().length, 
			    		IPAddress, 
			    		Integer.parseInt("10000"));
			    
			    sendHeartBeat.send(sendPacket);
			    System.out.println("I Sent the alive notification: "+aliveNotification);
			    sendHeartBeat.close();
			    // Sleep for two seconds, and again send alive notification to the server
				Thread.sleep(1000);				
			} catch(SocketTimeoutException s) {
				System.out.println("Socket timed out before sending heart beat!");
			} catch(IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
