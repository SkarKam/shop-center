package com.solvd.laba.sorts;

import java.util.Arrays;

/*
To run in terminal:
javac Main.java (compile)
java Main.java 9 3 0 2 1 45 44 (test)
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] tab = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            tab[i] = Integer.parseInt(args[i]);
        }
        System.out.println("Table before sorting algorithm: " + Arrays.toString(tab));

        System.out.println("Table after sorting algorithm: " + Arrays.toString(insertionSort(tab)));
    }

    /**
     * Insertion sort method
     * @param tab - table to be sorted
     * @return sorted table
     */
    public static int[] insertionSort(int[] tab){
        for(int i = 1; i<tab.length; i++) {
            int temp = tab[i];
            int j = i - 1;
                while(j>=0 && tab[j]>temp)
                {
                    tab[j+1] = tab[j];
                    j--;
                }
                tab[j + 1] = temp;

        }
       return tab;
    }

}