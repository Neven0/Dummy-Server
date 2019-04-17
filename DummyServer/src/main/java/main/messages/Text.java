package main.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import main.messages.MessageType;
import main.messages.Message;

@Entity
@Table(name="messages")
public class Text implements MessageInterface {
	
	private long id;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private final MessageType type=MessageType.TEXT;
	private Message message;
	
	public Text(Message message) {
		date= new Date();
		this.message=message;
		}

	@Override
	public void setPayload(String payload) {
		message.setPayload(payload);
	}
	
	@Column(name="payload", nullable=false)
	@Override
	public String getPayload() {
		return message.getPayload();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Override
	public long getId() {
		return id;
	}
	@Column(name="type", nullable=false)
	@Override
	public String getType() {
		return type.getType();
	}
	@Override
	public void setType(MessageType type) {}

	@Override
	public void setId(long id) {
		this.id=id;
		
	}

	@Override
	public void setDate(Date date) {
		this.date=date;
		
	}

	@Column(name="created_at", nullable=false)
	@Override
	public Date getDate() {
		return date;
	}

}
