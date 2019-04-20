package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Julia;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;

public class JuilaController extends FractalController{

	public static final int MAX_ITER = 1000;
	
	private Julia model;
	
	public JuilaController(Julia model){
		super();
		this.model = model;
	}
	
	public JuilaController(){
		this(null);
	}
	
	@Override
	public Fractal getModel() {
		return model;
	}
	public void setModel(Julia model){
		this.model = model;
	}

	@Override
	public boolean acceptParameters(String[] params){
		try{
			double x1 = Double.parseDouble(params[0]);
			double y1 = Double.parseDouble(params[1]);
			double x2 = Double.parseDouble(params[2]);
			double y2 = Double.parseDouble(params[3]);
			double real = Double.parseDouble(params[4]);
			double imaginary = Double.parseDouble(params[5]);
			
			model.setLocation(new Location(x1, y1, x2, y2));
			model.setConstant(new Complex(real, imaginary));
			
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		
		return true;
	}

	@Override
	public BufferedImage renderImage(){
		BufferedImage img = renderIterCounts();
		return img;
	}
	
	/**
	 * Finds the iteration counts of this fractal and renders the fractal to a 
	 * BufferedImage that is returned
	 * @return the BufferedImage with the rendered fractal
	 */
	public BufferedImage renderIterCounts(){
		int width = 800;
		int height = 800;

		int[][] iters = calculateIterCounts(width, height);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		for(int i = 0; i < img.getWidth(); i++){
	        for(int j = 0; j < img.getHeight(); j++){
	        	//select the color based on the iter count
	        	//select the color based on the iter count
	        	if(iters[i][j] == MAX_ITER) g.setColor(Color.BLACK);
	        	else{
	        		double red = (Math.sin(iters[i][j] * Math.PI / 2) + 1) * 127.0;
	        		double green = (Math.sin(iters[i][j] * Math.PI) + 1) * 127.0;
	        		double blue = (Math.cos(iters[i][j] * Math.PI / 2) + 1) * 127.0;
	        		g.setColor(new Color((int)red, (int)green, (int)blue));
	        	}
	        	//draw each point after determining color
	    	    g.drawLine(i, j, i, j);
	        }
	    }
		g.dispose();
		
		return img;
	}
	
	/**
	 * Calculates the values of every iteration of this fractal 
	 * The values calculated are based on the current x,y coordinates of the location of this fractal, 
	 * and are divided up 
	 * @param width the number of pixels in the width of the image that these iter counts will use
	 * @param height the number of pixels in the height of the image that these iter counts will use
	 * @return
	 */
	public int[][] calculateIterCounts(int width, int height){
		int[][] iterCounts = new int[width][height];
		
		double x1 = model.getLocation().getX1();
		double y1 = model.getLocation().getY1();
		double x2 = model.getLocation().getX2();
		double y2 = model.getLocation().getY2();
		
    	double dx = (x2 - x1);
    	double dy = (y2 - y1);
    	
    	double stepX = dx / width;
    	double stepY = dy / height;

        for(int x = 0; x < width; x++){
        	for(int y = 0; y < height; y++){
            	double realX = x1 + x * stepX;
            	double realY = y1 + y * stepY;
            	
                Complex c = new Complex(realX, realY);
                        
                iterCounts[x][y] = computeIterCount(c);
            }
        }
		return iterCounts;
	}
	
	/**
	 * Find the iteration count of the given complex number
	 * @param complex the number to find the iteration count
	 * @return the number of iterations	
	 */
	public int computeIterCount(Complex complex){
    	Complex c = new Complex(complex.getRealNum(), complex.getImagNum());
    	
    	//# of iterations
    	int count = 0;
    	//while z has magnitude of less than 2 and iteration counts its below the max
    	while(c.getMagnitude() < 4.0){
    		if (count >= MAX_ITER){
    			return MAX_ITER;
    		}
    		//iterate complex number
        	c = c.multiply(c);
        	c = c.add(model.getConstant());
        	//increment count
    		count++;
    	}
    	return count;
    }
}
