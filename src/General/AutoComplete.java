package General;

import java.util.*;
import java.util.Queue;

public class AutoComplete {
    private  TrieNode root;

    public AutoComplete(){
        this.root = new TrieNode();
    }

    public  void insert(String word){
        Map<Character, TrieNode> children = root.children;
        TrieNode node = null;
        for(int i=0; i< word.length(); i++){

            char c = word.charAt(i);
            //System.out.print(c);
            if(children.containsKey(c)){
                node = children.get(c);
            }
            else{
                node = new TrieNode(c);
                children.put(c, node);
            }
            children = node.children;

            if(i == word.length()-1){
                node.isLeaf = true;
            }

        }
    }
    public TrieNode searchNode(String word){
        Map<Character, TrieNode> children = root.children;
        TrieNode node = null;

        for(int i=0; i< word.length(); i++){
            char c = word.charAt(i);

            if(children.containsKey(c)){
                node = children.get(c);
                children = node.children;
            }
            else {
                return null;
            }
        }

        return node;
    }

    public List<String> startsWith(String prefix){
        List<String> result = new ArrayList<>();
        TrieNode node = searchNode(prefix);

        if(node==null)
            return result;

        Map<Character, TrieNode> children = node.children;
        Queue<Map<Character, TrieNode>> childrenQueue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        childrenQueue.offer(children);
        stringQueue.offer(prefix);

        while(!childrenQueue.isEmpty()){
            Map<Character, TrieNode> subChildren = childrenQueue.remove();
            String word = stringQueue.remove();

            for(Map.Entry<Character, TrieNode> child: subChildren.entrySet()){
                char c = child.getKey();
                TrieNode subNode = child.getValue();
                Map<Character, TrieNode> tempChildren = subNode.children;
                if(subNode.isLeaf){
                    result.add(word+c);
                }
                else {
                    childrenQueue.offer(tempChildren);
                    stringQueue.offer(word + c);
                }
            }
        }
        return result;

    }

    public static void main(String [] args){
        String [] words = {"Any Given Sunday","Bicentennial Man", "Cast Away", "Endgame", "Flight of the Conchords",
                            "Funny Games", "Game Day", "Game of Thrones", "Marley & Me", "The Breakfast Club"};
        AutoComplete obj = new AutoComplete();
        for(String word: words)
            obj.insert(word);
        System.out.println(Arrays.deepToString(obj.startsWith("Game").toArray()));

    }
}


class TrieNode{
    char c;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf;

    public TrieNode(){}

    public TrieNode(char c){
        this.c = c;
    }

}
