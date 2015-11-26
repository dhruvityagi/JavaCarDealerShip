package client.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import client.adapter.BuildAuto;
/*Default client socket*/
public class DefaultSocketClient implements DefaultClientInterface, DefaultClientInterfaceConst{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
	private Socket clientSocket;
	private String strHost;
	
	private int iPort;
	
	public DefaultSocketClient(String strHost, int iPort) {       
        setiPort (iPort);
        setStrHost (strHost);
	}//constructor
	
	public ReturnTypeVariety start(String id, String state, String car){
       if (openConnection()){
    	   ReturnTypeVariety temp  = handleSession(id, state, car);
          closeSession();
          return temp;
       }
       else {}
       return null;
    }//run
	/*Open connection*/
	public boolean openConnection(){
	   try {
		   clientSocket = new Socket(strHost, iPort);
		   System.out.println("Connection opened");
	   }
	   catch(IOException socketError){
	     System.err.println("Unable to connect to " + strHost);
	     return false;
	   }
	   try {
		   oos = new ObjectOutputStream(clientSocket.getOutputStream());
		   oos.flush();
		   ois = new ObjectInputStream(clientSocket.getInputStream());
	   }
	  catch (Exception e){
	     System.err.println("Unable to obtain stream to/from " + strHost);
	     return false;
	  }
	  return true;
	}
	/*Handle session*/
	public ReturnTypeVariety handleSession(String id, String state, String car){
		BuildAuto obj = new BuildAuto();
		SelectCarOption selectObject = new SelectCarOption();
		SocketClientInterface clientObject = new mainClient();
				
		if(id.equalsIgnoreCase("Select a car")) {
			ReturnTypeVariety temp = clientObject.clientActivityCarChoice(ois,oos, obj, 
																		  selectObject, state, car);
			return temp;
		}
		else if(id.equalsIgnoreCase("Upload a car configuration")){
			clientObject.clientActivityCarINI(ois,oos, selectObject,car);
			return null;
		}
		return null;
	}        
	/*close session*/
	public void closeSession(){
	   try {
	      ois = null;
	      oos = null;
	      clientSocket.close();
	      System.out.println("Connection closed");
	   }
	   catch (IOException e){
		   System.err.println("Error closing socket to " + strHost);
	   }       
	}

	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public String getStrHost() {
		return strHost;
	}

	public void setStrHost(String strHost) {
		this.strHost = strHost;
	}

	public int getiPort() {
		return iPort;
	}

	public void setiPort(int iPort) {
		this.iPort = iPort;
	}

}
