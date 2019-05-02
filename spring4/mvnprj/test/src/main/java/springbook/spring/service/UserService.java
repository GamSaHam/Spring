package springbook.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springbook.spring.dao.User;

@Component
public class UserService {

    UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.add(user);
    }


    public void printAllUser() {

        userRepository.print();

    }
}
