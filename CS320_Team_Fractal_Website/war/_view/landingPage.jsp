<!DOCTYPE html>

<html>
	<head>
		<title>Fractal Home Page</title>
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<div>
			<h1>Fractal Generation Website</h1>
			<h3>Created by: Dakota Hilbert, Madison Tibbet, Zachary Ronayne</h3>
		</div>
		
		<div>
			<p>Welcome to our website of fractals</p>
		</div>
		
		<div>
			<form action="${pageContext.servletContext.contextPath}/mainPage" method="doGet">
				<input type="submit" value="Create a fractal!" />
			</form>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
				<input type="submit" value="Login" />
			</form>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
				<input type="submit" value="Your Account" />
			</form>
		</div>
	</body>

</html>
