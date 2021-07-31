import com.mounish.Algorithms.FibonacciDynamicProgramming;
import com.mounish.DataStructures.SingleLinkedList;
import com.mounish.LeetCode.PalindromeInteger;
import com.mounish.LeetCode.ReverseInteger;
import com.mounish.LeetCode.TwoSum;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        for (int i = 0;i < 200;i++) {
            SingleLinkedList.insert(FibonacciDynamicProgramming.fib(i));
        }
        SingleLinkedList.printAt(100);
        SingleLinkedList.printAt(150);
        SingleLinkedList.printAt(200);
        int[] arr = {3,5,4,6};
        System.out.println(Arrays.toString(TwoSum.twoSumHashing(arr,11)));
        System.out.println(Arrays.toString(TwoSum.twoSumSorting(arr,11)));
        System.out.println(ReverseInteger.reverseInteger(1387623));
        System.out.println(PalindromeInteger.palindromeInteger(-1503432));
        System.out.println(PalindromeInteger.palindromeInteger(123454321));
    }

}
