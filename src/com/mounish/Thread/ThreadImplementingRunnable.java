package com.mounish.Thread;

public class ThreadImplementingRunnable {

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Starting Thread2");
            System.out.println("Finishing Thread2");
        }
    }

}
