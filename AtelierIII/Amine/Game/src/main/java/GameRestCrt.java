import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameRestCrt {
	
	
	@Autowired
	private GameService gs;
	
	public String Winner() {
		return "test";
	}

}
