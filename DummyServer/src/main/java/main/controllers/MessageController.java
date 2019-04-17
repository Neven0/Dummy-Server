package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.messages.Emotion;
import main.messages.Message;
import main.messages.Text;
import main.repository.EmotionRepository;
import main.repository.TextRepository;

@RestController
public class MessageController {

	@Autowired
	private EmotionRepository emotionRepository;
	
	@Autowired
	private TextRepository textRepository;
	
	@PostMapping(path="/send_emotion", consumes="application/json")
	public ResponseEntity<?> MessageInfo(@RequestBody Message message) {
		if (message.getLenght()<2 || message.getLenght()>10 || message.hasNumber()) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		else {
			emotionRepository.save(new Emotion(message));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
		
	@PostMapping(path="/send_text", consumes="application/json")
	public ResponseEntity<?> TextInfo(@RequestBody Message message) {
		if (message.getLenght()<1 || message.getLenght()>160) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		else {
			textRepository.save(new Text(message));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
}
