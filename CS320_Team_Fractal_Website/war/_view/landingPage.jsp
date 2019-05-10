<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Fractal Home Page</title>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
	<div class="flex-container">
		<div class="border"></div>
		<div class="center">
			<body id="home">
				<div class="topnav">
					<ul>
						<c:if test="${userName == null}">
							<li>
								<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
									<input type="submit" value="Login or Create Account" />
								</form>
							</li>
						</c:if>
						<c:if test="${userName != null}">
							<li>
								<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
									<input type="Submit" name="submit" value="${currentlyLoggedInMessage}">
								</form>
							</li>
							<li>
								<form action="${pageContext.servletContext.contextPath}/mainPage" method="doGet">
									<input type="Submit" name="submit" value="Create a fractal">
								</form>
							</li>
							<li>
								<form action="${pageContext.servletContext.contextPath}/browseFractals" method="doGet">
									<input type="Submit" name="submit" value="Browse all fractals">
								</form>
							</li>
							<li>
								<form action="${pageContext.servletContext.contextPath}/logout" method="post">
									<input type="Submit" name="submit" value="Log Out">
								</form>
							</li>
						</c:if>
					</ul>
				</div>
				<div class="account-header">
					<h1>Fractal Generation Website</h1>
					<h2>Created by: Dakota Hilbert, Madison Tibbett, Zachary Ronayne</h2>
				</div>
				<div class="info">
					<h2>Example Fractals:</h2>
					<div class="exampleImageContainer">
						<img src="img/example_mandelbrot1.png" alt="mandelbrot1" class="exampleImage">
						<img src="img/example_mandelbrot2.png" alt="mandelbrot2" class="exampleImage">
						<img src="img/example_julia1.png" alt="julia1" class="exampleImage">
						<img src="img/example_julia2.png" alt="julia2" class="exampleImage">
						
						<p class="exampleImageText">A zoomed out Mandelbrot</p>
						<p class="exampleImageText">A zoomed in Mandelbrot section</p>
						<p class="exampleImageText">A Julia Set</p>
						<p class="exampleImageText">Another Julia Set, zoomed in</p>
					</div>
					<div class="exampleImageContainer">
						<img src="img/example_burningship1.png" alt="burningship1" class="exampleImage">
						<img src="img/example_burningship2.png" alt="burningship2" class="exampleImage">
						<img src="img/example_tricorn2.png" alt="tricorn1" class="exampleImage">
						<img src="img/example_tricorn1.png" alt="tricorn2" class="exampleImage">
						
						<p class="exampleImageText">A zoomed out Burning Ship</p>
						<p class="exampleImageText">A zoomed in Burning Ship section</p>
						<p class="exampleImageText">A zoomed out Tricorn</p>
						<p class="exampleImageText">A zoomed in Tricorn section</p>
					</div>
					<div class="exampleImageContainer">
						<img src="img/example_barnsley1.png" alt="barnsley1" class="exampleImage">
						<img src="img/example_barnsley2.png" alt="barnsley2" class="exampleImage">
						<img src="img/example_koch.png" alt="koch" class="exampleImage">
						<img src="img/example_sierpinski.png" alt="sierpinski" class="exampleImage">
						
						<p class="exampleImageText">A Barnsley Fern</p>
						<p class="exampleImageText">A modified Barnsley Fern</p>
						<p class="exampleImageText">A Koch set</p>
						<p class="exampleImageText">A Sierpinksi Triangle</p>
					</div>
					<div class="exampleImageContainer">
						<img src="img/example_bakedkuriboh1.png" alt="bakedkuriboh1" class="exampleImage">
						<img src="img/example_bakedkuriboh2.png" alt="bakedkuriboh2" class="exampleImage">
						<img src="img/example_unnamed.png" alt="unnamed1" class="exampleImage">
						<img src="img/example_rocketship.png" alt="rocketship1" class="exampleImage">
						
						<p class="exampleImageText">A Zoomed Out Baked Kuriboh</p>
						<p class="exampleImageText">A Zoomed In Baked Kuriboh</p>
						<p class="exampleImageText">An Unnamed fractal</p>
						<p class="exampleImageText">A Rocket Ship fractal</p>
					</div>
					<div class="exampleImageContainer">
						<img src="img/example_pythagorastree.png" alt="pythagorastree1" class="exampleImage">
						<img src="img/example_sierpinskicarpet.png" alt="sierpinskicarpet1" class="exampleImage">
						<img src="img/Vicsek+.png" alt="vicsek+" class="exampleImage">
						<img src="img/VicsekX.png" alt="vicsekX" class="exampleImage">
						
						<p class="exampleImageText">A Pythagoras Tree</p>
						<p class="exampleImageText">A Sierpinski Carpet</p>
						<p class="exampleImageText">A Vicsek fractal with plus shape</p>
						<p class="exampleImageText">A Vicsek fractal with x shape</p>
					</div>
					<div>
						<h2>About</h2>
						<p>
							We are a group of students from York College of Pennsylvania, and we created this
							project as a part of our software engineering class.
							<br>
							This site allows you to generate a variety of fractals, change the parameters of each fractal, 
							and apply gradients to the fractals. You can save fractals to an account, download images, 
							and view all the fractals that any other use has made.
							<br>
						</p>
					</div>
				</div>
			</body>
		</div>
		<div class="border"></div>
	</div>

</html>
