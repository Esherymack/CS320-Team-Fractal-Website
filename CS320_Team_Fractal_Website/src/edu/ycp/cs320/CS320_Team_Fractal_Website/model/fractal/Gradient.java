package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.Color;

public class Gradient{
	
	/**
	 * The color that this gradient starts at, used for normal gradient and as the base for rainbow
	 */
	private Color baseColor;
	/**
	 * The color that this gradient fades to, used only by normal graident
	 */
	private Color secondaryColor;
	
	public Gradient(int r, int g, int b){
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));
		this.baseColor = new Color(r, g, b);
		
		this.secondaryColor = new Color(255, 255, 255);
	}
	public Gradient(){
		this(0, 0, 255);
	}

	public Color getBaseColor(){
		return baseColor;
	}
	public void setBaseColor(Color baseColor){
		this.baseColor = baseColor;
	}
	
	public Color getSecondaryColor(){
		return secondaryColor;
	}
	public void setSecondaryColor(Color secondaryColor){
		this.secondaryColor = secondaryColor;
	}
	
	/**
	 * Get the appropriate color for a pixel at the given location, 
	 * for a horizontal gradient. 
	 * given the width of a square and position the pixel is in
	 * @param x the x position in the width
	 * @param width the width relative to the x
	 * @return the color of the given location
	 */
	public Color getHorizontalGradientColor(double x, double width){
		double diff = secondaryColor.getRed() - baseColor.getRed();
		int r = (int)((baseColor.getRed() + diff * (x / width)));
		diff = secondaryColor.getGreen() - baseColor.getGreen();
		int g = (int)((baseColor.getGreen() + diff * (x / width)));
		diff = secondaryColor.getBlue() - baseColor.getBlue();
		int b = (int)((baseColor.getBlue() + diff * (x / width)));
		
		r = Math.max(0, Math.min(255, r));
		g = Math.max(0, Math.min(255, g));
		b = Math.max(0, Math.min(255, b));
		return new Color(r, g, b);
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

	/**
	 * Gets the hue of the color with the given RGB values. 
	 * R, G, and B must all be <= 255 and >= 0
	 * @param c the color to get the hue of
	 * @return the hue as used by Color
	 */
	public static float getHue(Color c){
		return getHue(c.getRed(), c.getGreen(), c.getBlue());
	}
	
}
