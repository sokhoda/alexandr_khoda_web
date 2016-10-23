package Scrum.controller.Service;

import Scrum.exception.StringDataException;

/**
 * Created by Solyk on 05.03.2016.
 */
public interface ServiceClass {
    String[] stringToArray(String string)throws StringDataException;
    String sum(String[] strings);
    String revers(String[] strings);
    String random(String[] strings);
    String arrToStr(String[] str);

}
