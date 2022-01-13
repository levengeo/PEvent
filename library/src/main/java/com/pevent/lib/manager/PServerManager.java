package com.pevent.lib.manager;

import android.os.Bundle;
import android.text.TextUtils;

import com.pevent.lib.utils.NotFoundRouteException;
import com.pevent.lib.invoker.MethodInvoker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Iterator;

import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_RESPONSE_CODE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_KEY_ROUTE;
import static com.pevent.lib.utils.PEventConstant.PEVENT_RESPONSE_RESULE_SUCCESS;

public class PServerManager {

    private static final String TAG = PServerManager.class.getSimpleName();

    private PServerManager() {
    }

    public static PServerManager getInstance() {
        return Holder.sInstance;
    }

    public Bundle dispatch(String method, Bundle in, Bundle out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotFoundRouteException {
        String route = in.getString(PEVENT_KEY_ROUTE);
        if (TextUtils.isEmpty(route)) {
            throw new NotFoundRouteException(route + " was not found");
        }
        ArrayDeque<MethodInvoker> callers = PServiceManager.getInstance().lookupMethods(route);
        if (callers == null || callers.isEmpty()) {
            throw new NotFoundRouteException(route + " was not found");
        }
        Iterator<MethodInvoker> iterators = callers.iterator();
        while (iterators.hasNext()) {
            MethodInvoker methodInvoker = iterators.next();
            methodInvoker.invoke(in, out);
        }
        out.putInt(PEVENT_KEY_RESPONSE_CODE, PEVENT_RESPONSE_RESULE_SUCCESS);
        return out;
    }

    private static class Holder {
        private static final PServerManager sInstance = new PServerManager();
    }
}
