package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import database.ReadAutoToAutomobile;

import model.Automobile;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.ProxyAutomobile;

public class ServerAuto {
	public String state = "Empty";
	
	/*Constructor*/
	public ServerAuto(){	}
	/*All server activities are listed here according to  protocol*/
	public void serverActivity(ObjectInputStream ois, ObjectOutputStream oos, BuildAuto buildAutoObj) {
			String temp = readFromClient(ois, oos, buildAutoObj, "EMPTY");
			//System.out.println("Read from client");
			/*Send car list to client*/
			if(temp.equalsIgnoreCase("C_SEND_CAR_LIST")) {
				String it1 = "";
				for(String it : ProxyAutomobile.getA1().keySet()) {
					it1 = it1 + it+"\n";
				}
				sendToClient(it1, oos);
				//readFromClient(ss, s, buildAutoObj, "S_WAIT_CAR_NAME");
				state = "EMPTY";
			}
			else if(temp.contains("CAR_SELECTED:")) {
				/*Send automobile object for car selected by client*/
				Automobile a1 = new Automobile();
				Automobile a1d = new Automobile();
				ReadAutoToAutomobile r = new ReadAutoToAutomobile();
				StringTokenizer st = new StringTokenizer(temp,":");
				st.nextToken();
				if(st.nextToken().equalsIgnoreCase("ZTWFord")) {
					a1 = ProxyAutomobile.getA1().get("ZTWFord");
					//a1d = r.readAutomobile("'ZTWFord'", a1d);
				}
				else {
					a1 = ProxyAutomobile.getA1().get("Model XTesla");
					//a1d = r.readAutomobile("'Model XTesla'", a1);
				}
				sendToClient(a1,oos);
				state = "EMPTY";
			}
			else if(temp.equalsIgnoreCase("C_SEND_CAR_INI")) {
				/*Read configuration file sent by client*/
				readFromClient(ois, oos, buildAutoObj, "S_WAIT_CAR_INI");
				state = "EMPTY";
			}
			else {
				state = "EMPTY";
				System.exit(1);
			}
		}
	/*Receive from client the data and deserialize according to 
	 * object received*/
	public String readFromClient(ObjectInputStream ois, ObjectOutputStream oos, 
								 BuildAuto buildAutoObj, String action) {
		String readSuccessful = "";
		
			try {
				if(action.equalsIgnoreCase("EMPTY")) {
					String to = (String)ois.readObject();
					if (to!=null && (to.equalsIgnoreCase("C_SEND_CAR_LIST")) || 
									(to.equalsIgnoreCase("C_SEND_CAR_INI"))){
						return to;
					}
					else if(to!=null && (to.contains("CAR_SELECTED:"))){
						return to;
					}
				}
				else if(action.equalsIgnoreCase("S_WAIT_CAR_INI")) {
					Properties to = (Properties)ois.readObject();
					if (to!=null){
						buildFromProp(to,buildAutoObj);
						ReadAutoToAutomobile r = new ReadAutoToAutomobile();
						r.parsePropertiesObject(to);
						sendToClient("S_ACK_CAR_INI", oos);
					}
					else {}
				}
		}catch(Exception e){
			System.out.println(e);
		}
		
		return readSuccessful;
	}
	
	/*Serialize string object and send to client*/
	public void sendToClient(String s, ObjectOutputStream oos) {
		try{
				oos.writeObject(s);
				oos.flush();
			}catch(Exception e){System.out.println(e);}
			//System.out.println("Data sent to server");
	}
	/*Serialize automobile object and send to client*/
	public void sendToClient(Automobile s, ObjectOutputStream oos) {
		try{
				oos.writeObject(s);
				//System.out.println(oos);
				oos.flush();
			}catch(Exception e){System.out.println(e);}
	}
	/*Build automobile object from properties file and add to 
	 * build auto object.*/
	public void buildFromProp(Properties props, BuildAuto obj) {        
        BuildCarModelOptions buildObject = new BuildCarModelOptions();
        Automobile a1 = buildObject.createAutomobileFromProp(props, obj);
        Automobile a1d = buildObject.createAutomobileFromProp(props, obj);
        buildObject.addAutoMap(obj, a1);
        obj.printAuto(a1.getModel(), a1.getMake());
	}
}
