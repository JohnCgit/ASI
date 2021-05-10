package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorRestCrt {
	
	@RequestMapping("/login")
	public String sayHello() {
		return "Hello Hero !!!";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/login/{user}/{pwd}")
	public String isUser(@PathVariable String id1, @PathVariable String id2) {
		String name=user;
		String password=pwd;
		return "Composed Message: msg1:"+msg1+"msg2:"+msg2;
	}


}
