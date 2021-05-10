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
	UserService uService;
	CardService cService;
	
	@RequestMapping(method=RequestMethod.GET,value="/market/getAll")
	public void getAllCard() 
	{
		mService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/sell/{idCard}/{idSeller}")
	public void sellCard(@PathVariable int idCard, @PathVariable int idSeller) 
	{
		mService.addTransaction(new Transaction(idCard,idSeller));
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/buy/{idTransaction}/{idBuyer}")
	public void buyCard(@PathVariable int idTransaction, @PathVariable int idBuyer) 
	{
		Transaction t = mService.getTransaction(idTransaction);
		uService.addCard(t.getCardId(),idBuyer);
		uService.updateMoney(t.getSellerId(),cService.getPrice(t.getCardId());
		uService.updateMoney(t.getBuyerId(),-cService.getPrice(t.getCardId());
		
	}
	
}
