package com.webAppCard.Market;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.webAppCard.Market.Market;
import com.webAppCard.Market.TransactionRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MarketService.class)
public class MarketServiceTest {
	
	@Autowired
	private MarketService mService;

	@MockBean
	private TransactionRepository tRepository;
	@MockBean
	private RestTemplateBuilder restTemplateBuilder;
	@MockBean
	private RestTemplate restTemplate;
		
	Transaction tmpTrans = new Transaction(2,1);
	
	@Before
	public void setUp() {
		restTemplateBuilder=new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Test
	public void getTransaction() {
		Mockito.when(
				tRepository.findById(Mockito.anyInt())
				).thenReturn(Optional.ofNullable(tmpTrans));
		Transaction infoTrans = mService.getTransaction(73);
		assertTrue(infoTrans.getIdCard()==2);
	}
	
	@Test
	public void getAll() {
		List<Transaction> ListTrans = new ArrayList<>();
		ListTrans.add(tmpTrans);
		Mockito.when(
				tRepository.findAll()
				).thenReturn(ListTrans);
		List<Transaction> InfoTrans = tRepository.findAll();
		assertTrue(InfoTrans.size()==1);
	}
}
