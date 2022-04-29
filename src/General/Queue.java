package General;

public class Queue {
    QueueNode first, last;
    private void enqueue(int val){
        QueueNode temp = new QueueNode(val);

        if(first==null){
            first = temp;
            last = first;
        }
        else{
            last.next = temp;
            last = temp;
        }

    }

    private QueueNode dequeue(){

        if(first==null)
            return null;
        else{
            QueueNode temp = first;
            first = first.next;
            return temp;

        }

    }
}

class QueueNode{
    int val;
    QueueNode next;

    public QueueNode(int val){
        this.val = val;
    }
}
