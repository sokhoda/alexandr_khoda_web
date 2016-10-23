package springnotes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class InvalidParamValueException extends  Exception{
    public InvalidParamValueException(String message) {
        super(message);
    }

    public String getMessage(){
        return "InvalidParamValueException: " + super.getMessage();
    }

}
