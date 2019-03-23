<!DOCTYPE html>

<html>
	<head>
		<title>Fractal Home Page</title>
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
			<form action="${pageContext.servletContext.contextPath}/addNumbers" method="doGet">
				<input type="submit" value="Create a fractal!" />
			</form>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="doGet">
				<input type="submit" value="Login" />
			</form>
		</div>
	<body>

</html>