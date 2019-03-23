package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Mandelbrot;

public class MandelbrotController extends FractalController{
	
	/**
	 * The maximum number of iterations that a calculation will take
	 */
	public static final int MAX_ITER = 1000;
	
	private Mandelbrot model;
	
	public MandelbrotController(Mandelbrot model){
		this.model = model;
	}
	
	@Override
	public boolean render(){
		//create a thread to render and calculate the set
		//TODO make this use multiple threads
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				BufferedImage img = renderIterCounts();
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
	
	private BufferedImage renderIterCounts(){
		int width = 500;
		int height = 500;
		int[][] iters = calculateIterCounts(width, height);
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		for(int i = 0; i < img.getWidth(); i++){
	        for(int j = 0; j < img.getHeight(); j++){
	        	//select the color based on the iter count
	        	if (iters[i][j] <= 0) g.setColor(Color.black);
	        	else if (iters[i][j] > MAX_ITER * .8) g.setColor(Color.magenta);
	        	else if (iters[i][j] > MAX_ITER * .6) g.setColor(Color.blue);
	        	else if (iters[i][j] > MAX_ITER * .4) g.setColor(Color.green);
	        	else if (iters[i][j] > MAX_ITER * .2) g.setColor(Color.yellow);
	        	else if (iters[i][j] > MAX_ITER * .1) g.setColor(Color.orange);
	        	else g.setColor(Color.red);
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
	private int[][] calculateIterCounts(int width, int height){
		int[][] iterCounts = new int[width][height];
		
		double x1 = model.getLocation().getX1();
		double y1 = model.getLocation().getY1();
		double x2 = model.getLocation().getX2();
		double y2 = model.getLocation().getY2();
		
		for(int i = 0; i < iterCounts.length; i++){
            for(int j = 0; j < iterCounts[i].length; j++){
            	double dx = x2 - x1;
            	double dy = y2 - y1;
                Complex c = new Complex(
                		dx * ((double)i / iterCounts.length) + x1,
                		dy * ((double)j / iterCounts[i].length) + y1
                );
                int iterCount = computeIterCount(c);
                iterCounts[i][j] = iterCount;
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
    	//Z is initially 0+0i
    	Complex z = new Complex(0,0);
    	Complex c = new Complex(complex.getRealNum(), complex.getImagNum());
    	
    	//# of iterations
    	int count = 0;
    	//while z has magnitude of less than 2 and iteration counts its below the max
    	while (z.getMagnitude() < 2.0){
    		if (count > MAX_ITER){
    			return 0;
    		}
    		//iterate complex number
    		for(int i = 1; i < model.getMultiplyTimes(); i++){
    			z = z.multiply(z);
    		}
        	z = z.multiply(z).add(c);
        	//increment count
    		count++;
    	}
    	return count;
    }
}
