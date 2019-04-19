package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

public class MandelbrotController extends FractalController{
	
	/**
	 * The maximum number of iterations that a calculation will take
	 */
	public static final int MAX_ITER = 4000;
	
	private Mandelbrot model;
	
	public MandelbrotController(Mandelbrot model){
		this.model = model;
	}
	
	public MandelbrotController(){
		this(null);
	}

	public Mandelbrot getModel(){
		return model;
	}
	
	public void setModel(Mandelbrot model){
		this.model = model;
	}

	/**
	 * params[0] = location.x1, 
	 * params[1] = location.y1, 
	 * params[2] = location.x2, 
	 * params[3] = location.y2, 
	 * params[4] = multiplyTimes
	 */
	@Override
	public boolean acceptParameters(String[] params){
		try{
			double x1 = Double.parseDouble(params[0]);
			double y1 = Double.parseDouble(params[1]);
			double x2 = Double.parseDouble(params[2]);
			double y2 = Double.parseDouble(params[3]);
			int multiplyTimes = Integer.parseInt(params[4]);
			
			model.setLocation(new Location(x1, y1, x2, y2));
			model.setMultiplyTimes(multiplyTimes);
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		
		return true;
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
		int width = 800;
		int height = 800;
		

		int[][] iters = calculateIterCounts(width, height);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		// TODO: move all this crap
		Color color0 = generateColor();
		Color color1 = generateColor();
		Color color2 = generateColor();
		Color color3 = generateColor();
		Color color4 = generateColor();
		Color color5 = generateColor();
		Color color6 = generateColor();  
		
		for(int i = 0; i < img.getWidth(); i++){
	        for(int j = 0; j < img.getHeight(); j++){
	        	//select the color based on the iter count
	        	//MAX_ITER * .8
	        	if (iters[i][j] <= 0) g.setColor(color0);
                else if (iters[i][j] > 55) g.setColor(color1);
                else if (iters[i][j] > 35) g.setColor(color2);
                else if (iters[i][j] > 20) g.setColor(color3);
                else if (iters[i][j] > 10) g.setColor(color4);
                else if (iters[i][j] > 5) g.setColor(color5);
                else g.setColor(color6);
	        	//draw each point after determining color
	    	    g.drawLine(i, j, i, j);
	        }
	    }
		g.dispose();
		
		return img;
	}
	
	
	/**
	 * Generates random colors for the image
	 * @return a new random color
	 */
	public Color generateColor()
	{
		// nextInt isn't inclusive so adding 1 to 255 to cover whole spectrum of RGB values
        int r = ThreadLocalRandom.current().nextInt(0, 256);
        int g = ThreadLocalRandom.current().nextInt(0, 256);
        int b = ThreadLocalRandom.current().nextInt(0, 256);
        
        Color color = new Color(r, g, b);
        return color;
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
		
    	double dx = (x2 - x1);
    	double dy = (y2 - y1);
    	
    	double step_x = dx / width;
    	double step_y = dy / height;

        for(int x = 0; x < width; x++)
        {
        	for(int y = 0; y < height; y++)
            {
            	double real_x = x1 + x * step_x;
            	double real_y = y1 + y * step_y;
            	
                Complex c = new Complex(real_x, real_y);
                        
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
