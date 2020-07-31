package com.mdy.sharp.container;

import java.util.HashMap;
import java.util.Map;

import com.mdy.sharp.container.invoker.AbstractProxyFactory;
import com.mdy.sharp.container.invoker.ActionSession;
import com.mdy.sharp.container.invoker.RemoteProxyFactory;
import com.mdy.sharp.container.job.BaseJob;
import com.mdy.sharp.container.job.JobScheduler;
import com.mdy.sharp.container.res.Resource;

public class Container {
    private static Container ins = null;

    public static Container get() {
        if (ins == null) {
            ins = new Container();
        }
        return ins;
    }

    private Map<String, Resource> resources = new HashMap<>();
    private ThreadLocal<ActionSession> sessionThreadLocal = new ThreadLocal<ActionSession>();
    private Map<String, AbstractProxyFactory> proxyFactoryMap = new HashMap<String, AbstractProxyFactory>();
    private RemoteProxyFactory localProxyFactory = new RemoteProxyFactory();

    public Resource getResource(String name) {
        return resources.get(name);
    }

    public void registerResource(String name, Resource res) {
        this.resources.put(name, res);
    }

    public <T> T createRemote(Class<T> clazz, String clusterName) {
        AbstractProxyFactory proxyFactory = (AbstractProxyFactory) this.proxyFactoryMap.get(clusterName);
        if (proxyFactory == null) {
            proxyFactory = new RemoteProxyFactory();
        }

        return (T) proxyFactory.create(clazz);
    }

    public <T> T createLocal(Class<T> clazz) {
        return (T) this.localProxyFactory.create(clazz);
    }

    public ActionSession getSession(boolean create) {
        ActionSession as = (ActionSession) this.sessionThreadLocal.get();
        if (!create) {
            return as;
        }
        if (as != null) {
            return as;
        }
        ActionSession session = new ActionSession();
        this.sessionThreadLocal.set(session);
        return session;
    }

    private JobScheduler jobScheduler = new JobScheduler();

    public void registerJob(BaseJob job) {
        this.jobScheduler.registerJob(job);
    }
}