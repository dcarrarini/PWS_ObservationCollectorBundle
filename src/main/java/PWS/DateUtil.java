package PWS;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static String date;

    public static String getSysdate(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
        return date;
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static String getYesterdayDateString(String pattern) {
        SimpleDateFormat simpleDateFormatt = new SimpleDateFormat(pattern);
        date = simpleDateFormatt.format(yesterday());
        return date;
    }

    public static String alignTS(String sDate){
        try{
            Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(sDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = simpleDateFormat.format(DateUtils.addMinutes(date1, 1));
        }catch (Exception e){
            e.printStackTrace();
        }

        return date;
    }
}
