package com.mounish.LeetCode;

import java.util.Stack;

public class ValidParanthesis {
    public static boolean validParanthesis(String string) {
        if(string.length() % 2 != 0 ) return false;
        Stack<Character> stack = new Stack<>();
        for(char c : string.toCharArray()) {
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
