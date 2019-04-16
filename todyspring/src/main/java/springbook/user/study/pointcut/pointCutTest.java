package springbook.user.study.pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import springbook.user.study.proxy.Hello;
import springbook.user.study.proxy.HelloTarget;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class pointCutTest {

    @Test
    public void classNamePointcutAdvisor(){

        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut(){

            public ClassFilter getClassFiter(){
                return new ClassFilter(){
                    public boolean matches(Class<?> clazz){
                        return clazz.getSimpleName().startsWith("HelloT");
                    }
                };
            }

        };

        classMethodPointcut.setMappedName("sayH*");

        checkAdviced(new HelloTarget(), classMethodPointcut, true);



    }

    private void checkAdviced(HelloTarget target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello)pfBean.getObject();

        if(adviced){
            assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
            assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
            assertThat(proxiedHello.sayThankYou("Toby"), is("Thank you Toby"));
        }else{
            assertThat(proxiedHello.sayHello("Toby"), is("Hello Toby"));
            assertThat(proxiedHello.sayHi("Toby"), is("Hi Toby"));
            assertThat(proxiedHello.sayThankYou("Toby"), is("Thank you Toby"));
        }

    }

    static class UppercaseAdvice implements MethodInterceptor {

        public Object invoke(MethodInvocation invocation) throws Throwable {
            String ret = (String)invocation.proceed();
            return ret.toUpperCase();
        }

    }
}
