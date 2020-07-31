package com.mdy.sharp.container.invoker;

import java.lang.reflect.Method;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class ActionFactory {
    private static final Logger logger = LoggerFactory.get(ActionFactory.class);
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private Object getActionObject(Class<?> clazz, Invocation ip) throws ActionException {
        String className = clazz.getPackage().getName() + ".impl." + clazz.getSimpleName() + "Impl";
        Class<?> targetClass;
        Object obj = null;
        try {
            targetClass = this.classLoader.loadClass(className);
            obj = targetClass.newInstance();
            if (obj == null) {
                logger.error("can not find instance for class:" + clazz);
                throw new ActionException(3, "can not find instance for class:" + clazz);
            }
        } catch (Exception e) {
            logger.error("can not find instance for class:" + clazz);
            throw new ActionException(3, "can not find instance for class:" + clazz, e);
        }
        return obj;
    }

    public Object invoke(Invocation ip) throws Throwable {

        Class<?> clazz = ip.getTargetClass();
        Object obj = getActionObject(clazz, ip);
        String methodName = ip.getMethodName();
        Class<?>[] parameterTypes = ip.getParameterTypes();
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        if (method == null) {
            StringBuilder sb = new StringBuilder();
            for (Class<?> clz : parameterTypes) {
                sb.append(clz.getName() + ",");
            }
            throw new ActionException(5, "can not find method:" + methodName + " with parameterTypes:" + sb);
        }
        Object ret = null;
        Object[] args = ip.getArgs();
        ret = method.invoke(obj, args);
        return ret;
    }

}