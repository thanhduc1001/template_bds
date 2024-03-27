package cgt.functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.ocpsoft.prettytime.PrettyTime;

/**
 *
 * @author Ho Sy Minh
 */
public class Function_time {

    public static void main(String[] args) {

        String thungay = getThuNgay("");
        System.out.println("Thứ:" + thungay + "");

//        // get today and clear time of day
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
//        cal.clear(Calendar.MINUTE);
//        cal.clear(Calendar.SECOND);
//        cal.clear(Calendar.MILLISECOND);
//
//// get start of this week in milliseconds
//        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
//        System.out.println("Start of this week:       " + cal.getTime());
//        System.out.println("... in milliseconds:      " + cal.getTimeInMillis());
//
//// start of the next week
//        cal.add(Calendar.WEEK_OF_YEAR, 1);
//        System.out.println("Start of the next week:   " + cal.getTime());
//        System.out.println("... in milliseconds:      " + cal.getTimeInMillis());
//        String myDate = "2019/12/29 17:23:52";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {
//            Date date = sdf.parse(myDate);
//            long millis = date.getTime();
//            String time = getTimeAgo_test(millis);
//
//            System.out.println("vnpt.media.Function_time.main(========time:" + time + "=============)");
//
//        } catch (Exception e) {
//        }
//
//        PrettyTime p = new PrettyTime();
//        System.out.println(p.format(new Date()));
//        //prints: “moments from now”
//
//        System.out.println(p.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10)));
//        //prints: “10 minutes from now”
//
//        PrettyTime p1 = new PrettyTime(new Locale("DEFAULT"));
//        String ss = p1.format(new Date());
//        System.out.println(ss);
//        System.out.println(p1.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10)));
//
//        // strsate = p1.format(new Date(strsate));
//        System.out.println("vnpt.media.Function_time.getTimeAgo(1111)");
//        System.out.println("vnpt.media.Function_time.getTimeAgo(222)");
//        try {
//            TimeUnit.SECONDS.sleep(10);
//            TimeUnit.MINUTES.sleep(1);
//            System.out.println(ss);
//            System.out.println("vnpt.media.Function_time.getTimeAgo(3333)");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("vnpt.media.Function_time.getTimeAgo(4444)");
    }

    public static String getThuNgay(String value) {
        String sValue = "";
        try {
            // get today and clear time of day
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);

            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

            System.out.println("Start of this week:" + cal.getTime());

            String strThuNgay = "" + cal.getTime();

            if (checkIndexOfKey("sun", strThuNgay) >= 0) {
                value = "Chủ nhật";
            }
            sValue = value;
            return sValue;
        } catch (Exception e) {
            System.out.println("eror getThuNgay");
            return "";
        }

    }

    public static int checkIndexOfKey(String key, String content) {
        try {
            key = key.toLowerCase();
            content = content.toLowerCase();
            int dem = content.indexOf(key);
            return dem;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("checkIndexOf(): error: " + e.getMessage() + " ===");
        }
        return -2;
    }

    public String getTimeAgo(String strDate, String strDateFormat) {
        String str = "";
        String myDate = strDate;
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        try {
            Date date = sdf.parse(myDate);
            long millis = date.getTime();
            PrettyTime p1 = new PrettyTime(new Locale("DEFAULT"));
            str = p1.format(new Date(millis));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return StatusTimeAgo(str);
    }

    public String StatusTimeAgo(String value) {
        String sValue = "";
        try {
            value = value.replaceAll("moments ago", "vừa xong");
            value = value.replaceAll("minutes ago", "phút trước");
            value = value.replaceAll("minutes from now", "phút trước");
            value = value.replaceAll("hours from now", "giờ trước");
            value = value.replaceAll("hours ago", "giờ trước");
            value = value.replaceAll("hour ago", "giờ trước");
            value = value.replaceAll("days ago", "ngày trước");
            value = value.replaceAll("day ago", "ngày trước");
            value = value.replaceAll("weeks ago", "tuần trước");
            value = value.replaceAll("week ago", "tuần trước");
            sValue = value;
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusTimeAgo");
            return "";
        }

    }

    public static String getTimeAgo_test(long strDate) {

        String str = "";
        try {
            PrettyTime p1 = new PrettyTime(new Locale("DEFAULT"));
            str = p1.format(new Date(strDate));
        } catch (Exception e) {
        }

        return str;
    }

    public String getLastDay(int num, String Format) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Format);
        String dates = "";
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -num);
            dates = formatter.format(cal.getTime());
            return dates;
        } catch (Exception e) {
            System.out.println("eror getLastDay");
            return "";
        }
    }

    public String getTimeID() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        String dates = "";
        try {
            dates = formatter.format(date) + randInt(1000, 9999);
            return dates;
        } catch (Exception e) {
            System.out.println("eror getTimeID");
            return "";
        }
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public String getTimeFormat(String strformat) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(strformat);
        String dates = "";
        try {
            dates = formatter.format(date);
            return dates;
        } catch (Exception e) {
            System.out.println("eror getTimeFormat");
            return "";
        }
    }

    public String FormatDateTime(String strDateTime, String strformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strformat);
        String dates = "";
        try {
            Date date1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(strDateTime);
            dates = formatter.format(date1);
            return dates;
        } catch (Exception e) {
            System.out.println("eror FormatDateTime");
            return "";
        }
    }

    public String FormatDate(String strDateTime, String strformat) {
        SimpleDateFormat formatter = new SimpleDateFormat(strformat);
        String dates = "";
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDateTime);
            dates = formatter.format(date1);
            return dates;
        } catch (Exception e) {
            System.out.println("eror FormatDate");
            return "";
        }
    }

}
