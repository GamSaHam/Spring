package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/*
 *  @RestController 를 보통 사용하지 않고 .html을 사용하는 @Controller를 사용한다.
 *  
 *  @Controller를 사용할려면 메이븐에 타임리프를 추가해주어야 한다.
 *  pom.xml dependency 수정
 * 
 * 
 */


/*
 * 사용 되어진 어노테이션 종류
 * 
 * @Controller - 컨트롤러 선언
 * @RestMapping - 주소 매핑
 * @PathVariable - 주소를 통해 전해진 매개변수 값
 * @Model - Model 객체를 통해서 데이터를 html쪽에 보냄 .addAttribute (Model은 뜻은 데이터이다.)
 * @ModelAndView - Model객채와 유사한 ModelAndView 어노테이션이다. addObject, setViewName등이 있다.
 * @RequestParam - view쪽에서 name을 찾아서 String 값을 받아오고 있다.
 * 
 */

@Controller
public class HelloController{
	
	
	
/*	  @RequestMapping("/{num}") 
	  public String index(@PathVariable int num, Model model) {
	  
	  int res = 0; for(int i=1; i<=num; i++) { res += i; }
	  
	  model.addAttribute("msg", "total: "+ res);
	  
	  return "index";
	  
	  }*/
	 
	
	/*
	 * Model을 ModelAndView로 바꿀시 setAttribute -> addObject 를 사용하고 setViewName으로 html
	 * 파일 이름을 지정 해 주어야한다.
	 */
/*	@RequestMapping("/{num}")
	public ModelAndView index (@PathVariable int num, ModelAndView mav) {
		int tot = 0;
		for(int i=1;i<=num;i++) {
			tot += i;
		}
		
		mav.addObject("msg", "total: "+ tot);
		mav.setViewName("index");
		
		return mav;
	}*/
	
	
	/*
	 * @RequestMapping 어노테이션에 method받는 방식을 설정으로 하여 GET으로 받을경우 POST로 받을경우를 설정하고 있다.
	 * @RequestParam을 통한
	 */
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "이름을 적어서 전송해주세요.");
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("text1")String str, ModelAndView mav) {
		mav.addObject("msg", "안녕하세요. "+ str + "님!");
		mav.addObject("value", str);
		mav.setViewName("index");
		return mav;
		
	}
	
	
	
	
	
	
	
}


