package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Hexaflake;

public class HexaflakeController extends FractalController{
	
	private Hexaflake model;
	
	public HexaflakeController(Hexaflake model){
		super();
		this.model = model;
	}
	public HexaflakeController(){
		this(null);
	}
	
	public Hexaflake getModel(){
		return model;
	}
	public void setModel(Hexaflake model){
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
			x = Double.parseDouble(params[9]);
			y = Double.parseDouble(params[10]);
			model.setP5(new Point2D.Double(x, y));
			x = Double.parseDouble(params[11]);
			y = Double.parseDouble(params[12]);
			model.setP6(new Point2D.Double(x, y));
			
		}catch(NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e){
			return false;
		}
		
		return true;
	}
		
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(Hexaflake.SIZE, Hexaflake.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Hexaflake.SIZE, Hexaflake.SIZE);
		
		drawHexaflake(model.getLevel(), g, model.getP1(), model.getP2(), model.getP3(), model.getP4(), model.getP5(), model.getP6());
		return img;
	}
	
	/**
	 * Recursively draw the triangle at the given parameters
	 * @param level the remaining levels to draw the fractal
	 * @param g the graphics object used to draw the fractal
	 * @param p1 the first point on the fractal
	 * @param p2 the second point on the fractal
	 * @param p3 the third point on the fractal
	 * @param p4 the fourth point on the fractal
	 * @param p5 the fifth point on the fractal
	 * @param p6 the sixth point on the fractal
	 */
	private void drawHexaflake(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4, Point2D.Double p5, Point2D.Double p6){
		Point2D.Double center = midPoint(p1, p4);
		
		if(level <= 1){
			//if any points are null, quit out of drawing this flake
			if(p1 == null || p2 == null || p3 == null || p4 == null || p5 == null || p6 == null) return;
			
			if(model.noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getStraightGradientColor(center.x, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.VERTICAL)){
				g.setColor(getGradient().getStraightGradientColor(center.y, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.DIAGONAL)){
				g.setColor(getGradient().getDiagonalGradientColor(center.x, center.y, Hexaflake.SIZE, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						center.x / Hexaflake.SIZE,
						center.y / Hexaflake.SIZE,
						(center.x * center.y) / (Hexaflake.SIZE * Hexaflake.SIZE)));
			}
			else g.setColor(Color.WHITE);
			
			Polygon p = new Polygon();
			p.addPoint((int)Math.round(p1.x), (int)Math.round(p1.y));
			p.addPoint((int)Math.round(p2.x), (int)Math.round(p2.y));
			p.addPoint((int)Math.round(p3.x), (int)Math.round(p3.y));
			p.addPoint((int)Math.round(p4.x), (int)Math.round(p4.y));
			p.addPoint((int)Math.round(p5.x), (int)Math.round(p5.y));
			p.addPoint((int)Math.round(p6.x), (int)Math.round(p6.y));
			g.fillPolygon(p);
		}
		else{
			
			//top hexagon
			Point2D.Double p7 = new Point2D.Double(p1.x, p1.y);
			Point2D.Double p8 = getThirdPoint(p1, p2);
			Point2D.Double p10 = getThirdPoint(center, p1);
			Point2D.Double p12 = getThirdPoint(p1, p6);
			
			Point2D.Double p9 = getIntersection(
					new Line2D.Double(midPoint(p1, p2), center),
					new Line2D.Double(p12, midPoint(p7, p10))
			);
			Point2D.Double p11 = getIntersection(
					new Line2D.Double(midPoint(p1, p6), center),
					new Line2D.Double(p8, midPoint(p7, p10))
			);
			
			drawHexaflake(level - 1, g, p7, p8, p9, p10, p11, p12);

			//upper right hexagon
			Point2D.Double p13 = getThirdPoint(p2, p1);
			Point2D.Double p14 = new Point2D.Double(p2.x, p2.y);
			Point2D.Double p15 = getThirdPoint(p2, p3);
			Point2D.Double p17 = getThirdPoint(center, p2);
			Point2D.Double p18 = new Point2D.Double(p9.x, p9.y);
			
			Point2D.Double p16 = getIntersection(
					new Line2D.Double(midPoint(p2, p3), center),
					new Line2D.Double(p13, midPoint(p2, p17))
			);
			drawHexaflake(level - 1, g, p13, p14, p15, p16, p17, p18);
			
			//lower right hexagon
			Point2D.Double p19 = new Point2D.Double(p16.x, p16.y);
			Point2D.Double p20 = getThirdPoint(p3, p2);
			Point2D.Double p21 = new Point2D.Double(p3.x, p3.y);
			Point2D.Double p22 = getThirdPoint(p3, p4);
			Point2D.Double p24 = getThirdPoint(center, p3);
			
			Point2D.Double p23 = getIntersection(
					new Line2D.Double(midPoint(p3, p4), center),
					new Line2D.Double(p20, midPoint(p24, p3))
			);
			drawHexaflake(level - 1, g, p19, p20, p21, p22, p23, p24);
			
			//bottom hexagon
			Point2D.Double p25 = getThirdPoint(center, p4);
			Point2D.Double p26 = new Point2D.Double(p23.x, p23.y);
			Point2D.Double p27 = getThirdPoint(p4, p3);
			Point2D.Double p28 = new Point2D.Double(p4.x, p4.y);
			Point2D.Double p29 = getThirdPoint(p4, p5);
			
			Point2D.Double p30 = getIntersection(
					new Line2D.Double(midPoint(p4, p5), center),
					new Line2D.Double(p27, midPoint(p25, p4))
			);
			drawHexaflake(level - 1, g, p25, p26, p27, p28, p29, p30);
			
			//lower left hexagon
			Point2D.Double p32 = getThirdPoint(center, p5);
			Point2D.Double p33 = new Point2D.Double(p30.x, p30.y);
			Point2D.Double p34 = getThirdPoint(p5, p4);
			Point2D.Double p35 = new Point2D.Double(p5.x, p5.y);
			Point2D.Double p36 = getThirdPoint(p5, p6);
			
			Point2D.Double p31 = getIntersection(
					new Line2D.Double(midPoint(p5, p6), center),
					new Line2D.Double(p34, midPoint(p32, p5))
			);
			drawHexaflake(level - 1, g, p31, p32, p33, p34, p35, p36);
			
			//upper left hexagon
			Point2D.Double p37 = getThirdPoint(p6, p1);
			Point2D.Double p38 = new Point2D.Double(p11.x, p11.y);
			Point2D.Double p39 = getThirdPoint(center, p6);
			Point2D.Double p40 = new Point2D.Double(p31.x, p31.y);
			Point2D.Double p41 = getThirdPoint(p6, p5);
			Point2D.Double p42 = new Point2D.Double(p6.x, p6.y);
			drawHexaflake(level - 1, g, p37, p38, p39, p40, p41, p42);
			
			//middle hexagon
			drawHexaflake(level - 1, g, p10, p17, p24, p25, p32, p39);
			
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
	
	/**
	 * Get the intersection point of the two lines
	 * @param line1 the first line
	 * @param line2 the second line
	 * @return the intersection point, null if the lines do not intersect
	 */
	public static Point2D.Double getIntersection(Line2D.Double line1, Line2D.Double line2){
		//find slopes
		double slope1 = slope(line1);
		double slope2 = slope(line2);

		//find intercepts
		double b1 = line1.y1 - line1.x1 * slope1;
		double b2 = line2.y1 - line2.x1 * slope2;
		
		//catch lines that are the same
		if(slope1 == slope2) return null;
		
		//variables for intersection
		double intX;
		double intY;
		
		//if a slope is vertical then it needs a different calculation
		if(Double.isInfinite(slope1)){
			intX = line1.x1;
			intY = line2.x1 * slope2 + b2;
		}
		else if(Double.isInfinite(slope2)){
			intX = line2.x1;
			intY = line1.x1 * slope1 + b1;
		}
		//find intersections normally
		else{
			intX = (b2 - b1) / (slope1 - slope2);
			intY = intX * slope1 + b1;
		}
		
		return new Point2D.Double(intX, intY);
	}
	
	/**
	 * Get the slope of the given line
	 * @param line the line to find the slope of
	 * @return the slope, Infinity if the slope is vertical
	 */
	public static double slope(Line2D.Double line){
		return (line.y2 - line.y1) / (line.x2 - line.x1);
	}
}
