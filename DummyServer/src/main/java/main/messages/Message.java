package main.messages;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public abstract class Message {
	protected String payload;
	protected String type;
	protected long id;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	
	public abstract void setPayload(String message);
	public abstract String getPayload();
	public abstract int getLenght();
	public abstract long getId();
	public abstract void setId(long id);
	public abstract String getType();
	public abstract void setType (String type);
	public abstract void setDate(Date date);
	public abstract Date getDate();
}
