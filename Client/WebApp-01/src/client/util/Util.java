package client.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import client.exception.AutoException;
import model.Automobile;
import model.OptionSet;
import model.Options;

/*class Util
 * Reads data from file and populates
 * the automotive object with data from
 * the input file*/
public class Util {
	/*selects the function to be called depending on the file provided at input*/
	public Automobile buildAutoObject(String filename, Automobile a1) {
		try {
			 FileReader file = new FileReader(filename);
			 BufferedReader buff = new BufferedReader(file);
			 String line;
			 
			 line = buff.readLine();
			 if(line != null) {
				 StringTokenizer st = new StringTokenizer(line,"=");
				 String temp = st.nextToken();
				 if(st.hasMoreTokens() && st.nextToken().equalsIgnoreCase("Properties"))
					 a1 = parsePropertiesFile(filename, a1);
				 else
					 a1 = buildAutoObjectText(filename, a1); 
			 }
			 else {/*Do nothing*/}
			 buff.close();
		  }	catch(IOException e1) {
		   System.out.println("Exception:IO Reading Error");
		  } 
		return a1;
	}
	
	/*Reads line from the text and updates the Automotive array.*/
	 public Automobile buildAutoObjectText(String filename, Automobile a1) {
		 boolean eof = false;
		 int index = 0;
		 
		 try {
			 FileReader file = new FileReader(filename);
			 BufferedReader buff = new BufferedReader(file);
			 String line;
			 while(!eof) {
				 line = buff.readLine();
				 if(line != null) {
					 try {
						 readToAutomotive(line, a1, index);
					 } catch(AutoException e2) { } 
					 index++;
				 }
				 else {
					 eof = true;
				 }
			 }
			 buff.close();
		  }	catch(IOException e1) {
		   System.out.println("Exception:IO Reading Error");
		  } 
                  
		  return a1;
	 }
	/*Parses the line read and updates the corresponding fields
	 *in the optionSet*/	
	public void readToAutomotive(String line, Automobile a1, int index)  throws AutoException{
		StringTokenizer st = new StringTokenizer(line,":");
		String carName;
		String name;
		String temp;
		float price;
		
		/*Set the car make*/
		if(index == 0) {
			if(st.hasMoreTokens())
				carName = st.nextToken();
			else
				carName = "";
			
			if(carName.equalsIgnoreCase("Make")) {
				if(st.hasMoreTokens())
					carName = st.nextToken();
				else {
					carName = "Empty";
					System.out.println("Car make is empty");
					throw new AutoException(0,index + 1);
				}
					
			}
			else {
				carName = "Empty";
				System.out.println("Car make is empty");
				throw new AutoException(0,index + 1);
			}
			a1.setMake(carName);
		}
		/*Set the car model*/
		if(index == 1) {
			if(st.hasMoreTokens())
				carName = st.nextToken();
			else
				carName = "";
			
			if(carName.equalsIgnoreCase("Model")) {
				if(st.hasMoreTokens())
					carName = st.nextToken();
				else {
					carName = "Empty";
					System.out.println("Car model is empty");
					throw new AutoException(0,index + 1);
				}
					
			}
			else {
				carName = "Empty";
				System.out.println("Car model is empty");
				throw new AutoException(0,index + 1);
			}
			a1.setModel(carName);
		}
		/*Set base price*/
		else if(index == 2) {
			if(st.hasMoreTokens())
				carName = st.nextToken();
			else
				carName = "";
			
			if(carName.equalsIgnoreCase("Base Price")) {
				if(st.hasMoreTokens()){
					try {
						price = Float.parseFloat(st.nextToken());
					}
					catch(NumberFormatException e4) {
						price = 0;
						throw new AutoException(6,index + 1);
					}
				}
				else {
					price = 0;
					System.out.println("Base price is empty");
					throw new AutoException(1,index + 1);
				}
			}
			else {
				price = 0;
				System.out.println("Base price is empty");
				throw new AutoException(1,index + 1);
			}
			a1.setBasePrice(price);
		}
		/*Other options*/
		else {	
			/*Set the optionSet*/
			if(st.hasMoreTokens()) {
				name = st.nextToken();
				if(name.equals("")) {
					name = "Empty";
					throw new AutoException(2,index + 1);
				}
				else {/*Do nothing*/}
			}
			else {
				name = "Empty";
				throw new AutoException(2,index + 1);
			}
			if(st.hasMoreTokens())	{
				temp = st.nextToken();
				if(temp.equals("")) {
					temp = "Empty";
					throw new AutoException(3,index + 1);
				}
				else {/*Do nothing*/}
			}
			else {
				temp = "Empty";
				throw new AutoException(3,index + 1);
			}
			a1.setOptionSetDetail(name, index, temp);
		}
	}
	/*Read user choice from console*/
	public void readChoice(Automobile a, int indexOpset) throws AutoException {
		Scanner in = new Scanner(System.in);
		String s;
		int index;

 		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Enter Choice:");
		
		System.out.println("Enter " + a.getOptionSetName(indexOpset) +" Number from bracket:");
		index = 0;
        for(index = 0; index < a.getOptionSetOption(indexOpset).size(); index++) {
        	if(a.getOption(indexOpset, index) != null) 
        		System.out.println(a.getOptionName1(indexOpset, index) + "(" + index + ")");
        	else
        		break;
        	//index++;
        }
        s = in.nextLine();
        if(Integer.parseInt(s) > (index - 1) || Integer.parseInt(s) < 0) {
        	a.setChoiceProb(indexOpset,"Empty");
        	throw new AutoException(indexOpset + 7,0);
        }
        else
        	a.setChoice(indexOpset, Integer.parseInt(s));
	        
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	}
	
