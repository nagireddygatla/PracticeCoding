package General;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {


    private static List<List<Integer>> permutations(int [] arr){
        List<List<Integer>> result = new ArrayList<>();
        getPermutation(arr, 0, result);
        return result;
    }

    private static void getPermutation(int[] arr, int start, List<List<Integer>> result) {
        if(start == arr.length-1){
            List<Integer> subList = new ArrayList<>();
            for(int arrItem: arr){
                subList.add(arrItem);
            }
            result.add(subList);
            return;
        }

        for(int i = start; i < arr.length; i++){
            swap(arr, i, start);
            getPermutation(arr, start+1, result);
            swap(arr, i, start);
        }
    }

    private static void swap(int[] arr, int i, int start) {
        int temp = arr[i];
        arr[i] = arr[start];
        arr[start] = temp;
    }

    public static void main(String [] args){
        int [] arr = {1,2,3};
        List<List<Integer>> list = permutations(arr);

        for(List<Integer> subList: list){
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }
}
