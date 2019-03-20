package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.io.IOException;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.renderer.Renderer;

public class MandelbrotController 
{
	private Mandelbrot model;
	
	public MandelbrotController(Mandelbrot model)
	{
		this.model = model;
	}
	
	public void setCoords(double x1, double y1, double x2, double y2)
	{
		model.setCoords(x1, y1, x2, y2);
	}
	
	public boolean buildMandelbrot() throws InterruptedException, IOException
	{
		double x1 = model.getX1();
		double y1 = model.getY1();
		double x2 = model.getX2();
		double y2 = model.getY2();
		// Renderer r = new Renderer("mandelbrot", x1, y1, x2, y2);
		Renderer r = new Renderer();
		r.renderMandelbrot(x1, y1, x2, y2);
		return true;
	}
}
