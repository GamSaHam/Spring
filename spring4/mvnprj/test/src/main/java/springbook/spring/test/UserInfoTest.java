package springbook.spring.test;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.spring.dao.User;
import springbook.spring.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void userConfigTest() {

        GenericApplicationContext ctx = new GenericXmlApplicationContext("/applicationContext.xml");

        UserService userService = ctx.getBean("userService", UserService.class);

        assertThat(userService, is(notNullValue()));

        User user = new User("MiKeeGangSoo");

        userService.addUser(user);

        userService.printAllUser();

        ctx.close();

    }








}
