package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.DragonCurve;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;

public class DragonCurveController extends FractalController{
	
	public static final int SIZE = 800;
	
	private DragonCurve model;
	
	public DragonCurveController(){
		this(null);
	}

	public DragonCurveController(DragonCurve model){
		super();
		this.model = model;
	}
	
	@Override
	public Fractal getModel(){
		return model;
	}
	public void setModel(DragonCurve model){
		this.model = model;
	}

	@Override
	public BufferedImage renderImage(){
		//set up image ands graphics objects
		BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		
		//draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SIZE, SIZE);
		
		//variables for loop
		int[] directions = calculateDragonDirections();
		Point2D.Double modelCenter = model.getCenter();
		Point2D.Double currentLoc = new Point2D.Double(SIZE / 2 +  modelCenter.x, SIZE / 2 +  modelCenter.y);
		double distance = 2 * model.getScalar();
		int direction = 0;

		//generate each iteration
		for(int i : directions){
			
			//find the new location based on the current direction
			Point2D.Double newLoc;
			if(direction == 0) newLoc = new Point2D.Double(currentLoc.x + distance, currentLoc.y);
			else if(direction == 1) newLoc = new Point2D.Double(currentLoc.x, currentLoc.y + distance);
			else if(direction == 2) newLoc = new Point2D.Double(currentLoc.x - distance, currentLoc.y);
			else newLoc = new Point2D.Double(currentLoc.x, currentLoc.y - distance);
			
			//get the center
			Point2D.Double center = SierpinskiController.midPoint(currentLoc, newLoc);
			
			//select the color based on the gradient of this fractal
			if(model.noGradient()) g.setColor(Color.WHITE);
			else if(getGradientType().equals(Gradient.HORIZONTAL)){
				g.setColor(getGradient().getStraightGradientColor(center.x, SIZE));
			}
			else if(getGradientType().equals(Gradient.VERTICAL)){
				g.setColor(getGradient().getStraightGradientColor(center.y, SIZE));
			}
			else if(getGradientType().equals(Gradient.DIAGONAL)){
				g.setColor(getGradient().getDiagonalGradientColor(center.x, center.y, SIZE, SIZE));
			}
			else if(getGradientType().equals(Gradient.RAINBOW)){
				g.setColor(getGradient().getRainbowGradient(
						center.x / SIZE,
						center.y / SIZE,
						(center.x * center.y) / (SIZE * SIZE)));
			}
			else g.setColor(Color.WHITE);
			
			//draw the line of the fractal
			g.drawLine((int)Math.round(currentLoc.x), (int)Math.round(currentLoc.y), (int)Math.round(newLoc.x), (int)Math.round(newLoc.y));
			
			//set the next location
			currentLoc = new Point2D.Double(newLoc.x, newLoc.y);
			
			//get the next direction
			if(i == 0) direction++;
			else direction--;
			direction = (direction + 4) % 4;
		}
		
		return img;
	}
	
	/**
	 * Get an array of directions the dragon curve will change in based on the model of this controller
	 * @return the array of values, 0
	 */
	public int[] calculateDragonDirections(){
		int[] dragon = new int[model.getIterations()];
		for(int i = 0; i < dragon.length; i++){
			if((i + 1) % 2 == 0) dragon[i] = dragon[i / 2];
			else if((i + 1) % 4 == 1) dragon[i] = 1;
			else dragon[i] = 0;
		}
		return dragon;
	}

	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setIterations(Integer.parseInt(params[0]));
			model.setScalar(Double.parseDouble(params[1]));
			model.setCenter(Double.parseDouble(params[2]), Double.parseDouble(params[3]));
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		
		return true;
	}

}
