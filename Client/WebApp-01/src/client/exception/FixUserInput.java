package client.exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
/*class FixUserInput
 * It fixes exceptions raised during the
 * reading from user for choice.*/
public class FixUserInput {
	
	public FixUserInput() { }
	/*Log the exception raised during reading from user*/
	public void FixUser(int errno, int line) {
		String message="";
		switch(errno) {
			case 7:
				message = "Option name or price not entered";
				break;
			case 8:
				message = "Choice selection aborted-Color number outside range";
				break;
			case 9:
				message = "Choice selection aborted-Transmission number outside range";
				break;
			case 10:
				message = "Choice selection aborted-Brakes/Traction Controls number outside range";
				break;
			case 11:
				message = "Choice selection aborted-Side Impact Air Bags number outside range";
				break;
			case 12:
				message = "Choice selection aborted-Power Moonroof number outside range";
				break;
		}
		writeToLogFile(message);
	}
	/*Write to log file*/
	public void writeToLogFile(String message) {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = new Date(System.currentTimeMillis());
	     String reportDate = df.format(today);
		try {
			File file = new File("log.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			else {/*Do nothing*/}
			FileWriter fw = new FileWriter(file.getName(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\nException - " + message +".@ ");
			bw.write(reportDate);
			bw.close();
		}
		catch(IOException e1) {
			System.out.println("Write to log file failed.");
		}
	}
}
