package com.mashang.ordering.utils;

public class Checker {
    static public boolean isTimeString(String timeString) {
        if(!timeString.matches("^[0-9]{2}:[0-9]{2}:[0-9]{2}$")){
            return false;
        }
        String[] timeParts = timeString.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        int second = Integer.parseInt(timeParts[2]);
        return  (hour   >= 0 && hour   < 24) &&
                (minute >= 0 && minute < 60) &&
                (second >= 0 && second < 60);
    }
}
