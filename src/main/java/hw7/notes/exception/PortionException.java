package hw7.notes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class PortionException extends  Exception{
    public PortionException(String message) {
        super(message);
    }

    public String getMessage(){
        return "PortionException: " + super.getMessage();
    }

}
