package cgt.functions;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

public class DateProc {

    public DateProc() {
    }

    public static java.sql.Timestamp createTimestamp() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        return new java.sql.Timestamp((calendar.getTime()).getTime());
    }

    public static Timestamp getNext7Days(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay + 7);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static java.sql.Timestamp createDateTimestamp(java.util.Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        return new java.sql.Timestamp((calendar.getTime()).getTime());
    }

    public static java.sql.Timestamp String2Timestamp(String strInputDate) {
        String strDate = strInputDate;
        int i, nYear, nMonth, nDay;
        String strSub = null;
        i = strDate.indexOf("/");
        if (i < 0) {
            return createTimestamp();
        }
        strSub = strDate.substring(0, i);
        nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if (i < 0) {
            return createTimestamp();
        }
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub.trim())).intValue() - 1; // Month begin from 0 value
        strDate = strDate.substring(i + 1);
        if (strDate.length() < 4) {
            if (strDate.substring(0, 1).equals("9")) {
                strDate = "19" + strDate.trim();
            } else {
                strDate = "20" + strDate.trim();
            }
        }
        nYear = (new Integer(strDate)).intValue();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new java.sql.Timestamp((calendar.getTime()).getTime());
    }

    public static String getDateTimeString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }
    /*return date with format: dd/mm/yyyy */

    public static String getDateString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2DDMMYYYY(ts);
    }

    public static String getTimeString(java.sql.Timestamp ts) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        return calendar.get(calendar.HOUR_OF_DAY) + ":" + calendar.get(calendar.MINUTE) + ":" + calendar.get(calendar.SECOND);
    }
    /*return date with format: dd/mm/yyyy */

    public static String Timestamp2DDMMYYYY(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            if (calendar.get(calendar.DAY_OF_MONTH) < 10) {
                strTemp = "0" + strTemp;
            }
            if (calendar.get(calendar.MONTH) + 1 < 10) {
                return strTemp + "/0" + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR);
            } else {
                return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR);
            }
        }

    }

    /*return date with format: mm/dd/yyyy */
    public static String Timestamp2MMDDYYYY(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            if (calendar.get(calendar.DAY_OF_MONTH) < 10) {
                strTemp = "0" + strTemp;
            }
            if (calendar.get(calendar.MONTH) + 1 < 10) {
                return "0" + (calendar.get(calendar.MONTH) + 1) + "/" + strTemp + "/" + calendar.get(calendar.YEAR);
            } else {
                return (calendar.get(calendar.MONTH) + 1) + "/" + strTemp + "/" + +calendar.get(calendar.YEAR);
            }
        }
    }

    /*return date with format: dd/mm/yy */
    public static String Timestamp2DDMMYY(java.sql.Timestamp ts) {
        int endYear;
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            endYear = calendar.get(calendar.YEAR) % 100;
            if (calendar.get(calendar.DAY_OF_MONTH) < 10) {
                strTemp = "0" + strTemp;
            }
            if (calendar.get(calendar.MONTH) + 1 < 10) {
                if (endYear < 10) {
                    return strTemp + "/0" + (calendar.get(calendar.MONTH) + 1) + "/0" + endYear;
                } else {
                    return strTemp + "/0" + (calendar.get(calendar.MONTH) + 1) + "/" + endYear;
                }
            } else {
                if (endYear < 10) {
                    return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/0" + endYear;
                } else {
                    return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + endYear;
                }
            }
        }
    }

    /*return date with format: d/m/yy */
    public static String Timestamp2DMYY(java.sql.Timestamp ts) {
        int endYear = 0;
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));
            endYear = calendar.get(calendar.YEAR) % 100;
            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            if (endYear < 10) {
                return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/0" + endYear;
            } else {
                return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + endYear;
            }
        }
    }

    /*return date with format: d/m/yyyy */
    public static String Timestamp2DMYYYY(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            return strTemp + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR);
        }
    }
    /*return date with format: YYYY/MM/DD to sort*/

    public static String Timestamp2YYYYMMDD(java.sql.Timestamp ts) {
        int endYear;
        if (ts == null) {
            return "";
        } else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new java.util.Date(ts.getTime()));

            String strTemp = Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
            endYear = calendar.get(calendar.YEAR);
            if (calendar.get(calendar.DAY_OF_MONTH) < 10) {
                strTemp = "0" + strTemp;
            }
            if (calendar.get(calendar.MONTH) + 1 < 10) {
                return endYear + "/0" + (calendar.get(calendar.MONTH) + 1) + "/" + strTemp;
            } else {
                return endYear + "/" + (calendar.get(calendar.MONTH) + 1) + "/" + strTemp;
            }
        }
    }

    /**
     *   Author: toantt
     * @param ts          Timestapm to convert
     * @param iStyle      0: 24h,  otherwise  12h clock
     * @return
     */
    public static String Timestamp2HHMMSS(java.sql.Timestamp ts, int iStyle) {
        if (ts == null) {
            return "";
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));

        String strTemp;
        if (iStyle == 0) {
            strTemp = Integer.toString(calendar.get(calendar.HOUR_OF_DAY));
        } else {
            strTemp = Integer.toString(calendar.get(calendar.HOUR_OF_DAY));
        }

        if (strTemp.length() < 2) {
            strTemp = "0" + strTemp;
        }
        if (calendar.get(calendar.MINUTE) < 10) {
            strTemp += ":0" + calendar.get(calendar.MINUTE);
        } else {
            strTemp += ":" + calendar.get(calendar.MINUTE);
        }
        if (calendar.get(calendar.SECOND) < 10) {
            strTemp += ":0" + calendar.get(calendar.SECOND);
        } else {
            strTemp += ":" + calendar.get(calendar.SECOND);
        }

        if (iStyle != 0) {
            if (calendar.get(calendar.AM_PM) == calendar.AM) {
                strTemp += " AM";
            } else {
                strTemp += " PM";
            }
        }
        return strTemp;
    }
    //Timestamp add ihour

    public static String Timestamp2HHMMSS(java.sql.Timestamp ts, int iStyle, int iHour, int iMinute) {
        if (ts == null) {
            return "";
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));

        String strTemp;
        if (iStyle == 0) {
            strTemp = Integer.toString(iHour);
        } else {
            strTemp = Integer.toString(iHour);
        }

        if (strTemp.length() < 2) {
            strTemp = "0" + strTemp;
        }
        if (iMinute < 10) {
            strTemp += ":0" + calendar.get(iMinute);
        } else {
            strTemp += ":" + calendar.get(iMinute);
        }
        if (calendar.get(calendar.SECOND) < 10) {
            strTemp += ":0" + calendar.get(calendar.SECOND);
        } else {
            strTemp += ":" + calendar.get(calendar.SECOND);
        }

        if (iStyle != 0) {
            if (calendar.get(calendar.AM_PM) == calendar.AM) {
                strTemp += " AM";
            } else {
                strTemp += " PM";
            }
        }
        System.out.println("strTemp :" + strTemp);
        return strTemp;
    }

    /**
     *  return date time used for 24 hour clock
     */
    public static String getDateTime24hStringByHour(java.sql.Timestamp ts, int iHour, int iMinute) {
        if (ts == null) {
            return "";
        }
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0, iHour, iMinute);
    }

    /**
     *  return date time used for 24 hour clock
     */
    public static String getDateTime24hString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 0);
    }

    /**
     *  return date time used for 12 hour clock
     */
    public static String getDateTime12hString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2DDMMYYYY(ts) + " " + Timestamp2HHMMSS(ts, 1);
    }

    /**
     *   return string dd/mm/yyyy from a Timestamp + a addtional day
     * @param ts
     * @param iDayPlus    number of day to add
     * @return
     */
    public static String TimestampPlusDay2DDMMYYYY(java.sql.Timestamp ts, int iDayPlus) {
        if (ts == null) {
            return "";
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay + iDayPlus);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return Timestamp2DDMMYYYY(tsNew);
    }

    public static Timestamp getPreviousDate(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay - 1);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static Timestamp getSomeDaysAgo(Timestamp ts, int some) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay - some);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static Timestamp get7DaysAgo(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay - 7);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static Timestamp get1WeekAgo(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay - 6);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static Timestamp getMonthDayDate(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        System.out.println("iDay --> " + iDay);
//   if(
//   calendar.set(calendar.DAY_OF_MONTH, iDay-1);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static Timestamp getNextDate(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay + 1);

        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static int getDayOfWeek(Timestamp ts) {
        if (ts == null) {
            return -1;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_WEEK);
        return iDay;
    }

    public static int getDay(Timestamp ts) {
        if (ts == null) {
            return -1;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        return iDay;
    }

    public static int getMonth(Timestamp ts) {
        if (ts == null) {
            return -1;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iMonth = calendar.get(calendar.MONTH);
        return iMonth + 1;
    }

    public static int getYear(Timestamp ts) {
        if (ts == null) {
            return -1;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iYear = calendar.get(calendar.YEAR);
        return iYear;
    }

    public static Timestamp getTimeInDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Timestamp date = new Timestamp(dateFormat.parse(dateStr, new ParsePosition(0)).getTime());
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

//timeComp > now return true, < now return false
    public static boolean compareDate(java.sql.Timestamp timeComp) {
        if (timeComp == null) {
            return false;
        }
        System.out.println("time stamp: " + timeComp);
        boolean result = true;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        cal1.setTime(new java.util.Date(timeComp.getTime()));

        int currDay = cal.get(cal.DAY_OF_MONTH);
        int currMonth = cal.get(cal.MONTH) + 1;
        int currYear = cal.get(cal.YEAR);
        int currHour = cal.get(cal.HOUR_OF_DAY);
        int currMinute = cal.get(cal.MINUTE);

        int creDay = cal1.get(cal1.DAY_OF_MONTH);
        int creMonth = cal1.get(cal1.MONTH) + 1;
        int creYear = cal1.get(cal1.YEAR);
        int creHour = cal1.get(cal1.HOUR_OF_DAY);
        int creMinute = cal1.get(cal1.MINUTE);

        if (creYear < currYear) {
            result = false;
        } else if (creYear == currYear && creMonth < currMonth) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay < currDay) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay == currDay && creHour < currHour) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay == currDay && creHour == currHour && creMinute < currMinute) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

//timeComp2 > timeComp1 : true, < timeComp1 : false
    public static boolean compareDate(java.sql.Timestamp timeComp1, java.sql.Timestamp timeComp2) {
        if (timeComp1 == null | timeComp2 == null) {
            return false;
        }

        boolean result = true;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        cal.setTime(new java.util.Date(timeComp1.getTime()));
        cal1.setTime(new java.util.Date(timeComp2.getTime()));

        int currDay = cal.get(cal.DAY_OF_MONTH);
        int currMonth = cal.get(cal.MONTH) + 1;
        int currYear = cal.get(cal.YEAR);
        int currHour = cal.get(cal.HOUR_OF_DAY);
        int currMinute = cal.get(cal.MINUTE);

        int creDay = cal1.get(cal1.DAY_OF_MONTH);
        int creMonth = cal1.get(cal1.MONTH) + 1;
        int creYear = cal1.get(cal1.YEAR);
        int creHour = cal1.get(cal1.HOUR_OF_DAY);
        int creMinute = cal1.get(cal1.MINUTE);

        if (creYear < currYear) {
            result = false;
        } else if (creYear == currYear && creMonth < currMonth) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay < currDay) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay == currDay && creHour < currHour) {
            result = false;
        } else if (creYear == currYear && creMonth == currMonth && creDay == currDay && creHour == currHour && creMinute < currMinute) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    /**
     * return the dd/mm/yyyy of current month
     *   eg:   05/2002  -->   31/05/2002
     *
     * @param strMonthYear  : input string mm/yyyy
     * @return
     */
    public static String getLastestDateOfMonth(String strMonthYear) {
        String strDate = strMonthYear;
        int i, nYear, nMonth, nDay;
        String strSub = null;

        i = strDate.indexOf("/");
        if (i < 0) {
            return "";
        }
        strSub = strDate.substring(0, i);
        nMonth = (new Integer(strSub)).intValue(); // Month begin from 0 value
        strMonthYear = strMonthYear.substring(i + 1);
        nYear = (new Integer(strMonthYear)).intValue();

        boolean leapyear = false;
        if (nYear % 100 == 0) {
            if (nYear % 400 == 0) {
                leapyear = true;
            }
        } else if ((nYear % 4) == 0) {
            leapyear = true;
        }

        if (nMonth == 2) {
            if (leapyear) {
                return "29/" + strDate;
            } else {
                return "28/" + strDate;
            }
        } else {
            if ((nMonth == 1) || (nMonth == 3) || (nMonth == 5) || (nMonth == 7) || (nMonth == 8) || (nMonth == 10) || (nMonth == 12)) {
                return "31/" + strDate;
            } else if ((nMonth == 4) || (nMonth == 6) || (nMonth == 9) || (nMonth == 11)) {
                return "30/" + strDate;
            }
        }
        return "";
    }

    public static Timestamp getFriday(Timestamp ts) {
        if (ts == null) {
            return null;
        }

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int iDoW = getDayOfWeek(ts);
        if (iDoW == calendar.SUNDAY) {
            iDoW = 8;
        }
        int k = calendar.FRIDAY - iDoW;
        calendar.setTime(new java.util.Date(ts.getTime()));
        int iDay = calendar.get(calendar.DAY_OF_MONTH);
        calendar.set(calendar.DAY_OF_MONTH, iDay + k);
        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static boolean isFriday(Timestamp ts) {
        if (ts == null) {
            return false;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        if (getDayOfWeek(ts) == calendar.FRIDAY) {
            return true;
        }
        return false;
    }

    public static Timestamp getTimestamp(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            Timestamp date = new Timestamp(dateFormat.parse(dateStr).getTime());
            return date;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return null;
        }
    }

    public static Timestamp getNextDateN(Timestamp ts, int n) {
        if (ts == null) {
            return null;
        }
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        calendar.add(calendar.DAY_OF_MONTH, n);
        java.sql.Timestamp tsNew = new java.sql.Timestamp((calendar.getTime()).getTime());
        return tsNew;
    }

    public static int getHour(Timestamp ts) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinute(Timestamp ts) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int minute = calendar.get(calendar.MINUTE);
        return minute;
    }

    public static int getMilis(Timestamp ts) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        int minute = calendar.get(calendar.MILLISECOND);
        return minute;
    }

    public static Timestamp getNextTime(Timestamp now, int next_second) {
        long next = now.getTime() + next_second * 1000;
        java.util.Date ok = new java.util.Date(next);
        Timestamp next_time = DateProc.createDateTimestamp(ok);
        return next_time;
    }

    public static void main(String[] args) {
        String sFrom = "01/10/2013";
        String sTo = "07102013";
        sTo = sTo.substring(0, 2) + "/" + sTo.substring(2, 4) + "/" + sTo.substring(4);
        System.out.println(sTo);
    }
}
