package com.dt.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reload
 */
@WebServlet("/Reload")
public class Reload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reload() {
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
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();	    
		    String docType =
		      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
		      "Transitional//EN\">\n";
		       out.println(docType +
		      "<html>"+
			  "<head>"+
			  "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
			  "<title>Car Config Site</title>"+
			  "</head>"+
			  "<body>"+
			  "<hl><font size=\"18\"><b>Select whether you want to select a car or upload a car configuration</b></font></hl>"+
			  "<hr/>"+
			  "<form method=\"post\" action=\"MyServelet\" >"+
			  "<table border=\"1\">"+
			  "<tr>"+
			  "<td>Select</td>"+
			  "<td>"+
			  "<select name=\"CustomerChoice\">"+
			  "<option>Select a car</option>"+
			  "<option>Upload a car configuration</option>"+
			  "</select>"+
			  "</td>"+
			  "</tr>"+
			  "</table>"+
			  "<INPUT TYPE=hidden NAME=\"inital\" VALUE=\"no\">"+
			  "<input type=\"submit\" value=\"Done\" />"+
			  "</form>"+
			  "</body>"+
			  "</html>");
	}

}
