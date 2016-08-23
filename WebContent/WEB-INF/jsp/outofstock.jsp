<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Sorry the item </h1> <h1 style="color: red;"><c:out  value="${model.products.name }" ></c:out></h1><h1> is Out Of Stock!  </h1> 
<h2><a href="dashboard"> Go Back To DashBoard</a><br> </h2>
</body>
</html>