package com.dt.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.adapter.BuildAuto;
import client.client.SocketClientInterface;
import client.client.mainClient;

/**
 * Servlet implementation class showSelectedCar
 */
@WebServlet("/showSelectedCar")
public class showSelectedCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BuildAuto obj = new BuildAuto();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showSelectedCar(BuildAuto obj) {
        super();
        this.obj = obj;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void selectCarFeatures(HttpServletResponse response, String carSelected) throws IOException {
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
				"<hl><font size=\"26\"><b>Basic Car Selection</b></font></hl>"+
				"<hr/>"+
				"<form method=\"post\" action=\"FinalDisplay\" >"+
				"<table border=\"1\">"+
				"<tr>"+
				"<td>Make/Model</td>"+
				"<td>"+
				"<select name=\"makeModel\">"+
				"<option>"+
				carSelected+
				"</option>"+
				"</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Color</td>"+
				"<td>"+
				"<select name=\"Color\">");
		        int indexOption = 0;
				while(indexOption < 6) {
					out.println("<option>");
					out.println(obj.getA1().get(carSelected).getOptionName1(0, indexOption));
					out.println("</option>");
					indexOption++;
				}
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Transmission</td>"+
				"<td>"+
				"<select name=\"Transmission\">");
				indexOption = 0;
				while(indexOption < 2) {
					out.println("<option>");
					out.println(obj.getA1().get(carSelected).getOptionName1(1, indexOption));
					out.println("</option>");
					indexOption++;
				}
				out.println("</select>"+
				"</td>"+
				"</tr>"+			
				"<tr>"+
				"<td>Brakes/Traction Control</td>"+
				"<td>"+
				"<select name=\"brakes\">");
				indexOption = 0;
				while(indexOption < 3) {
					out.println("<option>");
					out.println(obj.getA1().get(carSelected).getOptionName1(2, indexOption));
					out.println("</option>");
					indexOption++;
				}
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Side Impact Air Bags</td>"+
				"<td>"+
				"<select name=\"AirBags\">");
				indexOption = 0;
				while(indexOption < 2) {
					out.println("<option>");
					out.println(obj.getA1().get(carSelected).getOptionName1(3, indexOption));
					out.println("</option>");
					indexOption++;
				}
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Power Moonroof</td>"+
				"<td>"+
				"<select name=\"MoonRoof\">");
				indexOption = 0;
				while(indexOption < 2) {
					out.println("<option>");
					out.println(obj.getA1().get(carSelected).getOptionName1(4, indexOption));
					out.println("</option>");
					indexOption++;
				}
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"</table>"+  
				"<input type=\"submit\" value=\"Done\" />"+
				"</form>"+
				"</body>"+
				"</html>");
	}
}
