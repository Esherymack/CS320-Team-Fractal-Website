<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
		
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
												<input type="Submit" name="viewAllGradient${type}Fractals" value="View ${type} Fractals">
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
					
					<div>
						<tr>
							<td>
								<p>
									Previously rendered is downloaded
								</p>
							</td>
							<td class="browseGridText">
								<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download">
								<a id="downloadImage" href="img/result.png" download hidden></a>
							</td>
							<td>
								<p></p>
							</td>
						</tr>
					</div>
					
					<!-- Page number selector and page turner -->
					<input type="hidden" name=pageNumber value="${pageNumber}">
					
					<c:if test="${! empty pageFractals}">
						<div class="filternav">
							<ul class="searchbar">
								<li>
									<select id="fractalsPerPage" name="fractalsPerPage" value="10">
										<option value="5" ${fractalsPerPage == "5" ? 'selected="selected"' : ''}>5 per page</option>
										<option value="10" ${fractalsPerPage == "10" || empty fractalsPerPage ? 'selected="selected"' : ''}>10 per page</option>
										<option value="20" ${fractalsPerPage == "20" ? 'selected="selected"' : ''}>20 per page</option>
										<option value="50" ${fractalsPerPage == "50" ? 'selected="selected"' : ''}>50 per page</option>
										<option value="100" ${fractalsPerPage == "100" ? 'selected="selected"' : ''}>100 per page</option>
									</select>
								</li>
							</ul>
							<ul class="searchbar">
								<li>
									<input type="submit" name="pageStart" value="<<"}>
									
									<c:if test="${pageNumber > 1}">
										<input type="submit" name="page-2" value="${pageNumber - 1}">
									</c:if>
									<c:if test="${pageNumber > 0}">
										<input type="submit" name="page-1" value="${pageNumber}">
									</c:if>
									
									<input class="browsePageBold" type="submit" disabled value="${pageNumber + 1}">
										
									<c:if test="${pageNumber < maxPageNumber}">
										<input type="submit" name="page+1" value="${pageNumber + 2}">
									</c:if>
									<c:if test="${pageNumber < maxPageNumber - 1}">
										<input type="submit" name="page+2" value="${pageNumber + 3}">
									</c:if>
									
									<input type="submit" name="pageEnd" value=">>">
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
								<c:if test="${userType == 'Admin'}">
									<th class="browseGridText"></th>
								</c:if>
							</tr>
							
							<form action="#content" method="post" href="#content">
								<input type="hidden" id="fractalsPerPageSecond" name="fractalsPerPageSecond" value="${fractalsPerPage}">
								<input type="hidden" name="pageNumberSecond" value="${pageNumber}">
								<c:forEach items="${pageFractals}" var="fractal">
									<tr>
										<td class="browseGridText">${fractal.name}</td>
										<td class="browseGridText">${fractal.type}</td>
										<td class="browseGridText">${fractal.gradientType}</td>
										
										<c:set var="fValue" value="fractalUsername${fractal.id}" />
										<td class="browseGridText">${requestScope[fValue]}</td>
										
										<td class="browseGridText">${fractal.id}</td>
											<td class="browseGridText">
												<input type="Submit" name="viewFractal_${fractal.id}" value="Render" href="#content">
											</td>
										
										<c:if test="${userType == 'Admin'}">
											<td class="browseGridWarning">
												<input class="browseGridWarning" type="Submit" name="deleteFractal_${fractal.id}" value="Delete">
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</form>
						</table>
					</c:if>
					<c:if test="${empty pageFractals}">
						<p class="error">No fractals met your criteria</p>
					</c:if>
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

	$('#fractalsPerPage').change(function(){
		var val = $(this).val();
		
		document.getElementById("fractalsPerPage").setAttribute("value", val);
		document.getElementById("fractalsPerPageSecond").setAttribute("value", val);
	});
	
	$(function(){
		$('#fractalTable').tablesorter();
	});
	
	</script>
	
</html>
