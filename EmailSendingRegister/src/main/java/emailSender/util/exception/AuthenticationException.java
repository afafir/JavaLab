package emailSender.util.exception;

public class AuthenticationException extends IllegalArgumentException{
    public AuthenticationException (String message){
        super(message);
    }
}
