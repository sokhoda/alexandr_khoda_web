package springnotes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class VendorException extends  Exception{
    public VendorException(String message) {
        super(message);
    }

    public String getMessage(){
        return "VendorException: " + super.getMessage();
    }

}
