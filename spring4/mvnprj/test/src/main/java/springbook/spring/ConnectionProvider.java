package springbook.spring;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConnectionProvider implements EnvironmentAware {

    Environment env;

    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    public Environment getEnv(){
        return env;
    }

}
