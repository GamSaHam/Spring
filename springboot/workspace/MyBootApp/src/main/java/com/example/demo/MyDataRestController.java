package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.component.MySampleBean;

@RestController
public class MyDataRestController {

	@Autowired
	private MyDataService service;
	
	@Autowired
	private MySampleBean bean;
	
	
	@RequestMapping("/rest")
	public List<MyData> restAll(){
		return service.getAll();
	}
	
	@RequestMapping("/rest/{num}")
	public MyData restBy(@PathVariable int num) {
		return service.get(num);	
	}
	
	@RequestMapping("/count")
	public int count() {
		return bean.count();
	}
	
}
