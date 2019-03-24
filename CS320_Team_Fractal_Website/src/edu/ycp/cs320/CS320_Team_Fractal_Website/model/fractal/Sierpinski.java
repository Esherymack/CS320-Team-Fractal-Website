package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

public class Sierpinski extends Fractal{
	
	public static final int SIZE = 500;
	
	private int level;
	private double height;
	private Point2D.Double p1;
	private Point2D.Double p2;
	private Point2D.Double p3;
	
	public Sierpinski(){
		super();
	}
	
	@Override
	public Location getDefaultLocation(){
		return new Location(0, 0, SIZE, SIZE);
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		
		level = 0;
		height = Math.round(SIZE * Math.sqrt(3.0) / 2.0);

		this.p1 = new Point2D.Double(0, height);
		this.p2 = new Point2D.Double(SIZE / 2, 0);
		this.p3 = new Point2D.Double(SIZE, height);
	}

	public int getLevel(){
		return level;
	}
	public void setLevel(int levelParam){
		this.level = levelParam;
	}

	public double getHeight(){
		return this.height;
	}
	public void setHeight(double height){
		this.height = height;
	}
	
	public void setP1(Point2D.Double p){
		this.p1 = p;
	}
	public void setP2(Point2D.Double p){
		this.p2 = p;
	}
	public void setP3(Point2D.Double p){
		this.p3 = p;
	}
	
	public Point2D.Double getP1(){
		return p1;
	}
	public Point2D.Double getP2(){
		return p2;
	}
	public Point2D.Double getP3(){
		return p3;
	}
}
