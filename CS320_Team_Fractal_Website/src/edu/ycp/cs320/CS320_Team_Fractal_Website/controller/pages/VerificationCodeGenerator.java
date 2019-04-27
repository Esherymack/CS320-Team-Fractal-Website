package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

public class VerificationCodeGenerator 
{
	public static String getAlphaNumericString()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                					+ "0123456789"
                					+ "abcdefghijklmnopqrstuvxyz";
		
		StringBuilder sb = new StringBuilder(10);
		
		for (int i = 0; i < 10; i++)
		{
			int index = (int)(AlphaNumericString.length() * Math.random());
			
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}
}
