<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
		<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
		<meta charset="utf-8">
		<title>Fractal Main</title>

		<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">

	</head>

	<body>
		<div class="mainPage-nav-bg">
			<div class= "container">
				<div class="topnav">
					<ul>
						<li>
							<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
								<input type="Submit" name="submit" value="Home">
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
								<input type="Submit" name="submit" value="${currentlyLoggedInMessage}">
							</form>
						</li>
						<li>
							<form action="${pageContext.servletContext.contextPath}/logout" method="post">
								<input type="Submit" name="submit" value="LogOut">
							</form>
						</li>
					</ul>
				</div>
			</div>
		</div>
			<div class="left-box">
			<div class="interface">
				<c:if test="${! empty errorMessage}">
					<div class="error">${errorMessage}</div>
				</c:if>

				<div class="image">
				<p></p>
						<c:choose>
							<c:when test="${not result}">
								<img src="img/square.jpg" alt="placeholder"/>
							</c:when>
							<c:otherwise>
								<img src="img/result.png" alt="result" />
							</c:otherwise>
						</c:choose>
				</div>
				</div>
				<div class="right-box">
					<div class="col2">
						<div class="info">
							<c:if test="${! empty fractalInfo}">
								<h1>Information</h1>
							</c:if>
							<p>${fractalInfo}</p>
						</div>
					</div>
					<div class="col1">
				<div class="parameters">
						<form action="${pageContext.servletContext.contextPath}/mainPage" method="post">
							<select id="choice" name="choice" value="">
								<option value="" selected disabled hidden>Select Fractal</option>
								<option value="Sierpinski">Sierpinski</option>
								<option value="Mandelbrot">Mandelbrot</option>
							</select>
						</div>
							<input type="hidden" name="selectedChoice">
						<div class="form-labels">
							<table class="params">
								<tr>
									<td class="label" id="paramLab0" hidden=true>Param0</td>
									<td id="paramIn0" hidden=true><input type="text" name="param0" size="12" value="${param0}" /></td>

									<td class="label" id="paramLab1" hidden=true>Param1</td>
									<td id="paramIn1" hidden=true><input type="text" name="param1" size="12" value="${param1}" /></td>

									<td class="label" id="paramLab2" hidden=true>Param2</td>
									<td id="paramIn2" hidden=true><input type="text" name="param2" size="12" value="${param2}" /></td>

									<td class="label" id="paramLab3" hidden=true>Param3</td>
									<td id="paramIn3" hidden=true><input type="text" name="param3" size="12" value="${param3}" /></td>

									<td class="label" id="paramLab4" hidden=true>Param4</td>
									<td id="paramIn4" hidden=true><input type="text" name="param4" size="12" value="${param4}" /></td>

									<td class="label" id="paramLab5" hidden=true>Param5</td>
									<td id="paramIn5" hidden=true><input type="text" name="param5" size="12" value="${param5}" /></td>

									<td class="label" id="paramLab6" hidden=true>Param6</td>
									<td id="paramIn6" hidden=true><input type="text" name="param6" size="12" value="${param6}" /></td>

									<td class="label" id="paramLab7" hidden=true>Param7</td>
									<td id="paramIn7" hidden=true><input type="text" name="param7" size="12" value="${param7}" /></td>

									<td class="label" id="paramLab8" hidden=true>Param8</td>
									<td id="paramIn8" hidden=true><input type="text" name="param8" size="12" value="${param8}" /></td>

									<td class="label" id="paramLab9" hidden=true>Param9</td>
									<td id="paramIn9" hidden=true><input type="text" name="param9" size="12" value="${param9}" /></td>
								</tr>
							</table>
							</div>
								<input type="Submit" name="submit" value="Send" class="sender">
								<input type="Submit" name="save" value="Save" class="sender">
								<td class="label" id="saveLabel">Name</td>
								<td id="saveButton"><input type="text" name="saveButton" size="12" value="${saveButton}" /></td>
								<br>
								<c:if test="${! empty result}">
									<div class="label">
										<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download Image">
										<a id="downloadImage" href="img/result.png" download hidden></a>
									</div>
								</c:if>
							</div>
						</div>
					</form>
				</div>
			</div>
	</body>

	<script>

	$('#choice').change(function() {
		var selection = $(this).val();
		sessionStorage.setItem('Selection', selection);
		switch(selection){
			case "Sierpinski":
				document.getElementById("paramLab0").innerHTML = "Level: "
				$("#paramLab0").show()
				$("#paramIn0").show()

				for(var i = 1; i < 10; i++){
					$("#paramLab" + i).hide()
					$("#paramIn" + i).hide()
				}
				break;
			case "Mandelbrot":
				document.getElementById("paramLab0").innerHTML = "X1: "
				document.getElementById("paramLab1").innerHTML = "Y1: "
				document.getElementById("paramLab2").innerHTML = "X2: "
				document.getElementById("paramLab3").innerHTML = "Y2: "
				document.getElementById("paramLab4").innerHTML = "Multiplier: "

				for(var i = 0; i < 5; i++){
					$("#paramLab" + i).css('display', 'block')
					$("#paramIn" + i).css('display', 'inline-block')
				}

				for(var i = 5; i < 10; i++){
					$("#paramLab" + i).css('display', 'none')
					$("#paramIn" + i).css('display', 'none')
				}
				break;
		}
	});

	</script>

</html>
