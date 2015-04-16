package edu.ruc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*;
import java.util.*;
import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String Driver = null;
	String Database = null;
	String User = null;
	String Password = null;
	String UserTable = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		String userName = request.getParameter("UserName");
		String password = request.getParameter("Password");
		System.out.println(userName + " " + password);
		String userID = null;
		try 
		{ userID = addUser(userName, password); }
		catch (Exception e)
		{ e.printStackTrace(); }
		request.setAttribute("UserID", userID);
		request.setAttribute("Password", password);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public String addUser(String userName, String password) throws Exception
	{
		// Connect to database
		Connection conn = connect();
		
		// insert new user into user_info table
		String userID = null;
		Statement stat = conn.createStatement();
		String sql = "insert into " + UserTable + "(`Password`, `User_Name`) values(\"" + password + "\",\"" + userName + "\");";
		stat.executeUpdate(sql);
		sql = "select last_insert_id();"; ResultSet rs = stat.executeQuery(sql); 
		rs.next();	// Note that this sentence is necessary 
		userID = rs.getString("last_insert_id()");
		
		// Disconnect to database
		disconnect(conn);
		return userID;
	}
	
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

	public void initParams()
	{
        // Initilize parameters about database from web.xml
		if (Driver == null) { Driver = getServletContext().getInitParameter("Driver"); }
		if (Database == null) { Database = getServletContext().getInitParameter("Database"); }
		if (User == null) { User = getServletContext().getInitParameter("DatabaseUser"); }
		if (Password == null) { Password = getServletContext().getInitParameter("DatabasePassword"); }
		if (UserTable == null) { UserTable = getServletContext().getInitParameter("UserInfoTable"); }
	}
}
