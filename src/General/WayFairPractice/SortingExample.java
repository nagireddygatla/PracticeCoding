package General.WayFairPractice;

import java.util.*;

public class SortingExample {

    private Map<Integer, Integer> productCount = new HashMap<>();

    private Map<Integer, PriorityQueue<Product>> productCountTimestamp = new HashMap<>();

    /*public int addProduct(int id){

        if(productCount.containsKey(id)){
            int result = productCount.get(id);
            productCount.put(id,result+1);
            return result+1;
        }
        else{
            productCount.put(id,1);
            return 1;
        }

    }*/

    public int addProduct(int id){
        if(productCountTimestamp.containsKey(id)){
            PriorityQueue<Product> result = productCountTimestamp.get(id);
            result.offer(new Product(id, System.currentTimeMillis()));
            return result.size()+1;
        }
        else{
            PriorityQueue<Product> result = new PriorityQueue<>(Comparator.comparingLong((a) -> a.timestamp));
            //PriorityQueue<Product> result = new PriorityQueue<>((a,b) -> Long.compare(a.timestamp, b.timestamp));
            productCount.put(id,1);
            return 1;
        }
    }
    public boolean shipOrder(List<Integer> products){
        for(int temp: products){
            if(productCountTimestamp.containsKey(temp)){
                PriorityQueue<Product> pq = productCountTimestamp.get(temp);
                if(pq.size()==1)
                    productCount.remove(temp);
                else{
                    pq.remove();
                }

            }
            else
                return false;
        }
        return true;
    }

    /*public boolean shipOrder(List<Integer> products){

        for(int temp: products){
            if(productCount.containsKey(temp)){
                int count = productCount.get(temp);
                if(count==1)
                    productCount.remove(temp);
                else
                    productCount.put(temp, count-1);
            }

            else
                return false;
        }
        return true;
    }*/
}

class Product{
    int id;
    long timestamp;

    public Product(int id, long timestamp){
        this.id = id;
        this.timestamp = timestamp;
    }


}
