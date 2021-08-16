package com.mounish.Thread;

public class StopThreadImplementingRunnable {
    public static class StoppableRunnable implements Runnable{
        private static boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Starting StoppableRunnable");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("Ending StoppableRunnable");
        }
    };
}
