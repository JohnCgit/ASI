package com.WebAppCard.Game;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = GameRestCrt.class)
public class GameRestCrtTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GameService gService;
	
	@Test
	public void partie() throws Exception {
		Mockito.when(
				gService.Jeu(Mockito.anyInt(),Mockito.anyInt())
				).thenReturn("oui");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/23/55").accept(MediaType.ALL);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String res = result.getResponse().getContentAsString();
				
		String expectedResult = "oui";		
		
		assertTrue(expectedResult.equals(res));
	}
}
