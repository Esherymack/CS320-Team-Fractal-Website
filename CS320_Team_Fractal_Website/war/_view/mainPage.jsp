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
							<form action="${pageContext.servletContext.contextPath}/browseFractals" method="doGet">
								<input type="Submit" name="submit" value="Browse all fractals">
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
							<img src="img/square.jpg" alt="placeholder" class="fractalImage" />
						</c:when>
						<c:otherwise>
							<div id=fractalImage class="fractalImageParent">
								<img src="img/result.png" alt="result" class="fractalImage" id="fractalImageSource"/>
								<div id="fractalDisplayZoomSelector"></div>
							</div>
							
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
							<br>
							<h2>Tips and Examples:</h2>
							<div class="param-list">
								${paramsToTry}
							</div>
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
												<input id="${i}" name="${i}" type="text" size="12" value="${requestScope[iValue]}" />
											</td>
										</tr>
			
									</c:forEach>
								</table>
							</div>
							
							<div>
								<p>Gradient</p>
								<select id="gradientChoice" name="gradientChoice" value="None">
									<c:forEach items="${gradientList}" var="type">
										<option value="${type}" ${gradientChoice == type ? 'selected="selected"' : ''}>${type}</option>
									</c:forEach>
									
								</select>
							</div>
							
							<!- color parameters ->
							<div>
								<p id="baseColor" hidden>Base color</p>
								<table id="params">
									<tr>
										<td class="label"> <label id="gradientRedBaseLabel" hidden>Red:</label> </td>
										<td class="label"> <input type="text" id="gradientRedBase" name="gradientRedBase" size="12" value="${gradientRedBase}" hidden /> </td>
									</tr>
									<tr>
										<td class="label"> <label id="gradientGreenBaseLabel" hidden>Green:</label> </td>
										<td class="label"> <input type="text" id="gradientGreenBase" name="gradientGreenBase" size="12" value="${gradientGreenBase}" hidden /> </td>
									</tr>
									<tr>
										<td class="label"> <label id="gradientBlueBaseLabel" hidden>Blue:</label> </td>
										<td class="label"> <input type="text" id="gradientBlueBase" name="gradientBlueBase" size="12" value="${gradientBlueBase}" hidden /> </td>
									</tr>
								</table>
								<table class="params">
									<p id="endColor" hidden>End color:</p>
									<tr>
										<td class="label"> <label id="gradientRedEndLabel" hidden>Red:</label> </td>
										<td class="label"> <input type="text" id="gradientRedEnd" name="gradientRedEnd" size="12" value="${gradientRedEnd}" hidden /> </td>
									</tr>
									<tr>
										<td class="label"> <label id="gradientGreenEndLabel" hidden>Green:</label> </td>
										<td class="label"> <input type="text" id="gradientGreenEnd" name="gradientGreenEnd" size="12" value="${gradientGreenEnd}" hidden /> </td>
									</tr>
									<tr>
										<td class="label"> <label id="gradientBlueEndLabel" hidden>Blue:</label> </td>
										<td class="label"> <input type="text" id="gradientBlueEnd" name="gradientBlueEnd" size="12" value="${gradientBlueEnd}" hidden /> </td>
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
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
	
	<script>
	
	var mouseDown = 0;
	
	var x1 = 0;
	var y1 = 0;
	var x2 = 0;
	var y2 = 0;
	
	var oldDragZoomX1 = document.getElementById("param0").getAttribute("value");
	var oldDragZoomY1 = document.getElementById("param1").getAttribute("value");
	var oldDragZoomX2 = document.getElementById("param2").getAttribute("value");
	var oldDragZoomY2 = document.getElementById("param3").getAttribute("value");
	
	window.onload = function(){
		$('#choice').change()
		$('#gradientChoice').change()
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
	})
	
	$('#gradientChoice').change(function() {
		var gradientSelection = $(this).val();
		sessionStorage.setItem('GradientSelection', gradientSelection);
		
		$("#baseColor").show();
		$("#gradientRedBase").show();
		$("#gradientRedBaseLabel").show();
		$("#gradientGreenBase").show();
		$("#gradientGreenBaseLabel").show();
		$("#gradientBlueBase").show();
		$("#gradientBlueBaseLabel").show();
		
		$("#endColor").show();
		$("#gradientRedEnd").show();
		$("#gradientRedEndLabel").show();
		$("#gradientGreenEnd").show();
		$("#gradientGreenEndLabel").show();
		$("#gradientBlueEnd").show();
		$("#gradientBlueEndLabel").show();
			
		if(gradientSelection == "None" || gradientSelection == "Rainbow"){
			$("#endColor").hide();
			$("#gradientRedEnd").hide();
			$("#gradientRedEndLabel").hide();
			$("#gradientGreenEnd").hide();
			$("#gradientGreenEndLabel").hide();
			$("#gradientBlueEnd").hide();
			$("#gradientBlueEndLabel").hide();
		}
		if(gradientSelection == "None"){
			$("#baseColor").hide();
			$("#gradientRedBase").hide();
			$("#gradientRedBaseLabel").hide();
			$("#gradientGreenBase").hide();
			$("#gradientGreenBaseLabel").hide();
			$("#gradientBlueBase").hide();
			$("#gradientBlueBaseLabel").hide();
		}
	});
	
	$("#fractalImage").mousedown(function(e){
		if(window.getSelection){
			window.getSelection().removeAllRanges();
		}
		else if(document.selection){
			document.selection.empty();
		}
	});
	
	$("#fractalImage").mousedown(function(e){
		//get mouse position
	    var mouseX = e.pageX - this.offsetLeft;
	    var mouseY = e.pageY - this.offsetTop;
		mouseDown = 1;
		
		updateDragZoom(mouseX, mouseY);
	});
	
	$("#fractalImage").mouseup(function(e){
		//get mouse position
	    var mouseX = e.pageX - this.offsetLeft;
	    var mouseY = e.pageY - this.offsetTop;
		mouseDown = 0;
		
		updateDragZoom(mouseX, mouseY);
	});
	
	$("#fractalImage").mousemove(function(e){
		//get mouse position
	    var mouseX = e.pageX - this.offsetLeft;
	    var mouseY = e.pageY - this.offsetTop;
		
		if(mouseDown == 1){
			updateMousePositions(mouseX, mouseY);
		}
	});
	
	function updateDragZoom(mouseX, mouseY){
	
		//only run this function if a supported fractal is selected
		var list = document.getElementById("choice");
		var option = list.options[list.selectedIndex].value;
		if(!(option == "Mandelbrot" || option == "Julia")) return;
		
		//if the x1,y2,x2,y2 values are empty, then save the current params in the oldDragZoom attributes
		if(!oldDragZoomX1 || !oldDragZoomY1 || !oldDragZoomX2 || !oldDragZoomX2){
			oldDragZoomX1 = document.getElementById("param0").getAttribute("value");
			oldDragZoomY1 = document.getElementById("param1").getAttribute("value");
			oldDragZoomX2 = document.getElementById("param2").getAttribute("value");
			oldDragZoomY2 = document.getElementById("param3").getAttribute("value");
		}
		
		//if the mouse is pressed down, the first point is the mouse position
		if(mouseDown == 1){
			x1 = mouseX;
			y1 = mouseY;
			
			//set square display of first point
			document.getElementById("fractalDisplayZoomSelector").
				setAttribute("style",
					"left: " + x1 + "px;" +
					"top: " + y1 + "px;" +
					"width:0px;" +
					"height:0px;"
			);
		}
		//otherwise send the parameters and reset all the values to -1
		else{
			updateMousePositions(mouseX, mouseY);
		}
		
	}
	
	function updateMousePositions(mouseX, mouseY){
		//set mouse positions
		x2 = mouseX;
		y2 = mouseY;
		
		//get height of image
		var width = document.getElementById("fractalImageSource").width;
		var height = document.getElementById("fractalImageSource").height;
		
		//set square display of second point
		var minX = Math.min(x1, x2);
		var minY = Math.min(y1, y2);
		var maxWidth = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
		if(maxWidth + minX > width) maxWidth = width - minX;
		if(maxWidth + minY > height) maxWidth = height - minY;
		
		document.getElementById("fractalDisplayZoomSelector").
			setAttribute("style",
				"left:" + (Math.min(x1, x2)) + "px;" +
				"top:" + (Math.min(y1, y2)) + "px;" +
				"width: " + maxWidth + "px;" +
				"height: " + maxWidth + "px;"
		);
		
		//calculate new positions
		var percWidth = Math.abs(x1 - x2) / width;
		var percHeight = Math.abs(y1 - y2) / height;
		var percX = Math.min(x1, x2) / width;
		var percY = Math.min(y1, y2) / height;
		
		var realWidth = Math.abs(oldDragZoomX1 - oldDragZoomX2);
		var realHeight = Math.abs(oldDragZoomY1 - oldDragZoomY2);
		
		var newX1 = Math.min(oldDragZoomX1, oldDragZoomX2) + realWidth * percX;
		var newY1 = Math.min(oldDragZoomY1, oldDragZoomY2) + realHeight * percY;
		
		var w = Math.max(realWidth * percWidth, realHeight * percHeight);
		
		var newX2 = newX1 + w;
		var newY2 = newY1 + w;
		if(newX2 > oldDragZoomX2) newX2 = oldDragZoomX2;
		if(newY2 > oldDragZoomY2) newY2 = oldDragZoomY2;
		
		//set the new values
		document.getElementById("param0").setAttribute("value", newX1);
		document.getElementById("param1").setAttribute("value", newY1);
		document.getElementById("param2").setAttribute("value", newX2);
		document.getElementById("param3").setAttribute("value", newY2);
	}
	
	</script>

</html>
