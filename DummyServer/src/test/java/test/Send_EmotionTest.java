package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.controllers.MessageController;
import main.messages.Emotion;
import main.messages.Message;
import main.repository.EmotionRepository;

@RunWith(SpringRunner.class)
public class Send_EmotionTest {

private MockMvc mockMvc;
	
	@InjectMocks
	private MessageController messageController;
	@Mock
	private EmotionRepository emotionRepo;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}
	
	@Test
	public void Emotion_good_payload() throws Exception{
		Message message= new Message(":)");
		Emotion emotion=new Emotion(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void Emotion_bad_payload_under_2() throws Exception{
		Message message= new Message("T");
		Emotion emotion=new Emotion(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Emotion_bad_payload_above_10() throws Exception{
		Message message= new Message("Testing testing testing");
		Emotion emotion=new Emotion(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Emotion_bad_payload_with_number() throws Exception{
		Message message= new Message(":)1");
		Emotion emotion=new Emotion(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	public static String asJsonString(Object obj) {
		try {
		return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
