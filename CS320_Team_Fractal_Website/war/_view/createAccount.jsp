<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Account</title>
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<div class="content">
		
			<p>This is the account creation page</p>
			
			<c:if test="${! empty invalidMessage}">
				<div class="invalid">${invalidMessage}</div>
			</c:if>
			
			<form action="${pageContext.servletContext.contextPath}/createAccount" method="post">
				<table>
					<tr>
						<td class="label">Enter a new username:</td>
						<td><input type="text" name="username" size="12" value="${username}" /></td>
					</tr>
					<tr>
						<td class="label">Enter a new password:</td>
						<td><input type="text" name="password" size="12" value="${password}" /></td>
					</tr>
					<tr>
						<td class="label">Enter an email:</td>
						<td><input type="text" name="email" size="12" value="${email}" /></td>
					</tr>
				</table>
				<input type="Submit" name="submit" value="Create Account">
			</form>
			
			<c:if test="${! empty accountCreatedMessage}">
				<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
					<input type="Submit" name="submit" value="Log In">
				</form>
				<div class="accountCreated">${accountCreatedMessage}</div>
			</c:if>
		</div>
	</body>
</html>.