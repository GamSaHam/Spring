package springbook.spring.service;

import springbook.spring.dao.User;

import java.util.List;

public class UserRepository {

    List<User> users;

    public UserRepository(){

    }

    public UserRepository(List<User> users){
        this.users = users;
    }

    public void setUsers(List users){
        this.users = users;
    }

    public void add(User user) {
        if(users != null){
            users.add(user);
        }
    }

    public User get(String id) throws UserNotFindException{

        if(id == null){
            throw new UserNotFindException("id 정보가 없습니다.");
        }

        for(int i=0; i<users.size(); i++){

            if(users.get(i).getId().equals(id)){
                return users.get(i);
            }

        }

        throw new UserNotFindException(id+"정보가 없습니다.");


    }

    public void print() {

        for(int i=0; i<users.size(); i++){
            System.out.println((i+1)+":"+users.get(i).getId());
        }

    }
}
