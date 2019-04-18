package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.BrowseFractalsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BrowseFractalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("Browse Fractals Servlet: doGet");
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Browse Fractals Servlet: doPost");
		
		//variables for attributes
		Fractal renderFractal = null;
		String errorMessage = null;
		Boolean display = false;
		ArrayList<Fractal> fractals = null;
		
		//set up controller
		BrowseFractalsController browseController = new BrowseFractalsController();
		
		
		if (req.getParameter("viewAllFractals") != null) {
			fractals = browseController.getAllFractals();
		}
		else if (req.getParameter("viewAllFractalsByTypeMandelbrot") != null) {
			fractals = browseController.getAllFractalsByType("Mandelbrot");
		}
		else if (req.getParameter("viewAllFractalsByTypeSierpinski") != null) {
			fractals = browseController.getAllFractalsByType("Sierpinski");
		}
		else if (req.getParameter("viewAllFractalsByTypeKoch") != null) {
			fractals = browseController.getAllFractalsByType("Koch");
		}
		
		//TODO move this stuff to the derby database?
		//get fractal the user selected
		for(Fractal f : fractals){
			Object found = req.getParameter("viewFractal_" + f.getId());
			if(found != null){
				renderFractal = f;
				break;
			}
		}

		//check to see if the request was not to view all fractals
		//if the request was to view all fractals, no initial fractal should be displayed
		if (req.getParameter("viewAllFractals") == null && req.getParameter("viewAllFractalsByTypeMandelbrot") == null && req.getParameter("viewAllFractalsByTypeSierpinski") == null && req.getParameter("viewAllFractalsByTypeKoch") == null) {
			display = true;
		}
		
		//if the fractal was found, render it and display it
		if(renderFractal != null){
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
		
		//if the fractal was not found and one should be displayed, then send an error message
		else if(display) errorMessage = "Fractal couldn't be rendered";
		
		//set attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.setAttribute("fractals", fractals);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
		
}
