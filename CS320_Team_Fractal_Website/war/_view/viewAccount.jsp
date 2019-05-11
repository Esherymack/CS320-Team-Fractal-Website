<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
		
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
				<div class="info">
						Your user status is: ${adminStatus}
				</div>
	
				<div class="lower-nav">
					<form action="${pageContext.servletContext.contextPath}/changePassword" method="doGet">
						<input type="Submit" name="submit" value="Change Password">
					</form>
				</div>
	
				<div>
					<tr>
						<td>
							<h1>
								Your Fractals
							</h1>
						</td>
						<td>
							<p>
								Previously rendered is downloaded
							</p>
						</td>
						<td>
							<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download">
							<a id="downloadImage" href="img/result.png" download hidden></a>
						</td>
						<td>
							<p></p>
						</td>
					</tr>
				</div>
				
				<div class="lightbox-toggle">
				
					<table class="browseGridContainer">
						<tr>
							<th class="browseGridText">Name:</th>
							<th class="browseGridText">Type:</th>
							<th class="browseGridText">Gradient Type:</th>
							<th class="browseGridText">ID:</th>
							<th class="browseGridText"></th>
							<th class="browseGridText"></th>
							<th class="browseGridText"></th>
							<th class="browseGridText"></th>
						</tr>
					
						<c:forEach items="${fractals}" var="fractal">
							<tr>
								<td class="browseGridText">${fractal.name}</td>
								<td class="browseGridText">${fractal.type}</td>
								<td class="browseGridText">${fractal.gradientType}</td>
								<td class="browseGridText">${fractal.id}</td>
								<td class="browseGridText">
									<form action="#content" method="post" href="#content">
										<input type="Submit" name="viewFractal_${fractal.id}" value="Render" href="#content">
									</form>
								</td>
								<td class="browseGridWarning">
									<form action="#content" method="post" href="#content">
										<input class="browseGridWarning" type="Submit" name="deleteFractal_${fractal.id}" value="Delete" href="#content">
									</form>
								</td>
								<form action="#content" method="post" href="#content">
									<td class="browseGridTextInput">
										<input type="text" name="renameFractal" value="${rename}" placeholder="New Name">
									</td>
									<td class="browseGridTextInput">
										<input type="Submit" name="renameFractal_${fractal.id}" value="Rename" href="#content">
									</td>
								</form>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<c:if test="${display}">
					<div class="lightbox-content" id="content">
						<a href="#_">
							<img src="img/result.png" alt="result"/>
						</a>
					</div>
				</c:if>
			</body>
		</div>
		<div class="border"></div>
	</div>

	<script>
	
	$(window).scroll(function(){
		sessionStorage.scrollTop = $(this).scrollTop();
	});

	$(document).ready(function(){
		$(window).scrollTop(sessionStorage.scrollTop);
	});
	</script>
	
</html>
