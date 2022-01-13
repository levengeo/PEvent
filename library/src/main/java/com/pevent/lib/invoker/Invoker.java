
package com.pevent.lib.invoker;

import com.pevent.lib.annotations.support.NonNull;
import com.pevent.lib.annotations.support.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker extends AbsMethodInvoker {

    private Method target;
    private String route;
    private Object owner;

    public Invoker(@NonNull Method target, @Nullable String route, @Nullable Object owner) {
        super(target);
        this.target = target;
        this.route = route;
        this.owner = owner;
    }

    @Override
    public Object invoke(Object... arg) throws IllegalAccessException, InvocationTargetException {
        target.setAccessible(true);
        return super.invoke(owner, arg);
    }

}
