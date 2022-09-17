package com.sias.thread1;

//import lombok.extern.slf4j.Slf4j;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Edgar
 * @create 2022-09-17 16:59
 * @faction:
 */
@Slf4j(topic = "ThreadCreate")
public class ThreadCreate {
    /*1.第一种创建线程的方式*/
    public static void main(String[] args) {
        /*01.匿名内部类的方式
         *    去创建一个线程*/
        /*Thread thread = new Thread(() -> {
            log.debug("Running");
            System.out.println("fef");
        });*/
        Thread thread = new Thread(){
            @Override
            public void run() {
                log.debug("Running");
                System.out.println("fef");
            }
        };
        /*02.把上面创建的线程，运行起来
         *    和CPU的调度资源结合，main是一个线程，这个也是一个线程*/
        thread.start();
        /*03.指定线程的名字*/
        thread.setName("t1");
        log.debug("Running");
    }

    /*2.第二种创建线程的方式
    *   这中，把线程和任务分离，任务的单独放在
    *   一个地方，线程的单独放在一个地方*/
    @Test
    public void createThread2(){
        /*01.任务单独分开,下面是简化方式，
        *    任务对象*/
        /*Runnable run=new Runnable() {
            @Override
            public void run() {
                log.debug("Running2");
                System.out.println("");
            }
        };*/
        Runnable run= () -> {
            log.debug("Running2");
            System.out.println("");
        };
        /*02.线程对象*/
        Thread thread = new Thread(run,"t2");
        /*03.启动对象*/
        thread.start();
    }

    /**/
    @Test
    public void createThread3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(()->{
            log.debug("hello");
            Thread.sleep(1000);
            return 100;
        });
        Thread thread = new Thread(task,"t1");
        thread.start();
        log.debug("{}",task.get());
    }
}
