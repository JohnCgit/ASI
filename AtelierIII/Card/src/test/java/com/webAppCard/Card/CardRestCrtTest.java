package com.webAppCard.Card;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.webAppCard.Card.Card;
import com.webAppCard.Card.CardService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CardRestCrt.class)
public class CardRestCrtTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CardService cService;

	Card mockCard=new Card("Salamaiche", "Lezard", null, 40, 60, 10, 200, "https://i.imgur.com/szoTQIA.png");
	//TODO create getallcard and fix this
//	@Test
//	public void getAllCard() throws Exception {
//		List<Card> LCard = new ArrayList<>();
//		LCard.add(mockCard);
//		Mockito.when(
//				cService.getAllCard()
//				).thenReturn(LCard);
//				
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll").accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse().getContentAsString());
//		String expectedResult="{\"name\":\"Salamaiche\",\"family\":\"Lezard\",\"affinite\":null,\"energy\":40,\"hp\":60,\"strength\":10,\"price\":200,\"imgUrl\":\"https://i.imgur.com/szoTQIA.png\"}";
//
//
//		JSONAssert.assertEquals(expectedResult, result.getResponse()
//				.getContentAsString(), false);
//	}

}
