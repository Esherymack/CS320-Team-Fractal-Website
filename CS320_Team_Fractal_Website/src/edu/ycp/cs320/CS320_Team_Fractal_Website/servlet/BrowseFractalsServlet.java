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
		
		BrowseFractalsController browseController = new BrowseFractalsController();
		ArrayList<Fractal> fractals = browseController.getAllFractals();
		
		//TODO the buttons need to correctly display the corresponding fractal and the error message should only be shown when an error happens
		
		String errorMessage = null;
		Boolean display = null;
		
		//get fractal the user selected
		Fractal renderFractal = null;
		for(Fractal f : fractals){
			Object found = req.getParameter("viewFractal_" + f.getId());
			if(found != null){
				renderFractal = f;
				break;
			}
		}
		
		//if the fractal was found, render it and display it
		if(renderFractal != null){
			display = true;
			
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
		else{
			display = false;
			errorMessage = "Fractal couldn't be rendered";
		}
		//if the fractal was not found, return an error message
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.setAttribute("fractals", fractals);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
		
}
