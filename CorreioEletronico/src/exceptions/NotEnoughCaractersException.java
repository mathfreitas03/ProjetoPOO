package exceptions;

public class NotEnoughCaractersException extends Exception{
    public NotEnoughCaractersException(){

    }

    public NotEnoughCaractersException(String msg){
        super(msg);
    }
}
