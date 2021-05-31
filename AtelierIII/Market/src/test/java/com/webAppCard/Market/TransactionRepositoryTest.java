package com.webAppCard.Market;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {
	
	@Autowired
	TransactionRepository tRepo;
	
	@Before
	public void setUp() {
		tRepo.save(new Transaction(10,20));
	}

	@After
	public void cleanUp() {
		tRepo.deleteAll();
	}
	
	@Test
	public void saveAndGetTransaction() {
		tRepo.deleteAll();
		tRepo.save(new Transaction(50,60));
		List<Transaction> TransList = new ArrayList<>();
		tRepo.findAll().forEach(TransList::add);
		assertTrue(TransList.size()==1);
		assertTrue(TransList.get(0).getIdCard()==50);
		assertTrue(TransList.get(0).getSellerId()==60);
		
	}
	
}