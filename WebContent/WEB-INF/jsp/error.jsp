<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>oops! an error has occurred ,sorry for the inconvenience </h1><br/>
<h2><c:out value="${exception }"></c:out></h2><br/>
<c:out value="${url }"></c:out>
<br/>
<h2><a href="dashboard"> Back To DashBoard</a></h2>
</body>
</html>