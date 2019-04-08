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
import main.messages.Text;
import main.repository.EmotionRepository;
import main.repository.TextRepository;

@RunWith(SpringRunner.class)
public class MessageControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private MessageController messageController;
	@Mock
	private EmotionRepository emotionRepo;
	@Mock
	private TextRepository textRepo;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}
	
	@Test
	public void Emotion_good_payload() throws Exception{
		Emotion emotion=new Emotion();
		emotion.setPayload(":)");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void Emotion_bad_payload_under_2() throws Exception{
		Emotion emotion=new Emotion();
		emotion.setPayload("T");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Emotion_bad_payload_above_10() throws Exception{
		Emotion emotion=new Emotion();
		emotion.setPayload("Testing testing testing");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Emotion_bad_payload_with_number() throws Exception{
		Emotion emotion=new Emotion();
		emotion.setPayload(":)1");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_emotion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emotion)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Text_good_payload() throws Exception{
		Text text=new Text();
		text.setPayload("Testing");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_text")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(text)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void Text_bad_payload_under_1() throws Exception{
		Text text=new Text();
		text.setPayload("");
		mockMvc.perform(MockMvcRequestBuilders.post("/send_text")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(text)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Text_bad_payload_over_160() throws Exception{
		Text text=new Text();
		text.setPayload("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); //161 A
		mockMvc.perform(MockMvcRequestBuilders.post("/send_text")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(text)))
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
