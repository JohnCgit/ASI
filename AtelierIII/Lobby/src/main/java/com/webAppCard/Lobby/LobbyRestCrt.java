package com.webAppCard.Lobby;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class LobbyRestCrt {

	@Autowired
	LobbyService lService;
	
	@RequestMapping(method=RequestMethod.GET,value="/All")
	public List<Room>getAllRoom() {
		return lService.getAllRoom();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/join/{idRoom}/{idPlayer}/{idCard}")
	public void joinRoom(@PathVariable int idRoom,@PathVariable int idPlayer,@PathVariable int idCard) {
		lService.joinRoom(idRoom,idPlayer,idCard);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/create/{idRoom}/{idPlayer}/{idCard}")
	public void createRoom(@PathVariable int idRoom,@PathVariable int idPlayer,@PathVariable int idCard) {
		lService.createRoom(idRoom,idPlayer,idCard);
	}
	
	
}
