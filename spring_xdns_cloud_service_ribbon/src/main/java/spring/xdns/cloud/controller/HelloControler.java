package spring.xdns.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.xdns.cloud.service.HelloService;

@RestController
public class HelloControler {
	
	@Autowired
	HelloService helloService;
	
	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name){
		return helloService.hiService(name);
	}
	
	
}
