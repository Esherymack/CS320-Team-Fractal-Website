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
		<div class="border">
		</div>
		<div class="center">
			<body id="home">
			
				<h1>Browse Fractals</h1>
				
				<div>
					</form>
					<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
						<input type="Submit" name="submit" value="Home">
					</form>
				</div>
				
				<div>
					<form action="${pageContext.servletContext.contextPath}/browseFractals" method="post">
						<table>
						        
						    <c:forEach items="${fractals}" var="fractal">
						        <tr>
						            <td class="nameCol">${fractal.name}</td>      
						        </tr>
						    </c:forEach>
						    
							<input type="Submit" name="viewFractals" value="View Fractals">
							
						</table>
					</form>
				</div>
				
			</body>
		</div>
		<div class="border">
		</div>
	</div>
	
</html>