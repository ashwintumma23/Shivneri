package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * EmailServiceLogFormatter: Formatter for Logger 
 *
 */
public class EmailServiceLogFormatter extends Formatter{

	public String format(LogRecord record) {
		Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("[yyyy-M-dd hh:mm:ss a]");
        return ft.format(date) + " [" + record.getLevel() + "] " + record.getMessage() + "\r\n";
	}

}
