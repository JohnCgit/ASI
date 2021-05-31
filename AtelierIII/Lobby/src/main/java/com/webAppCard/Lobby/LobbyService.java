package com.webAppCard.Lobby;


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
	public int createRoom(int idPlayer, int idCard, int mise) {
		int id = -1;
		System.out.print("create");
		int moneyPlayer = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getMoney/"+idPlayer, Integer.class);
		List<Integer> collection = (List<Integer>)this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/user/getCollection/"+idPlayer, List.class);
		if(collection.contains(idCard)&&moneyPlayer>=mise) {
			Room room = new Room(idPlayer,idCard,mise);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+idPlayer+"/"+-mise,null);
			rRepository.save(room);
			id = room.getId();
		}
		return id;
		
	}
	public String startGame(int idRoom) {
		Room r = getRoom(idRoom);
		String reponse = this.restTemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/game/"+r.getIdCardPlayer1()+"/"+r.getIdCardPlayer2(), String.class);	
		return reponse;
	}

	public String play(int idRoom) {
		Room r = getRoom(idRoom);
		if(r.getMessage().isEmpty()) {
			r.setMessage(startGame(idRoom));
		}
		return r.getMessage();
	}

	private void deleteRoom(Room r) {
		rRepository.delete(r);
	}

	public void remove(int idRoom) {
		Room r = getRoom(idRoom);
		String reponse = r.getMessage();
		if(!reponse.isEmpty()) {
		int winner = (int)reponse.charAt(reponse.length()-1);
		if(winner==0) {
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+r.getIdPlayer1()+"/"+r.getMise(),null);
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+r.getIdPlayer2()+"/"+r.getMise(),null);
		}
		else if (winner == 1) {
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+r.getIdPlayer1()+"/"+r.getMise(),null);
		}
		else {
			this.restTemplate.put("http://127.0.0.1:"+ReverseProxyPort+"/user/updateMoney/"+r.getIdPlayer2()+"/"+r.getMise(),null);
		}}
		deleteRoom(r);
	}
}
