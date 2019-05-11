package springbook.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springbook.spring.dao.User;
import springbook.spring.service.UserRegistValidator;
import springbook.spring.service.UserService;

@Controller
public class RegistController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/regist.do", method = RequestMethod.GET)
    public String regist(){

        return "regist";

    }

    @RequestMapping(value = "/regist.do", method = RequestMethod.POST)
    public String registPost(@ModelAttribute("user")User user, BindingResult bindingResult){


        new UserRegistValidator().validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println("Has Erorr");
            return "redirect:/regist.do";
        }

        userService.addUser(user);

        return "redirect:/list.do";

    }



}
