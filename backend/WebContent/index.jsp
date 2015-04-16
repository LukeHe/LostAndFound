<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login and Register</title>
</head>
<body>
<%= "UserID: " + request.getAttribute("UserID") + "<br>" + " Password: " + request.getAttribute("Password") + "<br>" %>
<% 
	int flag = 0;
	if (request.getAttribute("Flag") != null) 
	{ flag = (int)request.getAttribute("Flag"); }
	switch (flag)
	{
		case 2:
		{ out.print("<script>alert(\"non-existent userID\");</script>"); break; }
		case 3:
		{ out.print("<script>alert(\"wrong password\");</script>"); break; }
	}
%>
<form method="post" action="VerifyUser">
	<table>
	<tr >
		<td ><strong>UserID</strong></td>
		<td><input name="UserID"></input></td>
	</tr>
	<tr>
		<td><strong>Password</strong></td>
		<td><input name="Password"></input></td>
	</tr>
	</table>
	<input type="submit" value="Login"></input>
</form>
<form method="post" action="AddUser">
	<table>
	<tr>
		<td ><strong>UserName</strong></td>
		<td><input name="UserName"></input></td>
	</tr>
	<tr>
		<td><strong>Password</strong></td>
		<td><input name="Password"></input></td>
	</tr>
	</table>
	<input type="submit" value="Register"></input>
</form>
</body>
</html>