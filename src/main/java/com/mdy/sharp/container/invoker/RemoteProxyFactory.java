package com.mdy.sharp.container.invoker;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class RemoteProxyFactory extends AbstractProxyFactory {
    private static Logger logger = LoggerFactory.get(RemoteProxyFactory.class);
    private ActionInvocationHandler invocationHandler = new ActionInvocationHandler();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> actionInterface) {
        if (!hasProxyObject(actionInterface)) {
            logger.info(actionInterface.getName());
            Object proxy = createProxy(actionInterface, this.invocationHandler);
            addProxyObject(actionInterface, proxy);
        }
        return (T) getProxyObject(actionInterface);
    }

    @Override
    public <T> T createAsync(Class<T> paramClass) {
        // TODO Auto-generated method stub
        return null;
    }
}
