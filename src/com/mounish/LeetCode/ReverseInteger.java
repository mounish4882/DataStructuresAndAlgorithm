package com.mounish.LeetCode;

public class ReverseInteger {
    public static int reverseInteger ( int x ) {
        long result = 0;
        boolean isNegative = false;
            if( x < 0){
                isNegative = true;
                x = x * -1;
            }
            while ( x != 0) {
                result = result * 10 + (x % 10);
                x = x / 10;
            }
            if (isNegative)
                result = result * -1;
            if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
                result = 0;
        return (int)result;
    }
}
