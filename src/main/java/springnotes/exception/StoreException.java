package springnotes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class StoreException extends  Exception{
    public StoreException(String message) {
        super(message);
    }

    public String getMessage(){
        return "StoreException: " + super.getMessage();
    }

}
