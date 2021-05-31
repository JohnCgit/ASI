package com.WebAppCard.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
	
	RestTemplate restTemplate;	
	
	
	public Card getCard(int id) {	//avoir la carte
		RestTemplate resttemplate = new RestTemplate();
		return resttemplate.getForObject("http://127.0.0.1:8040/"+id, Card.class);
	 }
		
	public int matchupAffinity(Affinity affA, Affinity affB) { 	//méthode qui permet de déterminer l'affinité de la carte A par rapport à la carte B
		
		int coeff = 2;	//coeff d'attaque qui va changer en fonction d'avantage/désavantage d'affinité
		if (affA.compareTo(affB) == 1 || affA.compareTo(affB) == -2) {
			coeff -= 1;
		}
		else if (affA.compareTo(affB) == -1 || affA.compareTo(affB) == 2 ) {
			 coeff += 1;
		}
		return coeff;
	}
	
	public String Jeu(int idCardA, int idCardB) {	//Met en place le début de partie (récupération des cartes et des infos nécessaires, comparaison des affinités,
							//lancement des rounds et descriptions, annonce d'un gagnant potentiel
		
		Card cardA = getCard(idCardA);
		Card cardB = getCard(idCardB);
		
		String des = "";
		
		System.out.print("\n"+cardA);
		Affinity affA = cardA.getAffinity();
		Affinity affB = cardB.getAffinity();
		int strA = cardA.getStrength();
		int strB = cardA.getStrength();
		int coeffA = matchupAffinity(affA,affB);
		int coeffB = matchupAffinity(affB,affA);
		int degA = coeffA * strA;
		
		int degB = coeffB * strB;
		
		int HPa = cardA.getHP();
		int HPb = cardB.getHP();
		while (HPa > 0 && HPb > 0) {
			des += Rounds(cardA, cardB, degA,degB,HPa,HPb);
			HPa = cardA.getHP();
			HPb = cardB.getHP();
		}
		
		if (HPa <= 0 && HPb <=0) {
			des += "End:0";
		}
		
		else if (HPb <=0) {
			des +="End:1";
		}
		else {
			des +="End:2";
		}
		
		return des;
		
	}
	
	private String Rounds(Card cardA,Card cardB, int degA, int degB, int HPa, int HPb) {	//Met en place un round (nombre de coups aléatoires par carte par tour, 
												//baisse des HP, string à analyser par le front-end
		
		int nCoupsA = (int)(Math.random() * 3 + 1);
		int nCoupsB = (int)(Math.random() * 3 + 1);
		HPa = HPa - (nCoupsA*degA);
		HPb = HPb - (nCoupsB*degB);
		
		String msg ="HP1:"+HPa+"HP2:"+HPb+"/";
		cardA.setHP(HPa);
		cardB.setHP(HPb);
		
		return msg;
		
	}
	
}
