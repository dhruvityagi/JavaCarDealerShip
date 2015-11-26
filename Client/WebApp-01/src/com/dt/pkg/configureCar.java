package com.dt.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
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
 * Servlet implementation class configureCar
 */
@WebServlet("/configureCar")
public class configureCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public configureCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String carSelected = request.getParameter("model");
//		//Socket clientSocket = new Socket();//(Socket) request.getAttribute("clientSocket");
//		BuildAuto obj = new BuildAuto();
//		//obj = (BuildAuto) request.getAttribute("obj");
//		SocketClientInterface clientObject = new mainClient();
//		//clientObject = (SocketClientInterface) request.getAttribute("clientObject");
//		//Socket clientSocket = clientObject.startClient();
//		//clientObject.clientActivityCarChoice2(clientSocket,obj, carSelected);
//		
//		//Page display
//		response.setContentType("text/html");
//	    PrintWriter out = response.getWriter();
//	    String docType =
//		      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
//		      "Transitional//EN\">\n";
//		out.println(docType +
//				"<html>"+
//				"<head>"+
//				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
//				"<title>Basic Car Choice</title>"+
//				"</head>"+
//				"<body>"+
//				"<hl><font size=\"26\"><b>Basic Car Selection</b></font></hl>"+
//				"<hr/>"+
//				"<form action=\"showSelectedCar\" >"+
//				"<table border=\"1\">"+
//				"<tr>"+
//				"<td>Make/Model</td>"+
//				"<td>"+
//				"<select name=\"makeModel\">"+
//				carSelected +
//				"</select>"+
//				"</td>"+
//				"</tr>"+
//				"<tr>"+
//				"<td>Color</td>"+
//				"<td>"+
//				"<select name=\"Color\">");
//		        int indexOption = 0;
//				while(indexOption < 6) {
//					out.println("<option>");
//					out.println(obj.getA1().get(carSelected).getOptionName1(0, indexOption));
//					out.println("</option>");
//					indexOption++;
//				}
//				out.println("</select>"+
//				"</td>"+
//				"</tr>"+
//				"<tr>"+
//				"<td>Transmission</td>"+
//				"<td>"+
//				"<select name=\"Transmission\">");
//				indexOption = 0;
//				while(indexOption < 2) {
//					out.println("<option>");
//					out.println(obj.getA1().get(carSelected).getOptionName1(1, indexOption));
//					out.println("</option>");
//					indexOption++;
//				}
//				out.println("</select>"+
//				"</td>"+
//				"</tr>"+			
//				"<tr>"+
//				"<td>Brakes/Traction Control</td>"+
//				"<td>"+
//				"<select name=\"brakes\">");
//				indexOption = 0;
//				while(indexOption < 3) {
//					out.println("<option>");
//					out.println(obj.getA1().get(carSelected).getOptionName1(2, indexOption));
//					out.println("</option>");
//					indexOption++;
//				}
//				out.println("</select>"+
//				"</td>"+
//				"</tr>"+
//				"<tr>"+
//				"<td>Side Impact Air Bags</td>"+
//				"<td>"+
//				"<select name=\"AirBags\">");
//				indexOption = 0;
//				while(indexOption < 2) {
//					out.println("<option>");
//					out.println(obj.getA1().get(carSelected).getOptionName1(3, indexOption));
//					out.println("</option>");
//					indexOption++;
//				}
//				out.println("</select>"+
//				"</td>"+
//				"</tr>"+
//				"<tr>"+
//				"<td>Power Moonroof</td>"+
//				"<td>"+
//				"<select name=\"MoonRoof\">");
//				indexOption = 0;
//				while(indexOption < 2) {
//					out.println("<option>");
//					out.println(obj.getA1().get(carSelected).getOptionName1(4, indexOption));
//					out.println("</option>");
//					indexOption++;
//				}
//				out.println("</select>"+
//				"</td>"+
//				"</tr>"+
//				"</table>"+  
//				"<input type=\"submit\" value=\"Done\" />"+
//				"</form>"+
//				"</body>"+
//				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String carSelected = request.getParameter("model");
		
		DefaultSocketClient d = new DefaultSocketClient("127.0.0.1", 7080);
		  ReturnTypeVariety temp = new ReturnTypeVariety();
		  temp = d.start("Select a Car","C_WAIT_CAR_PROP",carSelected);
		
		  if(temp.obj != null) {
			  showSelectedCar s = new showSelectedCar(temp.obj);
		  	  s.selectCarFeatures(response, carSelected);
		  }
	}
	
	public void selectCar(String temp,  HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType =
		      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
		      "Transitional//EN\">\n";
		    out.println(docType +
		    		    "<html>"+
					    "<head>"+
					    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
					    "<title>Basic Car Choice</title>"+
					    "</head>"+
					    "<body>"+
					    "<hl><font size=\"26\"><b>Select Car Model</b></font></hl>"+
					    "<hr/>"+
					    "<form method=\"post\" action=\"configureCar\" >"+
					    "<table border=\"1\">"+
			    		"<tr>"+
			    		"<td>Make/Model</td>"+
			    		"<td>"+ 
			    		"<select name=\"model\">");
		    StringTokenizer st = new StringTokenizer(temp, "\n");
		    while(st.hasMoreTokens()) {
		    	out.println("<option>");
		    	out.println(st.nextToken());
		    	out.println("</option>");
		    }
		    out.println("</option>"+
			    		"</select>"+
			    		"</td>"+
			    		"</tr>"+
			    		"</table>"+
			    		"<input type=\"submit\" value=\"Done\" />"+
			    	    "</form>"+
			    	    "</body>"+
			    	    "</html>"); 
	}
}
