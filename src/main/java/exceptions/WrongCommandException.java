package exceptions;

public class WrongCommandException extends RuntimeException {

    WrongCommandException(){}

    public WrongCommandException(String message){
        super(message);
    }

    public WrongCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
