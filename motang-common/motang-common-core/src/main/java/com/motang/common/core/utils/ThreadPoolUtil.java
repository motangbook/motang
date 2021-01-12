package com.motang.common.core.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    /**核心线程数*/
    private static int CORE_SIZE = 10;

    /**最大线程数 超过核心线程池数  进入拒绝策略*/
    private static int MAX_POOL_SIZE = 15;

    /**超过 corePoolSize 线程数量的线程最大空闲时间*/
    private static  long KEEP_ALIVE_TIME = 2;

    /**等待队列数量  如果当前线程大于核心线程数  且小于最大线程数 则进入等待队列  如果等待队列满了 则创建非核心线程执行认为有*/
    private static  int WORK_QUEUE = 4;

    /**
     * @Description 构建线程池
     * @author liuhu
     * @date 2021/1/11 16:41
     * @return java.util.concurrent.ThreadPoolExecutor
     */
    public static ThreadPoolExecutor buildThreadPoolExecutor(){
        //创建工作队列，用于存放提交的等待执行任务
        //AbortPolicy：默认策略，在需要拒绝任务时抛出RejectedExecutionException
        BlockingQueue<Runnable> waitQueue = new ArrayBlockingQueue<Runnable>(WORK_QUEUE);
        return new ThreadPoolExecutor(CORE_SIZE,
                        MAX_POOL_SIZE,
                        KEEP_ALIVE_TIME,
                        TimeUnit.SECONDS,
                        waitQueue,
                        new ThreadPoolExecutor.AbortPolicy());
    }

  /**
   * @Description 通过线程组获得线程
   * @author liuhu
   * @param threadId
   * @date 2021/1/11 17:06
   * @return java.lang.Thread
   */
    public static Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if(threadId == threads[i].getId()) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
