package PWS;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    String date = simpleDateFormat.format(new Date());

    public static String getSysdate(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
