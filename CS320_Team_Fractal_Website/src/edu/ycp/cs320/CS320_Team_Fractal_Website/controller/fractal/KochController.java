package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;

public class KochController extends FractalController{
	
	public static final int SIZE = 500;
	
	private Koch model;
	
	public KochController(Koch model){
		super();
		this.model = model;
	}
	public KochController(){
		this(null);
	}
	
	@Override
	public Fractal getModel(){
		return model;
	}
	public void setModel(Koch model){
		this.model = model;
	}

	/*
	 * params[0] = iterations
	 */
	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setIterations(Integer.parseInt(params[0]));
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		
		return true;
	}
	
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE, SIZE);
		
		//size of triangle
		double s = .9 * SIZE * Math.sqrt(3.0) / 2.0;
		//y offset
		double y = -50;
		renderKochLine(g, new Point2D.Double(s, s + y), new Point2D.Double(SIZE - s, s + y), model.getIterations());
		renderKochLine(g, new Point2D.Double(SIZE / 2, SIZE - s + y), new Point2D.Double(s, s + y), model.getIterations());
		renderKochLine(g, new Point2D.Double(SIZE - s, s + y), new Point2D.Double(SIZE / 2, SIZE - s + y), model.getIterations());
		
		return img;
	}
	
	public static void renderKochLine(Graphics g, Point2D.Double start, Point2D.Double end, int iterations){
		if(iterations <= 0) return;
		
		//get the point one third the distance from start to end, closest to start
		Point2D.Double low = getThirdPoint(start, end);
		//get the point one third the distance from start to end, closest to end
		Point2D.Double high = getThirdPoint(end, start);
		//get the appropriate point that forms an equilateral triangle
		Point2D.Double point = getEquilateralPoint(low, high);
		
		//draw new lines
		if(iterations == 1){
			g.setColor(Color.BLACK);
			g.drawLine(
					(int)Math.round(start.x),
					(int)Math.round(start.y),
					(int)Math.round(low.x),
					(int)Math.round(low.y)
			);
			g.drawLine(
					(int)Math.round(end.x),
					(int)Math.round(end.y),
					(int)Math.round(high.x),
					(int)Math.round(high.y)
			);
			g.drawLine(
					(int)Math.round(low.x),
					(int)Math.round(low.y),
					(int)Math.round(point.x),
					(int)Math.round(point.y)
			);
			g.drawLine(
					(int)Math.round(high.x),
					(int)Math.round(high.y),
					(int)Math.round(point.x),
					(int)Math.round(point.y)
			);
		}
		
		//call more lines
		renderKochLine(g, point, high, iterations - 1);
		renderKochLine(g, low, point, iterations - 1);
		renderKochLine(g, start, low, iterations - 1);
		renderKochLine(g, high, end, iterations - 1);
	}
	
	/**
	 * Find a point that makes an equilateral triangle with the given points. 
	 * For any two points there exists 2 points that create an equilateral triangle with the first 2 points. 
	 * Swap start and end to get the other point
	 * @param start the first point
	 * @param end the second point
	 * @return the point that completes the triangle
	 */
	public static Point2D.Double getEquilateralPoint(Point2D.Double start, Point2D.Double end){
		//do something like this?
		// (point){p3.x + (p5.x - p3.x)*cos(theta) + (p5.y - p3.y)*sin(theta),p3.y - (p5.x - p3.x)*sin(theta) + (p5.y - p3.y)*cos(theta)};
		//math from https://rosettacode.org/wiki/Koch_curve
		return new Point2D.Double(
			start.x + (end.x - start.x) * Math.cos(Math.PI / 3) + (end.y - start.y) * Math.sin(Math.PI / 3),
			start.y - (end.x - start.x) * Math.sin(Math.PI / 3) + (end.y - start.y) * Math.cos(Math.PI / 3)
		);
	}
	
	/**
	 * Gets a point that is one third the distance from start to end
	 * @param start the start point
	 * @param end the end point
	 * @return the point one third away
	 */
	public static Point2D.Double getThirdPoint(Point2D.Double start, Point2D.Double end){
		return new Point2D.Double(
				start.x + (end.x - start.x) / 3,
				start.y + (end.y - start.y) / 3);
	}
}
