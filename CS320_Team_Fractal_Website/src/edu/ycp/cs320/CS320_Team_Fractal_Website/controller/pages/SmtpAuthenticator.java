package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String username = null;
		String password = null;


		Path pathToFile = Paths.get("login.txt");
		System.out.println(pathToFile.toAbsolutePath().toString());
		Stream<String> stream = null;
		try
		{
			stream = Files.lines(pathToFile);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> listLogin = stream.collect(Collectors.toList());
		ArrayList<String> arrayListLogin = new ArrayList<String>(listLogin);
		username = arrayListLogin.get(0);
		password = arrayListLogin.get(1);

		if((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0))
		{
			return new PasswordAuthentication(username, password);
		}
		return null;
	}

}
