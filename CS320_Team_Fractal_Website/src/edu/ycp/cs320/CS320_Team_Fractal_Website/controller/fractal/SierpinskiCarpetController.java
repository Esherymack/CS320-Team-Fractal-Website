package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.SierpinskiCarpet;

public class SierpinskiCarpetController extends FractalController{
	
	private SierpinskiCarpet model;
	
	public SierpinskiCarpetController(SierpinskiCarpet model){
		super();
		this.model = model;
	}
	public SierpinskiCarpetController(){
		this(null);
	}
	
	public SierpinskiCarpet getModel(){
		return model;
	}
	public void setModel(SierpinskiCarpet model){
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
			x = Double.parseDouble(params[7]);
			y = Double.parseDouble(params[8]);
			model.setP4(new Point2D.Double(x, y));
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e){
			return false;
		}
		
		return true;
	}
		
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(SierpinskiCarpet.SIZE, SierpinskiCarpet.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SierpinskiCarpet.SIZE, SierpinskiCarpet.SIZE);
		
		drawSierpinskiBlanket(model.getLevel(), g, model.getP1(), model.getP2(), model.getP3(), model.getP4());
		return img;
	}
	
	/**
	 * Recursively draw the square at the given parameters
	 * @param level the remaining levels to draw the fractal
	 * * @param orientation the orientation of the fractal
	 * @param g the graphics object used to draw the fractal
	 * @param p1 the first point on the square
	 * @param p2 the second point on the square
	 * @param p3 the third point on the square
	 * @param p4 the fourth third point on the square
	 */
	private void drawSierpinskiBlanket(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4){
		// this is a recursive function
		if(level <= 1){
			Point2D.Double middle = midPoint(p1, p2);
			middle = midPoint(middle, p3);
			
			if(model.noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.x, SierpinskiCarpet.SIZE));
			}
			else if(getGradientType().equals(Gradient.VERTICAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.y, SierpinskiCarpet.SIZE));
			}
			else if(getGradientType().equals(Gradient.DIAGONAL)){
				g.setColor(getGradient().getDiagonalGradientColor(middle.x, middle.y, SierpinskiCarpet.SIZE, SierpinskiCarpet.SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						middle.x / SierpinskiCarpet.SIZE,
						middle.y / SierpinskiCarpet.SIZE,
						(middle.x * middle.y) / (SierpinskiCarpet.SIZE * SierpinskiCarpet.SIZE)));
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
			
			//top middle
			drawSierpinskiBlanket(level - 1, g, p5, p6, p7, p8);
			//top right
			drawSierpinskiBlanket(level - 1, g, p6, p2, p9, p7);
			//middle right
			drawSierpinskiBlanket(level - 1, g, p7, p9, p10, p11);
			//bottom right
			drawSierpinskiBlanket(level - 1, g, p11, p10, p3, p12);
			//bottom middle
			drawSierpinskiBlanket(level - 1, g, p14, p11, p12, p13);
			//bottom left
			drawSierpinskiBlanket(level - 1, g, p15, p14, p13, p4);
			//middle left
			drawSierpinskiBlanket(level - 1, g, p16, p8, p14, p15);
			//top left
			drawSierpinskiBlanket(level - 1, g, p1, p5, p8, p16);
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
