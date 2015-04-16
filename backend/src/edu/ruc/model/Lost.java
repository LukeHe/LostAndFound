package edu.ruc.model;

public class Lost {
	public Lost() { }
	public Lost(String lostID, String userID)
	{ this.lostID = lostID; this.userID = userID; }
	private String lostID;
	private String userID;
	private String lostType;
	private String lostDate;
	private String lostPlace;
	private String reportDate;
	public String getLostID()
	{ return lostID; }
	public void setLostID(String lostID)
	{ this.lostID = lostID; }
	public String getUserID()
	{ return userID; }
	public void setUserID(String userID)
	{ this.userID = userID; }
	public String getLostType()
	{ return lostType; }
	public void setLostType(String lostType)
	{ this.lostType = lostType; }
	public String getLostDate()
	{ return lostDate; }
	public void setLostDate(String lostDate)
	{ this.lostDate = lostDate; }
	public String getLostPlace()
	{ return lostPlace; }
	public void setLostPlace(String lostPlace)
	{ this.lostPlace = lostPlace; }
	public String getReportDate()
	{ return reportDate; }
	public void setReportDate(String reportDate)
	{ this.reportDate = reportDate; }
}
