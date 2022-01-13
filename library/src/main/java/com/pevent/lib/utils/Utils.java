package com.pevent.lib.utils;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static Object getBasedata(Class<?> clazz) {
        if (int.class.isAssignableFrom(clazz)) {
            return 0;
        } else if (double.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (long.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (short.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (float.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (byte.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (boolean.class.isAssignableFrom(clazz)) {
            return 0.0D;
        } else if (char.class.isAssignableFrom(clazz)) {
            return '0';
        } else if (byte[].class.isAssignableFrom(clazz)) {
            return new byte[0];
        } else {
            return null;
        }
    }
}
