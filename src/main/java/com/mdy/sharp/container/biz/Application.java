package com.mdy.sharp.container.biz;

public abstract class Application {

    public abstract void startUp();

    public abstract void shutDown();

    public String getVersion() {
        return getClass().getPackage().getImplementationVersion();
    }
}
