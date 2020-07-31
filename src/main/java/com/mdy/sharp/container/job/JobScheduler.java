package com.mdy.sharp.container.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.mdy.sharp.container.log.LoggerFactory;

import org.apache.logging.log4j.Logger;

public class JobScheduler {
    private static Logger logger = LoggerFactory.get(JobScheduler.class);

    private Map<String, BaseJob> jobMap = new HashMap<String, BaseJob>();
    private boolean isStop = true;
    private AtomicInteger threadCount = new AtomicInteger();

    private CronThread cronThread;

    public void registerJob(BaseJob job) {
        this.jobMap.put(job.getClass().getName(), job);
    }

    public List<BaseJob> getJobs() {
        return new ArrayList<BaseJob>(this.jobMap.values());
    }

    public void start() {
        logger.info("job scheduler started.");
        this.isStop = false;
        this.cronThread = new CronThread();
        this.cronThread.start();
    }

    public void stop() {
        logger.info("job scheduler stoped.");
        this.isStop = true;
    }

    public BaseJob removeJob(String id) {
        logger.debug("remove job:" + id);
        return (BaseJob) this.jobMap.remove(id);
    }

    public void removeAllJobs() {
        logger.debug("remove all jobs");
        this.jobMap.clear();
    }

    class CronThread extends Thread {
        public CronThread() {
            setName("CronThread");
            // if (Container.get().getAppClassLoader() != null) {
            // logger.debug("set classLoader:" + Container.get().getAppClassLoader());
            // setContextClassLoader(Container.get().getAppClassLoader());
            // }
        }

        public void run() {
            try {
                logger.debug("cron thread running.");
                loopEvent();
                logger.info("cron thread stoped.");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        public void loopEvent() throws Exception {
            while (!JobScheduler.this.isStop) {

                try {
                    Thread.sleep(30000L);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Job loopEvent");
                }
                for (BaseJob job : JobScheduler.this.jobMap.values()) {
                    Date nextRunTime = job.getNextDate();
                    if (logger.isDebugEnabled()) {
                        logger.debug("check Job:" + job.getClass().getSimpleName() + "/nextRun:" + nextRunTime);
                    }

                    Date now = new Date();
                    if (nextRunTime != null && nextRunTime.before(now)) {
                        JobScheduler.this.runJob(job);
                    }
                }
            }
        }
    }

    private void runJob(final BaseJob job) {
        Thread jobThread = new Thread() {
            public void run() {
                long startTime = System.currentTimeMillis();
                boolean isError = false;
                try {
                    logger.info(">>>job:" + job.getClass().getName());
                    job.runJob();
                } catch (Throwable e) {
                    job.setLastError(new Exception(e));
                    isError = true;
                    logger.error(e.getMessage(), e);
                } finally {
                    long endTime = System.currentTimeMillis();

                    try {
                        Date nextToRun = job.getNextDate();
                        logger.info("<<<job:" + job.getClass().getName() + " finished.nextRunDate:" + nextToRun
                                + "/Time:" + (endTime - startTime) + "/RunTimes:" + job.getRunTimes() + "/RetryTimes:"
                                + job.getRetryTimes());

                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
                if (isError) {
                    while (job.needForRetry()) {
                        logger.info("retry job:" + job.getClass().getName() + "/sleep:" + job.getRetryPeriod());

                        try {
                            sleep(job.getRetryPeriod());
                        } catch (InterruptedException e) {
                            logger.error(e.getMessage(), e);
                        }
                        if (JobScheduler.this.retry(job)) {
                            return;
                        }
                    }
                }
            }
        };
        jobThread.setName("JobRunner-" + this.threadCount.incrementAndGet() + "-" + job.getClass().getName());

        jobThread.start();
    }

    private boolean retry(BaseJob job) {
        try {
            logger.info(">>>retry job:" + job.getClass().getName() + "/retry times:" + job.getRetryTimes());

            job.retry();
            job.setRetryCount(0);
            return true;
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            logger.info("<<<retry job:" + job.getClass().getName() + "/retry times:" + job.getRetryTimes());
        }
    }

    public void initJobs() {
        for (BaseJob job : this.jobMap.values()) {
            logger.debug("init job:" + job.getClass().getName());
            try {
                job.init();
                logger.debug("init job:" + job.getClass().getName() + " done.");
            } catch (Exception e) {
                logger.fatal(e.getMessage(), e);
            }
        }
    }

    public boolean runJob(String id) {
        BaseJob job = (BaseJob) this.jobMap.get(id);
        if (job == null) {
            return false;
        }
        runJob(job);
        return true;
    }
}