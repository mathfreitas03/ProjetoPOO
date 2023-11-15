package exceptions;

public class UserAlreadySignedException extends Exception{
    public UserAlreadySignedException(){

    }

    public UserAlreadySignedException(String msg){
        super(msg);
    }
}
