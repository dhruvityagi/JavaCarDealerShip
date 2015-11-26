package exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/*class AutoException
 * Custom exception class with logging facility*/
public class AutoException extends Exception implements FixAuto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = null;
	/*Constructor*/
	public AutoException() {
		super();
	}
	public AutoException(String message) {
		super(message);
		this.message = message;
	}
	/*Getters and setters*/
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AutoException(int id, int line) {
		super();
		Fix(id, line);
	}
	public enum Error {
		CAR_NAME, CAR_PRICE, PROPERTY_MISSING, OPTION_MISSING,WRONG_OPTION};
		
	/*Fix an exception*/
	public void Fix(int errno, int line) {
		FixConfigFile f1 = new FixConfigFile();
		FixUserInput f2 = new FixUserInput();
		
		switch(errno) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				f1.FixConfig(errno,line);
				break;
			
			case 7:
			case 8:
			case 9:	
			case 10:
				f2.FixUser(errno,0);
				break;
		}
	}
}
