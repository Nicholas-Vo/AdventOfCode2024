package main.java;

public class NickUtil {
    //  0  1  2  3  4  5  6  7  8  9
    // [M, M, A, M, X, X, S, S, M, M]
    public static char[] reverseArray2(char[] arr) {
        int size = arr.length;
        char[] reversed = new char[size];

        System.out.println("start i = " + (size - 1)); // 9

        for (int i = size - 1; i < size; i--) {
            System.out.println("element: " + arr[i]);
        }

        return reversed;
    }
    
}
