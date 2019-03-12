<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
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
