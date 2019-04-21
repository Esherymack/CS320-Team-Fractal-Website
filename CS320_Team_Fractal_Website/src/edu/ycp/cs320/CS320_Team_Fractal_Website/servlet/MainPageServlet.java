package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;


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
		
		// otherwise
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		//message for logging in
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		
		//list of parameters that need to be sent for which values are displayed
		sendParamAttributes(req);
		
		//view page
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req,  resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{	
		System.out.println("mainPage Servlet: doPost");

		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		// holds the error message text, if any
		String errorMessage = null;
		// holds the fractal info
		String fractalInfo = null;
		
		Boolean result = null;
		
		//choice from the drop down menu
		String choice = req.getParameter("choice");
		
		//get all parameters
		String[] params = new String[NUM_PARAMS];
		for(int i = 0; i < NUM_PARAMS; i++){
			params[i] = req.getParameter("param" + i);
		}
		
		//color parameters
		String red = null;
		String green = null;
		String blue = null;

		//get color parameters
		red = req.getParameter("gradientRed");
		green = req.getParameter("gradientGreen");
		blue = req.getParameter("gradientBlue");
		
		//name parameter
		String name = null;
		
		//only generate the fractal if a value choice was found
		if(choice != null){
			//select the correct controller and model to use
			Fractal fractal = Fractal.getDefaultFractal(choice);
			FractalController controller = fractal.createApproprateController();
			
			//send parameters
			boolean sent = controller.acceptParameters(params);
			
			//get the color of the gradient for the controller
			Gradient gradient;
			try{
				int r = Integer.parseInt(red);
				int g = Integer.parseInt(green);
				int b = Integer.parseInt(blue);
				gradient = new Gradient(r, g, b);

				red = "" + gradient.getBaseColor().getRed();
				green = "" + gradient.getBaseColor().getGreen();
				blue = "" + gradient.getBaseColor().getBlue();
				
			}catch(NullPointerException | NumberFormatException e){
				gradient = new Gradient();
			}
			
			//set the gradient
			controller.setGradient(gradient);
			
			//render the fractal, only if no error occurred
			//if the request is for a submit, then just display the fractal to the site
			if(req.getParameter("submit") != null){
				if(controller != null && sent) result = controller.render();
				else errorMessage = "Failed to render fractal";
			}
			//if the request is for a save, only save the fractal
			else if(req.getParameter("save") != null){
				//get the name the user has typed in
				name = req.getParameter("saveName");
				
				//save and render the fractal
				if(name != null && controller != null && sent){
					//if no name was given, tell the user to give a name
					if(name.isEmpty()) errorMessage = "Please specify a name for the fractal";
					else{
						//try to save the fractal, if it saves, then render it
						if(controller.saveImage(name, getLoggedInUser(req, resp))){
							result = controller.render();
						}
						//otherwise send error message
						else{
							errorMessage = "Failed to save fractal";
							result = false;
						}
					}
				}
				else errorMessage = "Please provide a name and parameters";
			}
			
			//if the default values button is pressed, set all the parameters to the correct fractal type
			if(req.getParameter("setDefaultValues") != null){
				fractal.setDefaultParameters();
				params = fractal.getParameters();
			}
			//otherwise check if the fractal should be updated
			else{
				//detect if invalid parameters were given
				if(!sent) errorMessage = "Invalid parameters given";
				
				//set fractal info
				fractalInfo = controller.getModel().getInfo();
			}
		}
		else errorMessage = "Please select a fractal";
		
		//send parameters back
		for(int i = 0; i < NUM_PARAMS; i++){
			req.setAttribute("param" + i, params[i]);
		}
		//list of parameters that need to be sent for which values are displayed
		sendParamAttributes(req);
		
		//set attributes of page
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("fractalInfo", fractalInfo);
		req.setAttribute("result", result);
		req.setAttribute("choice", choice);
		
		//set color attributes
		req.setAttribute("gradientRed", red);
		req.setAttribute("gradientGreen", green);
		req.setAttribute("gradientBlue", blue);
		
		//set name attribute
		req.setAttribute("saveName", name);
		
		//go back to the page
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req, resp);
	}
	
	private void sendParamAttributes(HttpServletRequest req){
		//list of parameters that need to be sent for which values are displayed
		req.setAttribute("paramLabelList", getParamLabelList());
		
		//list of fractal names that need to be sent for which fractals are used as options
		String[] types = Fractal.getAllFractalTypes();
		req.setAttribute("fractalTypeList", types);
		
		//add attributes for all fractal type labels
		for(int j = 0; j < types.length; j++){
			Fractal f = Fractal.getDefaultFractal(types[j]);
			String[] labels = f.getParamLabels();
			for(int i = 0; i < labels.length; i++){
				req.setAttribute("fractalLabels" + types[j] + "param" + i, labels[i]);
			}
		}
		
	}
	
	/**
	 * Get a list of all the names of the parameters that are used for input values and their associated labels
	 * @return
	 */
	private String[] getParamLabelList(){
		String[] paramList = new String[NUM_PARAMS];
		for(int i = 0; i < paramList.length; i++) paramList[i] = "param" + i;
		return paramList;
	}
	
	protected String checkCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// User - should be logged in.
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// If there are no cookies, redirect the user to logIn.
		if(cookies == null)
		{
			resp.sendRedirect("logIn");
			return null;
		}
		// otherwise:
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("user")) userName = cookie.getValue();
		}
		// again, check if the user is logged in:
		if(userName == null)
		{
			resp.sendRedirect("logIn");
			return null;
		}
		
		// otherwise
		return("Currently logged in as " + userName);
	}
	
	/**
	 * Get the username of the user who is currently logged in
	 * @param req the request for the page
	 * @param resp the responce of the page
	 * @return the username of the user, null if no username was found
	 * @throws ServletException
	 * @throws IOException
	 */
	private String getLoggedInUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//get cookies
		Cookie[] cookies = req.getCookies();
		//if no cookies were found then return null
		if(cookies == null) return null;
		//look for the username, if it is found, return it
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")) return cookie.getValue();
		}
		//if no username is found, return null
		return null;
	}
	
}
