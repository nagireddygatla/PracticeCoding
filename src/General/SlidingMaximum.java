package General;

import java.util.*;

public class SlidingMaximum {

    private static int [] slidingWindowMaximim(int [] input, int k){
        int len = input.length;
        int [] res = new int[len-k+1];
        int resMax=0;
        for(int i=0; i<len-k+1 ; i++){
            int window = i+k;
            int currMax = Integer.MIN_VALUE;
            for(int j=i; j<window; j++){
                currMax = Math.max(input[j],currMax);
            }
            res[resMax] = currMax;
            resMax++;
        }

        Comparator<ListNode> comp2 = Comparator.comparing(ListNode-> ListNode.val);
        Comparator<int []> comp = Comparator.comparing((int [] itv)-> itv[0]);
        Comparator<int []> comp1 =  comp.reversed();


        PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        pq.offer(new int[]{1,2,3});





        return res;
    }

    public static void main(String [] args){
        int [] arr = {4,1,3,2,6,1,8,2,10,5,15};
        int k = 3;

        Arrays.asList(arr);
        int [] res = slidingWindowMaximim(arr,k);
        System.out.println(Arrays.toString(res));


    }
}

