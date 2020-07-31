package com.mdy.sharp.container.invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ActionInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> targetClass = method.getDeclaringClass();
        Invocation ip = new Invocation();
        ip.setArgs(args);
        ip.setReturnType(method.getReturnType());
        ip.setTargetClass(targetClass);
        ip.setMethodName(methodName);
        ip.setMethod(method);
        ip.setParameterTypes(parameterTypes);
        // ip.setSession(Container.get().getSession(false));
        Object o = new ActionFactory().invoke(ip);
        return o;
    }

}