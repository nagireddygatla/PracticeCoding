package General;

import java.util.*;

public class HeapSort {

    public static void main(String [] args){
        List<String> s = new ArrayList<>();
        List<String> s1 = new ArrayList<>();
        s.addAll(s1);
        s.remove("hello");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.remove(1);

        s.toArray();
        String s2 = "";
        s2.toCharArray();

        int [] arr = new int[5];
        char [] cha = new char[5];
        Arrays.asList(arr);
        Arrays.toString(arr);
        Collections.reverse(s);
        Collections.sort(s1);
        Arrays.binarySearch(arr,2);

        //default is min priority queue
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        String.valueOf(1);
        new String(cha);

        int [][] tem = new int[8][2];

        Arrays.sort(tem, Comparator.comparing((int [] itv) -> itv[0]));

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>();

    }
}

class ListNode implements  Comparable<ListNode>{
    int val;
    Node next;
    Node prev;
    public ListNode(int val){
        this.val = val;
    }

    public int compareTo(ListNode l1){
        return this.val - l1.val;
    }

}


