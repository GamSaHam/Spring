import org.junit.Test;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class Main {

    @Test
    public void helloBeanTest() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);


        Hello hello = ac.getBean("hello1", Hello.class);

        assertThat(hello, is(notNullValue()));


    }
}
