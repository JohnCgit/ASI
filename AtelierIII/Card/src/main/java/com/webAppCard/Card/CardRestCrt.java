package com.webAppCard.Card;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  import org.springframework.web.bind.annotation.RestController;

  @RestController
  public class CardRestCrt {
      @Autowired
      CardService cService;
      
      @RequestMapping(method=RequestMethod.POST,value="/New")
      public void createCard(@RequestBody Card Card) {
    	  System.out.print(Card);
    	  cService.createCard(Card);
      }
      
      @RequestMapping(method=RequestMethod.GET,value="/{id}")
      public Card getCard(@PathVariable int id) {
          Card c=cService.getCard(id);
          System.out.print("ok");
          System.out.print(c);
          return c;
      }
         
      @RequestMapping(method=RequestMethod.GET,value="/Energy/{id}")
      public int getEnergy(@PathVariable int id) {
          return cService.getEnergy(Integer.valueOf(id));
      }
      
      @RequestMapping(method=RequestMethod.GET,value="/Price/{id}")
      public int getPrice(@PathVariable int id) {
          return cService.getPrice(Integer.valueOf(id));
      }
  }
