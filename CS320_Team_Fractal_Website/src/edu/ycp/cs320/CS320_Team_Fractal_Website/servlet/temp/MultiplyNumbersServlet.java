package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.temp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.temp.NumbersController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.temp.Numbers;

public class MultiplyNumbersServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("MultiplyNumbers Servlet: doGet");
		
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("MultiplyNumbers Servlet: doPost");
		
		// holds the error message text
		String errorMessage = null;
		// result of calculation
		Double result = null;
		
		// decode the POSTed form params and dispatch to controller
		try
		{
			Double first = getDoubleFromParameter(req.getParameter("first"));
			Double second = getDoubleFromParameter(req.getParameter("second"));
			
			// check for errors
			if(first == null || second == null)
			{
				errorMessage = "Please specify two numbers.";
			}
			// otherwise the data is good 
			else
			{
				Numbers model = new Numbers();
				NumbersController controller = new NumbersController(model);
				result = controller.mult(first, second);
			}
		}
		catch (NumberFormatException e)
		{
			errorMessage = "Invalid double.";
		}
		
		req.setAttribute("first",  req.getParameter("first"));
		req.setAttribute("second",  req.getParameter("second"));
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("result",  result);
		
		req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
	}
	
	private Double getDoubleFromParameter(String s)
	{
		if(s == null || s.equals(""))
		{
			return null;
		}
		else
		{
			return Double.parseDouble(s);
		}
	}
}