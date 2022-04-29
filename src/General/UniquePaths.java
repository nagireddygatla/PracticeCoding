package General;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        return dfs(0,0,m,n);
    }

    public int dfs(int i, int j, int m, int n){
        if(i==m-1 && j==n-1){
            return 1;
        }

        if(i<m-1 && j<n-1){
            return dfs(i+1,j,m,n) + dfs(i,j+1,m,n);
        }

        if(i<m-1){
            return dfs(i+1,j,m,n);
        }

        if(j<n-1){
            return dfs(i,j+1,m,n);
        }

        return 0;
    }

    public static void main(String [] args){
        int result = new UniquePaths().uniquePaths(3,3);
        System.out.println(result);


        List<Product> list = new ArrayList<>();

        PriorityQueue<Product> pq = new PriorityQueue<>((a,b) -> Long.compare(a.timestamp, b.timestamp));
        PriorityQueue<Product> pq1 = new PriorityQueue<>(Comparator.comparingLong((a) -> a.timestamp));



    }

}

class Product{
    int id;
    long timestamp;

    public Product(int id, int timestamp ){
        this.id = id;
        this.timestamp = timestamp;
    }
}

