package springbook.spring.service;

public class UserNotFindException extends Exception {
    public UserNotFindException(String message) {
        super(message);
    }
}
