package com.mdy.sharp.container.job;

import java.text.ParseException;
import java.util.Date;

import com.mdy.sharp.container.Container;

public abstract class BaseJob {
    private String cronExpression;
    protected Container container;
    private Date lastRunTime;
    private Exception lastError;
    private int runTimes = 0;

    private int retryTimes;

    private int retryCount;
    private long retryPeriod;

    public Exception getLastError() {
        return this.lastError;
    }

    public void setLastError(Exception lastError) {
        this.lastError = lastError;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public long getRetryPeriod() {
        return this.retryPeriod;
    }

    public void setRetryPeriod(long retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    public void setRunTimes(int runTimes) {
        this.runTimes = runTimes;
    }

    public Date getNextDate() throws Exception {
        if (this.lastRunTime == null) {
            this.lastRunTime = new Date();
        }
        try {
            CronExpression cron = new CronExpression(this.cronExpression);
            return cron.getNextValidTimeAfter(this.lastRunTime);
        } catch (ParseException e) {
            throw new Exception(e);
        }
    }

    public void setLastRunTime(Date lastRunDate) {
        this.lastRunTime = lastRunDate;
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCronExpression(String cron) {
        if (!CronExpression.isValidExpression(cron)) {
            throw new IllegalArgumentException("bad cron expression:" + cron);
        }

        this.cronExpression = cron;
    }

    public Container getContainer() {
        return this.container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public abstract void init();

    public void runJob() {
        this.lastRunTime = new Date();
        this.runTimes++;
        run();
    }

    public abstract void run();

    boolean needForRetry() {
        return (this.retryTimes < this.retryCount);
    }

    void retry() {
        this.retryTimes++;
        runJob();
    }

    public int getRunTimes() {
        return this.runTimes;
    }

    public Date getLastRunTime() throws Exception {
        return this.lastRunTime;
    }

    public String toString() {
        return "BaseJob [cronExpression=" + this.cronExpression + ", container=" + this.container + ", lastRunDate="
                + this.lastRunTime + ", runTimes=" + this.runTimes + ", retryTimes=" + this.retryTimes + ", retryCount="
                + this.retryCount + ", retryPeriod=" + this.retryPeriod + "]";
    }
}
