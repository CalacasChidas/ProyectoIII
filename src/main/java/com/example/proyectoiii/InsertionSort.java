package com.example.proyectoiii;
/**
 * Clase con el algoritmo de insertion sort
 */
public class InsertionSort {
    /*static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {

            int currentValue = list[i];

            int j = i - 1;
            while (j >= 0 && list[j] > currentValue) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = currentValue;
        }
    }*/

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Desplazar los elementos mayores que key hacia la derecha
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
