package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repositories.MyDataRepository;

public class MyDataBean {
	
	@Autowired
	MyDataRepository repository;
	
	public String getTableTagById(Long id) {
		Optional<MyData> data = repository.findById((long)id);
		
		MyData myData = data.get();
		
		String result =   "<tr><td>" + myData.getName()
						+ "</td><td>" + myData.getMail()
						+ "</td><td>" + myData.getAge()
						+ "</td><td>" + myData.getMemo()
						+ "</td></tr>";
		return result;
		
	}
	
	
	
	
	
}




