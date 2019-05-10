package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Vicsek;

public class VicsekController extends FractalController{
	
	private Vicsek model;
	
	public VicsekController(Vicsek model){
		super();
		this.model = model;
	}
	public VicsekController(){
		this(null);
	}
	
	public Vicsek getModel(){
		return model;
	}
	public void setModel(Vicsek model){
		this.model = model;
	}
	
	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setLevel(Integer.parseInt(params[0]));
			model.setOrientation(Integer.parseInt(params[1]));
			double x = Double.parseDouble(params[2]);
			double y = Double.parseDouble(params[3]);
			model.setP1(new Point2D.Double(x, y));
			x = Double.parseDouble(params[4]);
			y = Double.parseDouble(params[5]);
			model.setP2(new Point2D.Double(x, y));
			x = Double.parseDouble(params[6]);
			y = Double.parseDouble(params[7]);
			model.setP3(new Point2D.Double(x, y));
			x = Double.parseDouble(params[8]);
			y = Double.parseDouble(params[9]);
			model.setP4(new Point2D.Double(x, y));
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e){
			return false;
		}
		
		return true;
	}
		
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(Vicsek.SIZE, Vicsek.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Vicsek.SIZE, Vicsek.SIZE);
		
		drawVicsek(model.getLevel(), g, model.getP1(), model.getP2(), model.getP3(), model.getP4());
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
	private void drawVicsek(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4){
		// this is a recursive function
		if(level <= 1){
			Point2D.Double middle = midPoint(p1, p2);
			middle = midPoint(middle, p3);
			
			if(model.noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.x, Vicsek.SIZE));
			}
			else if(getGradientType().equals(Gradient.VERTICAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.y, Vicsek.SIZE));
			}
			else if(getGradientType().equals(Gradient.DIAGONAL)){
				g.setColor(getGradient().getDiagonalGradientColor(middle.x, middle.y, Vicsek.SIZE, Vicsek.SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						middle.x / Vicsek.SIZE,
						middle.y / Vicsek.SIZE,
						(middle.x * middle.y) / (Vicsek.SIZE * Vicsek.SIZE)));
			}
			else g.setColor(Color.WHITE);
			
			Polygon p = new Polygon();
			p.addPoint((int)Math.round(p1.x), (int)Math.round(p1.y));
			p.addPoint((int)Math.round(p2.x), (int)Math.round(p2.y));
			p.addPoint((int)Math.round(p3.x), (int)Math.round(p3.y));
			p.addPoint((int)Math.round(p4.x), (int)Math.round(p4.y));
			g.fillPolygon(p);
		}
		else{
			//points
			Point2D.Double p5 = getThirdPoint(p1, p2);
			Point2D.Double p6 = getThirdPoint(p2, p1);
			Point2D.Double p9 = getThirdPoint(p2, p3);
			Point2D.Double p10 = getThirdPoint(p3, p2);
			Point2D.Double p12 = getThirdPoint(p3, p4);
			Point2D.Double p13 = getThirdPoint(p4, p3);
			Point2D.Double p15 = getThirdPoint(p4, p1);
			Point2D.Double p16 = getThirdPoint(p1, p4);
			Point2D.Double p7 = getThirdPoint(p6, p12);
			Point2D.Double p8 = getThirdPoint(p5, p13);
			Point2D.Double p11 = getThirdPoint(p12, p6);
			Point2D.Double p14 = getThirdPoint(p13, p5);
			
			//top 
			drawVicsek(level - 1, g, p5, p6, p7, p8);
			//right
			drawVicsek(level - 1, g, p7, p9, p10, p11);
			//bottom
			drawVicsek(level - 1, g, p14, p11, p12, p13);
			//left
			drawVicsek(level - 1, g, p16, p8, p14, p15);
			//middle
			drawVicsek(level - 1, g, p8, p7, p11, p14);
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
