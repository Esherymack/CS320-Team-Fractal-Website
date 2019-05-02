<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Browse Fractals</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
		
		<script src="sorttable.js"></script>
		
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
								<input type="Submit" name="submit" value="Log Out">
							</form>
						</li>
					</ul>
				</div>

				<div class="account-header">
					<h1>Browse Fractals</h1>
				</div>
			
				<form action="${pageContext.servletContext.contextPath}/browseFractals" method="post">
					<div class="filternav">
						<div class="filtercontainer">
							<input type="Submit" name="viewAllFractals" value="View All Fractals">
								
							<div class="dropdown">
								<button class="dropbtn">Filter by Type</button>
								<div class="dropdown-content">
									<ul>
										<c:forEach items="${fractalTypes}" var="type">
											<li>
												<input type="Submit" name="viewAll${type}Fractals" value="View ${type} Fractals">
											</li>
											<br>
										</c:forEach>
									</ul>	
								</div>
							</div>
							<div class="dropdown">
								<button class="dropbtn">Filter by GradientType</button>
								<div class="dropdown-content">
									<ul>
										<c:forEach items="${gradientTypes}" var="type">
											<li>
												<input type="Submit" name="viewAll${type}Fractals" value="View ${type} Fractals">
											</li>
											<br>
										</c:forEach>
									</ul>	
								</div>
							</div>
							<ul class="searchbar">
								<li>
									<form action="${pageContext.servletContext.contextPath}/browseFractals" method="post">
										<input type="text" name="searchForFractals" value="${charSeq}" placeholder="Search Term">
										<input type="Submit" name="search" value="Search">
									</form>
								</li>
							</ul>
						</div>
					</div>
					<!-- Page number slector and page turner -->
					<div class="searchbar">
						<li>
							<input type="hidden" id="fractalsPerPageChoice" name="fractalsPerPageChoice" value="test">
							<select id="fractalsPerPage" name="fractalsPerPage">
								<option value="10">10 per page</option>
								<option value="20">20 per page</option>
								<option value="50">50 per page</option>
								<option value="100">100 per page</option>
							</select>
						</li>
					</ul>
				</form>
				<div>
					<table class="sortable" id="fractalTable">
						<tr>
							<th class="browseGridText">Name:</th>
							<th class="browseGridText">Type:</th>
							<th class="browseGridText">Gradient Type:</th>
							<th class="browseGridText">Created by:</th>
							<th class="browseGridText">ID:</th>
							<th class="browseGridText"></th>
							<th class="browseGridText"></th>
						</tr>
						
						<c:forEach items="${fractals}" var="fractal">
							<tr>
								<td class="browseGridText">${fractal.name}</td>
								<td class="browseGridText">${fractal.type}</td>
								<td class="browseGridText">${fractal.gradientType}</td>
								
								<c:set var="fValue" value="fractalUsername${fractal.id}" />
								<td class="browseGridText">${requestScope[fValue]}</td>
								
								<td class="browseGridText">${fractal.id}</td>
								<td class="browseGridText">
									<form action="#content" method="post" href="#content">
									 	<input type="Submit" name="viewFractal_${fractal.id}" value="Render" href="#content">
									</form>
								</td>
								<td class="browseGridText">
									<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download">
									<a id="downloadImage" href="img/result.png" download hidden></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="lightbox-content" id="content">
					<c:if test="${display}">
						<a href="#_">
							<img src="img/result.png" alt="result"/>
						</a>
					</c:if>
				</div>
			</body>
		</div>
		<div class="border"></div>
	</div>
	
	<script>
	
	window.onload = function(){
		$('#fractalsPerPage').change();
	}
	
	$('#fractalsPerPage').change(function(){
		window.alert("yes");
		document.getElementById("fractalsPerPageChoice").setAttribute("value", $(this).val());
	});
	
	$(function(){
		$('#fractalTable').tablesorter();
	});
	
	</script>
	
</html>
