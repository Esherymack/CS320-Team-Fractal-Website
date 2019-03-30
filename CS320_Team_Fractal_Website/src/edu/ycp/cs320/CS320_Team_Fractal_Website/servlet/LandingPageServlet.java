package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;

public class LandingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static boolean setDatabase = false;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Landing Page Servlet: doGet");
		
		req.getRequestDispatcher("/_view/landingPage.jsp").forward(req, resp);
		
		if(!setDatabase){
			InitDatabase.initFake();
			setDatabase = true;
		}
	}
	
}
