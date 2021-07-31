package com.mounish.LeetCode;

import com.mounish.Utilities.RomanValues;

public class RomanToInteger {
    public static int romanToInteger(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++)
        {
            int s1 = RomanValues.romanValues(s.charAt(i));
            if (i + 1 < s.length())
            {
                int s2 = RomanValues.romanValues(s.charAt(i + 1));
                if (s1 >= s2)
                {
                    res = res + s1;
                }
                else
                {
                    res = res + s2 - s1;
                    i++;
                }
            }
            else {
                res = res + s1;
            }
        }
        return res;
    }
}
