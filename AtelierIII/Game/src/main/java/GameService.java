import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
	
	@Autowired
	RestTemplate restTemplate;	
	
	public String getUserCard() {		//avoir l'énergie
		RestTemplate resttemplate = new RestTemplate();
		return resttemplate.getForObject("http://localhost:8080/user/card", String.class);
	 }
	
	public Card getCard(int id) {		//avoir la carte
		RestTemplate resttemplate = new RestTemplate();
		return resttemplate.getForObject("http://localhost:8080/card/{id}", Card.class, id);
	 }
	
	public int matchupAffinity(String affA, String affB) {
		int coeff = 1;	//coeff d'attaque qui va changer en fonction d'avantage/désavantage d'affinité
		if (affA.compareTo(affB) == 1 || affA.compareTo(affB) == -2) {
			coeff -= 0.25;
		}
		else if (affA.compareTo(affB) == -1 || affA.compareTo(affB) == 2 ) {
			 coeff += 0.25;
		}
		return coeff;
	}
	
	public String Victor(Card card) {
		return "Victoire de : " + card.name + "!";
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
		
		if (HPa == 0) {
			des += Victor(cardB);
		}
		
		else if (HPb ==0) {
			des +=Victor(cardA);
		}
		
		return des;
		
	}
	
	public String Rounds(Card cardA, Card cardB, int HPa, int HPb) {
		
		String affA = cardA.getAffinity();
		String affB = cardB.getAffinity();
		
		int coeffA = matchupAffinity(affA,affB);
		int coeffB = matchupAffinity(affB,affA);
		
		int degA = coeffA * cardA.getStrength();
		int degB = coeffB * cardB.getStrength();
		
		int nCoupsA = (int)(Math.random() * 6 + 1);
		int nCoupsB = (int)(Math.random() * 6 + 1);
		
		HPa = HPa - (nCoupsA*degA);
		HPb = HPb - (nCoupsB*degB);
		
		String msg = cardA.name + "a perdu " + nCoupsB*degB + "points de vie ! " + "\n" + cardB.name + "a perdu " + nCoupsA*degA + "points de vie ! \n ";
		
		cardA.setHP(HPa);
		cardB.setHP(HPb);
		
		return msg;
		
	}
	
	
	 /*public static void main(String[] args) {
		System.out.println(Affinity.FEU.compareTo(Affinity.PLANTE));
		
	} */
	
}
