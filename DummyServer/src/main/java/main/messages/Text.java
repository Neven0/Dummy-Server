package main.messages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="messages")
public class Text extends Message {
	
	public Text() {
		type="text";
		date= new Date();
		}

	@Override
	public void setPayload(String payload) {
		this.payload=payload;
	}
	@Column(name="payload", nullable=false)
	@Override
	public String getPayload() {
		return payload;
	}

	@Override
	@Transient
	public int getLenght() {
		return payload.length();
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
		return type;
	}
	@Override
	public void setType(String type) {
		this.type=type;
	}

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
