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
			<body id="home">
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
								<input type="Submit" name="submit" value="Browse All Fractals">
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/logout" method="post">
								<input type="Submit" name="submit" value="Log Out">
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
	
				<div class="lower-nav">
					<form action="${pageContext.servletContext.contextPath}/changePassword" method="doGet">
						<input type="Submit" name="submit" value="Change Password">
					</form>
				</div>
	
				<div class="account-header">
					<h2>
						Your Fractals
					</h2>
					<div class="lightbox-toggle">
						<form action="#content" method="post" href="#content">
							<table>
								<br>
								<div class="button-container">
									<c:forEach items="${fractals}" var="fractal">
										<tr>
											${fractal.name} | Type: ${fractal.type} | Gradient Type: ${fractal.gradientType} | Id: ${fractal.id} 
											<input type="Submit" name="viewFractal_${fractal.id}" value="Render" href="#content">
											<input type="Submit" name="deleteFractal_${fractal.id}" value="Delete" href="#content">
										</tr>
									</c:forEach>
								</div>
							</table>
						</form>
					</div>
					<c:if test="${display}">
						<div class="lightbox-content" id="content">
							<a href="#_">
								<img src="img/result.png" alt="result"/>
							</a>
						</div>
					</c:if>
				</div>
			</body>
		</div>
		<div class="border"></div>
	</div>

</html>
