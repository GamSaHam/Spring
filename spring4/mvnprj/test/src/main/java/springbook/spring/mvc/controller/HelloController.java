package springbook.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springbook.spring.dao.User;
import springbook.spring.service.UserNotFindException;
import springbook.spring.service.UserRepository;
import springbook.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
/*@RequestMapping("/hello")*/
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello.do")
    public String hello(Model model){
        model.addAttribute("greeting", "안녕하세요");

        return "hello";
    }

    @RequestMapping(value = "/hello2.do", method = RequestMethod.GET)
    public String hello2(Model model){
        model.addAttribute("greeting", "Hello World");

        return "hello";
    }

    @RequestMapping("/list.do")
    public String list(Model model){
        List<User> users = userService.getUsers();

        model.addAttribute("users", users);

        return "list";
    }

    @RequestMapping("/list2.do")
    public ModelAndView list2(){
        List<User> users = userService.getUsers();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("users", users);

        return mav;
    }


    public String findUser(String id, Model model){

        try {
            User user = userService.getUserById(id);
            List<User> users = Arrays.asList(user);
            model.addAttribute("users", users);

            return "list";

        }catch (UserNotFindException e) {
            model.addAttribute("message", e.getMessage());

            return "error";

        }
    }



    @RequestMapping("/{id}/list3.do")
    public String list3(@PathVariable("id") String id, Model model){

        return findUser(id, model);

    }

    @RequestMapping("/list4.do")
    public String list4(HttpServletRequest request, Model model){
        String id = request.getParameter("id");

        if(id == null) return "redirect:/hello.do";

        return findUser(id, model);

    }

    @RequestMapping("/list5.do")
    public String list5(@RequestParam(value = "id", required = false) String id, Model model){

        return findUser(id, model);

    }

    @RequestMapping("/list6.do")
    public String list6(@RequestParam(value = "id", defaultValue = "") String id, Model model){

        return findUser(id, model);

    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginGet(){
        return "login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "id", defaultValue = "") String id, Model model){

        return findUser(id, model);

    }

    @RequestMapping(value = "/loginUser.do", method = RequestMethod.GET)
    public String loginUserGet(){
        return "loginUser";
    }

    @RequestMapping(value = "/loginUser.do", method = RequestMethod.POST)
    public String loginUserPost(User user, Model model){
        // 파라미터에 user를 글자수를 줄이고 싶을때
        // ModelAttribute("user") 를 사용하면된다.

        try {
            User foundUser = userService.getUserById(user.getId());

            model.addAttribute("user", foundUser);

            return "list2";
        } catch (UserNotFindException e) {
            model.addAttribute("message", e.getMessage());

            return "error";

        }


    }


    @ModelAttribute("recGetUsers")
    public List<User> getUsers(){

        return userService.getUsers();
    }

    @RequestMapping("/list7.do")
    public String modelAttributeTest(@ModelAttribute("recGetUsers") List<User> users, Model model){

        model.addAttribute("users", users);

        return "list";
    }

    @RequestMapping("/list8.do")
    public String list8(@RequestHeader(value = "Accept") String acceptType, @CookieValue(value = "auth", required = false) Integer autuValue){

        System.out.println(acceptType);
        System.out.println(autuValue);

        return "list";
    }

    @RequestMapping("/list9.do")
    public String list9(){

        return "redirect:/hello.do";
    }












}
