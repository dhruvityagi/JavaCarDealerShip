package client.driver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.servlet.ServletException;

import com.dt.pkg.MyServelet;

import client.client.DefaultSocketClient;
import client.client.SelectCarOption;
import client.client.SocketClientInterface;
import client.client.mainClient;

import client.adapter.BuildAuto;

/*Driver class
 * Contains main*/
public class Driver {
	public static void main(String args[]) {
		
//		MyServelet serv = new MyServelet();	
//		CustomerChoiceInit choice = new CustomerChoiceInit();
//		try {
//			java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost:8080/WebApp-01/CustomerChoiceInit"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String strLocalHost = "";
//		  try{
//		      strLocalHost = 
//		        InetAddress.getLocalHost().getHostName();
//		  }
//		 catch (UnknownHostException e){
//		      System.err.println ("Unable to find local host");
//		 }
//		  DefaultSocketClient d = new DefaultSocketClient(strLocalHost, 7080);
//		  //d.start();
//
//		while(true) {
//			d.start(null);
//		}
			/*if(selectObject.overActivitySet == true) {
				System.out.println("Enter your choice with number in bracket:");
				System.out.println("	-> Choose a Car(0)");
				System.out.println("	-> Load configuation file of a car(1)");
				Scanner in = new Scanner(System.in);
				s = in.nextLine();
				selectObject.overActivitySet = false;
			}*/
		//}
		
//		EditOptions e1 = new EditOptions();
//		EditOptions e2 = new EditOptions();
//				
//		/*Set the values*/
//		e1.editOptionAC("Ford","ZTW","Color", "Fort Knox Gold Clearcoat Metallic",
//						"Metallic Red");
//		e2.editOptionAC("Ford","ZTW","Color", "Fort Knox Gold Clearcoat Metallic",
//						"Metallic Green");
//		/*Start the threads*/
//		e1.start();
//		e2.start();
//		/*boolean e1IsAlive = true;
//		boolean e2IsAlive = true;
//		do {
//			if(e1IsAlive && e1.isAlive()) {
//				e1IsAlive = false;
//				System.out.println("e1 is dead");
//			}
//			else {
//				System.out.println("e1 is alive");
//			}
//			if(e2IsAlive && e2.isAlive()) {
//				e2IsAlive = false;
//				System.out.println("e2 is dead");
//			}
//			else {
//				System.out.println("e2 is alive");
//			}	
//		}while (e1IsAlive || e1IsAlive);*/
	}
}
