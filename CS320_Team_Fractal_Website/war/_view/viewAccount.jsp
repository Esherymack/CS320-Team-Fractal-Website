<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<head>
		<title>View Account</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	<div class="flex-container">
		<div class="border"></div>
		<div class="center">
		<body>
			<div class="topnav">
				<ul>
					<li>
						<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
							<input type="Submit" name="submit" value="Home">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
							<input type="Submit" name="submit" value="Currently logged in as ${userName}">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/logout" method="post">
							<input type="Submit" name="submit" value="LogOut">
						</form>
					</li>
				</ul>
			</div>
			<div class="account-header">
				<h1>
					${userName}
				</h1>
			</div>

			<div>
				<h2>
					Your Fractals
				</h2>
			</div>
		</body>
		</div>
		<div class="border"></div>
	</div>

</html>
