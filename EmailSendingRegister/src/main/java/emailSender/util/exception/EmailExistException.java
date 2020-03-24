package emailSender.util.exception;


public class EmailExistException extends IllegalArgumentException {

    public EmailExistException(String msg) {
        super(msg);
    }

}
