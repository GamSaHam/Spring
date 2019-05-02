package springbook.spring.service;

import springbook.spring.dao.User;

import java.util.List;

public class UserRepository {

    List<User> users;

    public void setUsers(List users){
        this.users = users;
    }

    public void add(User user) {
        if(users != null){
            users.add(user);
        }
    }

    public void print() {

        for(int i=0; i<users.size(); i++){
            System.out.println((i+1)+":"+users.get(i).getId());
        }

    }
}
