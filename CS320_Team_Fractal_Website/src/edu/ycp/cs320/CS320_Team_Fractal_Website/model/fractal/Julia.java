package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.JuilaController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;

public class Julia extends Fractal{
	
	/**
	 * The constant number added to the iteration each time during a calculation
	 */
	private Complex constant;
	
	public Julia(Complex constant){
		this.constant = constant;
	}
	public Julia(){
		this(new Complex(0, 0));
	}
	
	@Override
	public String getInfo() {
		return "Julia set fractal";
	}

	@Override
	public String[] getParameters() {
		return new String[]{
				"" + getLocation().getX1(),
				"" + getLocation().getY1(),
				"" + getLocation().getX2(),
				"" + getLocation().getY2(),
				"" + constant.getRealNum(),
				"" + constant.getImagNum(),
				"",
				"",
				"",
				""
		};
	}
	
	@Override
	public void setDefaultParameters(){
		setLocation(getDefaultLocation());
		constant = new Complex(0, 0);
	}
	
	@Override
	public Location getDefaultLocation() {
		return new Location(-2, -2, 2, 2);
	}
	
	@Override
	public String[] getParamLabels(){
		return new String[]{
				"X1: ",
				"Y1: ",
				"X2: ",
				"Y2: ",
				"Real const: ",
				"Imaginary const: ",
				"",
				"",
				"",
				""
		};
	}

	@Override
	public FractalController createApproprateController(){
		JuilaController controller = new JuilaController();
		controller.setModel(this);
		return controller;
	}
	
	public Complex getConstant(){
		return constant;
	}
	public void setConstant(Complex constant){
		this.constant = constant;
	}

}
