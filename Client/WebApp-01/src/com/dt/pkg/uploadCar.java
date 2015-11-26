package com.dt.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.client.DefaultSocketClient;
import client.client.ReturnTypeVariety;

/**
 * Servlet implementation class uploadCar
 */
@WebServlet("/uploadCar")
public class uploadCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadCar() {
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
		  String car = request.getParameter("CustomerChoice");
		  DefaultSocketClient d1 = new DefaultSocketClient("127.0.0.1", 7080);
		  ReturnTypeVariety temp1 = new ReturnTypeVariety();
		  temp1 = d1.start("Upload a car configuration",null,car);
		  
		  RequestDispatcher rd = request.getRequestDispatcher("Reload");
		  rd.forward(request,response);
	}

}
