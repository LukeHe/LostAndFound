package edu.ruc.model;

public class Message {
	public String messageID;
	public String senderID;
	public String receiverID;
	public String context;
	public String date;
	public Message()
	{ }
	public Message(String messageID, String senderID, String receiverID)
	{ this.messageID = messageID; this.senderID = senderID; this.receiverID = receiverID; }
	public String getMessageID()
	{ return messageID; }
	public void setMessageID(String messageID)
	{ this.messageID = messageID; }
	public String getSenderID()
	{ return senderID; }
	public void setSenderID(String senderID)
	{ this.senderID = senderID; }
	public String getReceiverID()
	{ return receiverID; }
	public void setReceiverID(String receiverID)
	{ this.receiverID = receiverID; }
	public String getContext()
	{ return context; }
	public void setContext(String context)
	{ this.context = context; }
	public String getDate()
	{ return date; }
	public void setDate(String date)
	{ this.date = date; }
}
