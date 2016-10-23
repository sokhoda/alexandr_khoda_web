package session14.service;

import java.util.List;

/**
 * Created by s_okhoda on 14.02.2016.
 */
public interface GeneralService {
    List getEmployees(Long id);
    boolean loginCheck(String firstName, String lastName);
}
