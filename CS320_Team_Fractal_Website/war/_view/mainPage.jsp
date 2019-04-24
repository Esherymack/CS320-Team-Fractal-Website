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
							<p>${fractalInfo}</p>
						</c:if>
					</div>
				</div>

				<!- Adding attributes for the function that sets which labels are displayed ->
				<c:forEach items="${fractalTypeList}" var="type">
					<c:forEach items="${paramLabelList}" var="i">
						<c:set var="iValue" value="fractalLabels${type}${i}" />
						<input type="hidden" id="fractalLabels${type}${i}" value="${requestScope[iValue]}" />
					</c:forEach>
				</c:forEach>
	
				<div class="col1">
					<div class="parameters">
						<form action="${pageContext.servletContext.contextPath}/mainPage" method="post">
							<select id="choice" name="choice" value="">
	
								<option value="" ${choice == "" || empty choice ? 'selected="selected"' : ''} disabled hidden>Select Fractal</option>
	
								<c:forEach items="${fractalTypeList}" var="type">
									<option value="${type}" ${choice == type ? 'selected="selected"' : ''}>${type}</option>
								</c:forEach>
	
							</select>
							<input type="hidden" name="selectedChoice">
							<div class="form-labels">
								<table class="params">
									<c:forEach items="${paramLabelList}" var="i">
										<tr>
											<td class="label" id="paramInput${i}" hidden=true>
												<label id="paramLab${i}"> ${i} </label>
												<c:set var="iValue" value="${i}" />
												<input name="${i}" type="text" size="12" value="${requestScope[iValue]}" />
											</td>
										</tr>
			
									</c:forEach>
								</table>
								
								<!- color parameters ->
								<div>
									<tr>
										<p>Use gradient:
											<input type="checkbox" value="useGradient" name="useGradient" ${useGradient ? 'checked' : ''}>
										</p>
									</tr>
									<p>
										Gradient<br>
										Base color
									</p>
									<table class="params">
										<tr>
											<td class="label"> <label>Red:</label> </td>
											<td class="label"> <input type="text" name="gradientRedBase" size="12" value="${gradientRedBase}" /> </td>
										</tr>
										<tr>
											<td class="label"> <label>Green:</label> </td>
											<td class="label"> <input type="text" name="gradientGreenBase" size="12" value="${gradientGreenBase}" /> </td>
										</tr>
										<tr>
											<td class="label"> <label>Blue:</label> </td>
											<td class="label"> <input type="text" name="gradientBlueBase" size="12" value="${gradientBlueBase}" /> </td>
										</tr>
									</table>
									<table class="params">
										<p>End color:</p>
										<tr>
											<td class="label"> <label>Red:</label> </td>
											<td class="label"> <input type="text" name="gradientRedEnd" size="12" value="${gradientRedEnd}" /> </td>
										</tr>
										<tr>
											<td class="label"> <label>Green:</label> </td>
											<td class="label"> <input type="text" name="gradientGreenEnd" size="12" value="${gradientGreenEnd}" /> </td>
										</tr>
										<tr>
											<td class="label"> <label>Blue:</label> </td>
											<td class="label"> <input type="text" name="gradientBlueEnd" size="12" value="${gradientBlueEnd}" /> </td>
										</tr>
									</table>
								</div>
								<input type="Submit" name="submit" value="Send" class="sender">
								<input type="Submit" name="save" value="Save" class="sender">
								<td id="saveName"><input type="text" name="saveName" size="12" value="${saveName}" placeholder="Name" /></td>
								<br>
								<div class="label">
									<input id="setDefaultValues" name="setDefaultValues" type="submit" value="Default Values" hidden>
								</div>
								<c:if test="${! empty result}">
									<div class="label">
										<input type="button" onclick="document.getElementById('downloadImage').click()" value="Download Image">
										<a id="downloadImage" href="img/result.png" download hidden></a>
									</div>
								</c:if>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>

	<script>
	window.onload = function(){
		$('#choice').change()
	}
	$('#choice').change(function() {
		var selection = $(this).val();
		sessionStorage.setItem('Selection', selection);

		if(selection == ""){
			$("#setDefaultValues").hide()
		}
		else{
			$("#setDefaultValues").show()
		}

		for(var i = 0; i < 10; i++){
			var s =
				document.getElementById(
					"fractalLabels" + selection + "param" + i
				).getAttribute("value");
			document.getElementById("paramLabparam" + i).innerHTML = s;

			if(s == ""){
				$("#paramInputparam" + i).hide()
			}
			else{
				$("#paramInputparam" + i).show()
			}
		}
	});

	</script>

</html>
