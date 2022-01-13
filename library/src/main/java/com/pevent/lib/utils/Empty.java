package com.pevent.lib.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Empty implements Parcelable, Serializable {
    public Empty() {
    }

    protected Empty(Parcel in) {
    }

    public static final Creator<Empty> CREATOR = new Creator<Empty>() {
        @Override
        public Empty createFromParcel(Parcel in) {
            return new Empty(in);
        }

        @Override
        public Empty[] newArray(int size) {
            return new Empty[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
