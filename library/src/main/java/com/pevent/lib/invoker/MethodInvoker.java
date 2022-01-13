
package com.pevent.lib.invoker;

import java.lang.reflect.InvocationTargetException;

public interface MethodInvoker {

    Object invoke(Object... arg) throws IllegalAccessException, InvocationTargetException;

}
