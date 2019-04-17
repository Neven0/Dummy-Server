package main.messages;

import java.util.Date;
import main.messages.MessageType;

public interface MessageInterface {

	public void setPayload(String message);
	public String getPayload();
	public long getId();
	public void setId(long id);
	public String getType();
	public void setType (MessageType type);
	public void setDate(Date date);
	public Date getDate();
}
