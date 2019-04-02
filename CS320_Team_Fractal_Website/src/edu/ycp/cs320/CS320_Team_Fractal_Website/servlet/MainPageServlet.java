package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;


public class MainPageServlet extends HttpServlet{
	
	/**
	 * The number of parameters used by the website
	 */
	public static final int NUM_PARAMS = 10;

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
		//contains fractal info
		String fractalInfo = null;
		boolean result = false;
		
		int choice = -1;
		try {
			choice = Integer.parseInt(req.getParameter("choice"));
		}catch(NumberFormatException e){
			errorMessage = "Please select a fractal";
		}

		FractalController controller = null;
		
		//get all parameters
		String[] params = new String[NUM_PARAMS];
		for(int i = 0; i < NUM_PARAMS; i++){
			params[i] = req.getParameter("param" + i);
		}
		
		//select the correct controller and model to use
		
		//Sierpinski
		if(choice == 0){
			Sierpinski sierpinskiModel = new Sierpinski();
			controller = new SierpinskiController(sierpinskiModel);
			
			//contains fractal info
			fractalInfo = "The Sierpinski triangle, also called the Sierpinski gasket or the Sierpinski sieve, is a "
					+ "fractal and attractive fixed set with the overall shape of an equilateral triangle, subdivided "
					+ "recursively into smaller equilateral triangles. ";
		}
		
		//Mandelbrot
		else if(choice == 1){
			Mandelbrot mandelModel = new Mandelbrot();
			controller = new MandelbrotController(mandelModel);
			
			//contains fractal info
			fractalInfo = "The Mandelbrot set is the set of complex numbers for which the function does not diverge "
					+ "when iterated from, i.e., for which the sequence, etc., remains bounded in absolute value. Its "
					+ "definition and name are due to Adrien Douady, in tribute to the mathematician Benoit Mandelbrot.";
		}
		
		//only generate the fractal if a value choice was found
		if(choice != -1){
			//send parameters
			boolean sent = controller.acceptParameters(params);
			
			//render the fractal, only if no error occurred
			if(controller != null && sent) result = controller.render();
			
			//detect if invalid parameters were given
			if(!sent) errorMessage = "Invalid parameters given";
		}
		
		for(int i = 0; i < NUM_PARAMS; i++){
			req.setAttribute(params + "i", params[i]);
		}
		
		req.setAttribute("errorMessage",  errorMessage);
		req.setAttribute("fractalInfo",  fractalInfo);
		req.setAttribute("result", result);
		req.setAttribute("choice", choice);
		
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req, resp);
	}
}
