package com.celebration.demo.common.utils;

public class RandomNumberUtil {
    
    public static String getRandomNumber() {
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int randomNumber = (int) Math.round(Math.random() * 9 + 48);
            sb.append(String.valueOf((char)randomNumber));
        }
        return String.valueOf(sb);
    }
}
