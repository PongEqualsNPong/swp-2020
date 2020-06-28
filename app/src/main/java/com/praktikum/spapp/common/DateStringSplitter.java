package com.praktikum.spapp.common;

public class DateStringSplitter {

    public static String datePrettyPrint(String date){

        String[] arrayDate =  date.split("-");
        String year = arrayDate[0];
        String month = arrayDate[1];
        String day = arrayDate[2].substring(0, 2);

        String[] arryTime = date.split(":");
        String hour = arryTime[0].substring(11, 13);
        String minute = arryTime[1].substring(0, 2);

        return  day + "." + month + "." + year + " / " + hour + ":" + minute + " Uhr";
    }

}
