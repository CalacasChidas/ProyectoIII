package com.example.proyectoiii;

public class InsertionSort {

    private static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {

            int currentValue = list[i];

            int j = i - 1;
            while (j >= 0 && list[j] > currentValue) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = currentValue;
        }
    }
}
