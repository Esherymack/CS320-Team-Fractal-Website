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
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
			
			<div class="type">
				<select>
					<option value="0">Sierpinski</option>
					<option value="1">Other</option>
				</select>
			</div>
			
			<div class="info">
			</div>
			
			<div class="parameters">
				<form action="${pageContext.servletContext.contextPath}/mainPage" method="post">
					<table>
						<tr>
							<td class="label">Level</td>
							<td><input type="text" name="level" size="12" value="${level}" /></td>
						</tr>
						<tr>
							<td class="label">Height</td>
							<td><input type="text" name="height" size="12" value="${height}" /></td>
						</tr>
					</table>
					<table>
						<!-- Here is where the fields for params goes -->
						
					</table>
					<input type="Submit" name="submit" value="Send">
				</form>
			</div>
			
			<div class="gradient"</div>
			</div>
			
			<div class="save">
			</div>
			
			<div class="share">
			</div>
		</div>
	</body>
</html>