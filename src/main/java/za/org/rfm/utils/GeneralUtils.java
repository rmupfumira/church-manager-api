package za.org.rfm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtils {

    public static Date getDateFromString(String format,String date){
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getDateAsString(String format,Date dateObj){
       return new SimpleDateFormat(format).format(dateObj);
    }

    public static String changeDateFormat(String from,String to,String date){
        try {
            Date theDate = new SimpleDateFormat(from).parse(date);
            return new SimpleDateFormat(to).format(theDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }
}
