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
						<div>
							<input type="Submit" name="viewAllFractals" value="View All Fractals">
							<div class="dropdown">
								<div class="dropbtn">Filter by Type</div>
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
								<div class="dropbtn">Filter by GradientType</div>
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
							<div class="dropdown">
								<ul class="searchbar">
									<li>
										<input type="text" name="searchForFractals" value="${charSeq}" placeholder="Search Term">
										<input type="Submit" name="search" value="Search">
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- Page number slector and page turner -->
					
					<input type="text" name="pageNumber" value="${pageNumber}">
					
					<c:if test="${! empty pageFractals}">
						<div class="filternav">
							<ul class="searchbar">
								<li>
									<select id="fractalsPerPage" name="fractalsPerPage" value="10">
										<option value="10" ${fractalsPerPage == "10" ? 'selected="selected"' : ''}>10 per page</option>
										<option value="20" ${fractalsPerPage == "20" ? 'selected="selected"' : ''}>20 per page</option>
										<option value="50" ${fractalsPerPage == "50" ? 'selected="selected"' : ''}>50 per page</option>
										<option value="100" ${fractalsPerPage == "100" ? 'selected="selected"' : ''}>100 per page</option>
									</select>
								</li>
							</ul>
						</div>
					</c:if>
				</form>
				<div>
					<c:if test="${! empty pageFractals}">
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
							
							<c:forEach items="${pageFractals}" var="fractal">
								<tr>
									<td class="browseGridText">${fractal.name}</td>
									<td class="browseGridText">${fractal.type}</td>
									<td class="browseGridText">${fractal.gradientType}</td>
									
									<c:set var="fValue" value="fractalUsername${fractal.id}" />
									<td class="browseGridText">${requestScope[fValue]}</td>
									
									<td class="browseGridText">${fractal.id}</td>
									<form action="#content" method="post" href="#content">
										<input type="hidden" id="fractalsPerPageSecond" name="fractalsPerPageSecond" value="${fractalsPerPage}">
										<input type="hidden" name="pageNumberSecond" value="${pageNumber}">
										<td class="browseGridText">
											<input type="Submit" name="viewFractal_${fractal.id}" value="Render" href="#content">
										</td>
									</form>
									<td class="browseGridText">
										<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download">
										<a id="downloadImage" href="img/result.png" download hidden></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty pageFractals}">
						<p class="error">No fractals met your criteria</p>
					</c:if>
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
	
	$(function(){
		$('#fractalTable').tablesorter();
	});
	
	</script>
	
</html>
