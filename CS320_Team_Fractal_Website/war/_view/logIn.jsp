<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Log In</title>
			<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

	<div class="flex-container">
		<div class="border">
		</div>
		<div class="center">
			<body>
				<c:if test="${! empty errorMessage}">
					<div class="error">${errorMessage}</div>
				</c:if>

				<div class="login-center">
				<form action="${pageContext.servletContext.contextPath}/logIn" method="post">
					<table>
						<tr>
							<td class="label">Username:</td>
							<td><input type="text" name="username" size="12" value="${username}" /></td>
						</tr>
						<tr>
							<td class="label">Password:</td>
							<td><input type="password" name="password" size="12" value="${password}" /></td>
						</tr>
					</table>
					<input type="Submit" name="submit" value="Log In">
					<div class="loggedIn">${logInMessage}</div>
				</form>
				</div>
				<div class="login-center">
				<div class="lower-nav">
					<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
						<input type="Submit" name="submit" value="Home">
					</form>
					<form action="${pageContext.servletContext.contextPath}/forgotPassword" method = "doGet">
						<input type="Submit" name="submit" value="Forgot Password">
					</form>
					<form action="${pageContext.servletContext.contextPath}/createAccount" method="doGet">
						<input type="Submit" name="submit" value="Create Account">
					</form>
				</div>
				</div>
			</body>
		</div>
		<div class="border">
		</div>
	</div>
</html>
