/**
 * CS3340 2023
 * Assignment 1
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-01-31
 */

/*
 * Question 7 (b)
 * Return the Fibonacci-like number by passed n
 * with time complexity of O(n)
 */
public class asn1_b {

    /*
     * Question 7 (b)
     * Return the Fibonacci-like number by passed n
     *
     * Each fibonacci-like number is stored in an array,
     * every single digit is an element of the array.
     * Adding two arrays (fib_1 and fib_2) digit by digit and
     * store the result into array fib to create a new big integer
     */
    public static int[] fibonacciLike(int n) {
        // array length for fib, fib_1, and fib-2
        int length = 120;

        //base case: N0=2 & N1=2
        if (n == 0 || n == 1) {
            int[] fib = createArray(length);
            fib[length - 1] = 2;
            return fib;
        }

        // Recursive call: Nn = Nn-1 + Nn-2
        //F(n)
        int[] fib = createArray(length);
        // F(n-1)
        int[] fib_1 = createArray(length);
        // F(n-2)
        int[] fib_2 = createArray(length);

        fib_1[length - 1] = 2;
        fib_2[length - 1] = 2;

        for (int i = 2; i <= n; i++) {
            //F(n) = F(n-1) + F(n-2)
            fib = add(fib_1, fib_2);
            fib_2 = fib_1;
            fib_1 = fib;
        }
        return fib;
    }

    // Help functions

    // create a new array with passed length
    private static int[] createArray(int size) {
        int[] array = new int[size];
        return array;
    }

    /*
     *  This function is designed to add two Big Integers
     *  F(n) = F(n-1) + F(n-2)
     * Every single digit of a Big Integer is stored in the array as an element
     * The sum of fib_1 and fib_2 at the same position (i) will
     * be added to fib[i]
     *  fib[i] =  fib_1[i] + fib_2[i]
     */
    private static int[] add(int[] fib_1, int[] fib_2) {

        // fib_1,fib_2, and fib have the same length
        int length = fib_1.length;

        int[] fib = new int[length];
        boolean hasCarry = false;    // flag for carry

        // adding from right to left (length:1)
        for (int i = length; i > 0; i--) {
            int sum = 0; // with carry 1; otherwise 0
            // set value to 1 if there is a carry
            if (hasCarry) {
                sum = 1;
                hasCarry = false;
            }
            // Add digit by digit
            // fib[i] = fib_1[i] + fib_2[i], i is the index of array
            sum = sum + fib_1[i - 1] + fib_2[i - 1];
            // check if there is a carry (sum is two digits)
            if (sum > 9) {
                hasCarry = true;
            }
            // set sum as a single digit
            fib[i - 1] = sum % 10;
        }
        return fib;
    }


    /*
     * Print out each digit in the array as a number format
     * Trim leading 0 is also handled in this function
     */
    public static void print(int[] fib) {
        int length = fib.length;
        boolean trimZero = true; // Flag for trimming leading zeroes

        // Iterate each digit and print it out
        for (int i = 0; i < length; i++) {
            // Trimming front 0s
            while (trimZero && i < length && fib[i] == 0) {
                i++;  // skip
            }
            trimZero = false;
            // if there exists valid digit
            if (i < length) {
                System.out.print(fib[i]);
            } else // for case all 0's
            {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
       
        // display fibonacci like number up to F(500)
        for (int i = 0; i <= 25; i++) {
            System.out.print("fib(" + i * 20 + "): ");
            print(fibonacciLike(i * 20));
        }
    }
}