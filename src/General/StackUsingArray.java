package General;

public class StackUsingArray<E> {
    int CAP;
    int size = 0;
    E [] arr;

    public StackUsingArray(int CAP){
        this.CAP = CAP;
        this.arr = (E[]) new Object[CAP];
    }

    private E pop(){
        if(this.size==0)
            return null;
        this.size--;
        E res = arr[size];
        arr[size] = null;
        return res;
    }

    private boolean push(E val){

        if(this.size==this.CAP)
            return false;
        else{
            arr[this.size] = val;
            this.size++;
        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(this.size==0)return sb.toString();

        for(int i=0; i<size; i++){
            sb.append(arr[i]);
            if(i!=size-1)
                sb.append(", ");
        }
        return sb.toString();
    }

    public static void main(String [] args){
        StackUsingArray obj = new StackUsingArray(5);
        obj.push(1);
        obj.push("nagi");
        obj.push(3);
        obj.push(4);
        obj.pop();
        System.out.println(obj.toString());
    }
}
