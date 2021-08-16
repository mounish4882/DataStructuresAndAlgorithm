package com.mounish.Thread;

public class ThreadExtendingThreadClass {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Starting Thread1");
            System.out.println("Finishing Thread1");
        }

    }

}
