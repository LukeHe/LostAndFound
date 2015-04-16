package edu.ruc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.*;
import java.util.*;
import com.mysql.jdbc.Driver;
import java.sql.*;
import edu.ruc.model.*;

/**
 * Servlet implementation class VerifyUser
 */
@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String Driver = null;
	String Database = null;
	String DatabaseUser = null;
	String DatabasePassword = null;
	String UserInfoTable = null;
	String LostTable = null;
	String PickTable = null;
	String MessageTable = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		initParams();
		String userID = request.getParameter("UserID");
		String password = request.getParameter("Password");
		int flag = 0; // 1 means successful login; 2 means non-existent User_ID; 3 means wrong Password
		try { flag = validate(userID, password); } catch (Exception e) { e.printStackTrace(); }
		if (flag == 1)
		{ 
			UserDB userDB = new UserDB(Driver, Database, DatabaseUser, DatabasePassword);
			HttpSession session = request.getSession();
			ArrayList<Lost> losts = null;
			try { losts = userDB.selectLost(LostTable, userID); } catch (Exception e) { e.printStackTrace(); }
			ArrayList<Pick> picks = null;
			try { picks = userDB.selectPick(PickTable, userID); } catch (Exception e) { e.printStackTrace(); }
			ArrayList<Message> rMessages = null;
			try { rMessages = userDB.selectMessageReceived(MessageTable, userID); } catch (Exception e) { e.printStackTrace(); }
			ArrayList<Message> sMessages = null;
			try { sMessages = userDB.selectMessageSent(MessageTable, userID); } catch (Exception e) { e.printStackTrace(); }
			
			session.setAttribute("UserID", userID);
			session.setAttribute("Losts", losts);
			session.setAttribute("Picks", picks);
			session.setAttribute("rMessages", rMessages);
			session.setAttribute("sMessages", sMessages);
			response.sendRedirect("homepage.jsp");
		}
		else
		{ 
			request.setAttribute("Flag", flag);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	public int validate(String userID, String password) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// Verify user identification in user_info table
		int flag = 0; // 1 means successful login; 2 means non-existent User_ID; 3 means wrong Password
		Statement stat = conn.createStatement();
		String sql = "select `Password` from `" + UserInfoTable + "` where `User_ID` = \"" + userID + "\";";
		ResultSet rs = stat.executeQuery(sql);
		if (!rs.next())
		{ flag = 2; }
		else
		{
			if (rs.getString("Password").equals(password))
			{ flag = 1; }
			else
			{ flag = 3; }
		}
		
		// Disconnect to database
		disconnect(conn);
		return flag;
	}
	
	public Connection connect() throws Exception
	{ 
		Class.forName(Driver);
		Connection conn = DriverManager.getConnection(Database, DatabaseUser, DatabasePassword);
		System.out.println("Successfully connect to database");
		return conn;
	}
	
	public void disconnect(Connection conn) throws Exception
	{
		if (conn != null)
		{ conn.close(); }
		System.out.println("Successfully disconnect to database");
	}

	public void initParams()
	{
        // Initilize parameters about database from web.xml
		if (Driver == null) { Driver = getServletContext().getInitParameter("Driver"); }
		if (Database == null) { Database = getServletContext().getInitParameter("Database"); }
		if (DatabaseUser == null) { DatabaseUser = getServletContext().getInitParameter("DatabaseUser"); }
		if (DatabasePassword == null) { DatabasePassword = getServletContext().getInitParameter("DatabasePassword"); }
		if (UserInfoTable == null) { UserInfoTable = getServletContext().getInitParameter("UserInfoTable"); }
		if (LostTable == null) { LostTable = getServletContext().getInitParameter("LostTable"); }
		if (PickTable == null) { PickTable = getServletContext().getInitParameter("PickTable"); }
		if (MessageTable == null) { MessageTable = getServletContext().getInitParameter("MessageTable"); }
	}
}
