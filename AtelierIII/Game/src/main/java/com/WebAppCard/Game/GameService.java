package com.WebAppCard.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
	
	RestTemplate restTemplate;	
	@Autowired
	public static int ReverseProxyPort = 8082;
	
	
	public Card getCard(int id) {	//avoir la carte
		RestTemplate resttemplate = new RestTemplate();
		return resttemplate.getForObject("http://127.0.0.1:"+ReverseProxyPort+"/card/"+id, Card.class);
	 }
	
	public int matchupAffinity(Affinity affA, Affinity affB) {
		int coeff = 1;	//coeff d'attaque qui va changer en fonction d'avantage/désavantage d'affinité
		if (affA.compareTo(affB) == 1 || affA.compareTo(affB) == -2) {
			coeff -= 0.25;
		}
		else if (affA.compareTo(affB) == -1 || affA.compareTo(affB) == 2 ) {
			 coeff += 0.25;
		}
		return coeff;
	}
	
	public String Jeu(int idCardA, int idCardB) {
		
		Card cardA = getCard(idCardA);
		Card cardB = getCard(idCardB);
		
		String des = "";
		
		int HPa = cardA.getHP();
		int HPb = cardB.getHP();
		
		while (HPa > 0 && HPb > 0) {
			des += Rounds(cardA,cardB,HPa,HPb);
		}
		
		if (HPa == 0 && HPb ==0) {
			des += "End:0";
		}
		
		else if (HPb ==0) {
			des +="End:1";
		}
		else {
			des +="End:2";
		}
		
		return des;
		
	}
	
	public String Rounds(Card cardA, Card cardB, int HPa, int HPb) {
		
		Affinity affA = cardA.getAffinity();
		Affinity affB = cardB.getAffinity();
		
		int coeffA = matchupAffinity(affA,affB);
		int coeffB = matchupAffinity(affB,affA);
		
		int degA = coeffA * cardA.getStrength();
		int degB = coeffB * cardB.getStrength();
		
		int nCoupsA = (int)(Math.random() * 6 + 1);
		int nCoupsB = (int)(Math.random() * 6 + 1);
		
		HPa = HPa - (nCoupsA*degA);
		HPb = HPb - (nCoupsB*degB);
		
		String msg ="HP1:"+HPa+"HP2:"+HPb+"/";
		cardA.setHP(HPa);
		cardB.setHP(HPb);
		
		return msg;
		
	}
	
	public void main(){
		System.out.print(Jeu(17,18));
	}
	
	
}
