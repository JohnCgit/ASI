package com.webAppCard.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
public class LobbyService {
	
	 public static int ReverseProxyPort = 8081;
	@Autowired
	RoomRepository rRepository;
	
	public Room getRoom(int id) {
			Optional<Room> rOpt = rRepository.findById(id);
			if (rOpt.isPresent()) {
				return rOpt.get();
			}else {
				return null;
			}	
		}
	
	public List<Room> getAllRoom(){
		List<Room> res = new ArrayList<Room>();
		int id = 0;
		Room r = getRoom(id);
		while(r != null) 
		{
			res.add(r);
			r = getRoom(id++);
		}
		return res;
	}
//	public String joinRoom(int idRoom, int idPlayer2, int idCardPlayer2) {
//		Room r = getRoom(idRoom);
//		r.setPlayer2(idPlayer2, idCardPlayer2);
//		rRepository.save(r);
//		return startGame(idRoom);
//	}
	public void joinRoom(int idRoom, int idPlayer2, int idCardPlayer2) {
		Room r = getRoom(idRoom);
		r.setPlayer2(idPlayer2, idCardPlayer2);
		rRepository.save(r);
	}
	public void createRoom(int idPlayer, int idCard, int mise) {
		//hasCard
		//hasMoney
		Room res = new Room(idPlayer,idCard,mise);
		rRepository.save(res);
	}
	public String startGame(int idRoom) {
		Room r = getRoom(idRoom);
//		String url="http://127.0.0.1:"+ReverseProxyPort+"/game/"+r.getIdCardPlayer1()+"/"+r.getIdCardPlayer2();
		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<String> request = new HttpEntity<>("game");
//		String res = restTemplate.postForObject(url, request, String.class);
		String reponse = restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/game/"+r.getIdCardPlayer1()+"/"+r.getIdCardPlayer2(), String.class);	
		return reponse;
	}

}
