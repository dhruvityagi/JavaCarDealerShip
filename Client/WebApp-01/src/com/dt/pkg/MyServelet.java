package com.dt.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.adapter.BuildAuto;
import client.client.DefaultSocketClient;
import client.client.ReturnTypeVariety;
import client.client.SelectCarOption;
import client.client.SocketClientInterface;
import client.client.mainClient;

/**
 * Servlet implementation class MyServelet
 */
@WebServlet("/MyServelet")
public class MyServelet extends HttpServlet implements Runnable{
	private static final long serialVersionUID = 1L;
	public String id = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServelet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException
    {}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						 throws ServletException, IOException {

	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		id = request.getParameter("CustomerChoice");
		/*MyServelet m1 = new MyServelet();
    	Thread t = new Thread(m1);
    	t.start();*/
		if(id.equalsIgnoreCase("Upload a car configuration")) {
				response.setContentType("text/html");
			    PrintWriter out = response.getWriter();	    
			    String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
			       out.println(docType +
			      "<html>"+
				  "<head>"+
				  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
				  "<title>Car Upload Site</title>"+
				  "</head>"+
				  "<body>"+
				  "<hl><font size=\"18\"><b>Select which car you want to upload</b></font></hl>"+
				  "<hr/>"+
				  "<form method=\"post\" action=\"uploadCar\" >"+
				  "<table border=\"1\">"+
				  "<tr>"+
				  "<td>Select</td>"+
				  "<td>"+
				  "<select name=\"CustomerChoice\">"+
				  "<option>ZTWFord</option>"+
				  "<option>Model XTesla</option>"+
				  "</select>"+
				  "</td>"+
				  "</tr>"+
				  "</table>"+
				  "<input type=\"submit\" value=\"Done\" />"+
				  "</form>"+
				  "</body>"+
				  "</html>");
		}
		else {
		  /*DefaultSocketClient d1 = new DefaultSocketClient("127.0.0.1", 7080);
		  ReturnTypeVariety temp1 = new ReturnTypeVariety();
		  temp1 = d1.start("Upload a car configuration",null,"ZTWFord");*/
		  /*Select a car and configure it*/
		  DefaultSocketClient d = new DefaultSocketClient("127.0.0.1", 7080);
		  ReturnTypeVariety temp = new ReturnTypeVariety();
		  temp = d.start("Select a Car","C_SEND_CAR_LIST",null);
		  if(id.equalsIgnoreCase("Select a Car")) {
			  configureCar c = new configureCar();
			  try {
				c.selectCar(temp.temp, response);
			  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		  }
		  else{ }
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

}
