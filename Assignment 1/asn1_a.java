/**
 * CS3340 2023
 * Assignment 1
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-01-31
 */

/*
 * Question 7 (a)
 * Return the Fibonacci-like number by passed in n
 */
public class asn1_a {
    // Return the Fibonacci-like number by passed in n
    public static long fibonacciLike(int n) {
        //base case: N0=2 & N1=2
        if (n == 0 || n == 1) {
            return 2;
        }
        // Recursive call: Nn = Nn-1 + Nn-2
        return fibonacciLike(n - 1) + fibonacciLike(n - 2);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("fib(" + i * 5 + "): " + fibonacciLike(i * 5));
        }
    }
}