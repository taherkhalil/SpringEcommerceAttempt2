<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign In </title>
</head>
<body bgcolor="black">
<br><br><br><br><br><br><br><br><br>
<h2 style="color: white;  " align="center">Login to continue</h2>
<c:if test="${ not empty error }">
		<p style="color: red;" align="center">Invalid Credentials</p>
	</c:if>
	<form:form commandName="user" method="POST" action="login" align="center" >
		 
		<label style="color: white;">Username:</label>
		<form:input type="text" path="username"   />
		<font color="red"><form:errors path="username"/></font><br/><br/>
		<label style="color: white;">Password:</label>
		<form:input type="password" path="password" />
		<font color="red"><form:errors path="password"/></font><br/><br/>
		
		<input type="submit" value="Sign in">		
	</form:form>
</body>
</html>