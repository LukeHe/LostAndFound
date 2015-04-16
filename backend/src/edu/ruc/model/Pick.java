package edu.ruc.model;

public class Pick {
	private String pickID;
	private String userID;
	private String pickType;
	private String pickDate;
	private String pickPlace;
	private String reportDate;
	public Pick() { }
	public Pick(String pickID, String userID)
	{ this.pickID = pickID; this.userID = userID; }
	public String getPickID()
	{ return pickID; }
	public void setPicktID(String pickID)
	{ this.pickID = pickID; }
	public String getUserID()
	{ return userID; }
	public void setUserID(String userID)
	{ this.userID = userID; }
	public String getPickType()
	{ return pickType; }
	public void setPickType(String pickType)
	{ this.pickType = pickType; }
	public String getPickDate()
	{ return pickDate; }
	public void setPickDate(String pickDate)
	{ this.pickDate = pickDate; }
	public String getPickPlace()
	{ return pickPlace; }
	public void setPickPlace(String pickPlace)
	{ this.pickPlace = pickPlace; }
	public String getReportDate()
	{ return reportDate; }
	public void setReportDate(String reportDate)
	{ this.reportDate = reportDate; }
}
