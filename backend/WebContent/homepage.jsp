<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,edu.ruc.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
</head>
<body>
<form method="post" action="index.jsp">
	<input type="submit" value="logout" ></input>
</form>
<form method="post" action="AddLost">
	<table>
	<tr>
		<td ><strong>LostType</strong></td>
		<td><input name="LostType"></input></td>
	</tr>
	<tr>
		<td><strong>LostYear</strong></td>
		<td><input name="LostYear"></input></td>
	</tr>
	<tr>
		<td><strong>LostMonth</strong></td>
		<td><input name="LostMonth"></input></td>
	</tr>
		<tr>
		<td><strong>LostDay</strong></td>
		<td><input name="LostDay"></input></td>
	</tr>
	<tr>
		<td><strong>LostPlace</strong></td>
		<td><input name="LostPlace"></input></td>
	</tr>
	</table>
	<input type="submit" value="AddLost"></input>
</form>
<form method="post" action="AddPick">
	<table>
	<tr>
		<td ><strong>PickType</strong></td>
		<td><input name="PickType"></input></td>
	</tr>
	<tr>
		<td><strong>PickYear</strong></td>
		<td><input name="PickYear"></input></td>
	</tr>
	<tr>
		<td><strong>PickMonth</strong></td>
		<td><input name="PickMonth"></input></td>
	</tr>
	<tr>
		<td><strong>PickDay</strong></td>
		<td><input name="PickDay"></input></td>
	</tr>
	<tr>
		<td><strong>PickPlace</strong></td>
		<td><input name="PickPlace"></input></td>
	</tr>
	</table>
	<input type="submit" value="AddPick"></input>
</form>
<form method="post" action="AddMessage">
	<table>
	<tr>
		<td ><strong>ReceiverID</strong></td>
		<td><input name="ReceiverID"></input></td>
	</tr>
	<tr>
		<td><strong>Context</strong></td>
		<td><input name="Context"></input></td>
	</tr>
	</table>
	<input type="submit" value="AddMessage"></input>
</form>
<%
	String userID = (String)session.getAttribute("UserID");
	if (userID != null)
	{ out.println("Welcome Back, My Dear " + userID + "<br><br><br>"); }
	
	ArrayList<Lost> losts = (ArrayList<Lost>)session.getAttribute("Losts");
	if (losts != null)
	{
		out.println("You have lost the following items:<br>");
		out.println("LostID\tLostType\tLostDate<br>");
		for (int i = 0; i < losts.size(); i++)
		{ 
			String lostID = losts.get(i).getLostID();
			String lostType = losts.get(i).getLostType();
			String lostDate = losts.get(i).getLostDate();
			out.println(lostID + "\t" + lostType + "\t" + lostDate + "<br>");
		}	
		out.println("<br><br>");
	}

	ArrayList<Pick> picks = (ArrayList<Pick>)session.getAttribute("Picks");
	if (picks != null)
	{
		out.println("You have picked the following items:<br>");
		out.println("PickID\tPickType\tPickDate<br>");
		for (int i = 0; i < picks.size(); i++)
		{ 
			String pickID = picks.get(i).getPickID();
			String pickType = picks.get(i).getPickType();
			String pickDate = picks.get(i).getPickDate();
			out.println(pickID + "\t" + pickType + "\t" + pickDate + "<br>");
		}	
		out.println("<br><br>");
	}
	
	ArrayList<Message> rMessages = (ArrayList<Message>)session.getAttribute("rMessages");
	if (rMessages != null)
	{
		out.println("You have received the follwing messages:<br>");	
		out.println("SenderID\tContext\tDate<br>");
		for (int i = 0; i < rMessages.size(); i++)
		{ 
			String messageID = rMessages.get(i).getMessageID();
			String senderID = rMessages.get(i).getSenderID();
			String context = rMessages.get(i).getContext();
			String date = rMessages.get(i).getDate();
			out.println(senderID + "\t" + context + "\t" + date + "<br>");
		}	
		out.println("<br><br>");
	}
	
	ArrayList<Message> sMessages = (ArrayList<Message>)session.getAttribute("sMessages");
	if (sMessages != null)
	{
		out.println("You have sent the follwing messages:<br>");
		out.println("ReceiverID\tContext\tDate<br>");
		for (int i = 0; i < sMessages.size(); i++)
		{ 
			String messageID = sMessages.get(i).getMessageID();
			String receiverID = sMessages.get(i).getSenderID();
			String context = sMessages.get(i).getContext();
			String date = sMessages.get(i).getDate();
			out.println(receiverID + "\t" + context + "\t" + date + "<br>");
		}	
		out.println("<br><br>");
	}
%>
</body>
</html>