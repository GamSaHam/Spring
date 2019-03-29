package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.MyDataRepository;


@Controller
public class HelloController8 {
	
	@Autowired
	MyDataRepository repository;
	
	@Autowired
	private MyDataService service;
	
	@RequestMapping(value = "/service/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyData의 예제입니다.");
		List<MyData> list = service.getAll(); //●
		mav.addObject("datalist", list);
		return mav;
	}
	
	@RequestMapping(value = "/service/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("/service/find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyData의 예제입니다.");
		mav.addObject("value","");
		List<MyData> list = service.getAll(); //●
		mav.addObject("datalist", list);
		return mav;
	}
	
	@RequestMapping(value = "/service/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,
			ModelAndView mav) {
		mav.setViewName("/service/find");
		String param = request.getParameter("fstr");
		if (param == ""){
			mav = new ModelAndView("redirect:/service/find");
		} else {
			mav.addObject("title","Find result");
			mav.addObject("msg","「" + param + "」의 검색 결과");
			mav.addObject("value",param);
			List<MyData> list = service.find(param); //●
			mav.addObject("datalist", list);
		}
		return mav;
	}

	@PostConstruct
	public void init(){
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("kim@gilbut.co.kr");
		d1.setMemo("090999999");
		repository.save(d1);
		MyData d2 = new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@flower");
		d2.setMemo("080888888");
		repository.save(d2);
		MyData d3 = new MyData();
		d3.setName("choi");
		d3.setAge(37);
		d3.setMail("choi@happy");
		d3.setMemo("070777777");
		repository.save(d3);

	}

}
