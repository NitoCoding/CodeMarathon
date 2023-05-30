package mdbudget.utils;

import java.util.Date;

public class dateGenerator {
    public static String getDateString(){
        Date date = new Date();
        String time = Long.toString(date.getTime());
        return time;
    } 
}
