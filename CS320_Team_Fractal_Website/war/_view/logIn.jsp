<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Log In</title>
			<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

	<body>
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>
			
			<form action="${pageContext.servletContext.contextPath}/logIn" method="post">
				<table>
					<tr>
						<td class="label">Username:</td>
						<td><input type="text" name="username" size="12" value="${username}" /></td>
					</tr>
					<tr>
						<td class="label">Password:</td>
						<td><input type="text" name="password" size="12" value="${password}" /></td>
					</tr>
				</table>
				<input type="Submit" name="submit" value="Log In">
			</form>
			<form action="${pageContext.servletContext.contextPath}/createAccount" method="doGet">
				<input type="Submit" name="submit" value="Create Account">
			</form>
			
			<c:if test="${! empty logInMessage}">
				<form action="${pageContext.servletContext.contextPath}/mainPage" method="doGet">
					<input type="Submit" name="submit" value="Go To Main Page">
				</form>
				<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
					<input type="Submit" name="submit" value="View Your Account">
				</form>
				
				<div class="loggedIn">${logInMessage}</div>
			</c:if>
	</body>
</html>.