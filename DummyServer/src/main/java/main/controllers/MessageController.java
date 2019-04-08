package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.messages.Emotion;
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
	public ResponseEntity<?> MessageInfo(@RequestBody Emotion emotion) {
		if (emotion.getLenght()<2 || emotion.getLenght()>10 || emotion.hasNumber()) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		else {
			emotionRepository.save(emotion);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
		
	@PostMapping(path="/send_text", consumes="application/json")
	public ResponseEntity<?> TextInfo(@RequestBody Text text) {
		if (text.getLenght()<1 || text.getLenght()>160) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		else {
			textRepository.save(text);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}
	
}
