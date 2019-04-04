<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Account</title>
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	<div class="flex-container">
		<div class="border"></div>
		<div class="center">
		<body>
			<div class="topnav">
				<ul>
					<li>
						<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
							<input type="Submit" name="submit" value="Home">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
							<input type="Submit" name="submit" value="Login">
						</form>
					</li>
					<li>
						<form action="${pageContext.servletContext.contextPath}/logout" method="post">
							<input type="Submit" name="submit" value="LogOut">
						</form>
					</li>
				</ul>
			</div>
			<div class="login-center">
				<form action="${pageContext.servletContext.contextPath}/createAccount" method="post">
					<table>
						<tr>
							<td class="label">First Name:</td>
							<td><input type="text" name="firstname" size="12" value="${firstname}" /></td>
						</tr>
						<tr>
							<td class="label">Last Name:</td>
							<td><input type="text" name="lastname" size="12" value="${lastname}" /></td>
						<tr>
							<td class="label">Enter a new username:</td>
							<td><input type="text" name="username" size="12" value="${username}" /></td>
						</tr>
						<tr>
							<td class="label">Enter a new password:</td>
							<td><input type="password" name="password" size="12" value="${password}" /></td>
						</tr>
						<tr>
							<td class="label">Enter an email:</td>
							<td><input type="text" name="email" size="12" value="${email}" /></td>
						</tr>
					</table>
					<input type="Submit" name="submit" value="Create Account">
				</form>
				<c:if test="${! empty invalidMessage}">
					<div class="invalid">${invalidMessage}</div>
				</c:if>
			</div>
		</body>
	</div>
	<div class="border"></div>
	</div>
</html>
