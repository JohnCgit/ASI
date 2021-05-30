package com.WebAppCard.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRestCrt {
	
	
	@Autowired
	private GameService gs;
	
	@RequestMapping(method=RequestMethod.GET,value="/{idCardA}/{idCardB}")
	public String partie(@PathVariable int idCardA, @PathVariable int idCardB) {
		return gs.Jeu(idCardA, idCardB);
	}
}
