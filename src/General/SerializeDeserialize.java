package General;


import java.util.*;
import java.util.Queue;

public class SerializeDeserialize {

    public static String serialize(NaryNode node){
        if(node==null)return null;

        Queue<NaryNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(node);
        sb.append(node.val);
        sb.append("#");

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++) {
                NaryNode curr = queue.remove();
                if(curr!=null) {
                    List<NaryNode> list = curr.children;
                    if (list == null || list.size()==0) {
                        sb.append("null");
                    } else {
                        for (NaryNode temp : list) {
                            sb.append(temp.val);
                            sb.append(",");
                            queue.offer(temp);
                        }
                    }
                    sb.append("#");
                }
            }

        }

        return sb.toString().substring(0,sb.length()-1);
    }

    public static NaryNode deserialize(String nodeInfo){
        if(nodeInfo==null)
            return null;
        String [] nodeLevels = nodeInfo.split("#");
        NaryNode root = new NaryNode(Integer.valueOf(nodeLevels[0]),null);
        Queue<NaryNode> queue = new LinkedList<>();
        queue.offer(root);

        for(int i=1; i< nodeLevels.length; i++){
            NaryNode current = queue.remove();
            String [] list = nodeLevels[i].split(",");

            List<NaryNode> children = new ArrayList<>();

            for(String elem: list){
                if(!elem.equals("null") && elem.length()==0){
                    NaryNode currNode = new NaryNode(Integer.valueOf(elem),null);
                    children.add(currNode);
                    queue.offer(currNode);
                }
            }
            current.children = children;

        }
        return root;

    }
}

class NaryNode{
    int val;
    List<NaryNode> children;

    public NaryNode(int val, List<NaryNode> children){
        this.val = val;
        this.children = children;
    }
}
