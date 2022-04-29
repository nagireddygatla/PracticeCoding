package General;

/*
In newyork city, there are n buildings. Based on the number of building, you can visit set of building a day.

for any number, you can visit one building per day
if number divisible by 2, you can visit n/2 buildings
if number divisible by 3, you can visit 2n/3 buildings.

Find the least number of days you can cover all buildings.

n=10 {1, 6, 2, 1} -> 4 days is the least days you can cover all buildings.

1 building -> 1 day
2 buildings -> 2 days
3 buildings -> 2 days


*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CompassDP {

    static Map<Integer, Integer> cache = new HashMap<>();

    public static int numDays(int n){

        if(n==1)return 1;
        if(n==2)return 2;
        if(n==3)return 2;

        if(cache.containsKey(n))
            cache.get(n);

        int n1=Integer.MAX_VALUE;
        int n2 = Integer.MAX_VALUE;
        int n3 = Integer.MAX_VALUE;

        if(n%2==0)
            n1 = numDays(n-n/2);
        if(n%3==0)
            n2 = numDays(n-2*n/3);
        n3 = numDays(n-1);

        int result = Math.min(Math.min(n1, n2), n3)+1;
        cache.put(n, result);
        return cache.get(n);
    }


    public static int numDaysBFS(int n){
        if(n<=1)return n;

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> days = new LinkedList<>();

        Map<Integer, Boolean> cache = new HashMap<>();
        queue.offer(n-1);
        days.offer(1);

        if(n%2==0 && !cache.containsKey(n-n/2)){
            cache.put(n-n/2, true);
            queue.offer(n-n/2);
            days.offer(1);
        }

        if(n%3==0 && !cache.containsKey(n-2*n/3)){
            cache.put(n-2*(n/3), true);
            queue.offer(n-2*(n/3));
        }
        int count = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            count++;

            for(int i=0; i< size; i++){
                int leftSum = queue.remove();
                if(leftSum==0) return count;
                queue.offer(leftSum-1);

                if(leftSum%2==0 && !cache.containsKey(leftSum-leftSum/2)){
                    cache.put(leftSum-leftSum/2, true);
                    queue.offer(leftSum - leftSum/2);
                }

                if(leftSum%3==0 && !cache.containsKey(leftSum-2*leftSum/3)){
                    cache.put(leftSum-2*leftSum/3, true);
                    queue.offer(leftSum - 2*(leftSum/3));
                }
            }
        }
        return -1;
    }


    public static void main(String [] args){

        int num = 62;

        System.out.println(numDays(num));
        System.out.println(numDaysBFS(num));

    }
}
