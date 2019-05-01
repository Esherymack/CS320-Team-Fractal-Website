package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class SendEmail 
{
	public void sendEmail(User user, String subject, String messageText) throws IOException
	{
		String to = user.getEmail();
		String from = null;
		String fromUserEmailPassword = null;
		Path pathToFile = Paths.get("login.txt");
		System.out.println(pathToFile.toAbsolutePath().toString());
		Stream<String> stream = Files.lines(pathToFile);

		List<String> listLogin = stream.collect(Collectors.toList());
		ArrayList<String> arrayListLogin = new ArrayList<String>(listLogin);
		from = arrayListLogin.get(0);
		fromUserEmailPassword = arrayListLogin.get(1);

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		SmtpAuthenticator authentication = new SmtpAuthenticator();
		Session mailSession = Session.getDefaultInstance(properties, authentication);

		try
		{
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("(Do Not Reply) " + subject);
			message.setText(messageText);
			
			// Transport.send(message);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, from, fromUserEmailPassword);
			Transport.send(message);
			transport.close();
			stream.close();

		}
		catch(MessagingException mex)
		{
			mex.printStackTrace();
		}
	}
}
