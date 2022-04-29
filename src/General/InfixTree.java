package General;

public class InfixTree {


/*    +
    -,+
    6,3,5,4

1

+ ->
7 -
 45

    */

    public static int infixResult(TreeNode root){
        if(root.left==null && root.right==null)
            return root.val;

        int left = infixResult(root.left);
        int right = infixResult(root.right);
        System.out.println(left + " : "+ right);

        if(root.val == Integer.MAX_VALUE)
            return left + (right);
        else if(root.val == Integer.MIN_VALUE){
            if(right<0){
                right = -right;
                return left+right;
            }
            else
                return left-right;
        }
        else
            return 0;

    }

    public static void main(String [] args){
        TreeNode node1 = new TreeNode(Integer.MAX_VALUE);
        TreeNode node2 = new TreeNode(Integer.MIN_VALUE);
        TreeNode node3 = new TreeNode(1);

        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(Integer.MIN_VALUE);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node6;
        node5.right = node7;

        System.out.println(infixResult(node1));
    }
}


class TreeNode{
    TreeNode node;
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val){
        this.val = val;
        left = right = null;
    }
}
