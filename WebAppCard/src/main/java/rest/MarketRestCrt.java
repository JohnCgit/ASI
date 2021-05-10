package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Transaction;
import repository.TransactionRepository;
import service.MarketService;

@RestController
public class MarketRestCrt {
	
	@Autowired
	MarketService mService;
	
	@RequestMapping(method=RequestMethod.GET,value="/market/getAll")
	public void getAllCard() 
	{
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/sell/{idCard}/{idSeller}")
	public void sellCard(@PathVariable String idCard, @PathVariable String idSeller) 
	{
		Transaction t = new Transaction()
		addTransaction(Transaction t)
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/buy/{idCard}/{idBuyer}")
	public void buyCard(@PathVariable String idCard, @PathVariable String idBuyer) 
	{
	}
	
}
