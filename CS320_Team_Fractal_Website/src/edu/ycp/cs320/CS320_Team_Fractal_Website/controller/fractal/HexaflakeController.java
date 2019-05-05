package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
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
	 * @param p1 the first point on the triangle
	 * @param p2 the second point on the triangle
	 * @param p3 the third point on the triangle
	 */
	private void drawHexaflake(int level, Graphics g, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4, Point2D.Double p5, Point2D.Double p6){
		// this is a recursive function
		if(level <= 1){
			Point2D.Double middle = midPoint(p1, p4);
			
			if(model.noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.x, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.VERTICAL)){
				g.setColor(getGradient().getStraightGradientColor(middle.y, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.DIAGONAL)){
				g.setColor(getGradient().getDiagonalGradientColor(middle.x, middle.y, Hexaflake.SIZE, Hexaflake.SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						middle.x / Hexaflake.SIZE,
						middle.y / Hexaflake.SIZE,
						(middle.x * middle.y) / (Hexaflake.SIZE * Hexaflake.SIZE)));
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
			
			//middle hexagon
			Point2D.Double p7 = new Point2D.Double(0, 0);
			Point2D.Double p8 = new Point2D.Double(0, 0);
			Point2D.Double p9 = new Point2D.Double(0, 0);
			Point2D.Double p10 = new Point2D.Double(0, 0);
			Point2D.Double p11 = new Point2D.Double(0, 0);
			Point2D.Double p12 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p7, p8, p9, p10, p11, p12);
			//top hexagon
			Point2D.Double p13 = new Point2D.Double(0, 0);
			Point2D.Double p14 = new Point2D.Double(0, 0);
			Point2D.Double p15 = new Point2D.Double(0, 0);
			Point2D.Double p16 = new Point2D.Double(0, 0);
			Point2D.Double p17 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p1, p13, p14, p15, p16, p17);
			//upper right hexagon
			Point2D.Double p18 = new Point2D.Double(0, 0);
			Point2D.Double p19 = new Point2D.Double(0, 0);
			Point2D.Double p20 = new Point2D.Double(0, 0);
			Point2D.Double p21 = new Point2D.Double(0, 0);
			Point2D.Double p22 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p18, p2, p19, p20, p21, p22);
			//lower right hexagon
			Point2D.Double p23 = new Point2D.Double(0, 0);
			Point2D.Double p24 = new Point2D.Double(0, 0);
			Point2D.Double p25 = new Point2D.Double(0, 0);
			Point2D.Double p26 = new Point2D.Double(0, 0);
			Point2D.Double p27 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p23, p24, p3, p25, p26, p27);
			//bottom hexagon
			Point2D.Double p28 = new Point2D.Double(0, 0);
			Point2D.Double p29 = new Point2D.Double(0, 0);
			Point2D.Double p30 = new Point2D.Double(0, 0);
			Point2D.Double p31 = new Point2D.Double(0, 0);
			Point2D.Double p32 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p28, p29, p30, p4, p31, p32);
			//lower left hexagon
			Point2D.Double p33 = new Point2D.Double(0, 0);
			Point2D.Double p34 = new Point2D.Double(0, 0);
			Point2D.Double p35 = new Point2D.Double(0, 0);
			Point2D.Double p36 = new Point2D.Double(0, 0);
			Point2D.Double p37 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p33, p34, p35, p36, p5, p37);
			//upper left hexagon
			Point2D.Double p38 = new Point2D.Double(0, 0);
			Point2D.Double p39 = new Point2D.Double(0, 0);
			Point2D.Double p40 = new Point2D.Double(0, 0);
			Point2D.Double p41 = new Point2D.Double(0, 0);
			Point2D.Double p42 = new Point2D.Double(0, 0);
			drawHexaflake(level - 1, g, p38, p39, p40, p41, p42, p6);
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
	
	public double dist(Point2D.Double p1, Point2D.Double p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
}