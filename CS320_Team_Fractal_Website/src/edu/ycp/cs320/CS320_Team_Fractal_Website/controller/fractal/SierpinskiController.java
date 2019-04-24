package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class SierpinskiController extends FractalController{
	
	private Sierpinski model;
	
	public SierpinskiController(Sierpinski model){
		super();
		this.model = model;
	}
	public SierpinskiController(){
		this(null);
	}
	
	public Sierpinski getModel(){
		return model;
	}
	public void setModel(Sierpinski model){
		this.model = model;
	}
	
	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setLevel(Integer.parseInt(params[0]));
			double x = Double.parseDouble(params[1]);
			double y = Double.parseDouble(params[2]);
			model.setP1(new Point2D.Double(x, y));
			x = Double.parseDouble(params[3]);
			y = Double.parseDouble(params[4]);
			model.setP2(new Point2D.Double(x, y));
			x = Double.parseDouble(params[5]);
			y = Double.parseDouble(params[6]);
			model.setP3(new Point2D.Double(x, y));
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e){
			return false;
		}
		
		return true;
	}
		
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(Sierpinski.SIZE, Sierpinski.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Sierpinski.SIZE, Sierpinski.SIZE);
		
		drawSierpinski(model.getLevel(), g, model.getP1(), model.getP2(), model.getP3());
		return img;
	}
	
	/**
	 * Recursively draw the triangle at the given parameters
	 * @param level the remaining levels to draw the fractal
	 * @param g the graphics object used to draw the fractal
	 * @param p1 the first point on the triangle
	 * @param p2 the second point on the triangle
	 * @param p3 the third point on the triangle
	 */
	private void drawSierpinski(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3){
		// this is a recursive function
		if(level <= 1){
			Point2D.Double middle = midPoint(p1, p2);
			middle = midPoint(middle, p3);
			
			if(noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getHorizontalGradientColor(middle.x, Sierpinski.SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						middle.x / Sierpinski.SIZE,
						middle.y / Sierpinski.SIZE,
						(middle.x * middle.y) / (Sierpinski.SIZE * Sierpinski.SIZE)));
			}
			else g.setColor(Color.WHITE);
			
			Polygon p = new Polygon();
			p.addPoint((int)Math.round(p1.x), (int)Math.round(p1.y));
			p.addPoint((int)Math.round(p2.x), (int)Math.round(p2.y));
			p.addPoint((int)Math.round(p3.x), (int)Math.round(p3.y));
			g.fillPolygon(p);
		}
		else{
			Point2D.Double p4 = midPoint(p1, p2);
			Point2D.Double p5 = midPoint(p2, p3);
			Point2D.Double p6 = midPoint(p1, p3);
			drawSierpinski(level - 1, g, p1, p4, p6);
			drawSierpinski(level - 1, g, p4, p2, p5);
			drawSierpinski(level - 1, g, p6, p5, p3);
		}
	}
	
	/**
	 * Find the midpoint of the two given points
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the midpoint
	 */
	public static Point2D.Double midPoint(Point2D.Double p1, Point2D.Double p2){
		return new Point2D.Double((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
}
