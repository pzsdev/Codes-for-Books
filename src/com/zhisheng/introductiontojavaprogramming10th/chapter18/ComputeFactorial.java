package com.zhisheng.introductiontojavaprogramming10th.chapter18;

import java.util.Scanner;

public class ComputeFactorial {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a nonnegative integer: ");
        int n = input.nextInt();

        System.out.println("Factorial of " + n + " is " + factorial(n));
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            System.out.println("Now the n is " + n + ", and n-1 is " + (n -1) + " .");
//            System.out.println("the factorial is " + factorial(n -1));
            return n * factorial(n -1);
        }
    }
}
