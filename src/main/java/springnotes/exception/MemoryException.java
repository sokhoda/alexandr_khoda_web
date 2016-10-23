package springnotes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class MemoryException extends  Exception{
    public MemoryException(String message) {
        super(message);
    }

    public String getMessage(){
        return "MemoryException: " + super.getMessage();
    }

}
