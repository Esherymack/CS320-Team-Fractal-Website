<!DOCTYPE html>

<html>
	<head>
		<title>Fractal Home Page</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	<div class="flex-container">
		<div class="border">
		</div>
		<div class="center">
			<body id="home">
				<h1>Fractal Generation Website</h1>
				<h2>Created by: Dakota Hilbert, Madison Tibbett, Zachary Ronayne</h2>
				<nav class="topnav">
					<ul>	
						<li>
							<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
								<input type="submit" value="Login or Create Account" />
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
								<input type="submit" value="Account" />
							</form>
						</li>
					</ul>
				</nav>
			</body>
		</div>
		<div class="border">
		</div>
	</div>

</html>
