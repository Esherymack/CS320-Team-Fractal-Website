<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Change Password</title>
			<link href="https://fonts.googleapis.com/css?family=Open+Sans|Poiret+One" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/c.css" rel="stylesheet" type="text/css">
	</head>

  <div class="flex-container">
		<div class="border">
		</div>
		<div class="center"><body>
				<div class="login-center">
            <form action="${pageContext.servletContext.contextPath}/changePassword" method="post">
              <table>
                <tr>
                  <td class="label">Current password:</td>
                  <td><input type="password" name="currentPassword" size="12" value="${currentPassword}" /></td>
                </tr>
                <tr>
                  <td class="label">New password:</td>
                  <td><input type="password" name="newPassword" size="12" value="${newPassword}" /></td>
                </tr>
              </table>
              <input type="Submit" name="submit" value="Submit">
              <div class="loggedIn">${passwordResetMessage}</div>
              <c:if test="${! empty errorMessage}">
                <div class="error">${errorMessage}</div>
              </c:if>
            </form>
        </div>

        <div class="login-center">
				<div class="lower-nav">
					<form action="${pageContext.servletContext.contextPath}/landingPage" method="doGet">
						<input type="Submit" name="submit" value="Home">
					</form>
          <form action="${pageContext.servletContext.contextPath}/viewAccount" method="doGet">
            <input type="Submit" name="submit" value="Return">
          </form>
				</div>
				</div>

      </body>
    </div>
    <div class="border"></div>
  </div>
</html>
