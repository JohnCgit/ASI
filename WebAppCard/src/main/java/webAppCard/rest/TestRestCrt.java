package webAppCard.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestCrt {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello Hero !!!";
	}

}