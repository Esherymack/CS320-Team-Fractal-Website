package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.Color;

public class Gradient{
	
	/**
	 * The color that this gradient starts at
	 */
	private Color baseColor;
	
	public Gradient(int r, int g, int b){
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));
		this.baseColor = new Color(r, g, b);
	}
	public Gradient(){
		this(0, 0, 0);
	}
	
	public Color getBaseColor(){
		return baseColor;
	}
	public void setBaseColor(Color baseColor){
		this.baseColor = baseColor;
	}
	
	/**
	 * Gets the hue of the color with the given RGB values. 
	 * R, G, and B must all be <= 255 and >= 0
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 * @return the hue as used by Color
	 */
	public static float getHue(int r, int g, int b){
		
		double red = r / 255.0;
		double green = g / 255.0;
		double blue = b / 255.0;
		
		double max = Math.max(red, Math.max(green, blue));
		double min = Math.min(red, Math.min(green, blue));

		if(max == min) return 0;
		else if(red == max) return (float)((green - blue) / (max - min));
		else if(green == max) return (float)(2 + (blue - red) / (max - min));
		else return (float)(4 + (red - green) / (max - min));
	}
	
	public static float getHue(Color c){
		return getHue(c.getRed(), c.getGreen(), c.getBlue());
	}
	
}
