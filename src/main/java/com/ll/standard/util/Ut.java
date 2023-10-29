package com.ll.standard.util;

public class Ut {
    public static class str{
        public  static int parseInt(String value, int defaultValue){
            try {
                int index = Integer.parseInt(value);
                return index;
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
}
