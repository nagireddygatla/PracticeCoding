package General;

/**
 e  */

// n/2 -> 2n/3 -
// 2-> root(n) -> n/2 -> n/3 -> n/5 - root(n)loglogn

import java.io.*;
import java.util.*;
import java.util.Queue;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Compass {

    public static int daysVisit(int n){
        if(n<=1)return n;

        Queue<Integer> queue = new LinkedList<>();

        Map<Integer, Boolean> map = new HashMap<>();

        queue.offer(n-1);
        map.put(n-1,true);


        if(n%2==0){
            queue.offer(n/2);
            map.put(n/2, true);

        }

        if(n%3==0){
            queue.offer(2*(n/3));
            map.put(2*(n/3), true);

        }
        int count = 0;


        while(!queue.isEmpty()){
            int size = queue.size();
            count++;
            for(int i=0; i< size; i++){
                int leftSum = queue.remove();
                if(leftSum==0) return count;
                queue.offer(leftSum-1);
                if(leftSum%2==0){
                    if(leftSum==2)
                        queue.offer(0);
                    else
                        if(!map.containsKey(leftSum/2)){
                            queue.offer(leftSum/2);
                            map.put(leftSum/2, true);
                        }
                }
                if(leftSum%3==0){
                    if(!map.containsKey(2*(leftSum/3))){
                        queue.offer(2*(leftSum/3));
                        map.put(2*(leftSum)/3, true);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 62;
        System.out.println(daysVisit(n));

    }
}

