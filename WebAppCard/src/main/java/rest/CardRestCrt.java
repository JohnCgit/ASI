package rest;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  import org.springframework.web.bind.annotation.RestController;

  import model.Card;
  import service.CardService;

  @RestController
  public class CardRestCrt {
      @Autowired
      CardService cService;
      
      @RequestMapping(method=RequestMethod.POST,value="/Card")
      public void addCard(@RequestBody Card Card) {
          cService.addCard(Card);
      }
      
      @RequestMapping(method=RequestMethod.GET,value="/Card/{id}")
      public Card getCard(@PathVariable int id) {
          Card c=cService.getCard(Integer.valueOf(id));
          return c;
      }
  }
