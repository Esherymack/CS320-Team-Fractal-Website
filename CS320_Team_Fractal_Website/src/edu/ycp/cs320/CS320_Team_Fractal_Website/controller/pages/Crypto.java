package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import com.lambdaworks.crypto.SCryptUtil;

public class Crypto 
{
	public String encrypt(String password)
	{
		String encryptedPassword = SCryptUtil.scrypt(password, 16, 16, 16);
		System.out.println("Hashed password: " + encryptedPassword);
		return encryptedPassword;
	}
	
	public boolean match(String originalPassword, String pass)
	{
		return SCryptUtil.check(originalPassword, pass);
	}
}
