package hw7.notes.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class NotebookException extends  Exception{
    public NotebookException(String message) {
        super(message);
    }

    public String getMessage(){
        return "NotebookException: " + super.getMessage();
    }

}
