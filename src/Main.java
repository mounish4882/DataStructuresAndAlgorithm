import com.mounish.Thread.StopThreadImplementingRunnable;
import com.mounish.Thread.ThreadUsingLambdaFunction;

public class Main {

    public static void main(String[] args) {
        /*for (int i = 0;i < 200;i++) {
            SinglyLinkedList.insert(FibonacciDynamicProgramming.fib(i));
        }
        SinglyLinkedList.printAt(100);
        SinglyLinkedList.printAt(150);
        SinglyLinkedList.printAt(200);
        int[] arr = {3,5,4,6};
        System.out.println(Arrays.toString(TwoSum.twoSumHashing(arr,11)));
        System.out.println(Arrays.toString(TwoSum.twoSumSorting(arr,11)));
        System.out.println(ReverseInteger.reverseInteger(1387623));
        System.out.println(PalindromeInteger.palindromeInteger(-1503432));
        System.out.println(PalindromeInteger.palindromeInteger(123454321));
        System.out.println(RomanToInteger.romanToInteger("CLXIV"));
        String[] strings = {"FLOWER","FLOW","FLOOR"};
        System.out.println(LongestCommonPrefix.longestCommonPrefix(strings));
        String parentheses = "([}}])";
        System.out.println(ValidParanthesis.validParanthesis(parentheses));
        ThreadExtendingThreadClass.MyThread myThread = new ThreadExtendingThreadClass.MyThread();
        myThread.start();
        Thread thread = new Thread(new ThreadImplementingRunnable.MyRunnable());
        thread.start();
        Thread thread1 = new Thread(ThreadCreatingRunnable.runnable);
        thread1.start();*//*
        Thread thread2 = new Thread(ThreadUsingLambdaFunction.runnable,"The Thread 2");
        thread2.start();
        Thread thread3 = new Thread(ThreadUsingLambdaFunction.runnable,"The Thread 3");
        thread3.start();*/
        StopThreadImplementingRunnable.StoppableRunnable stoppableRunnable = new StopThreadImplementingRunnable.StoppableRunnable();
        Thread thread4 = new Thread(stoppableRunnable,"Hello Thread");
        thread4.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("requesting Stop");
        stoppableRunnable.requestStop();
        System.out.println("Stop Requested");
    }

}
