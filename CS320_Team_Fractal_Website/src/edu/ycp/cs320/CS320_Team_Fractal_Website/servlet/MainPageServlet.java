package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;


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

		FractalController controller = null;
		
		//get parameters based on the fractal that is selected
		
		//Sierpinski
		if(choice == 0){
			try{
				int level = getIntFromParameter(req.getParameter("level"));
				
				if(level == 0){
					errorMessage = "Please specify a valid integer.";
				}
				else{
					Sierpinski sierpinskiModel = new Sierpinski();
					
					sierpinskiModel.setLevel(level);
					
					controller = new SierpinskiController(sierpinskiModel);

					req.setAttribute("level", level);
				}
				
			}catch(NumberFormatException e){
				errorMessage = "Invalid integer.";
			}
			
		}
		//Mandelbrot
		else if(choice == 1){
			try {
				Mandelbrot mandelModel = new Mandelbrot();
				
				double x1 = getDoubleFromParameter(req.getParameter("x1"));
				double y1 = getDoubleFromParameter(req.getParameter("y1"));
				double x2 = getDoubleFromParameter(req.getParameter("x2"));
				double y2 = getDoubleFromParameter(req.getParameter("y2"));
				int multiplyTimes = getIntFromParameter(req.getParameter("multiplyTimes"));
				
				mandelModel.setLocation(new Location(x1, y1, x2, y2));
				mandelModel.setMultiplyTimes(multiplyTimes);

				controller = new MandelbrotController(mandelModel);

				req.setAttribute("x1", x1);
				req.setAttribute("y1", y1);
				req.setAttribute("x2", x2);
				req.setAttribute("y2", y2);
				req.setAttribute("multiplyTimes", multiplyTimes);
				
			}catch(NumberFormatException e){
				errorMessage = "Invalid integer.";
			}
		}
		
		//render the fractal, only if no error occurred
		if(controller != null) result = controller.render();
		
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
