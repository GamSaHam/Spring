package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController3 {

	
	@RequestMapping(value = "/index3")
	public String index3() {
		
		return "index3";
		
	}
	
	
	
	
}
