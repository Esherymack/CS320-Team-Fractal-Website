package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.renderer.Complex;

public class MandelbrotController extends FractalController{
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
				OutputStream os = null;
				try{
					os = new BufferedOutputStream(new FileOutputStream("./war/img/result.png"));
					ImageIO.write(img, "PNG", os);
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}catch (IOException e){
					e.printStackTrace();
				}
				if(os != null)
					try{
						os.close();
					}catch (IOException e){
						e.printStackTrace();
					}
				
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
	        	//select the color based on the iter count TODO make this scale based on a max iter count variable
	        	if (iters[i][j] <= 0) g.setColor(Color.black);
	        	else if (iters[i][j] > 800) g.setColor(Color.magenta);
	        	else if (iters[i][j] > 600) g.setColor(Color.blue);
	        	else if (iters[i][j] > 400) g.setColor(Color.green);
	        	else if (iters[i][j] > 200) g.setColor(Color.yellow);
	        	else if (iters[i][j] > 100) g.setColor(Color.orange);
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
    		if (count > 1000){
    			return 0;
    		}
    		//iterate complex number
    		//TODO allow this to use the multiplyTimes value
        	z = z.multiply(z).add(c);
        	//increment count
    		count++;
    	}
    	return count;
    }
}
