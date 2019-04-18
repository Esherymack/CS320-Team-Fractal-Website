package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.BarnsleyController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;

public class Barnsley extends Fractal{
	
	/**
	 * Variable used for determining the shape of the Barnsley Fern
	 */
	private double f1, f2, f3, f4;
	
	/**
	 * The random seed used to generate this fern
	 */
	private int seed;
	
	/**
	 * The number of iterations the fractal will generate for
	 */
	private int itterations;
	
	public Barnsley(){
		this(0, 0, 0, 0, 0, 1);
	}
	public Barnsley(double f1, double f2, double f3, double f4, int seed, int iterations){
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.seed = seed;
		this.itterations = iterations;
	}

	@Override
	public String getInfo(){
		return "Barnsley Fern fractal (add a proper description later)";
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		setF1(0);
		setF2(0);
		setF3(0);
		setF4(0);
		setSeed(0);
		setIterations(1);
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				"" + f1,
				"" + f2,
				"" + f3,
				"" + f4,
				"" + seed,
				"" + itterations,
				"",
				"",
				"",
				""
		};
	}

	@Override
	public FractalController createApproprateController(){
		BarnsleyController controller = new BarnsleyController();
		controller.setModel(this);
		return controller;
	}
	
	public double getF1(){
		return f1;
	}
	public void setF1(double f1){
		this.f1 = f1;
	}

	public double getF2(){
		return f2;
	}
	public void setF2(double f2){
		this.f2 = f2;
	}

	public double getF3(){
		return f3;
	}
	public void setF3(double f3){
		this.f3 = f3;
	}

	public double getF4(){
		return f4;
	}
	public void setF4(double f4){
		this.f4 = f4;
	}

	public int getSeed(){
		return seed;
	}
	public void setSeed(int seed){
		this.seed = seed;
	}

	public int getIterations(){
		return itterations;
	}
	public void setIterations(int iterations){
		this.itterations = iterations;
	}

}
