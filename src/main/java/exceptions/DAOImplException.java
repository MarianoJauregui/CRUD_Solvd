package exceptions;

public class DAOImplException extends RuntimeException {

    DAOImplException(){}

    public DAOImplException(String message){
        super(message);
    }

    public DAOImplException(String message, Throwable cause) {
        super(message, cause);
    }
}
