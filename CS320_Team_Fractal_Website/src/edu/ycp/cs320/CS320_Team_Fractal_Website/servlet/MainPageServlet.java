package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Mandelbrot;
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
		boolean result = false;
		int choice = getIntFromParameter(req.getParameter("choice"));	
		
		if(choice == 0)
		{
			System.out.println(choice);
			int level = getIntFromParameter(req.getParameter("level"));	
			try 
			{	
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
						result = controller.buildSierpinski(level);
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
		}
		
		else if(choice == 1)
		{
			System.out.println(choice);
			double x1 = getDoubleFromParameter(req.getParameter("x1"));
			double x2 = getDoubleFromParameter(req.getParameter("x2"));
			double y1 = getDoubleFromParameter(req.getParameter("y1"));
			double y2 = getDoubleFromParameter(req.getParameter("y2"));
			try
			{	
				Mandelbrot mandelModel = new Mandelbrot();
				MandelbrotController mandelController = new MandelbrotController(mandelModel);
				try 
				{
					mandelController.setCoords(x1, y1, x2, y2);
					result = mandelController.buildMandelbrot();
				}
				catch(InterruptedException e)
				{
					errorMessage = "A thread was interrupted.";
				}
			}
			catch(NumberFormatException e)
			{
				errorMessage = "Invalid value.";
			}
			
			req.setAttribute("x1",  req.getParameter("x1"));
			req.setAttribute("x2",  req.getParameter("x2"));
			req.setAttribute("y1",  req.getParameter("y1"));
			req.setAttribute("y2",  req.getParameter("y2"));	
		}
	
		req.setAttribute("errorMessage",  errorMessage);
		req.setAttribute("result", result);
		
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
	
	private double getDoubleFromParameter(String s)
	{
		return Double.parseDouble(s);
	}
}