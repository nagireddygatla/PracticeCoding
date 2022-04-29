package General;
import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeKSortedArray {

    public static int [] merge(int [][] arr){

        PriorityQueue<ArrayContainer> pq = new PriorityQueue<>();
        int total = 0;
        for(int i=0; i< arr.length; i++){
            pq.offer(new ArrayContainer(arr[i],0));
            total = total + arr[i].length;
        }

        int [] result = new int[total];
        int i=0;

        while(!pq.isEmpty()){
            ArrayContainer curr = pq.poll();
            result[i] = curr.arr[curr.index];
            i++;

            if(curr.index < curr.arr.length - 1){
                pq.offer(new ArrayContainer(curr.arr, curr.index+1));
            }
        }
        return result;

    }



    public static void main(String [] args){
        int [][] arr = {{1,4,7,9},{2,3,12},{6,8,11,14},{0,5,11,13}};
        System.out.println(Arrays.toString(merge(arr)));
    }

}

class ArrayContainer implements Comparable<ArrayContainer>{

    int [] arr;
    int index;

    public ArrayContainer(int [] arr, int index){
        this.arr = arr;
        this.index = index;
    }
    public int compareTo(ArrayContainer A1){
        return this.arr[this.index] - A1.arr[A1.index];
    }
}
