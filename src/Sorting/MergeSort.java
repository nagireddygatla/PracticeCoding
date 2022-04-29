package Sorting;

import java.util.Arrays;

public class MergeSort {


    public static void mergeSort(int start, int end, int [] arr){

        if(start<end){
            int mid = start + (end-start)/2;
            mergeSort(start, mid, arr);
            mergeSort(mid+1, end, arr);
            merge(start, mid, mid+1, end, arr);
        }
    }

    public static void merge(int firstStart, int firstEnd, int secStart, int secEnd, int [] arr){

        int [] firstArray = new int[firstEnd-firstStart+1];
        int [] secondArray = new int[secEnd-secStart+1];

        int firstTemp = firstStart;
        int secondTemp = secStart;

        for(int i=0; i<firstArray.length; i++)
            firstArray[i] = arr[firstTemp++];

        for(int i=0; i<secondArray.length; i++)
            secondArray[i] = arr[secondTemp++];

        int firstInit = 0;
        int secondInit = 0;

        int pos = firstStart;

        while(firstInit < firstArray.length && secondInit < secondArray.length){
            if(firstArray[firstInit] > secondArray[secondInit])
                arr[pos++] = secondArray[secondInit++];
            else
                arr[pos++] = firstArray[firstInit++];
        }

        while(firstInit < firstArray.length)
            arr[pos++] = firstArray[firstInit++];

        while(secondInit < secondArray.length)
            arr[pos++] = secondArray[secondInit++];


    }


    public static void main(String [] args){
        int [] arr = {4, 5, 1, 2, 3, 3};
        mergeSort(0, arr.length-1, arr);
        System.out.println(Arrays.toString(arr));

    }

}
