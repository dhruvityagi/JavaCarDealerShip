package exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
/*class FixConfigFile
 * It fixes exceptions raised during the
 * reading from config file.*/
public class FixConfigFile {
	public FixConfigFile() { }
	/*Log the exception raised during config file read*/
	public void FixConfig(int errno, int line) {
		String message="";
		switch(errno) {
			case 0:
				message = "Car name not entered";
				break;
			case 1:
				message = "Car price not entered";
				break;
			case 2:
			case 3:
				message = "Options not entered";
				break;
			case 4:
				message = "Option price entered is not a number";
				break;
			case 5:
				message = "Option name or price not entered";
				break;
			case 6:
				message = "Base price not a number";
				break;
		}
		/*Log the exception*/
		writeToLogFile(message, line);
	}
	
	/*Write to log file*/
	public void writeToLogFile(String message, int line) {
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
			bw.write("\nException @ line: "+ line + " - " + message +".@ ");
			bw.write(reportDate);
			bw.close();
		}
		catch(IOException e1) {
			System.out.println("Write to log file failed.");
		}
	}
}
