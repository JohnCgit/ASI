package com.webAppCard.Utilisateur;

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

import com.webAppCard.Utilisateur.Utilisateur;
import com.webAppCard.Utilisateur.UtilisateurService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UtilisateurRestCrt.class)
public class UtilisateurRestCrtTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UtilisateurService uService;

	Utilisateur mockUser=new Utilisateur("User","Pwd","Surname");
	

	@Test
	public void getUserById() throws Exception {
		Mockito.when(
				uService.getUserById(Mockito.anyInt())
				).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/20").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//System.out.println(result.getResponse().getContentAsString());

		String expectedResult="{\"id\":0,\"name\":\"User\",\"surname\":\"Surname\",\"password\":\"Pwd\",\"collection\":[],\"money\":0,\"collectionSize\":0}";

		
		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	} 
	
	@Test
	public void getUserByName() throws Exception {
		Mockito.when(
				uService.getUserByName(Mockito.any())
				).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/name/20").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//System.out.println(result.getResponse().getContentAsString());

		String expectedResult="{\"id\":0,\"name\":\"User\",\"surname\":\"Surname\",\"password\":\"Pwd\",\"collection\":[],\"money\":0,\"collectionSize\":0}";

		
		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	} 
	
	@Test
	public void getAllUser() throws Exception {
		List<Utilisateur> LUser = new ArrayList<>();
		LUser.add(mockUser);
		Mockito.when(
				uService.getAllUsers()
				).thenReturn(LUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//System.out.println(result.getResponse().getContentAsString());

		String expectedResult="[{\"id\":0,\"name\":\"User\",\"surname\":\"Surname\",\"password\":\"Pwd\",\"collection\":[],\"money\":0,\"collectionSize\":0}]";

		
		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	} 
	
	@Test
	public void getCollection() throws Exception {
		List<Integer> LIdCard= new ArrayList<>();
		LIdCard.add(1);
		LIdCard.add(2);
		Mockito.when(
				uService.getCollection(Mockito.anyInt())
				).thenReturn(LIdCard);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCollection/85").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//System.out.println(result.getResponse().getContentAsString());

		String expectedResult="[1,2]";

		
		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	} 
	
	@Test
	public void getMoney() throws Exception {
		Integer Money = 500;
		Mockito.when(
				uService.getMoney(Mockito.anyInt())
				).thenReturn(Money);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getMoney/27").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		//System.out.println(result.getResponse().getContentAsString());

		String expectedResult="500";

		
		JSONAssert.assertEquals(expectedResult, result.getResponse()
				.getContentAsString(), false);
	} 
}
