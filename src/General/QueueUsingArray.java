package General;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QueueUsingArray<E> {
    int size;
    int head = -1;
    int tail = -1;
    E [] arr;

    //Below helps us to insert any element in the array
    /*public General.QueueUsingArray(int capacity){
        this.arr = (E [])new Object[capacity];
        this.size = 0;
    }*/

    //Below we are forcing Array to have it inserted only integers!
    public QueueUsingArray(Class<E> e, int capacity){
        this.arr = (E[])Array.newInstance(e,capacity);
        this.size = 0;
    }

    public boolean push(E e){
        if(this.size == arr.length)
            return false;
        head = (head+1)%arr.length;
        arr[head] = e;
        this.size++;
        return true;
    }

    public E pop(){
        if(this.size==0)
            return null;
        tail = (tail+1)%arr.length;
        E e = arr[tail];
        arr[tail] = null;
        this.size--;

        if(size==0){
            head = -1;
            tail = -1;
        }
        return e;
    }

    public E peek(){
        if(this.size==0)
            return null;
        return arr[(tail+1)%arr.length];
    }

    public int size(){
        return this.size;
    }
    public String toString(){
        return Arrays.toString(this.arr);
    }
    public static void main(String [] args){
        int capacity = 10;
        QueueUsingArray obj = new QueueUsingArray(Integer.class,capacity);
        System.out.println(obj.push(1));
        System.out.println(obj.push(2));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        //If you uncomment below line you get ArrayStoreException.
        //System.out.println(obj.push("hello"));
        System.out.println(obj.toString());
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.push(3));
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.toString());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.push(3));
        System.out.println(obj.peek());
        System.out.println(obj.pop());
        System.out.println(obj.peek());
        System.out.println(obj.toString());
    }
}
