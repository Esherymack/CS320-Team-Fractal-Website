package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

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
				BufferedImage img = new BufferedImage(Sierpinski.SIZE, Sierpinski.SIZE, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics g = img.getGraphics();
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
	public void drawSierpinski(int level, Graphics g, Point p1, Point p2, Point p3){
		g.setColor(Color.RED);
		
		// this is a recursive function
		if(level == 1){
			Polygon p = new Polygon();
			p.addPoint(p1.x,  p1.y);
			p.addPoint(p2.x,  p2.y);
			p.addPoint(p3.x,  p3.y);
			g.fillPolygon(p);
		}
		else{
			Point p4 = midpoint(p1, p2);
			Point p5 = midpoint(p2, p3);
			Point p6 = midpoint(p1, p3);
			drawSierpinski(level - 1, g, p1, p4, p6);
			drawSierpinski(level - 1, g, p4, p2, p5);
			drawSierpinski(level - 1, g, p6, p5, p3);
		}
	}
	
	public static Point midpoint(Point p1, Point p2){
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
}
