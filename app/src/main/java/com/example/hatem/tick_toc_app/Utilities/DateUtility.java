package com.example.hatem.tick_toc_app.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hatem on 9/23/16.
 */
public class DateUtility {


    public static String getFormattedMonth( String strDate ) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {

            Date date = formatter.parse(strDate);
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            int month = Integer.parseInt(monthFormat.format(date));
            String[] monthNames = {"Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
            return monthNames[month-1];

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getFormatedDayNumber(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {

            Date date = formatter.parse(strDate);
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
            String dayNumberStr =  dayFormat.format(date);
            int dayNum =  Integer.parseInt(dayNumberStr);

            switch (dayNum){
                case 1 : return "01st";
                case 2 : return "02nd";
                case 3 : return "03rd";
                default: return dayNum+"th";
            }


        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatedDayName(String strDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {

            Date date = formatter.parse(strDate);
            SimpleDateFormat dayNameFormat = new SimpleDateFormat("EE", Locale.ENGLISH);
            return dayNameFormat.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormattedyear( String strDate ) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {

            Date date = formatter.parse(strDate);
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String YearString =  yearFormat.format(date);
            return YearString;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  null ;

    }


    public static  String getFormateDate(String strDate){
        String dayName = getFormatedDayName(strDate);
        String dayNum = getFormatedDayNumber(strDate);
        String monthName = getFormattedMonth(strDate);
        String year = getFormattedyear(strDate);

        return dayName+", "+dayNum+" of "+monthName+", "+year;
    }


    public static String getFormattedTime( String strDate ) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try {
            Date date = formatter.parse(strDate);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String time = timeFormat.format(date);
            String[] timeArr = time.split(":");

            if(Integer.parseInt(timeArr[0]) <12 ){
               return time+" AM";
            }else{
              return time+" PM";
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  null ;

    }


    public static  String getDetailedEventFormatedDate(String strDate){
            String dayName = getFormatedDayName(strDate);
            String dayNum = getFormatedDayNumber(strDate);
            String monthName = getFormattedMonth(strDate);
            String year = getFormattedyear(strDate);

            return dayName+", "+dayNum+" "+monthName+" "+year;
    }
}
