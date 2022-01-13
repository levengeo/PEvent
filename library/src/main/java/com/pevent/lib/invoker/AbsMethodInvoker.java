package com.pevent.lib.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbsMethodInvoker implements MethodInvoker {

    private final Method target;

    public AbsMethodInvoker(Method target) {
        this.target = target;
    }

    protected Object invoke(Object owner, Object... parameters) throws InvocationTargetException, IllegalAccessException {
        return target.invoke(owner, parameters);
    }

}
