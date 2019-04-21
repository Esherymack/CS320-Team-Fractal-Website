<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Browse Fractals</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	
	<div class="flex-container">
		<div class="border"></div>
		<div class="center">
			<body id="home">
				
				<div>
					<c:if test="${! empty errorMessage}">
						<div class="invalid">${errorMessage}</div>
					</c:if>
				</div>
				
				<div class="topnav">
					<ul>
						<li>
							<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
								<input type="Submit" name="submit" value="Home">
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
								<input type="Submit" name="submit" value="${currentlyLoggedInMessage}">
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/mainPage" method="doGet">
								<input type="Submit" name="submit" value="Create a fractal">
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
					<h1>Browse Fractals</h1>
				</div>
				
				<div class="filternav">
					
					<c:if test="${display}">
						<img src="img/result.png" alt="result"/>
					</c:if>
					
					<form action="${pageContext.servletContext.contextPath}/browseFractals" method="post">
						<ul>
							<li>
								<input type="Submit" name="viewAllFractals" value="View All Fractals">
							</li>
							<c:forEach items="${fractalTypes}" var="type">
								<li>
									<input type="Submit" name="viewAll${type}Fractals" value="View ${type} Fractals">
								</li>
							</c:forEach>
							<li>
								<input type="text" name="name" value="${name}">
								<input type="Submit" name="searchForFractals" value="Search For Fractals">
							</li>
							<li>
								<input type="text" name="charSeq" value="${charSeq}">
								<input type="Submit" name="searchForFractals2" value="Search For Fractals2">
							</li>
						</ul>
							
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