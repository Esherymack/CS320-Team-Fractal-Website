package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class Gallery{
	
	private ArrayList<Fractal> fractals;
	
	public Gallery(){
		fractals = new ArrayList<Fractal>();
	}
	
	public ArrayList<Fractal> getFractals(){
		return fractals;
	}
	
	public void addFractal(Fractal f){
		fractals.add(f);
	}
	
}
