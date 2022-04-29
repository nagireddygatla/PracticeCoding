package Sorting;

import java.util.Arrays;

public class QuickSort {


    public static void sort(int start, int end, int [] arr){

        if(start<end) {
            int k = partition(start, end, arr);

            sort(start, k - 1, arr);
            sort(k + 1, end, arr);
        }

    }

    public static void select(int start, int end, int [] arr, int k){

        if(start<end) {
            int sep = partition(start, end, arr);
            if(sep == k)
                return;
            if(sep<k)
                select(sep + 1, end, arr, k);
            else if(sep>k)
                select(start, sep - 1, arr, k);
        }
    }
/**
 This is the most critical method for quick sort, be careful with this.
 Compare pivot with ever increasing i, not j. j is crucial for splitting array
 into two parts..
 */
/** This function takes last element as pivot,
	places the pivot element at its correct
	position in sorted array, and places all
	smaller (smaller than pivot) to left of
	pivot and all greater elements to right
	of pivot */
    public static int partition(int start, int end, int [] arr){
        int pivot = arr[end];
        int i=start;
        int j=start;

        for(;i<=end; i++){
            // this gives descending order, if we say arr[i]<pivot then we get ascending order, useful especially for quick select (top K elements)
            if(arr[i] > pivot){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }

        int temp = arr[j];
        arr[j] = pivot;
        arr[end] = temp;

        return j;
    }

    public static void quickSort(int [] arr){
        int start = 0;
        int end = arr.length-1;
        sort(start, end, arr);
    }

    public static void quickSelect(int [] arr, int k){
        int start = 0;
        int end = arr.length-1;
        select(start, end, arr, k);
    }

    public static void main(String [] args){
        int [] arr = {4, 5, 1, 2, 3, 3};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
        int [] arr1 = {4, 5, 1, 2, 3, 3};
        int k = 2;
        quickSelect(arr1, k);
        System.out.println(Arrays.toString(Arrays.copyOf(arr1, k)));

    }
}
