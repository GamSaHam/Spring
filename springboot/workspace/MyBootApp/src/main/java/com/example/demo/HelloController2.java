package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * checkbox, radio, select, select 속성이 multiple을 정보를 출력하는 예제 
 * 
 */

/*
 * return "redirect:[URL] " 
 * return "forward:[URL] " 
 * 두개 차이는 redirect 는 주소가 변경된체 이동하고
 * forward는 주소는 바뀌지 않지만 해당 Mapping 된 URL로 이동한다.
 */

@Controller
public class HelloController2 {

	@RequestMapping(value="/index2", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index2");
		mav.addObject("msg", "폼을 전송해주세요.");
		return mav;
	}
	
	@RequestMapping(value="/index2", method=RequestMethod.POST)
	public ModelAndView send( @RequestParam(value="check1", required=false) boolean check1
							 ,@RequestParam(value="radio1", required=false) String radio1
							 ,@RequestParam(value="select1", required=false) String select1
							 ,@RequestParam(value="select2", required=false) String[] select2
							 ,ModelAndView mav) {
		
		String res = "";
		
		try {
			res = "check:" + check1 
				+ " radio:" + radio1
				+ " select:" + select1
				+ "\nselect2";
			
		}catch (NullPointerException e) {}
		
		try {
			res += " "+select2[0];
			for(int i=1; i<select2.length; i++) {
				res +=", " + select2[i];
				
			}
			
		}catch (NullPointerException e) {
			res += "null";
			
		}
		
		mav.addObject("msg", res);
		mav.setViewName("index2");
		return mav;
		
	}
	
	@RequestMapping("/other")
	public String other() {
		return "redirect:/example2";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "forward:/example2";
	}
	
	
}
 