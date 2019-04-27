package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator
{
	public SmtpAuthenticator()
	{
		super();
	}
	
	@Override
	public PasswordAuthentication getPasswordAuthentication()
	{
		String username = "320fractalsite@gmail.com";
		String password = "";
		
		if((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0))
		{
			return new PasswordAuthentication(username, password);
		}
		return null;
	}

}
