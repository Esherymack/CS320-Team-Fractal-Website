<!DOCTYPE html>

<html>
	<head>
		<title>Fractal Home Page</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
			<h1>Fractal Generation Website</h1>
			<h2>Created by: Dakota Hilbert, Madison Tibbet, Zachary Ronayne</h2>
			
			<div class="square1"></div>
			<div class="square2"></div>
			<div class="square3"></div>
			<div class="square4"></div>
			
			<h3>Welcome to our website of fractals</h3>
			
			<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
				<input type="submit" value="Login or Create Account" />
			</form>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
				<input type="submit" value="Your Account" />
			</form>
		</div>
	</body>

</html>
