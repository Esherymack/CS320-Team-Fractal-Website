package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Barnsley;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;

public class BarnsleyController extends FractalController{
	
	public static final int SIZE = 500;
	
	private Barnsley model;
	
	public BarnsleyController(Barnsley model){
		super();
		this.model = model;
	}
	public BarnsleyController(){
		this(null);
	}
	
	@Override
	public Fractal getModel(){
		return model;
	}
	public void setModel(Barnsley model){
		this.model = model;
	}

	/*
	 * params[0] = f1
	 * params[1] = f2
	 * params[2] = f3
	 * params[3] = f4
	 * params[4] = seed
	 * params[5] = iterations
	 */
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
	
	@Override
	public BufferedImage renderImage(){
		BufferedImage img = renderBarnleyFern();
		return img;
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SIZE, SIZE);
		
		//seed a random number generator
		Random rand = new Random();
		rand.setSeed(model.getSeed());
		
		//get parameters for initial state
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		
		//list of probabilities for which option of the fern to take
		double[] chances = new double[4];
		for(int i = 0; i < 4; i++) chances[i] += model.getF1();
		for(int i = 1; i < 4; i++) chances[i] += model.getF2();
		for(int i = 2; i < 4; i++) chances[i] += model.getF3();
		for(int i = 3; i < 4; i++) chances[i] += model.getF4();
		
		//create the fractal
		g.setColor(new Color(0, 170, 0));
		for(int i = 0; i < model.getIterations(); i++){
			//get the random number for this iteration
			double num = rand.nextDouble();
			
			if(num < chances[0]){
				x2 = 0;
				y2 = 0.16 * y1;
			}
			else if(num < chances[1]){
				x2 = 0.85 * x1 + 0.04 * y1;
				y2 = -0.04 * x1 + 0.85 * y2 + 1.6;
			}
			else if(num < chances[2]){
				x2 = 0.2 * x1 - 0.26 * y1;
				y2 = 0.23 * x1 + 0.22 * y1 + 1.6;
			}
			else{
				x2 = -0.15 * x1 + 0.28 * y1;
				y2 = 0.26 * x1 + 0.24 * y1 + 0.44;
			}
			
			double x = SIZE / 2 + (SIZE * .95 / 4.8378) * x2 - 20;
			double y = SIZE - (SIZE * .95 / 9.99983) * y2 - (SIZE * .05) + 20;
			
			//pick color
			if(noGradient()){
				g.setColor(Color.WHITE);
			}
			else{
				int red = (int)(Math.max(Math.min(255.0 * (x / SIZE), 255), 0));
				int green = (int)(Math.max(Math.min(255.0 * (y / SIZE), 255), 0));
				int blue = (int)(Math.max(Math.min(255.0 * ((x * y) / (SIZE * SIZE)), 255), 0));
	
				Color gColor = getGradient().getBaseColor();
				
				float h = Gradient.getHue(
						(red + gColor.getRed()) / 2,
						(green + gColor.getGreen()) / 2,
						(blue + gColor.getBlue()) / 2
				);
				
				float s = .6f;
				float b = .7f;
				Color c = Color.getHSBColor(h, s, b);
				g.setColor(c);
			}
			
			//draw pixel
			g.fillRect((int)Math.round(x), (int)Math.round(y), 1, 1);
			
			x1 = x2;
			y1 = y2;
		}
		
		return img;
	}

}
