import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

@Service
public class GameService {
	
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
	
	public String Round(Card cardA, Card cardB) {
		
		int HPa = cardA.getHP();
		int HPb = cardB.getHP();
		
		while (HPa != 0 || HPb != 0) {
		
		String affA = cardA.getAffinity();
		String affB = cardB.getAffinity();
		int coeffA = matchupAffinity(affA,affB);
		int coeffB = matchupAffinity(affB,affA);
		
		int degA = coeffA * cardA.getStrength();
		int degB = coeffB * cardB.getStrength();
		
		HPa = HPa - degA;
		HPb = HPb - degB;
		
		cardA.setHP(HPa);
		cardB.setHP(HPb);
		
		if (HPa >= 0 && HPb >= 0) {
			
			System.out.println("HPa: " + HPa);
			System.out.println("HPb: " + HPb);
			
		}
		
		else if (HPa == 0) {
			return "Victoire de " + cardB.name + "!";
		}
		
		else {
			return "Victoire de " + cardA.name + "!";
		}
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(Affinity.FEU.compareTo(Affinity.PLANTE));
		
	}
	
	

}
