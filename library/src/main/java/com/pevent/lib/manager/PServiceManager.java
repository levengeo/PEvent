package com.pevent.lib.manager;

import android.text.TextUtils;

import com.pevent.lib.buket.BuketMethod;
import com.pevent.lib.invoker.MethodInvoker;
import com.pevent.lib.invoker.RouteInvoker;
import com.pevent.lib.utils.PEventLog;

import com.pevent.lib.annotations.Route;
import com.pevent.lib.utils.ClassUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;

public final class PServiceManager implements IServiceManager {

    private static final String TAG =  PServiceManager.class.getSimpleName();
    private final Object lock = new Object();
    private final ConcurrentHashMap<Class<?>, BuketMethod> sCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ArrayDeque<MethodInvoker>> routers = new ConcurrentHashMap<>();

    private PServiceManager() {
    }

    private static final PServiceManager sInstance = new PServiceManager();

    public static PServiceManager getInstance() {
        return sInstance;
    }

    @Override
    public void publish(Object service, Class<?>... interfaces) {
        if (interfaces.length == 0) {
            PEventLog.e(TAG, "without interfaces ");
            return;
        }
        synchronized (lock) {
            for (Class<?> aInterface : interfaces) {
                BuketMethod buket = sCache.get(aInterface);
                if (buket != null) {
                    PEventLog.e(TAG, " publish failure, " + "please don't repeat publish same api:" + aInterface.getName());
                    continue;
                }
                BuketMethod buketMethod = new BuketMethod();
                buketMethod.setOwner(service);
                buketMethod.setInterfaceClass(aInterface);
                PEventLog.e(TAG, "publish:" + aInterface.getName());
                sCache.put(aInterface, buketMethod);
            }
        }
    }

    @Override
    public void publish(Object service) {
        synchronized (lock) {
            Class<?> clazz = service.getClass();
            Class<?>[] interfaces = ClassUtil.getValidInterface(clazz);
            publish(service, interfaces);
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                Route route = method.getAnnotation(Route.class);
                if (route != null) {
                    String routeValue = route.value();
                    boolean encode = route.encoded();
                    if (TextUtils.isEmpty(routeValue)) {
                        PEventLog.e(TAG, " the route enable to empty .");
                        continue;
                    }
                    if (encode) {
                        try {
                            routeValue = URLDecoder.decode(routeValue, StandardCharsets.UTF_8.name());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    method.setAccessible(true);
                    MethodInvoker methodInvoker = new RouteInvoker(method, routeValue, service);
                    cacheMethodToRoute(routeValue, methodInvoker);
                }
            }
        }
    }

    public BuketMethod getMethods(Class<?> clazz) {
        return this.sCache.get(clazz);
    }

    public ArrayDeque<MethodInvoker> lookupMethods(String route) {
        return this.routers.get(route);
    }

    private void cacheMethodToRoute(String key, MethodInvoker caller) {
        synchronized (routers) {
            ArrayDeque<MethodInvoker> methodInvokers = routers.get(key);
            if (methodInvokers == null) {
                methodInvokers = new ArrayDeque<>();
                methodInvokers.addFirst(caller);
                routers.put(key, methodInvokers);
            } else {
                methodInvokers.addLast(caller);
            }
        }
    }

    @Override
    public void unPublish(Object service) {
        synchronized (lock) {
            Class<?>[] interfaces = ClassUtil.getValidInterface(service.getClass());
            if (interfaces.length == 0) {
                PEventLog.e(TAG, "unpublish interface is not exist");
            }
            for (int i = 0; i < interfaces.length; i++) {
                Class<?> aInterface = interfaces[i];
                sCache.remove(aInterface);
            }

            Method[] methods = service.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                Route route = method.getAnnotation(Route.class);
                if (route != null) {
                    String path = route.value();
                    boolean encode = route.encoded();
                    if (TextUtils.isEmpty(path)) {
                        continue;
                    }
                    if (encode) {
                        try {
                            path = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    routers.remove(path);
                    break;
                }
            }
        }
    }

    @Override
    public void unPublish(Object service, Class<?>... interfaces) {
        synchronized (lock) {
            if (interfaces.length == 0) {
                PEventLog.e(TAG, "unpublish interface is not exist");
            }
            for (int i = 0; i < interfaces.length; i++) {
                Class<?> aInterface = interfaces[i];
                sCache.remove(aInterface);
            }
        }
    }
}
