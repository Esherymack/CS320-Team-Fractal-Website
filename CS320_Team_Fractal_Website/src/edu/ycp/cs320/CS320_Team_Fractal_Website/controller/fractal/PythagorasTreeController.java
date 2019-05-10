package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.PythagorasTree;

public class PythagorasTreeController extends FractalController{
	
	public static final int SIZE = 800;
	
	private PythagorasTree model;

	public PythagorasTreeController(PythagorasTree model){
		super();
		this.model = model;
	}
	public PythagorasTreeController(){
		this(null);
	}
	
	@Override
	public PythagorasTree getModel(){
		return model;
	}
	public void setModel(PythagorasTree tree){
		this.model = tree;
	}

	@Override
	public BufferedImage renderImage(){
		BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SIZE, SIZE);

		double x1 = SIZE / 2 - model.getInitialSize() / 2;
		double y1 = SIZE - model.getInitialSize() - SIZE * .2;
		g.setColor(chooseColor(SIZE / 2, y1 + model.getInitialSize() / 2));
		
		g.fillRect(
				(int)Math.round(x1),
				(int)Math.round(y1),
				(int)Math.round(model.getInitialSize()),
				(int)Math.round(model.getInitialSize())
		);
		renderNextSquares(g, model.getIterations(), new Point2D.Double(x1, y1), new Point2D.Double(x1 + model.getInitialSize(), y1));
		
		return img;
	}
	
	/**
	 * Draw the next 2 squares of the tree based on the next set of points
	 * @param g the graphics object used to draw the fractal
	 * @param iterations the number of iterations left
	 * @param leftPoint the left point of the square
	 * @param rightPoint the right point of the square
	 */
	public void renderNextSquares(Graphics g, int iterations, Point2D.Double leftPoint, Point2D.Double rightPoint){
		if(iterations <= 0) return;
		
		double baseRads = Math.atan2(rightPoint.y - leftPoint.y, rightPoint.x - leftPoint.x);
		double treeRads = Math.toRadians(model.getAngle());
		double midRads = Math.PI - treeRads * 2;
		double oldSize = leftPoint.distance(rightPoint);
		//use law of sines to determine the length of the new side
		double newSize = oldSize * Math.sin(treeRads) / Math.sin(midRads);
		
		//draw left square
		Polygon left = new Polygon();
		Point2D.Double p1 = new Point2D.Double(leftPoint.x, leftPoint.y);
		Point2D.Double p2 = new Point2D.Double(
				leftPoint.x + Math.cos(baseRads - treeRads) * newSize,
				leftPoint.y + Math.sin(baseRads - treeRads) * newSize
		);
		Point2D.Double p3 = new Point2D.Double(
				p2.x - Math.cos(baseRads - treeRads + Math.PI / 2) * newSize,
				p2.y - Math.sin(baseRads - treeRads + Math.PI / 2) * newSize
		);
		Point2D.Double p4 = new Point2D.Double(
				p1.x - Math.cos(baseRads - treeRads + Math.PI / 2) * newSize,
				p1.y - Math.sin(baseRads - treeRads + Math.PI / 2) * newSize
		);
		
		left.addPoint((int)Math.round(p1.x), (int)Math.round(p1.y));
		left.addPoint((int)Math.round(p2.x), (int)Math.round(p2.y));
		left.addPoint((int)Math.round(p3.x), (int)Math.round(p3.y));
		left.addPoint((int)Math.round(p4.x), (int)Math.round(p4.y));
		
		Point2D.Double mid = SierpinskiController.midPoint(p1, p3);
		g.setColor(chooseColor(mid.x, mid.y));
		g.fillPolygon(left);
		
		renderNextSquares(g, iterations - 1, p4, p3);
		
		//draw right square
		baseRads = Math.atan2(leftPoint.y - rightPoint.y, leftPoint.x - rightPoint.x);
		Polygon right = new Polygon();
		p1 = new Point2D.Double(rightPoint.x, rightPoint.y);
		p2 = new Point2D.Double(
				rightPoint.x + Math.cos(baseRads + treeRads) * newSize,
				rightPoint.y + Math.sin(baseRads + treeRads) * newSize
		);
		p3 = new Point2D.Double(
				p2.x + Math.cos(baseRads + treeRads + Math.PI / 2) * newSize,
				p2.y + Math.sin(baseRads + treeRads + Math.PI / 2) * newSize
		);
		p4 = new Point2D.Double(
				p1.x + Math.cos(baseRads + treeRads + Math.PI / 2) * newSize,
				p1.y + Math.sin(baseRads + treeRads + Math.PI / 2) * newSize
		);
		
		right.addPoint((int)Math.round(p1.x), (int)Math.round(p1.y));
		right.addPoint((int)Math.round(p2.x), (int)Math.round(p2.y));
		right.addPoint((int)Math.round(p3.x), (int)Math.round(p3.y));
		right.addPoint((int)Math.round(p4.x), (int)Math.round(p4.y));
		
		mid = SierpinskiController.midPoint(p1, p3);
		g.setColor(chooseColor(mid.getX(), mid.getY()));
		g.fillPolygon(right);
		
		renderNextSquares(g, iterations - 1, p3, p4);
	}
	
	/**
	 * Choose the color for the given coordinates
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the color
	 */
	public Color chooseColor(double x, double y){
		Color c;
		if(model.noGradient()) c = Color.WHITE ;
		else if(getGradientType().equals(Gradient.HORIZONTAL)){
			c = getGradient().getStraightGradientColor(x, SIZE);
		}
		else if(getGradientType().equals(Gradient.VERTICAL)){
			c = getGradient().getStraightGradientColor(y, SIZE);
		}
		else if(getGradientType().equals(Gradient.DIAGONAL)){
			c = getGradient().getDiagonalGradientColor(x, y, SIZE, SIZE);
		}
		else if(getGradientType().equals(Gradient.RAINBOW)){
			c = getGradient().getRainbowGradient(
					x / SIZE,
					y / SIZE,
					(x * y) / (SIZE * SIZE));
		}
		else c = Color.WHITE;
		
		return c;
	}

	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setIterations(Integer.parseInt(params[0]));
			model.setInitialSize(Double.parseDouble(params[1]));
			model.setAngle(Double.parseDouble(params[2]));
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		
		return true;
	}

}
