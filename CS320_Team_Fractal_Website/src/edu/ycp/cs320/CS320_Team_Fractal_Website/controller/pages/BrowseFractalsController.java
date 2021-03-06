package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BrowseFractalsController{
	
	private IDatabase database;
	
	public BrowseFractalsController(){
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
	}
	
	/**
	 * Get every fractal from the database
	 * @return and ArrayList of all the fractals
	 */
	public ArrayList<Fractal> getAllFractals(){
		return database.getAllFractals();
	}
	
	/**
	 * Get every fractal by type from the database
	 * @return and ArrayList of all the fractals of type
	 */
	public ArrayList<Fractal> getAllFractalsByType(String type){
		return database.getAllFractalsByType(type);
	}
	
	/**
	 * 
	 * Get every fractal from the database with the given character sequence
	 * @return and ArrayList of all the fractals of name with character sequence
	 */
	public ArrayList<Fractal> getAllFractalsWithCharSeq(String charSeq){
		System.out.println(charSeq);
		return database.getAllFractalsWithCharSeq(charSeq);
	}

	/**
	 * Get every fractal by type from the database
	 * @return and ArrayList of all the fractals of type
	 */
	public ArrayList<Fractal> getAllFractalsByGradientType(String gradientType){
		return database.getAllFractalsByGradientType(gradientType);
	}
	
	/**
	 * Get the username of the user who created the specific fractal
	 * @param id the id of the fractal
	 * @return the username who created the fractal, "Unknown" if user couldn't be found
	 */
	public String getUsernameByFractalId(int id){
		String name = database.getUsernameByFractalId(id);
		if(name == null) return "Unknown";
		return name;
	}
	
	/**
	 * Get all the fractals of the given fractal list that would be on the specified page
	 * @param fractals the fractal list to look through
	 * @param numPerPage the number of fractals on each page
	 * @param page the page this list is of
	 * @return the list of fractals, can be less than numPerPage if fractals.size() % page != 0, returns an empty list if fractals is null
	 */
	public ArrayList<Fractal> getFractalPageList(ArrayList<Fractal> fractals, int numPerPage, int page){
		ArrayList<Fractal> pageFractals = new ArrayList<Fractal>();
		
		if(fractals == null) return pageFractals;
		
		for(int i = 0; i < numPerPage && i + numPerPage * page < fractals.size(); i++){
			pageFractals.add(fractals.get(numPerPage * page + i));
		}
		return pageFractals;
	}
	
	/**
	 * Get the number of fractals per page from the given request
	 * @param req the request
	 * @return the number of fractals
	 */
	public int getFractalsPerPage(HttpServletRequest req){
		//get the number of fractals per page
		String fractalsPerPageString = req.getParameter("fractalsPerPage");
		//if the first parameter couldn't be found, try for the second
		if(fractalsPerPageString == null) fractalsPerPageString = req.getParameter("fractalsPerPageSecond");
		Integer fractalsPerPage = null;
		try{
			fractalsPerPage = Integer.parseInt(fractalsPerPageString);
		}catch(NumberFormatException | NullPointerException e){
			fractalsPerPage = 10;
		}
		return fractalsPerPage;
	}
	
	/**
	 * Get the page number from the current request
	 * @param req the request
	 * @return the page number
	 */
	public int getPageNumber(HttpServletRequest req){
		String pageNumberString = req.getParameter("pageNumber");
		//if the first parameter couldn't be found, try for the second
		if(pageNumberString == null) pageNumberString = req.getParameter("pageNumberSecond");
		Integer pageNumber = null;
		try{
			pageNumber = Integer.parseInt(pageNumberString);
		}catch(NumberFormatException | NullPointerException e){
			pageNumber = 0;
		}
		return pageNumber;
	}
	
	/**
	 * Get all the fractals from session
	 * @param req the request with the session
	 * @return the list of fractals, null if it couldn't be found
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Fractal> getFractalsFromSession(HttpServletRequest req){
		ArrayList<Fractal> fractals = null;
		//attempt to get the fractals currently loaded in the page
		try{
			fractals = (ArrayList<Fractal>) req.getSession().getAttribute("fractals");
		}catch(ClassCastException | NullPointerException e){
			fractals = null;
			e.printStackTrace();
		}
		
		return fractals;
	}
	
}
