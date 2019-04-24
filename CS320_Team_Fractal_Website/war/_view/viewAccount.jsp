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
					<div>
						<c:if test="${! empty errorMessage}">
							<div class="invalid">${errorMessage}</div>
						</c:if>
					</div>
					<li>
						<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
							<input type="Submit" name="submit" value="Home">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/mainPage" method="doGet">
							<input type="Submit" name="submit" value="Create a fractal">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/browseFractals" method="doGet">
							<input type="Submit" name="submit" value="Browse all fractals">
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

			<div class="info">
					${firstName} ${lastName} | ${email}
			</div>
				
			<div class="account-header">
				<h1>
					Your Fractals
				</h1>

			<div>
				<h2>
					Your Fractals
				</h2>
				<c:if test="${display}">
					<br>
					<img src="img/result.png" alt="result"/>
				</c:if>
				<form action="${pageContext.servletContext.contextPath}/viewAccount" method="post">
					<table>
							<c:forEach items="${fractals}" var="fractal">
									<tr>
								<input type="Submit" name="viewFractal_${fractal.id}" value="${fractal.name} (${fractal.type}, ${fractal.id})">
								<br>
									</tr>
							</c:forEach>
					</table>
				</form>
			</div>
		</body>
		</div>
		<div class="border"></div>
	</div>

</html>
