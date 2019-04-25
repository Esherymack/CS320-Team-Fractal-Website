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
	private int iterations;
	
	public Barnsley(){
		this(0, 0, 0, 0, 0, 1);
	}
	public Barnsley(double f1, double f2, double f3, double f4, int seed, int iterations){
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.seed = seed;
		this.iterations = iterations;
	}

	@Override
	public String getInfo(){
		return	"The Barnsley Fern creates a fern like structure, hence the name. " +
				"The fern is generated by randomly choosing one of four transformations " + 
				"and drawing a point at the new location. This is repeated once for each " +
				"iteration, so a very high iteration count must be used for a substantial " +
				"fractal to generate.";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>You can change the appearance of the fern by adjusting F1, F2, F3, and F4.</li>"
				+ "<li>Changing F1 will modify the stem.</li>"
				+ "<ul><li>Setting it to 0 will remove it completely</li>"
				+ "<li>Keeping the value between -0.5 to 0.5 will yield best results.</li></ul>"
				+ "<li>Changing F2 will modify the successively smaller leaflets.</li>"
				+ "<ul><li>Setting it to 0 will remove them completely.</li>"
				+ "<li>Keeping the value between 0 and 1 will yield best results.</li></ul>"
				+ "<li>Changing F3 will modify the largest left-hand leaflet.</li>"
				+ "<ul><li>Setting it to 0 will remove all left-hand leaves.</li>"
				+ "<li>Keeping the value between 0 and 0.5 will yield best results.</li>"
				+ "<li>Setting this value significantly higher than F4 will outweigh F4.</li></ul>"
				+ "<li>Changing F4 will modify the largest right-hand leaflet.</li>"
				+ "<ul><li>Setting it to 0 will do nothing visually different.</li></ul></ul>";
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		setF1(0.01);
		setF2(0.85);
		setF3(0.07);
		setF4(0.07);
		setSeed(0);
		setIterations(100000);
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				"" + f1,
				"" + f2,
				"" + f3,
				"" + f4,
				"" + seed,
				"" + iterations,
				"",
				"",
				"",
				""
		};
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"F1: ",
				"F2: ",
				"F3: ",
				"F4: ",
				"Seed: ",
				"Iterations: ",
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
		return iterations;
	}
	public void setIterations(int iterations){
		this.iterations = iterations;
	}

}
