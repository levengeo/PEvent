package com.pevent.lib.utils;

import android.util.Log;

import com.pevent.lib.BuildConfig;

public class PEventLog {

    public static boolean DEBUG = BuildConfig.DEBUG;

    private PEventLog() {
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }
}
