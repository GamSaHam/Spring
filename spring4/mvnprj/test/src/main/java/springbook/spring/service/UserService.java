package springbook.spring.service;

import org.springframework.stereotype.Component;
import springbook.spring.dao.User;

import java.util.List;

@Component
public class UserService {

    UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.add(user);
    }

    public List<User> getUsers(){
        return userRepository.users;
    }

    public void printAllUser() {

        userRepository.print();

    }

    public User getUserById(String id) throws UserNotFindException {

        return  userRepository.get(id);

    }
}
