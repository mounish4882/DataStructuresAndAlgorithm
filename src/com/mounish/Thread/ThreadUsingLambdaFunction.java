package com.mounish.Thread;

public class ThreadUsingLambdaFunction {
    public static Runnable runnable = () -> {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Starting " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finishing " + Thread.currentThread().getName());
    };
}
