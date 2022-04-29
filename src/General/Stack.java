package General;

public class Stack {
    StackNode top;
    private void push(int val){
        StackNode st = new StackNode(val);
        st.next = top;
        top = st;
    }

    private StackNode pop(){
        if(top!=null){
            StackNode temp = top;
            top = top.next;
            return temp;
        }
        return null;
    }
}

class StackNode{
    int val;
    StackNode next;

    public StackNode(int val){
        this.val = val;
    }
}
