package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;

public class KochController extends FractalController{
	
	public static final int SIZE = 500;
	
	private Koch model;
	
	public KochController(){
		super();
	}
	
	@Override
	public Fractal getModel(){
		return model;
	}
	
	public void setModel(Koch model){
		this.model = model;
	}
	
	@Override
	public boolean render(){
		//create a thread to render and calculate the set
		//TODO make this use multiple threads
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics g = img.getGraphics();
				
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, SIZE, SIZE);
				
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

	@Override
	public boolean acceptParameters(String[] params){
		try{
			model.setIterations(Integer.parseInt(params[0]));
		}catch(NumberFormatException e){
			return false;
		}
		
		return true;
	}

}
