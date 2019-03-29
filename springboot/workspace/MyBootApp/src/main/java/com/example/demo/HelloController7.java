package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.MyDataRepository;

@Controller
public class HelloController7 {
	
	@Autowired
	MyDataRepository repository;
	
	
	@PersistenceContext
	EntityManager entityManager;
	
	MyDataDaoImpl dao;
	
	/*
	@PostConstruct
	public void init() {
		
		dao = new MyDataDaoImpl(entityManager);
		
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
	*/
	
	@RequestMapping(value = "/example16", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("index16");
		mav.addObject("msg", "MyData의 예제입니다");
		
		Iterable<MyData> list = dao.getAll();
	
		mav.addObject("datalist", list);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제입니다");
		mav.addObject("value", "");
		
		Iterable<MyData> list = dao.getAll();
		
		mav.addObject("dataList", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
		mav.setViewName("find");
		
		String param = request.getParameter("fstr");
		
		if(param == "") {
			mav = new ModelAndView("redirect:/find");
		}else {
			mav.addObject("title", "Find result");
			mav.addObject("msg", "\""+param+"\" 의 검색결과");
			
			mav.addObject("value", param);
			
			List<MyData> list = dao.find(param);
			mav.addObject("datalist", list);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/example17", method = RequestMethod.GET)
	public ModelAndView example17(ModelAndView mav) {
		mav.setViewName("/index16");
		
		
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제입니다");
		
		Iterable<MyData> list = repository.findAllOrderByName();
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/example18", method = RequestMethod.GET)
	public ModelAndView example18(ModelAndView mav) {
		mav.setViewName("/index16");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제입니다");
		
		Iterable<MyData> list = dao.findByAge(10, 40);
		
		mav.addObject("datalist", list);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/example19", method = RequestMethod.GET)
	public ModelAndView example19(ModelAndView mav) {
		mav.setViewName("/index16");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 예제입니다");
		
		Iterable<MyData> list = repository.findByAge(10, 40);
		
		mav.addObject("datalist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/example20", method = RequestMethod.GET)
	public ModelAndView example20(ModelAndView mav) {
		mav.setViewName("/index16");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData 예제입니다");
		
		Iterable<MyData> list = dao.getAll();
		
		mav.addObject("datalist", list);
		
		return mav;
	}

	
	
	
	
	
	
}
