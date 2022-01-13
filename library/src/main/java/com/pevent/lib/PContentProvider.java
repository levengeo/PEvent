package com.pevent.lib;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;

import com.pevent.lib.annotations.support.NonNull;
import com.pevent.lib.annotations.support.Nullable;
import com.pevent.lib.utils.PEventLog;
import com.pevent.lib.manager.PServerManager;
import com.pevent.lib.utils.NotFoundRouteException;
import com.pevent.lib.utils.Par;
import com.pevent.lib.utils.ParametersUtils;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_CALLING_PACKAGE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_FLAGS;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_RESPONSE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_RESPONSE_CODE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_ROUTE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_ILLEGALACCESS;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_NOT_FOUND_ROUTE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_NO_SUCH_METHOD;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_REMOTE_EXCEPTION;

public class PContentProvider extends ContentProvider {

    public static final String TAG =  PContentProvider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle in) {
        if (in == null) {
            return null;
        }
        long start = SystemClock.elapsedRealtime();
        Bundle response = new Bundle();
        in.setClassLoader(Par.class.getClassLoader());
        Parcelable returnResponse = in.getParcelable(PEVENT_KEY_RESPONSE);
        response.putParcelable(PEVENT_KEY_RESPONSE, returnResponse);
        try {
            String calling = getCallingPackage();
            long callingTime = SystemClock.elapsedRealtime();
            PEventLog.e(TAG, "used time calling:" + (callingTime - start));
            if (!TextUtils.isEmpty(calling)) {
                in.putString(PEVENT_KEY_CALLING_PACKAGE, calling);
            }
            String route = in.getString(PEVENT_KEY_ROUTE);
            int flags = in.getInt(PEVENT_KEY_FLAGS);
            if (ParametersUtils.isParamParcel(flags)) {
                PServerManager.getInstance().dispatch(method, in, response);
            }
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            response.putInt(PEVENT_KEY_RESPONSE_CODE, PEVENT_RESPONSE_RESULE_NO_SUCH_METHOD);
        } catch (NotFoundRouteException e) {
            response.putInt(PEVENT_KEY_RESPONSE_CODE, PEVENT_RESPONSE_RESULE_NOT_FOUND_ROUTE);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            response.putInt(PEVENT_KEY_RESPONSE_CODE, PEVENT_RESPONSE_RESULE_ILLEGALACCESS);
        } catch (Throwable e) {
            e.printStackTrace();
            response.putInt(PEVENT_KEY_RESPONSE_CODE, PEVENT_RESPONSE_RESULE_REMOTE_EXCEPTION);
        }
        return response;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        return super.call(authority, method, arg, extras);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return uri.toString();
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @NonNull
    @Override
    public <T> ParcelFileDescriptor openPipeHelper(@NonNull Uri uri, @NonNull String mimeType, @Nullable Bundle opts, @Nullable T args, @NonNull PipeDataWriter<T> func) throws FileNotFoundException {
        return super.openPipeHelper(uri, mimeType, opts, args, func);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
