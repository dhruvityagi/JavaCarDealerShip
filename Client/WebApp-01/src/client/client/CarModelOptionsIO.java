package client.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import client.adapter.BuildAuto;
import model.Automobile;

public abstract class CarModelOptionsIO {
	private String state = "Empty";
	private String temp = "";
	public Automobile a1 = new Automobile();
	
	/*Constructor*/
	public CarModelOptionsIO(){}
	
	public ReturnTypeVariety clientActivityCarChoice(ObjectInputStream ois, ObjectOutputStream oos, 
			BuildAuto obj, SelectCarOption selectObject, String state, String car) {
		
		ReturnTypeVariety r = new ReturnTypeVariety();
		if(state.equalsIgnoreCase("C_SEND_CAR_LIST")) {
			r.temp = clientActivityCarChoice1(ois,oos,obj, selectObject);
			return r;
		}
		else if(state.equalsIgnoreCase("C_WAIT_CAR_PROP")) {
			clientActivityCarChoice2(ois,oos,obj, selectObject, car);
			r.obj = obj;
			return r;
		}
		return null;
	}
	/*Client activity when a car is to be chosen and choice is to be made
	 * from the options*/
	public String clientActivityCarChoice1(ObjectInputStream ois, ObjectOutputStream oos, 
										BuildAuto obj, SelectCarOption selectObject) {
		
		/*Send request to server to send car list*/
		sendDataToServer("C_SEND_CAR_LIST", oos);
		/*Read car list sent by server*/
		temp = readFromServer("C_WAIT_CAR_LIST", ois, oos, null);

		return temp;	
	}
	
	public void clientActivityCarChoice2(ObjectInputStream ois, ObjectOutputStream oos, 
										   BuildAuto obj, SelectCarOption selectObject, String carSelected) {
		sendDataToServer("CAR_SELECTED:"+carSelected, oos);
		/*Read the car features sent by server*/
		readFromServer("C_WAIT_CAR_PROP", ois, oos, obj);
	}
	
	
	/*Client activity when car config file is to be uploaded on server*/
	public void clientActivityCarINI(ObjectInputStream ois, ObjectOutputStream oos, SelectCarOption selectObject, String car) {
		if(state.equalsIgnoreCase("EMPTY")) {
			/*Send to server that you are sending car config file*/
			sendDataToServer("C_SEND_CAR_INI", oos);
			Properties pros = new Properties();
			if(car.equalsIgnoreCase("ZTWFord"))
				pros = readPropertyFile(selectObject, "/home/dtyagi/Documents/Assg/Project1Unit5/WebApp-01/WebContent/WEB-INF/FordProperties.txt");
			else
				pros = readPropertyFile(selectObject, "/home/dtyagi/Documents/Assg/Project1Unit5/WebApp-01/WebContent/WEB-INF/TeslaProperties.txt");
			sendDataToServer(pros, oos);
			readFromServer("C_WAIT_ACK_INI", ois, oos, null);
			
			state = "EMPTY";
			selectObject.overActivitySet = true;
		}
		else {
			/*Unknown state*/
			System.out.println("Reset Program");
			state = "empty";
			System.exit(0);
		}
	}
	/*Read from server*/
	public String readFromServer(String action, ObjectInputStream ois, ObjectOutputStream oos, BuildAuto obj) {
		String to="";
		String s="0";
		
		try {
			if(action.equalsIgnoreCase("C_WAIT_CAR_PROP")) {
				/*Server sends properties of a selected car*/
				//System.out.println(ois);
				//a1 = (Automobile) (new Automobile());
				a1 = (Automobile) ois.readObject();
				//String abc = (String) ois.readObject();
				if(a1!=null)
					obj.addToMap(obj, a1);
			}
			else {
				/*Receiving strings from server*/
				to = (String) ois.readObject();
				if (to!=null && action.equalsIgnoreCase("C_WAIT_ACK_INI")){
					/*Acknowledgment for car config file from server*/
					if(to.equalsIgnoreCase("S_ACK_CAR_INI"))
						System.out.println("Car ini loaded to server");
					else
						System.out.println("Car ini not loaded to server");
				}
				else if (to!=null && action.equalsIgnoreCase("C_WAIT_CAR_LIST")){
					/*List of car models sent at server*/
					return to;
				}
				else{}
			}	
		} catch(Exception e){System.out.println(e);}
		return s;
	}
	/*Read a properties file and build an automobile object*/
	public Properties readPropertyFile(SelectCarOption selectObject, String filename) {
		Properties prop = new Properties();
		FileInputStream in = null;
		//System.out.println(filename);
		try {
			in = new FileInputStream(filename);
			try {
				prop.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				selectObject.overActivitySet = true;
				state = "Empty";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			selectObject.overActivitySet = true;
			state = "Empty";
		}
	
		return prop;
	}
	/*Send Property file to server*/
	public void sendDataToServer(Properties pros, ObjectOutputStream oos) {
		try{
				oos.writeObject(pros);
				oos.flush();
			}catch(Exception e){System.out.println(e);}
			//System.out.println("Data sent to server");
	}
	/*Send String file to server*/
	public void sendDataToServer(String pros, ObjectOutputStream oos) {
			
		try{
				oos.writeObject(pros);
				oos.flush();
			}catch(Exception e){
				System.out.println(e);
			}
			System.out.println("Data sent to server");
	}
	/*Build an automobile object from a property file*/
	public Automobile parsePropertiesObject(Properties props, Automobile a1) {
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
		return a1;
	}
}
