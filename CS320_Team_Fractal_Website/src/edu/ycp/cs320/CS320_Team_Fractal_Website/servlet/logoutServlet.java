package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logoutServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user"))
				{
					loginCookie = cookie;
					break;
				}
			}
		}
		if(loginCookie != null)
		{
			loginCookie.setMaxAge(0);
			resp.addCookie(loginCookie);
		}
		resp.sendRedirect("logIn");
	}

}
