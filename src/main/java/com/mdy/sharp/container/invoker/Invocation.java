package com.mdy.sharp.container.invoker;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Invocation implements Serializable {
    private static final long serialVersionUID = 6880994824156121478L;
    private Class<?> targetClass;
    private String methodName;
    private Method method;
    private Class<?> returnType;
    private Class<?>[] parameterTypes;
    private Object[] args;
    private Object ret;
    private Throwable exception;
    private long elapseTime;
    private ActionSession session;

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public ActionSession getSession() {
        return this.session;
    }

    public void setSession(ActionSession session) {
        this.session = session;
    }

    public Object getRet() {
        return this.ret;
    }

    public void setRet(Object ret) {
        this.ret = ret;
    }

    public long getElapseTime() {
        return this.elapseTime;
    }

    public void setElapseTime(long elapseTime) {
        this.elapseTime = elapseTime;
    }

    public Throwable getException() {
        return this.exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getReturnType() {
        return this.returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
