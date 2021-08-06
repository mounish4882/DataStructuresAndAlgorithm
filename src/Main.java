import com.mounish.Algorithms.FibonacciDynamicProgramming;
import com.mounish.DataStructures.SinglyLinkedList;
import com.mounish.LeetCode.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        for (int i = 0;i < 200;i++) {
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
    }

}
