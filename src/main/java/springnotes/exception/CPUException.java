package springnotes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class CPUException extends  Exception{
    public CPUException(String message) {
        super(message);
    }

    public String getMessage(){
        return "CPUException: " + super.getMessage();
    }

}
