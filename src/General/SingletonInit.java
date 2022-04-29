package General;

import java.util.*;

public class SingletonInit {

    public void getCustomers(){
        Department1 dept = new Department1(1,"Science");
        Customer cust = new Customer(1L,"nagi",dept);
        SingletonExample.getInstance().addCustomer(cust);

        Map<Integer, Integer> map = new HashMap<>();

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(entrySet);

        Collections.sort(list, (a, b) -> a.getValue() - b.getValue());
    }
}
