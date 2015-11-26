package com.dt.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalDisplay
 */
@WebServlet("/FinalDisplay")
public class FinalDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalDisplay() {
        super();
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
		String make = request.getParameter("makeModel");
		String Color = request.getParameter("Color");
		String Transmission = request.getParameter("Transmission");
		String brakes = request.getParameter("brakes");
		String AirBags = request.getParameter("AirBags");
		String MoonRoof = request.getParameter("MoonRoof");
		
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
				"<hl><font size=\"26\"><b>Final Selected Car</b></font></hl>"+
				"<hr/>"+
				"<form action=\"showSelectedCar\" >"+
				"<table border=\"1\">"+
				"<tr>"+
				"<td>Make/Model</td>"+
				"<td>"+
				"<select name=\"makeModel\">"+
				"<option>"+make+"</option>"+
				"</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Color</td>"+
				"<td>"+
				"<select name=\"Color\">");
				out.println("<option>"+Color+"</option>");
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Transmission</td>"+
				"<td>"+
				"<select name=\"Transmission\">");
				out.println("<option>"+Transmission+"</option>");
				out.println("</select>"+
				"</td>"+
				"</tr>"+			
				"<tr>"+
				"<td>Brakes/Traction Control</td>"+
				"<td>"+
				"<select name=\"brakes\">");
				out.println("<option>"+brakes+"</option>");
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Side Impact Air Bags</td>"+
				"<td>"+
				"<select name=\"AirBags\">");
				out.println("<option>"+AirBags+"</option>");
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"<tr>"+
				"<td>Power Moonroof</td>"+
				"<td>"+
				"<select name=\"MoonRoof\">");
				out.println("<option>"+MoonRoof+"</option>");
				out.println("</select>"+
				"</td>"+
				"</tr>"+
				"</table>"+  
				"</form>"+
				"</body>"+
				"</html>");
	}

}
