package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import src.main.java.com.lambdaworks.crypto.SCryptUtil;

public class Crypto 
{
	public String encrypt(String password)
	{
		return SCryptUtil.scrypt(password, 16, 16, 16);
	}
	
	public boolean doesMatch(String originalPassword, String pass)
	{
		return SCryptUtil.check(originalPassword, pass);
	}
}
