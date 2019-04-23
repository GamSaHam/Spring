package springbook.user.service;

public class SqlRetrievalFailureException extends RuntimeException {

    public SqlRetrievalFailureException(Exception e){
        super(e);
    }

    public SqlRetrievalFailureException(String message) {
        super(message);
    }
}
