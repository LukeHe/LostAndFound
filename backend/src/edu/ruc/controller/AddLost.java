package edu.ruc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import edu.ruc.model.*;

/**
 * Servlet implementation class AddLost
 */
@WebServlet("/AddLost")
public class AddLost extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String Driver = null;
	String Database = null;
	String User = null;
	String Password = null;
	String UserTable = null;
	String LostTable = null;
	String PickTable = null;
	String MessageTable = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLost() {
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
		// response.getWriter().println("Hello from AddList");
		initParams();
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("UserID");
		String lostType = request.getParameter("LostType");
		String lostYear = request.getParameter("LostYear");
		String lostMonth = request.getParameter("LostMonth");
		String lostDay = request.getParameter("LostDay");
		String lostDate = lostYear + "-" + lostMonth + "-" + lostDay;
		String lostPlace = request.getParameter("LostPlace");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("UserID", userID);
		params.put("LostType", lostType);
		params.put("LostDate", lostDate);
		params.put("LostPlace", lostPlace);
		UserDB userDB = new UserDB(Driver, Database, User, Password);
		try { userDB.insertLost(LostTable, params); } catch (Exception e) { e.printStackTrace(); }
		ArrayList<Lost> losts = null;
		try { losts = userDB.selectLost(LostTable, userID); } catch (Exception e) { e.printStackTrace(); }
		ArrayList<Pick> picks = null;
		try { picks = userDB.selectPick(PickTable, userID); } catch (Exception e) { e.printStackTrace(); }
		ArrayList<Message> rMessages = null;
		try { rMessages = userDB.selectMessageReceived(MessageTable, userID); } catch (Exception e) { e.printStackTrace(); }
		ArrayList<Message> sMessages = null;
		try { sMessages = userDB.selectMessageSent(MessageTable, userID); } catch (Exception e) { e.printStackTrace(); }
		session.removeAttribute("UserID");
		session.setAttribute("UserID", userID);
		session.removeAttribute("Losts");
		session.setAttribute("Losts", losts);
		session.removeAttribute("Picks");
		session.setAttribute("Picks", picks);
		session.removeAttribute("rMessages");
		session.setAttribute("rMessages", rMessages);
		session.removeAttribute("sMessages");
		session.setAttribute("sMessages", sMessages);
		response.sendRedirect("homepage.jsp");
	}
	
	public void initParams()
	{
        // Initilize parameters about database from web.xml
		if (Driver == null) { Driver = getServletContext().getInitParameter("Driver"); }
		if (Database == null) { Database = getServletContext().getInitParameter("Database"); }
		if (User == null) { User = getServletContext().getInitParameter("DatabaseUser"); }
		if (Password == null) { Password = getServletContext().getInitParameter("DatabasePassword"); }
		if (UserTable == null) { UserTable = getServletContext().getInitParameter("UserInfoTable"); }
		if (LostTable == null) { LostTable = getServletContext().getInitParameter("LostTable"); }
		if (PickTable == null) { PickTable = getServletContext().getInitParameter("PickTable"); }
		if (MessageTable == null) { MessageTable = getServletContext().getInitParameter("MessageTable"); }
	}
}
