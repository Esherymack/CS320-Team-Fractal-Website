<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<p>This is the index view jsp</p>
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="doGet">
			<input type="Submit" name="submit" value="addNumbers">
		</form>
		<form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="doGet">
			<input type="Submit" name="submit" value="multiplyNumbers">
		</form>
		<form action="${pageContext.servletContext.contextPath}/guessingGame" method="doGet">
			<input type="Submit" name="submit" value="guessingGame">
		</form>
	</body>
</html>
