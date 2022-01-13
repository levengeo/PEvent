package com.pevent.lib;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import com.pevent.lib.annotations.support.Nullable;
import com.pevent.lib.utils.ParametersUtils;

import java.io.Serializable;
import java.util.ArrayList;

import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_FLAGS;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_ROUTE;

public class PEventPost {
    protected String route;
    protected PEvent mPEvent;
    protected Bundle mBundle;

    public PEventPost(PEvent pEvent, String route) {
        this.route = route;
        this.mPEvent = pEvent;
        this.mBundle = new Bundle();
    }

    public Bundle post() {
        Bundle in = this.mBundle;
        if (in == null) {
            in = new Bundle();
        }
        in.putString(PEVENT_KEY_ROUTE, route);
        int flags = 0;
        flags = ParametersUtils.setParamParcel(flags, true);
        in.putInt(PEVENT_KEY_FLAGS, flags);
        return mPEvent.post(in);
    }

    public PEventPost with(Bundle bundle) {
        if (null != bundle) {
            mBundle = bundle;
        }
        return this;
    }

    public PEventPost withString(@Nullable String key, @Nullable String value) {
        mBundle.putString(key, value);
        return this;
    }

    public PEventPost withBoolean(@Nullable String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    public PEventPost withShort(@Nullable String key, short value) {
        mBundle.putShort(key, value);
        return this;
    }

    public PEventPost withInt(@Nullable String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    public PEventPost withLong(@Nullable String key, long value) {
        mBundle.putLong(key, value);
        return this;
    }

    public PEventPost withDouble(@Nullable String key, double value) {
        mBundle.putDouble(key, value);
        return this;
    }

    public PEventPost withByte(@Nullable String key, byte value) {
        mBundle.putByte(key, value);
        return this;
    }

    public PEventPost withChar(@Nullable String key, char value) {
        mBundle.putChar(key, value);
        return this;
    }

    public PEventPost withFloat(@Nullable String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }

    public PEventPost withCharSequence(@Nullable String key, @Nullable CharSequence value) {
        mBundle.putCharSequence(key, value);
        return this;
    }

    public PEventPost withParcelable(@Nullable String key, @Nullable Parcelable value) {
        mBundle.putParcelable(key, value);
        return this;
    }

    public PEventPost withParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
        mBundle.putParcelableArray(key, value);
        return this;
    }

    public PEventPost withParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends Parcelable> value) {
        mBundle.putParcelableArrayList(key, value);
        return this;
    }

    public PEventPost withSparseParcelableArray(@Nullable String key, @Nullable SparseArray<? extends Parcelable> value) {
        mBundle.putSparseParcelableArray(key, value);
        return this;
    }

    public PEventPost withIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        mBundle.putIntegerArrayList(key, value);
        return this;
    }

    public PEventPost withStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        mBundle.putStringArrayList(key, value);
        return this;
    }

    public PEventPost withCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        mBundle.putCharSequenceArrayList(key, value);
        return this;
    }

    public PEventPost withSerializable(@Nullable String key, @Nullable Serializable value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    public PEventPost withByteArray(@Nullable String key, @Nullable byte[] value) {
        mBundle.putByteArray(key, value);
        return this;
    }

    public PEventPost withShortArray(@Nullable String key, @Nullable short[] value) {
        mBundle.putShortArray(key, value);
        return this;
    }

    public PEventPost withCharArray(@Nullable String key, @Nullable char[] value) {
        mBundle.putCharArray(key, value);
        return this;
    }

    public PEventPost withFloatArray(@Nullable String key, @Nullable float[] value) {
        mBundle.putFloatArray(key, value);
        return this;
    }

    public PEventPost withCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        mBundle.putCharSequenceArray(key, value);
        return this;
    }

    public PEventPost withBundle(@Nullable String key, @Nullable Bundle value) {
        mBundle.putBundle(key, value);
        return this;
    }
}
