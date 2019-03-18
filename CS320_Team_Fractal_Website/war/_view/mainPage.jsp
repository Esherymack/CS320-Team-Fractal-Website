<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Fractal Main</title>
	</head>
	
	<body>
		<p>Main page of the fractal site</p>
		
		<nav class="topnav">
			<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
				<input type="Submit" name="submit" value="Home">
			</form>
			<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
				<input type="Submit" name="submit" value="Login">
			</form>
		</nav>
		
		<div class="renderer">
		</div>
		<div class="interface">
		</div>
	</body>
</html>