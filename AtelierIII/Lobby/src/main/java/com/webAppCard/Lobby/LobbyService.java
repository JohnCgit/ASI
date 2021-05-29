package com.webAppCard.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LobbyService {
	
	 private final RestTemplate restTemplate;
	 public static int ReverseProxyPort = 8082;
	@Autowired
	RoomRepository rRepository;
	
	public LobbyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	public Room getRoom(int id) {
			Optional<Room> rOpt = rRepository.findById(id);
			if (rOpt.isPresent()) {
				return rOpt.get();
			}else {
				return null;
			}	
		}
	
	public List<Room> getAllRoom(){
		return (List<Room>) rRepository.findAll();
	}
//	public String joinRoom(int idRoom, int idPlayer2, int idCardPlayer2) {
//		Room r = getRoom(idRoom);
//		r.setPlayer2(idPlayer2, idCardPlayer2);
//		rRepository.save(r);
//		return startGame(idRoom);
//	}
	public boolean joinRoom(int idRoom, int idPlayer2, int idCardPlayer2) {
		Room r = getRoom(idRoom);
		int mise = r.getMise();	
		boolean res = false;
		int moneyPlayer = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getMoney/"+idPlayer2, Integer.class);
		List<Integer> collection = (List<Integer>)this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getCollection/"+idPlayer2, List.class);
		if(collection.contains(idCardPlayer2)&&moneyPlayer>=mise) {
			r.setPlayer2(idPlayer2, idCardPlayer2);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+idPlayer2+"/"+-mise,null);
			rRepository.save(r);
			res=true;
		}
		return res;
	}
	public boolean createRoom(int idPlayer, int idCard, int mise) {
		boolean res = false;
		System.out.print("create");
		int moneyPlayer = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getMoney/"+idPlayer, Integer.class);
		List<Integer> collection = (List<Integer>)this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getCollection/"+idPlayer, List.class);
		if(collection.contains(idCard)&&moneyPlayer>=mise) {
			Room room = new Room(idPlayer,idCard,mise);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+idPlayer+"/"+-mise,null);
			rRepository.save(room);
			res=true;
		}
		return res;
		
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
