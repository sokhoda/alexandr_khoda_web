package hw8.taxi.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class ClientException extends  Exception{
    public ClientException(String message) {
        super(message);
    }

    public String getMessage(){
        return "StoreException: " + super.getMessage();
    }

}
