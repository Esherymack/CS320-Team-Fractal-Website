package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.Color;

public class Gradient{
	;
	public static final String NONE = "None";
	public static final String RAINBOW = "Rainbow";
	public static final String HORIZONTAL = "Horizontal";
	public static final String VERTICAL = "Vertical";
	public static final String DIAGONAL = "Diagonal";
	
	/**
	 * All of the types of gradients that can be used
	 */
	public static final String[] TYPES = {
		NONE, RAINBOW, HORIZONTAL, VERTICAL, DIAGONAL
	};
	
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
	 * Get the color in the rainbow based on the given parameters
	 * @param rParam 0-1 inclusive, how much red is in the color
	 * @param gParam 0-1 inclusive, how much green is in the color
	 * @param bParam 0-1 inclusive, how much blue is in the color
	 * @return
	 */
	public Color getRainbowGradient(double rParam, double gParam, double bParam){
		int red = Math.max(0, Math.min(255, (int)(255.0 * rParam)));
		int green = Math.max(0, Math.min(255, (int)(255.0 * gParam)));
		int blue = Math.max(0, Math.min(255, (int)((255.0 * bParam))));
		
		float h = Gradient.getHue(
				(red + baseColor.getRed()) / 2,
				(green + baseColor.getGreen()) / 2,
				(blue + baseColor.getBlue()) / 2
		);
		
		float s = .6f;
		float b = .7f;
		
		return Color.getHSBColor(h, s, b);
	}

	/**
	 * Get the appropriate color for a pixel at the given location, 
	 * for a straight gradient
	 * given the width of a square and position the pixel is in
	 * @param x the x position in the width
	 * @param width the width relative to the x, can also be based on y or another position
	 * @return the color of the given location
	 */
	public Color getStraightGradientColor(double x, double width){
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
	 * Get the appropriate color for a pixel at the given location, 
	 * for a vertical gradient. 
	 * given the width of a square and position the pixel is in
	 * @param x the x position in the width
	 * @param width the width relative to the x
	 * @return the color of the given location
	 */
	public Color getDiagonalGradientColor(double x, double y, double width, double height){
		double diff = secondaryColor.getRed() - baseColor.getRed();
		int r = (int)((baseColor.getRed() + diff * ((x * y) / (width * height))));
		diff = secondaryColor.getGreen() - baseColor.getGreen();
		int g = (int)((baseColor.getGreen() + diff * ((x * y) / (width * height))));
		diff = secondaryColor.getBlue() - baseColor.getBlue();
		int b = (int)((baseColor.getBlue() + diff * ((x * y) / (width * height))));
		
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
