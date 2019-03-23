<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
		<meta charset="utf-8">
		<title>Fractal Main</title>

		<style type="text/css">
		.error {
			color: red;
		}

		td.label {
			text-align: right;
		}
		</style>​
	</head>

	<body>
		<p>Main page of the fractal site</p>

		<nav class="topnav">
			<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
				<input type="Submit" name="submit" value="Home">
			</form>
			<form action="${pageContext.servletContext.contextPath}/logIn" method="doGet">
				<input type="Submit" name="submit" value="Login">
			</form>
		</nav>

		<div class="interface">
			<c:if test="${! empty errorMessage}">
				<div class="error">${errorMessage}</div>
			</c:if>

			<div class="image">
					<c:choose>
						<c:when test="${not result}">
							<img src="img/square.jpg" alt="placeholder"/>
						</c:when>
						<c:otherwise>
							<img src="img/result.png" alt="result" />
						</c:otherwise>
					</c:choose>
			</div>

			<div class="type">
				<form action="${pageContext.servletContext.contextPath}/mainPage" method="post">

				</form>
			</div>

			<div class="parameters">
					<form action="${pageContext.servletContext.contextPath}/mainPage" method="post">
						<select id="choice" name="choice">
							<option value="0">Sierpinski</option>
							<option value="1">Mandelbrot</option>
						</select>
					<div id="sierpinski">
						<table>
							<tr>
								<td class="label">Level</td>
								<td><input type="text" name="level" size="12" value="${level}" /></td>
							</tr>
						</table>
					</div>
					<div id="mandelbrot" style="display:none;">
						<table>
							<td class="label">X1</td>
							<td><input type="text" name="x1" size="12" value="${x1}"/></td>
							<td class="label">Y1</td>
							<td><input type="text" name="y1" size="12" value="${y1}"/></td>
							<td class="label">X2</td>
							<td><input type="text" name="x2" size="12" value="${x2}"/></td>
							<td class="label">Y2</td>
							<td><input type="text" name="y2" size="12" value="${y2}"/></td>
						</table>
					</div>
					<input type="Submit" name="submit" value="Send">
				</form>
			</div>
		</div>
	</body>
	<script>
		$('#choice').on('change', function(){
			var selection = $(this).val();
		switch(selection){
		case "0":
			$("#sierpinski").show()
			$("#mandelbrot").hide()
			break;
		case "1":
			$("#mandelbrot").show()
			$("#sierpinski").hide()
			break;
		}
	});
	</script>
</html>
