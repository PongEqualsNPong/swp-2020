package com.praktikum.spapp.common;

public class DateStringSplitter {

    public static String datePrettyPrint(String date){

        String[] arrayDate =  date.split("-");
        String year = arrayDate[0];
        String month = arrayDate[1];
        String day = arrayDate[2].substring(0, 2);

        return  Integer.parseInt(day) + "." + Integer.parseInt(month) + "." + year;
    }

    public static int yearPrettyPrint(String date){

        String[] arrayDate =  date.split("-");
        String year = arrayDate[0];
        String month = arrayDate[1];
        String day = arrayDate[2].substring(0, 2);

        return Integer.parseInt(year);
    }

    public static int monthPrettyPrint(String date){

        String[] arrayDate =  date.split("-");
        String year = arrayDate[0];
        String month = arrayDate[1];
        String day = arrayDate[2].substring(0, 2);

        return Integer.parseInt(month) - 1;
    }

    public static int dayPrettyPrint(String date){

        String[] arrayDate =  date.split("-");
        String year = arrayDate[0];
        String month = arrayDate[1];
        String day = arrayDate[2].substring(0, 2);

        return Integer.parseInt(day);
    }

    public static String timePrettyPrint(String date){

        String[] arryTime = date.split(":");
        String hour = arryTime[0].substring(11, 13);
        String minute = arryTime[1].substring(0, 2);

        return  hour + ":" + minute + " Uhr";
    }

    public static int hourPrettyPrint(String date){

        String[] arryTime = date.split(":");
        String hour = arryTime[0].substring(11, 13);

        return Integer.parseInt(hour);
    }

    public static int minutePrettyPrint(String date){

        String[] arryTime = date.split(":");
        String minute = arryTime[1].substring(0, 2);

        return Integer.parseInt(minute);
    }

    public static String changeToDateFormat(String date, String time){
        String[] arrayDate = date.split("\\.");
        String[] arrayTime = time.split(":");
        String year = arrayDate[2];
        String month = arrayDate[1];
        String[] monthLengthCheck = month.split("");
        String day = arrayDate[0];
        String[] dayLengthCheck = day.split("");

        String hour = arrayTime[0];
        String[] hourLengthCheck = hour.split("");
        String minute = arrayTime[1].substring(0, 2);
        String[] minuteLengthCheck = minute.split("");

        return year + "-" + (monthLengthCheck.length == 1 ? "0" + month : month) + "-" + (dayLengthCheck.length == 1 ? "0" + day : day) + "T" + (hourLengthCheck.length == 1 ? "0" + hour : hour)+ ":" + (minuteLengthCheck.length == 1 ? "0" + minute : minute) + ":00.000000+02:00";
    }

}
