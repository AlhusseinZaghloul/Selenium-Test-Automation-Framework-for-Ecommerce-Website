package utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TImeStampUtils {
    /**
     * This method returns the current timestamp in the format "yyyy-MM-dd_HH-mm-ss"
     *
     * @return String representation of the current timestamp
     */
    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return dateFormat.format(date);
    }
}
