package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import adapter.BuildAuto;

/*Class implementing socket server methods*/
public class DefaultSocketServer implements DefaultServerInterface{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
	private Socket serverSocket;
	private static ServerSocket ss;
		
	public DefaultSocketServer() {       
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            ss = new ServerSocket(7080, 10, inetAddress);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7080.");
            e.printStackTrace();
            System.exit(1);
        }
	}//constructor
	
	public void start(){
       if (openConnection()){
    	  handleSession();
          closeSession();
       }
       else {}
    }//run
	/*Establish connection and stream*/
	public boolean openConnection(){
        try {
        	//System.out.println("Waitin for Connection");
            serverSocket = ss.accept();
            //System.out.println("Connection Accepted");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
 	   try {
 		   oos = new ObjectOutputStream(serverSocket.getOutputStream());
 		   oos.flush();
 		   ois = new ObjectInputStream(serverSocket.getInputStream());
 	   }
 	  catch (Exception e){
 	     System.err.println("Unable to obtain stream");
 	     return false;
 	  }
 	  return true;
	}
	
	/*Handle a session*/
	public void handleSession(){
		BuildAuto obj = new BuildAuto();
		ServerAuto s = new ServerAuto();
		s.serverActivity(ois, oos, obj);
	}        
	
	/*close as session*/
	public void closeSession(){
	   try {
	      //ois = null;
	      //oos = null;
	      serverSocket.close();
	   }
	   catch (IOException e){
		   System.err.println("Error closing socket to");
	   }       
	}
	
	public Socket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(Socket clientSocket) {
		this.serverSocket = clientSocket;
	}
}
