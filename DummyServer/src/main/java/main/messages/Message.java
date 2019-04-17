package main.messages;

import java.util.regex.Pattern;

public class Message {
	
	private String payload;
	
	public Message () {}
	public Message (String text) {
		payload=text;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	public int getLenght() {
		return payload.length();
	}
	
	public boolean hasNumber() {
		return Pattern.matches("^(?=.*\\d).+$", payload);
	}
	
}
