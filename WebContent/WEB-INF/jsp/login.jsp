<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign In </title>
</head>
<body>

<c:if test="${ not empty error }">
		<p>Invalid Credentials</p>
	</c:if>
	<form:form commandName="user" method="POST" action="login" align="center" >
		 
		<label>Username:</label>
		<form:input type="text" path="username"   />
		<font color="red"><form:errors path="username"/></font><br/><br/>
		<label>Password:</label>
		<form:input type="password" path="password" />
		<font color="red"><form:errors path="password"/></font><br/><br/>
		
		<input type="submit" value="Sign in">		
	</form:form>
</body>
</html>