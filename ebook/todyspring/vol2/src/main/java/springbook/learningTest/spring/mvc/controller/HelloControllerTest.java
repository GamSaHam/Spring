package springbook.learningTest.spring.mvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import springbook.learningTest.spring.mvc.ConfigurableDispatcherServlet;

import javax.servlet.ServletException;
import javax.swing.*;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class HelloControllerTest {



    @Test
    public void servletTest() throws ServletException, IOException {

        ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();

        servlet.setRelativeLocations(getClass(), "../../../../../spring-servlet.xml");


        servlet.setClasses(HelloSpring.class);
        servlet.init(new MockServletConfig("spring"));


        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");

        req.addParameter("name", "Spring");

        MockHttpServletResponse res = new MockHttpServletResponse();

        /*SimpleGetServlet servlet = new SimpleGetServlet();*/
        servlet.service(req, res);

        ModelAndView mav = servlet.getModelAndView();
        assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
        assertThat((String)mav.getModel().get("message"), is("Hello Spring"));


    }


}



