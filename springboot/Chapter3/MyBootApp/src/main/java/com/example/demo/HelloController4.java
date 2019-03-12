package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController4 {

	
	@RequestMapping(value="/index4")
	public ModelAndView index4(ModelAndView mav) {
		
		mav.setViewName("index4");
		mav.addObject("msg", "current date.");
		DateObject obj = new DateObject(123, "lee", "lee@flower");
		
		mav.addObject("object", obj);
		
		return mav;
	}
	
	@RequestMapping(value="/index5")
	public ModelAndView index5(ModelAndView mav) {
		mav.setViewName("index5");
		mav.addObject("msg", "message 1<hr/>message 2<br/>message 3");
		
		return mav;
	}
	
	@RequestMapping(value="/example6/{id}")
	public ModelAndView example6(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("index6");
		mav.addObject("id", id);
		mav.addObject("check", id % 2 == 0);
		mav.addObject("trueVal", "Even number!");
		mav.addObject("falseVal", "Odd nubmer...");
		
		return mav;
		
	}
	
	@RequestMapping(value="/example7/{id}")
	public ModelAndView example7(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("index7");
		mav.addObject("id", id);
		mav.addObject("check", id >= 0);
		mav.addObject("trueVal", "Positive");
		mav.addObject("falseVal", "Negative");
		
		return mav;	
	}
	
	@RequestMapping(value="/example8/{month}")
	public ModelAndView example8(@PathVariable int month, ModelAndView mav) {
		
		mav.setViewName("index8");
		int m = Math.abs(month) % 12;
		m = m == 0 ? 12 : m;
		mav.addObject("month", m);
		mav.addObject("check", Math.floor(m/3));
		
		return mav;
	}
	
	@RequestMapping(value="/example9")
	public ModelAndView example10(ModelAndView mav) {
		
		mav.setViewName("index9");
		
		ArrayList<String[] > data = new ArrayList<String[]>();
		
		data.add(new String[] {"park", "park@park.com","000-0000-0000"});
		data.add(new String[] {"lee", "lee@lee.com","111-1111-1111"});
		mav.addObject("data", data);
		
		return mav;
		
	}
		
	
}
