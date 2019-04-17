package main.messages;

public enum MessageType {
	TEXT("Text"),
	EMOTION("Emotion");
	
	private final String Type;
	
	MessageType(String type){
		Type=type;
	}
	
	public String getType() {
		return Type;
	}

}
