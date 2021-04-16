package com.sahadev.springbootin100.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	//@RequestMapping(method=RequestMethod.GET,path="/helloworld")
	@GetMapping("/helloworld")
	public String helloworld() {
		return "helloworld";
	}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("sahadev","kunwar","dhangadhi");
	}

}
