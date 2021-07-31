package com.mounish.LeetCode;

public class PalindromeInteger {
    public static boolean palindromeInteger(int x) {
        return x >= 0 && ReverseInteger.reverseInteger(x) == x;
    }
}
