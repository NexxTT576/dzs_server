package com.mdy.sharp.container.invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Logger;
import com.mdy.sharp.container.log.LoggerFactory;

public abstract class AbstractProxyFactory {
    private static Logger logger = LoggerFactory.get(AbstractProxyFactory.class);

    private Map<Class<?>, Object> proxyActionMap = new ConcurrentHashMap<Class<?>, Object>();

    private Map<Class<?>, Object> asyncProxyActionMap = new ConcurrentHashMap<Class<?>, Object>();

    public abstract <T> T create(Class<T> paramClass);

    public abstract <T> T createAsync(Class<T> paramClass);

    protected boolean hasAsyncProxyObject(Class<?> clazz) {
        return this.asyncProxyActionMap.containsKey(clazz);
    }

    protected Object getAsyncProxyObject(Class<?> clazz) {
        return this.asyncProxyActionMap.get(clazz);
    }

    protected void addAsyncProxyObject(Class<?> clazz, Object obj) {
        this.asyncProxyActionMap.put(clazz, obj);
    }

    protected boolean hasProxyObject(Class<?> clazz) {
        return this.proxyActionMap.containsKey(clazz);
    }

    protected Object getProxyObject(Class<?> clazz) {
        return this.proxyActionMap.get(clazz);
    }

    protected void addProxyObject(Class<?> clazz, Object obj) {
        this.proxyActionMap.put(clazz, obj);
    }

    protected Object createProxy(Class<?> actionInterface, InvocationHandler theHandler) {
        logger.info("create proxy:[" + actionInterface + "] with handler:[" + theHandler.getClass() + "]");

        return Proxy.newProxyInstance(actionInterface.getClassLoader(), new Class[] { actionInterface }, theHandler);
    }

    public Map<Class<?>, Object> getProxyActionMap() {
        return this.proxyActionMap;
    }

    public Map<Class<?>, Object> getAsyncProxyActionMap() {
        return this.asyncProxyActionMap;
    }

}