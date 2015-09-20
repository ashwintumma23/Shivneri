package client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;

import common.EmailTemplate;

/** 
 * ClientProcess: Read Email(s) from File and send mails
 *
 */
public class ClientProcess {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Please specify the name of Email file");
			System.out.println("Usage: java client.ClientProcess <Path of Email File>");
			return;
		}

		String fileName = args[0].trim();
		try {
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(fileName));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String splittedLine[] = line.split(";");
				String to = splittedLine[0].trim();
				String from = splittedLine[1].trim();
				String subject = splittedLine[2].trim();
				String body = splittedLine[3].trim();
				
				DatagramSocket sendClientSocket;
				
				EmailTemplate et = new EmailTemplate(to,from,subject,body);
				sendClientSocket = new DatagramSocket();
		
				byte[] dataToSend = new byte[1024];
				
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        ObjectOutputStream os = new ObjectOutputStream(outputStream);
		        os.writeObject(et);
		        dataToSend = outputStream.toByteArray();
		        DatagramPacket sendPacket=null;
				sendPacket = new DatagramPacket(dataToSend, dataToSend.length, 
						InetAddress.getByName("127.0.0.1"),11000);
			
		        sendClientSocket.send(sendPacket);
		        sendClientSocket.close();
		        System.out.println("Email Sent");
		        Thread.sleep(4000);
				} 
			reader.close();
			}catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}		
}
