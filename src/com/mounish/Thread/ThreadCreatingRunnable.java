package com.mounish.Thread;

public class ThreadCreatingRunnable {
    public static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Starting Thread3");
            System.out.println("Finishing Thread3");
        }
    };
}