	public Automobile parsePropertiesFile(String filename, Automobile a1) {
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(filename);
			props.load(in);
			
			a1.setMake(props.getProperty("CarMake"));
			if(!a1.getMake().equals(null)) {
				a1.setModel(props.getProperty("CarModel"));
				a1.setBasePrice(Float.parseFloat(props.getProperty("BasePrice")));
				/*Option Color*/
				a1.addOptionSet();
				a1.setOptionSetName(props.getProperty("Option1"), 0);
				if(props.getProperty("Option1").equals(null)) {}
				else {
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1a"), 0, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1a")), 0, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1b"), 1, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1b")), 1, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1c"), 2, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1c")), 2, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1d"), 3, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1d")), 3, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1e"), 4, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1e")), 4, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1f"), 5, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1f")), 5, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1g"), 6, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1g")), 6, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1h"), 7, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1h")), 7, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1i"), 8, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1i")), 8, 0);
					a1.addOption(0);
					a1.setOptionName(props.getProperty("OptionValue1j"), 9, 0);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice1j")), 9, 0);
				}
				/*Option Transmission*/
				a1.addOptionSet();
				a1.setOptionSetName(props.getProperty("Option2"), 1);
				if(props.getProperty("Option2").equals(null)) {}
				else {
					a1.addOption(1);
					a1.setOptionName(props.getProperty("OptionValue2a"), 0, 1);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice2a")), 0, 1);
					a1.addOption(1);
					a1.setOptionName(props.getProperty("OptionValue2b"), 1, 1);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice2b")), 1, 1);
				}
				/*Brakes*/
				a1.addOptionSet();
				a1.setOptionSetName(props.getProperty("Option3"), 2);
				if(props.getProperty("Option3").equals(null)) {}
				else {
					a1.addOption(2);
					a1.setOptionName(props.getProperty("OptionValue3a"), 0, 2);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3a")), 0, 2);
					a1.addOption(2);
					a1.setOptionName(props.getProperty("OptionValue3b"), 1, 2);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3b")), 1, 2);
					a1.addOption(2);
					a1.setOptionName(props.getProperty("OptionValue3c"), 2, 2);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice3c")), 2, 2);
				}
				/*Side Impact*/
				a1.addOptionSet();
				a1.setOptionSetName(props.getProperty("Option4"), 3);
				if(props.getProperty("Option4").equals(null)) {}
				else {
					a1.addOption(3);
					a1.setOptionName(props.getProperty("OptionValue4a"), 0, 3);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice4a")), 0, 3);
					a1.addOption(3);
					a1.setOptionName(props.getProperty("OptionValue4b"), 1, 3);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice4b")), 1, 3);
				}
				/*Power Moonroof*/
				a1.addOptionSet();
				a1.setOptionSetName(props.getProperty("Option5"), 4);
				if(props.getProperty("Option5").equals(null)) {}
				else {
					a1.addOption(4);
					a1.setOptionName(props.getProperty("OptionValue5a"), 0, 4);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice5a")), 0, 4);
					a1.addOption(4);
					a1.setOptionName(props.getProperty("OptionValue5b"), 1, 4);
					a1.setOptionPrice(Float.parseFloat(props.getProperty("OptionPrice5b")), 1, 4);
				}
			}
			else {/*Do nothing*/}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a1;
	}
	
}