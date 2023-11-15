package exceptions;

public class InvalidNickException extends Exception{
    
    public InvalidNickException(){

    }

    public InvalidNickException(String msg){
        super(msg);
    }
}
