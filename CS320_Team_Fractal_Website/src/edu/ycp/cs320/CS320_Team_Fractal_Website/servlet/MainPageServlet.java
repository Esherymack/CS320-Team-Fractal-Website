package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Sierpinski;

public class MainPageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		System.out.println("Main Page Servlet: doGet");
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req,  resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("mainPage Servlet: doPost");
		
		// holds the error message text, if any
		String errorMessage = null;
		
		try 
		{
			int level = getIntFromParameter(req.getParameter("level"));			
			if(level == 0)
			{
				errorMessage = "Please specify an integer.";
			}
			else
			{
				Sierpinski model = new Sierpinski();
				SierpinskiController controller = new SierpinskiController(model);
				try
				{
					controller.buildSierpinski(level);
				}
				catch (InterruptedException e)
				{
					errorMessage = "A thread was interrupted";
				}
			}
		}
		catch (NumberFormatException e)
		{
			errorMessage = "Invalid integer.";
		}
		
		req.setAttribute("level",  req.getParameter("level"));
		
		req.setAttribute("errorMessage",  errorMessage);
		
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req,  resp);
	}
	
	private int getIntFromParameter(String s)
	{
		if (s == null || s.equals(""))
		{
			return 0;
		}
		else
		{
			return Integer.parseInt(s);
		}
	}
}
