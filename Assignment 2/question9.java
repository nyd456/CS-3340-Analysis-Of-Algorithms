/**
 * CS3340 2023
 * Assignment 2
 * Daniel Xie
 * 251075206
 * dxie32
 * 2023-02-24
 */

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class question9 {
    // constants
    static final int ROWS = 72;
    static final int COLUMNS = 75;
    //variables
    static int[][] image = null;
    static boolean[][] flag = null;
    static int[] sets = null;
    static int[] chars = null;
    static uandf uf;

    public static void main(String[] args) {
        // initial variables
        uf = new uandf(ROWS * COLUMNS + 1);
        image = new int[ROWS][COLUMNS];
        flag = new boolean[ROWS][COLUMNS];

        // process input file
        processFile();
        // q9 (b1) - part 1 - print input binary image
        printPart1();
        // Create connected component image with characters
        unionSets();
        // q9 (b2) - part 2 - print image with characters
        printPart2();
        // q9 (b3) - part 3 - print sorted list of component size
        printPart3();
        // q9 (b4) - part 4 - print image with item size greater than 1
        printPart4();
        // q9 (b5) - part 5 - print image with item size greater than 11
        printPart5();
    }

    // Help functions
    //process input binary image file
    private static void processFile() {
        try {
            // Read the file from input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            // Process each line - set + as 1 in image table and true in flag table
            for (int i = 0; reader.ready(); i++) {
                line = reader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == '+') {
                        image[i][j] = 1;
                        flag[i][j] = true;
                    }
                }
            }
            // close the buffer reader
            reader.close();
        } // File not found
        catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception");
        } catch (IOException e) {
            System.out.println("IOException Exception");
        }
    }

    // Create connected component image where each component is labelled with a unique character
    private static void unionSets() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (image[i][j] == 1) {
                    uf.make_set((i * ROWS + j) + 1);
                    if (j > 0 && image[i][j - 1] == 1) {
                        uf.union_sets(i * ROWS + (j - 1) + 1, i * ROWS + j + 1);
                    }
                    if (i > 0 && image[i - 1][j] == 1) {
                        uf.union_sets((i - 1) * ROWS + j + 1, i * ROWS + j + 1);
                    }
                    if (i > 0 && j > 0 && image[i - 1][j - 1] == 1) {
                        uf.union_sets((i - 1) * ROWS + (j - 1) + 1, i * ROWS + j + 1);
                    }
                    if (i > 0 && j < COLUMNS - 1 && image[i - 1][j + 1] == 1) {
                        uf.union_sets((i - 1) * ROWS + (j + 1) + 1, i * ROWS + j + 1);
                    }
                }
            }
        }

        sets = new int[uf.final_sets()];
        chars = new int[sets.length];
    }

    // q9 (b) - 1 - print input file
    private static void printPart1() {
        System.out.println("------------- 1 The input binary image -------------");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // q9 (b) - 2
    private static void printPart2() {
        System.out.println("------------- 2 The image with characters -------------");
        printPart245(2);
    }

    // q9 (b) - 3
    private static void printPart3() {
        System.out.println("------------- 3 The sorted list of component size -------------");

        // sorting
        int[] sortedSets = new int[sets.length];
        // copy value from set first
        for (int i = 0; i < sets.length; i++) {
            sortedSets[i] = sets[i];
            chars[i] = sortedSets[i];
        }
        // sortingA
        Arrays.sort(sortedSets);

        for (int i = 0; i < sortedSets.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (sortedSets[i] == chars[j]) {
                    // print the char with size
                    System.out.println((char) (j + 97) + "\t  \t" + sortedSets[i]);
                    chars[j] = -1;
                    break;
                }
            }
        }
        System.out.println();
    }

    // q9 (b) - 4
    private static void printPart4() {
        System.out.println("------------- 4 The image with set size greater than 1-------------");
        printPart245(4);
    }

    // q9 (b) - 5
    private static void printPart5() {
        System.out.println("------------- 5 The image with size greater than 11-------------");
        printPart245(5);
    }

    // q9 (b) - 2, 4, and 5 - print function for question 9b-2,4,5 based on the input question number
    private static void printPart245(int qNum) {
        int code;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                // Assign character
                code = uf.find_set(i * ROWS + j + 1) + 96;
                //remove set size greater than 1 for question part 4
                //remove set size greater than 11 for question part 5
                if ((code == 96) || (qNum == 4 && sets[code - 97] <= 1) || (qNum == 5 && sets[code - 97] <= 11)) {
                    System.out.print(' ');
                } else {
                    // Print out the character
                    System.out.print((char) code);
                    sets[code - 97]++;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
