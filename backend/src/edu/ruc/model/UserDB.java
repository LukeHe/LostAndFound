package edu.ruc.model;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.*;

public class UserDB {
	
	String Driver = null;
	String Database = null;
	String User = null;
	String Password = null;
	
	public UserDB()
	{ }
	public UserDB(String driver, String database, String user, String password)
	{ this.Driver = driver; this.Database = database; this.User = user; this.Password = password; }
	
	public Connection connect() throws Exception
	{ 
		Class.forName(Driver);
		Connection conn = DriverManager.getConnection(Database, User, Password);
		System.out.println("Successfully connect to database");
		return conn;
	}
	
	public void disconnect(Connection conn) throws Exception
	{
		if (conn != null)
		{ conn.close(); }
		System.out.println("Successfully disconnect to database");
	}
	
	public void SelectUser(String userTable, String userID)
	{
		
	}
	
	public ArrayList<Lost> selectLost(String lostTable, String userID) throws Exception
	{ 
		// Connect to database
		Connection conn = connect();
		
		// Select lost belonging to the UserID
		ArrayList<Lost> losts = new ArrayList<Lost>();
		Statement stat = conn.createStatement();
		String sql = "select * from `" + lostTable + "` where `User_ID` = " + userID + ";";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next())
		{
			//System.out.println(rs.getString("Lost_ID"));
			String lostID = rs.getString("Lost_ID");
			String lostType = rs.getString("Lost_Type");
			String lostDate = rs.getString("Lost_Date");
			String reportDate = rs.getString("Report_Date");
			Lost lost = new Lost(lostID, userID);
			lost.setLostType(lostType);
			lost.setLostDate(lostDate);
			lost.setReportDate(reportDate);
			losts.add(lost);
		}
		
		// Disconnect to database
		disconnect(conn);
		return losts;
	}
	
	public ArrayList<Pick> selectPick(String pickTable, String userID) throws Exception
	{ 
		// Connect to database
		Connection conn = connect();
		
		// Select lost belonging to the UserID
		ArrayList<Pick> picks = new ArrayList<Pick>();
		Statement stat = conn.createStatement();
		String sql = "select * from `" + pickTable + "` where `User_ID` = " + userID + ";";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next())
		{
			//System.out.println(rs.getString("Lost_ID"));
			String pickID = rs.getString("Pick_ID");
			String pickType = rs.getString("Pick_Type");
			String pickDate = rs.getString("Pick_Date");
			String reportDate = rs.getString("Report_Date");
			Pick pick = new Pick(pickID, userID);
			pick.setPickType(pickType);
			pick.setPickDate(pickDate);
			pick.setReportDate(reportDate);
			picks.add(pick);
		}
		
		// Disconnect to database
		disconnect(conn);
		return picks;
	}
	
	public ArrayList<Message> selectMessageReceived(String messageTable, String userID) throws Exception
	{ 
		// Connect to database
		Connection conn = connect();
		
		// Select message whose ReceiverID is UserID
		ArrayList<Message> receiverMessages = new ArrayList<Message>();
		Statement stat = conn.createStatement();
		String sql = "select * from `" + messageTable + "` where `Receiver_ID` = " + userID + ";";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next())
		{
			String messageID = rs.getString("Message_ID");
			String senderID = rs.getString("Sender_ID");
			String receiverID = rs.getString("Receiver_ID");
			String context = rs.getString("Context");
			String date = rs.getString("Date");
			Message message = new Message(messageID, senderID, receiverID);
			message.setContext(context);
			message.setDate(date);
			receiverMessages.add(message);
		}
		
		// Disconnect to database
		disconnect(conn);
		return receiverMessages;	
	}
	
	public ArrayList<Message> selectMessageSent(String messageTable, String userID) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// Select messages whose SenderID is UserID
		ArrayList<Message> senderMessages = new ArrayList<Message>();
		Statement stat = conn.createStatement();
		String sql = "select * from `" + messageTable + "` where `Sender_ID` = " + userID + ";";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next())
		{
			String messageID = rs.getString("Message_ID");
			String senderID = rs.getString("Sender_ID");
			String receiverID = rs.getString("Receiver_ID");
			String context = rs.getString("Context");
			String date = rs.getString("Date");
			Message message = new Message(messageID, senderID, receiverID);
			message.setContext(context);
			message.setDate(date);
			senderMessages.add(message);
		}
		
		// Disconnect to database
		disconnect(conn);
		return senderMessages;
	}
	

	public void insertLost(String lostTable, HashMap<String, String> params) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// insert new lost into lost table
		String userID = params.get("UserID");
		String lostType = params.get("LostType");
		String lostDate = null;
		if (params.containsKey("LostDate"))
		{ lostDate = params.get("LostDate"); }
		String lostPlace = null;
		if (params.containsKey("LostPlace"))
		{ lostPlace = params.get("LostPlace"); }
		//Iterator iter = params.entrySet().iterator(); while (iter.hasNext()) { Map.Entry entry = (Map.Entry) iter.next(); Object key = entry.getKey(); Object val = entry.getValue(); }
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String reportDate = dateFormat.format(new Date());
		System.out.println(userID + " " + lostType + " " + lostDate + " " + lostPlace + " " + reportDate);
		Statement stat = conn.createStatement();
		String sql = "insert into `" + lostTable + "` (`User_ID`, `Lost_Date`, `Lost_Type`, `Report_Date`) values ('" + userID + "', '" + lostDate + "', '" + lostType + "', '" + reportDate + "');";
		stat.executeUpdate(sql);
		
		// Disconnect to database
		disconnect(conn);
	}
	
	public void insertPick(String pickTable, HashMap<String, String> params) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// insert new lost into lost table
		String userID = params.get("UserID");
		String pickType = params.get("PickType");
		String pickDate = null;
		if (params.containsKey("PickDate"))
		{ pickDate = params.get("PickDate"); }
		String pickPlace = null;
		if (params.containsKey("PickPlace"))
		{ pickPlace = params.get("PickPlace"); }
		//Iterator iter = params.entrySet().iterator(); while (iter.hasNext()) { Map.Entry entry = (Map.Entry) iter.next(); Object key = entry.getKey(); Object val = entry.getValue(); }
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String reportDate = dateFormat.format(new Date());
		System.out.println(userID + " " + pickType + " " + pickDate + " " + pickPlace + " " + reportDate);
		Statement stat = conn.createStatement();
		String sql = "insert into `" + pickTable + "` (`User_ID`, `Pick_Date`, `Pick_Type`, `Report_Date`) values ('" + userID + "', '" + pickDate + "', '" + pickType + "', '" + reportDate + "');";
		stat.executeUpdate(sql);
		
		// Disconnect to database
		disconnect(conn);
	}
	
	public void insertMessage(String messageTable, HashMap<String, String> params) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// insert new lost into lost table
		String userID = params.get("UserID");
		String receiverID = params.get("ReceiverID");
		String context = params.get("Context");
		if (context == null) { return; }
		//Iterator iter = params.entrySet().iterator(); while (iter.hasNext()) { Map.Entry entry = (Map.Entry) iter.next(); Object key = entry.getKey(); Object val = entry.getValue(); }
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		System.out.println(userID + " " + receiverID + " " + context + " " + date);
		Statement stat = conn.createStatement();
		String sql = "insert into `" + messageTable + "` (`Sender_ID`, `Receiver_ID`, `Context`, `Date`) values ('" + userID + "', '" + receiverID + "', '" + context + "', '" + date + "');";
		stat.executeUpdate(sql);
		
		// Disconnect to database
		disconnect(conn);
	}
	
}
