package Scrum.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class StringDataException extends  Exception{
    public StringDataException(String message) {
        super(message);
    }

    public String getMessage(){
        return "StringDataException: " + super.getMessage();
    }

}
