package exceptions;

public class EmailToYourselfException extends Exception {
    public EmailToYourselfException(){

    }

    public EmailToYourselfException(String msg){
        super(msg);
    }
}
