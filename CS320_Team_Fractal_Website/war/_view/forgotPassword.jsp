<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Forgot Password</title>
			<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

  <div class="flex-container">
		<div class="border">
		</div>
		<div class="center"><body>
				<c:if test="${! empty errorMessage}">
					<div class="error">${errorMessage}</div>
				</c:if>

				<div class="login-center">
        </div>
      </body>
    </div>
    <div class="border"></div>
  </div>
</html>
