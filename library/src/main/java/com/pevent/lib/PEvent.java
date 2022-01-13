package com.pevent.lib;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.pevent.lib.annotations.support.NonNull;
import com.pevent.lib.utils.CallRemoteException;
import com.pevent.lib.utils.Par;

import java.util.Objects;

import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_RESPONSE_CODE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_ROUTE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_ILLEGALACCESS;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_LOST_CLASS;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_NOT_FOUND_ROUTE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_NO_SUCH_METHOD;

public final class PEvent {

    private static final String TAG =  PEvent.class.getSimpleName();

    private final String authority;
    private final Context mContext;
    private final Uri base;

    private PEvent(Builder builder) {
        mContext = builder.mContext;
        authority = builder.authority;
        base = Uri.parse("content://" + authority);
    }

    public PEventPost route(String route) {
        return new PEventPost(this, route);
    }

    Bundle post(@NonNull Bundle in) {
//        in.putInt(PEVENT_KEY_LOOK_UP_APPROACH, PEVENT_APPROACH_ROUTE);
        ContentResolver contentResolver = mContext.getContentResolver();
        Bundle out = null;
        try {
            out = contentResolver.call(base, "", null, in);
            parseReponse(in, out);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return out;
    }

    void parseReponse(Bundle in, Bundle out) throws CallRemoteException {
        out.setClassLoader(Par.class.getClassLoader());
        int responseCode = out.getInt(PEVENT_KEY_RESPONSE_CODE);
        if (responseCode == PEVENT_RESPONSE_RESULE_NO_SUCH_METHOD) {
            throw new CallRemoteException("404 , method not found ");
        }

        if (responseCode == PEVENT_RESPONSE_RESULE_LOST_CLASS) {
            throw new CallRemoteException("404 , class not found ");
        }

        if (responseCode == PEVENT_RESPONSE_RESULE_ILLEGALACCESS) {
            throw new CallRemoteException("404 , illegal access ");
        }

        if (responseCode == PEVENT_RESPONSE_RESULE_NOT_FOUND_ROUTE) {
            String route = in.getString(PEVENT_KEY_ROUTE);
            throw new CallRemoteException(route + " was not found ");
        }
        out.remove(PEVENT_KEY_RESPONSE_CODE);
    }

    public static PEvent.Builder newBuilder(@NonNull Context context) {
        Objects.requireNonNull(context);
        return new Builder(context);
    }

    public static final class Builder {
        private Context mContext;
        private String authority;

        private Builder(Context context) {
            this.mContext = context;
        }

        public Builder setAuthority(@NonNull String authority) {
            if (TextUtils.isEmpty(authority)) {
                throw new IllegalArgumentException("authorities error");
            }
            this.authority = authority;
            return this;
        }

        public PEvent build() {
            return new PEvent(this);
        }
    }

}
