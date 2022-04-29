package General;

import java.util.*;
import java.util.Queue;


public class GraphBasics {


    public static void main(String [] args){
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);
        GraphNode g5 = new GraphNode(5);
        g1.adjacencyList.addAll(new ArrayList<>(Arrays.asList(g2,g3,g5)));
        g2.adjacencyList.addAll(new ArrayList<>(Arrays.asList(g1,g4)));
        g3.adjacencyList.addAll(new ArrayList<>(Arrays.asList(g1,g4,g5)));
        g4.adjacencyList.addAll(new ArrayList<>(Arrays.asList(g2,g3,g5)));
        g5.adjacencyList.addAll(new ArrayList<>(Arrays.asList(g1,g3,g4)));

       // BFS(g1, 5);
        DFS(g1);

    }

    private static void BFS(GraphNode g1, int val) {

        if(g1.val==val)
            System.out.println(val + " is found");
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(g1);
        g1.visited = true;
        while(!queue.isEmpty()){
            GraphNode curr = queue.remove();
            //System.out.println(curr.val);
            for(GraphNode node:curr.adjacencyList){
                if(!node.visited){
                    queue.offer(node);
                    node.visited = true;
                    if(node.val==val)
                        System.out.println(val + " is found");
                }
            }

        }
    }

    private static void DFS(GraphNode g1){

        g1.visited = true;

        System.out.println(g1.val);
        for(GraphNode node: g1.adjacencyList){
            if(!node.visited){
                DFS(node);
                node.visited = true;
            }
        }
    }
}

class GraphNode{
    int val;
    boolean visited;
    List<GraphNode> adjacencyList;

    public GraphNode(int val){
        this.val = val;
        this.adjacencyList = new ArrayList<>();
        this.visited = false;
    }

    public GraphNode(int val, List<GraphNode> adjacencyList){
        this.val = val;
        this.adjacencyList = adjacencyList;
        this.visited = false;
    }
}
