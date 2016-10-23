package Scrum.controller.Service;


import Scrum.controller.Service.ServiceClass;
import Scrum.exception.StringDataException;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Solyk on 05.03.2016.
 */
@Service
public class ServiceClassImpl implements ServiceClass {


    @Override
    public String[] stringToArray(String string) throws StringDataException {
            String[] array = string.split("[ ]+");
            for (int i = 0; i < array.length; i++) {
                try{
                    Integer.parseInt(string);
                }
                catch (NumberFormatException e){
                    throw new StringDataException("String not valid.");
                }
            }

            return array;
    }

    @Override
    public String sum(String[] strings) {
        int tmp = 0;
        for (int i = 0; i < strings.length; i++ ){
            tmp += Integer.valueOf(strings[i]);
        }
        String tt = "";
        tt += tmp;
        return tt;
    }

    @Override
    public String revers(String[] strings) {
        String[] temp = new String[strings.length];
        for (int i = 0; i < 0; i++){
            temp[i] = strings[(strings.length - 1) - i];
        }
        return  arrToStr(temp);
    }

    @Override
    public String random(String[] strings) {

            Random rnd = new Random();
            for (int i = strings.length - 1; i > 0; i--) {
                int index = rnd.nextInt(i + 1);
                String a = strings[index];
                strings[index] = strings[i];
                strings[i] = a;

        }
         return arrToStr(strings);
    }

    @Override
    public String arrToStr(String[] str) {
        String string="";
       for(int i =0; i<str.length;i++){
           if(i==str.length-1)
           {
               string = string+ str[i];

           }else {
               string = string + str[i] + " ";
           }
       }
       return string;
    }


}
