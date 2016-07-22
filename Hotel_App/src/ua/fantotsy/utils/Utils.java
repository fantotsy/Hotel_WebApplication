package ua.fantotsy.utils;

import java.util.Calendar;

public class Utils {

    public static String encryptionMD5(String password) {
        try {
            String encryption = null;
            if (password != null) {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(password.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }
                encryption = sb.toString();
            }
            return encryption;
        } catch (java.security.NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] getDateLimits() {
        String[] result = new String[2];
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String dayString;
        String monthString;
        if (Integer.toString(day).length() == 1) {
            dayString = "0" + day;
        } else {
            dayString = "" + day;
        }
        if (Integer.toString(month).length() == 1) {
            monthString = "0" + month;
        } else {
            monthString = "" + month;
        }
        String today = year + "-" + monthString + "-" + dayString;
        String yearLater = (year + 1) + "-" + monthString + "-" + dayString;
        result[0] = today;
        result[1] = yearLater;
        return result;
    }
}