package com.pevent.lib.utils;

import android.os.RemoteException;

public class CallRemoteException extends RemoteException {

    public CallRemoteException(String message) {
        super(message);
    }
}
