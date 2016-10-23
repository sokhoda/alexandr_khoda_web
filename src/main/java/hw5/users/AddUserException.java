package hw5.users;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class AddUserException extends  Exception{
    public AddUserException(String message) {
        super(message);
    }

    public String getMessage(){
        return "AddUserException: " + super.getMessage();
    }

}
