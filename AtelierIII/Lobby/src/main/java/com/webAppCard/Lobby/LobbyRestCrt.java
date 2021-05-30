package com.webAppCard.Lobby;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LobbyRestCrt {

	@Autowired
	LobbyService lService;
	
	@RequestMapping(method=RequestMethod.GET,value="/getAll")
	public List<Room>getAllRoom() {
		return lService.getAllRoom();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/{idRoom}")
	public Room getRoom(@PathVariable int idRoom) {
		return lService.getRoom(idRoom);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/join/{idRoom}/{idPlayer}/{idCard}")
	public boolean joinRoom(@PathVariable int idRoom,@PathVariable int idPlayer,@PathVariable int idCard) {
		return lService.joinRoom(idRoom,idPlayer,idCard);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/create/{idPlayer}/{idCard}/{mise}")
	public int createRoom(@PathVariable int idPlayer,@PathVariable int idCard,@PathVariable int mise) {
		return lService.createRoom(idPlayer,idCard,mise);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/play/{idRoom}")
	public String play(@PathVariable int idRoom) {
		return lService.play(idRoom);
	}
	
}
