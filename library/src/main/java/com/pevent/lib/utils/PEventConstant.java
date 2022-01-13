package com.pevent.lib.utils;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public interface PEventConstant {
    String PEVENT_KEY_ROUTE = "key_path";
    int PEVENT_RESPONSE_RESULE_NO_SUCH_METHOD = 404;
    int PEVENT_RESPONSE_RESULE_LOST_CLASS = 405;
    int PEVENT_RESPONSE_RESULE_ILLEGALACCESS = 403;
    int PEVENT_RESPONSE_RESULE_NOT_FOUND_ROUTE = 402;
    int PEVENT_RESPONSE_RESULE_REMOTE_EXCEPTION = 500;
    int PEVENT_RESPONSE_RESULE_SUCCESS = 200;
    String PEVENT_KEY_RESPONSE_CODE = "reponse_code";
    String PEVENT_KEY_LENGTH = "key_length";
    String PEVENT_KEY_INDEX = "key_%d";
    String PEVENT_KEY_CLASS_INDEX = "key_class_%d";
    String PEVENT_KEY_CLASS = "key_class";
    String PEVENT_KEY_TYPE = "key_type";
    String PEVENT_KEY_RESPONSE = "key_response";
    String PEVENT_KEY_FLAGS = "key_flags";
    String PEVENT_KEY_ARRAY_LENGTH = "_array_length";
    String PEVENT_KEY_CALLING_PACKAGE = "key_calling_package";

    ConcurrentHashMap<Class, ParameterHandler> map = new ConcurrentHashMap<Class, ParameterHandler>() {
        {
            put(int.class, new ParameterHandler.IntHandler());
            put(double.class, new ParameterHandler.DoubleHandler());
            put(long.class, new ParameterHandler.LongHandler());
            put(short.class, new ParameterHandler.ShortHandler());
            put(float.class, new ParameterHandler.FloatHandler());
            put(byte.class, new ParameterHandler.ByteHandler());
            put(byte[].class, new ParameterHandler.ByteArrayHandler());
            put(boolean.class, new ParameterHandler.BooleanHandler());
            put(Parcelable.class, new ParameterHandler.ParcelableHandler());
            put(Serializable.class, new ParameterHandler.SerializableHandler());
            put(String.class, new ParameterHandler.StringHandler());
        }
    };

}
