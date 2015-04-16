<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.ruc.demo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>
</head>
<body>
<%= ((Person)request.getAttribute("Person")).getName() %>
<br>
<jsp:useBean id="Person" class="edu.ruc.demo.Person" scope="request"></jsp:useBean>
Person created by servlet: <jsp:getProperty property="name" name="Person"/>
</body>
</html>