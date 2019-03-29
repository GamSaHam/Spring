package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController5 {

	
	@RequestMapping(value="/example10/{num}")
	public ModelAndView example10(@PathVariable int num,ModelAndView mav) {
		
		mav.setViewName("index10");
		mav.addObject("num", num);
		
		if(num >= 0) {
			mav.addObject("check", "num >= data.size() ? 0 : num");
		}else {
			mav.addObject("check", "num <= date.size() * -1 ? 0 : num*-1");
		}
		
		ArrayList<DataObject> data = new ArrayList<DataObject>();
		
		data.add(new DataObject(0, "park", "park@yamada"));
		data.add(new DataObject(1, "lee", "lee@flower"));
		data.add(new DataObject(2, "choi", "choi@happy"));
		mav.addObject("data", data);
		
		return mav;
	}
	
	@RequestMapping(value="/example11")
	public ModelAndView example11(ModelAndView mav) {
		
		mav.setViewName("index11");
		
		ArrayList<DataObject> data = new ArrayList<DataObject>();
		
		data.add(new DataObject(0, "park", "park@yamada"));
		data.add(new DataObject(1, "lee", "lee@flower"));
		data.add(new DataObject(2, "choi", "choi@happy"));
		mav.addObject("data", data);
		
		
		
		return mav;
		
	}
	
	@RequestMapping("/example12/{tax}")
	public ModelAndView example12(@PathVariable int tax, ModelAndView mav) {
		
		mav.setViewName("index12");
		mav.addObject("tax",tax);
		
		return mav;
	}

	@RequestMapping("/example13")
	public ModelAndView example13(ModelAndView mav) {
		
		mav.setViewName("index13");
		
		
		return mav;
	}
	
	
}






