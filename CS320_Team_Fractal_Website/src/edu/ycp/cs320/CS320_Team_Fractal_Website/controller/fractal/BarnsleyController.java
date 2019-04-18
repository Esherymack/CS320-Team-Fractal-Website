package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Barnsley;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BarnsleyController extends FractalController{
	
	public static final int SIZE = 500;
	
	private Barnsley model;
	
	public BarnsleyController(){
		this(null);
	}
	
	public BarnsleyController(Barnsley model){
		this.model = model;
	}
	
	@Override
	public boolean render(){
		//create a thread to render and calculate the set
		//TODO make this use multiple threads
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				BufferedImage img = renderBarnleyFern();
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
	
	/**
	 * Draws the Barnsley fern of the current model and returns the image of that fractal
	 * @return
	 */
	public BufferedImage renderBarnleyFern(){
		
		//specifics for render code came from https://rosettacode.org/wiki/Barnsley_fern
		
		//create the image object
		BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		
		//fill in the background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE, SIZE);
		
		//seed a random number generator
		Random rand = new Random();
		rand.setSeed(model.getSeed());
		
		//get parameters for initial state
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		//create the fractal
		g.setColor(new Color(0, 170, 0));
		for(int j = 0; j < 1000; j++)
		for(int i = 0; i < model.getIterations(); i++){
			//get the random number for this iteration
			double num = rand.nextDouble();
			
			if(num < .1){
				x2 = 0;
				y2 = 0.16 * y1;
			}
			else if(num < .86){
				x2 = -0.15 * x1 + 0.28 * y1;
				y2 = 0.26 * x1 + 0.24 * y1 + 0.44;
			}
			else if(num < .93){
				x2 = 0.2 * x1 - 0.26 * y1;
				y2 = 0.23 * x1 + 0.22 * y1 + 1.6;
			}
			else{
				x2 = 0.85 * x1 + 0.04 * y1;
				y2 = -0.04 * x1 + 0.85 * y2 + 1.6;
			}
			
			g.fillRect((int)Math.round(-100 * x2 + SIZE / 2),(int)Math.round(-100 * y2 + SIZE), 1, 1);
			
			x1 = x2;
			y1 = y2;
		}
		
		return img;
	}
	
	@Override
	public Fractal getModel(){
		return model;
	}
	public void setModel(Barnsley model){
		this.model = model;
	}

	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setF1(Double.parseDouble(params[0]));
			model.setF2(Double.parseDouble(params[1]));
			model.setF3(Double.parseDouble(params[2]));
			model.setF4(Double.parseDouble(params[3]));
			model.setSeed(Integer.parseInt(params[4]));
			model.setIterations(Integer.parseInt(params[5]));
		}catch(NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e){
			return false;
		}
		return true;
	}

}
