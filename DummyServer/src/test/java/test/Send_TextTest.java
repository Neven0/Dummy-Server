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
import main.messages.Text;
import main.repository.TextRepository;
import main.messages.Message;

@RunWith(SpringRunner.class)
public class Send_TextTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private MessageController messageController;
	@Mock
	private TextRepository textRepo;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}
	
	@Test
	public void Text_good_payload() throws Exception{
		Message message= new Message("Testing");
		Text text=new Text(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_text")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(text)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void Text_bad_payload_under_1() throws Exception{
		Message message= new Message("");
		Text text=new Text(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/send_text")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(text)))
				.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}
	
	@Test
	public void Text_bad_payload_over_160() throws Exception{
		Message message= new Message("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+ 
									"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+ 
									"AAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); //161 A
		Text text=new Text(message);
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
