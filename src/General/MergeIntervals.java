package General;

import java.util.*;

public class MergeIntervals {

    private static int[][] mergeInterval(int [][] input){

        Arrays.sort(input, (a,b) -> a[0]-b[0]);

        int [] prev = input[0];
        List<int[]> result = new ArrayList<>();

        for(int i=1; i< input.length; i++){
            int [] current = input[i];

            if(current[0]<=prev[1]){
                prev[1] = Math.max(current[1],prev[1]);
            }
            else{
                result.add(prev);
                prev = current;
            }

        }

        result.add(prev);
        return result.toArray(new int[result.size()][]);


    }

    public static void main(String [] args){
        int [][] input = {{1,3},{2,6},{8,10},{15,18}};
        int [][] input1 = {{1,4},{4,5}};
       // System.out.println(Arrays.toString(mergeInterval(input)));
        int [][] result = mergeInterval(input1);
        for(int[] item:result){
            System.out.print(Arrays.toString(item) + " ");
        }

    }
}
