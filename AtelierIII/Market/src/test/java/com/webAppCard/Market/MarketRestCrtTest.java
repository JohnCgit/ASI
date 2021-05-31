package com.webAppCard.Market;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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

import com.webAppCard.Market.Market;
import com.webAppCard.Market.MarketService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MarketRestCrt.class)
public class MarketRestCrtTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MarketService mService;

	public List<Transaction> L_Transaction;

	@Test
	public void getAllCard() throws Exception {
		List<Transaction> L_Transaction = new ArrayList<>();
		L_Transaction.add(new Transaction(1,2));
		Mockito.when(
				mService.getAll()
				).thenReturn(L_Transaction);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedResult = "[{\"id\":0,\"sellerId\":2,\"idCard\":1}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);	
	}
	
	@Test
	public void sellCard() throws Exception{
		Mockito.when(
				mService.sellTransaction(Mockito.anyInt(), Mockito.anyInt())
				).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sell/99/54").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedResult = "true";
		
		assertTrue(expectedResult.equals(result.getResponse().getContentAsString()));			
	}
	
	public void buyCard() throws Exception {
		Mockito.when(
				mService.buyCard(Mockito.anyInt(), Mockito.anyInt())
				).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/buy/99/45").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expectedResult = "true";
		
		assertTrue(expectedResult.equals(result.getResponse().getContentAsString()));	
	}
}




//	Utilisateur mockUser=new Utilisateur("User","Pwd","Surname");
//	
//
//	@Test
//	public void getUserById() throws Exception {
//		Mockito.when(
//				uService.getUserById(Mockito.anyInt())
//				).thenReturn(mockUser);
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/20").accept(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//		//System.out.println(result.getResponse().getContentAsString());
//
//		String expectedResult="{\"id\":0,\"name\":\"User\",\"surname\":\"Surname\",\"password\":\"Pwd\",\"collection\":[],\"money\":0,\"collectionSize\":0}";
//
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse()
//				.getContentAsString(), false);
//	} 