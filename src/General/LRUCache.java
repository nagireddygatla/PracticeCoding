package General;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    //create a map that holds int as key and General.Node as val, so that there is O(1) access to it based on key.
    //Nodes are doubly linked list so that we can get access to prev and next node. and based on access we can move the the forward of the node.

    Map<Integer, Node> map = new HashMap<>();
    Node head;

    public Node getValue(int key){
        Node res = map.get(key);
        if(res!=null) {
            Node temp = res;
            res.prev.next = res.next;
            res.next.prev = res.prev;
            res.prev = null;
            res.next = head;
            head.prev = res;
        }
        else {
            Node newOne = new Node(key);
            map.put(key, newOne);
            newOne.next = head;
            head.prev = newOne;
            res = newOne;
        }
        return res;
    }


}

class Node{
    int val;
    Node next;
    Node prev;
     public Node(int val){
         this.val = val;
     }
}