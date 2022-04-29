package General;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//eager initialization is prevalent, class laoder will do initialization, if data is less do eager initialization. If data is more prefer lazy as data to reduc
// load on class loader.

//Bill Pugh singleton -> holder pattern.
//not much control with bill pugh implementation.
public class SingletonExample {

    private static volatile SingletonExample obj = null;
    private static Map<Long, Customer> customerMap;
    private static Map<Integer, List<Customer>>   departmentMap;

    private SingletonExample(){
    }

    public static SingletonExample getInstance(){
        if(obj==null){
            synchronized (SingletonExample.class){
                if(obj==null)
                obj = new SingletonExample();
                customerMap = new HashMap<>();
                departmentMap = new HashMap<>();
            }
        }
        return obj;
    }

    //If OOPS -> always go through primary data structures --- List, Set, Map, Priority Queues, Stacks, Queues.
    //spend time on datastructure if it is obj oriented, always Map. Be very careful about stores
    //private Map<Long, Customer> customerMap = new HashMap<>();
    //private Map<Integer, List<Customer>>   departmentMap = new HashMap<>();

    public List<Customer> getCustomersByDepartment(int departmentId){
        return departmentMap.getOrDefault(departmentId, new ArrayList<>());
    }

    //Long vs long , Long can be null..
    //Cracking the coding interview Check OOPS.
    //set of related values and small, store in Enum. Prepare to write enums.
    public long addCustomer(Customer customer){
        //based on scenario do exception or return -1;
        if(customer == null) throw new IllegalArgumentException("Customer Not Valid");
        customerMap.put(customer.id, customer);
        departmentMap.computeIfAbsent(customer.dept.id, k-> new ArrayList<>()).add(customer);
        return customer.id;
    }

    //primitive types if not passed, will be zero default, null cannot be passed if we pass primitve(check). check on autoboxing.
    public Customer getCustomer(long id){
        return customerMap.getOrDefault(id, null);
    }

    public boolean deleteCustomer(long id){
            if (customerMap.containsKey(id)) {
                Customer customer = customerMap.remove(id);
                int deptId = customer.dept.id;
                List<Customer> customerList = departmentMap.get(deptId);
                customerList.remove(customer);
                return true;
            }
        return false;
    }
}

class Customer{
    long id;
    String name;
    Department1 dept;
    public Customer(Long id, String name, Department1 department){
        this.id = id;
        this.name = name;
        this.dept = department;
    }
}

class Department1 {
    int id;
    String name;

    public Department1(int id, String name) {
        this.id = id;
        this.name = name;
    }
}