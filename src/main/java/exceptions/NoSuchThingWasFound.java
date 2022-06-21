package exceptions;

public class NoSuchThingWasFound extends RuntimeException {

    NoSuchThingWasFound(){}

    public NoSuchThingWasFound(String message){
        super(message);
    }

    public NoSuchThingWasFound(String message, Throwable cause) {
        super(message, cause);
    }
}
