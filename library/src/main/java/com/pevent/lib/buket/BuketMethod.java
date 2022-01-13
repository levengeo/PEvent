package com.pevent.lib.buket;

import com.pevent.lib.invoker.Invoker;
import com.pevent.lib.invoker.MethodInvoker;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class BuketMethod {

    private ConcurrentHashMap<String, MethodInvoker> methods;
    private Object owner;
    private Class<?> interfaceClass;

    private static final String TAG = BuketMethod.class.getSimpleName();

    public BuketMethod() {
        methods = new ConcurrentHashMap<>();
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public MethodInvoker match(String method, Class<?>[] clazzs) throws NoSuchMethodException {
        StringBuffer methodUnique = new StringBuffer();
        methodUnique.append(method);
        for (int i = 0; i < clazzs.length; i++) {
            methodUnique.append(clazzs[i].getSimpleName());
        }
        MethodInvoker target = methods.get(methodUnique.toString());
        if (target == null) {
            Method m = owner.getClass().getDeclaredMethod(method, clazzs);
            target = new Invoker(m, "", owner);
            methods.put(methodUnique.toString(), target);
        }
        return target;
    }

}
