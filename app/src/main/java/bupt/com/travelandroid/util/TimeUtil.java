package bupt.com.travelandroid.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String UnixToDate(Long unix){
        String date = new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date(unix));
        return date;
    }
}