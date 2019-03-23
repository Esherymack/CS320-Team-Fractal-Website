package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Sierpinski;

public class SierpinskiController extends FractalController{
	private Sierpinski model;
	
	public SierpinskiController(Sierpinski model){
		this.model = model;
	}
	
	public Sierpinski getModel(){
		return model;
	}
	
	public void setModel(Sierpinski model){
		this.model = model;
	}
	
	@Override
	public boolean render(){
		//create a thread to render and calculate the set
		//TODO make this use multiple threads
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				BufferedImage img = new BufferedImage(Sierpinski.SIZE, Sierpinski.SIZE, BufferedImage.TYPE_INT_ARGB);
		        Graphics g = img.getGraphics();
		        
		        g.setColor(Color.BLACK);
		        g.fillRect(0, 0, Sierpinski.SIZE, Sierpinski.SIZE);
		        
		        
				drawSierpinski(model.getLevel(), g, model.getP1(), model.getP2(), model.getP3());
				sendImage(img);
			}
		});
		
		thread.start();
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Recursively draw the triangle at the given parameters
	 * @param level
	 * @param g
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public void drawSierpinski(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
		
		int r = ThreadLocalRandom.current().nextInt(0, 256);
		int gr = ThreadLocalRandom.current().nextInt(0, 256);
		int b = ThreadLocalRandom.current().nextInt(0, 256);
		
		g.setColor(new Color(r, gr, b));
		
		// this is a recursive function
		if(level == 1){
			Polygon p = new Polygon();
			p.addPoint((int)Math.round(p1.x),  (int)Math.round(p1.y));
			p.addPoint((int)Math.round(p2.x),  (int)Math.round(p2.y));
			p.addPoint((int)Math.round(p3.x),  (int)Math.round(p3.y));
			g.fillPolygon(p);
		}
		else{
			Point2D.Double p4 = midpoint(p1, p2);
			Point2D.Double p5 = midpoint(p2, p3);
			Point2D.Double p6 = midpoint(p1, p3);
			drawSierpinski(level - 1, g, p1, p4, p6);
			drawSierpinski(level - 1, g, p4, p2, p5);
			drawSierpinski(level - 1, g, p6, p5, p3);
		}
	}
	
	public static Point2D.Double midpoint(Point2D.Double p1, Point2D.Double p2){
		return new Point2D.Double((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
}
