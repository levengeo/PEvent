package com.pevent.lib.utils;

public class ParametersUtils {
    static final int PEVENT_PARAMETER_IS_PARCE_MASK = 0x00000300;

    static final int PEVENT_PARAMETER_IS_PARCEL_TRUE = 0x00000100;

    static final int PEVENT_PARAMETER_IS_PARCEL_FALSE = 0x00000200;


    public static int getParcelParameter(int flags) {
        return flags & PEVENT_PARAMETER_IS_PARCE_MASK;
    }

    private static synchronized int setFlag(int old, int flags, int mask) {
        return old = (old & ~mask) | (flags & mask);
    }

    public static int setParamParcel(int old, boolean isParcel) {
        return setFlag(old, PEVENT_PARAMETER_IS_PARCE_MASK, isParcel ? PEVENT_PARAMETER_IS_PARCEL_TRUE : PEVENT_PARAMETER_IS_PARCEL_FALSE);
    }

    public static boolean isParamParcel(int flags) {
        return getParcelParameter(flags) == PEVENT_PARAMETER_IS_PARCEL_TRUE;
    }

}
