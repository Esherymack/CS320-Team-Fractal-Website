<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Verify Account</title>
			<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>
  <div class="flex-container">
		<div class="border">
		</div>
		<div class="center">
			<body>
        <h1>Enter the verification code sent to ${userEmail} below.</h1>
        <div class="login-center">
          <form action="${pageContext.servletContext.contextPath}/verifyAccount" method ="post">
            <table>
              <tr>
                <td class="label">Verification:</td>
                <td><input type="text" name="verification" size="12" value="${verification}" /></td>
              </tr>
            </table>
            <input type="Submit" name="submit" value="Submit Code">
            <div class="loggedIn">${verifiedUserMessage}</div>
          </form>
        </div>
        <div class="login-center">
          <div class="lower-nav">
            <form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
              <input type="Submit" name="submit" value="Home">
            </form>
            <form action="${pageContext.servletContext.contextPath}/logout" method="post">
              <input type="Submit" name="submit" value="Log Out">
            </form>
          </div>
        </div>
      </body>
    </div>
    <div class="border">
    </div>
  </div>
</html>
