package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Location;
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
		int choice = Integer.parseInt(req.getParameter("choice"));

		if(choice == 0)
		{
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
					
					model.setLevel(level);
					result = controller.render();
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
			double x1 = getDoubleFromParameter(req.getParameter("x1"));
			double x2 = getDoubleFromParameter(req.getParameter("x2"));
			double y1 = getDoubleFromParameter(req.getParameter("y1"));
			double y2 = getDoubleFromParameter(req.getParameter("y2"));
			//TODO make this use the abstract Fractal and FractalControler classes so that any new fractal can be used from here
			Mandelbrot mandelModel = new Mandelbrot();
			MandelbrotController mandelController = new MandelbrotController(mandelModel);

			mandelModel.setLocation(new Location(x1, y1, x2, y2));
			result = mandelController.render();

			req.setAttribute("x1", x1);
			req.setAttribute("y1", y1);
			req.setAttribute("x2", x2);
			req.setAttribute("y2", y2);
		}

		req.setAttribute("errorMessage",  errorMessage);
		req.setAttribute("result", result);
		req.setAttribute("choice", choice);
		
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req, resp);
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
