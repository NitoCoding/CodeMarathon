package mdbudget.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateGenerator {
    public static String getDateString(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", new Locale("id"));
        String formattedDate = formatter.format(date);
        return formattedDate;
    } 
}
