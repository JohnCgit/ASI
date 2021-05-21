import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
	
	@Autowired
	RestTemplate restTemplate;
		
	public boolean verifUser(String username, String password) {
		return uService.verifUser(username, password);
	}
	
	public String getUserCard() {		//avoir l'énergie
		RestTemplate resttemplate = new RestTemplate();
		return resttemplate.getForObject("http://localhost:8080/user/card", String.class);
	 }

} 