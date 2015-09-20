package common;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  EmailServiceLogger: Customized Logger
 *  
 */
public class EmailServiceLogger{
	public static final Logger logger = Logger.getLogger("Test");
	private static EmailServiceLogger instance = null;

	 public static EmailServiceLogger getInstance() {
	    if(instance == null) {
	        prepareLogger();
	        instance = new EmailServiceLogger();
	    }
	    return instance;
	 }

	 private static void prepareLogger() {
			 try {
			    FileHandler myFileHandler = new FileHandler("./logs/EmailService.txt", false);
			    myFileHandler.setFormatter(new EmailServiceLogFormatter());
			    logger.addHandler(myFileHandler);
			    logger.setUseParentHandlers(false);
			    logger.setLevel(Level.ALL);
			 } catch (Exception e) {
			 }
		 }
	 
}
