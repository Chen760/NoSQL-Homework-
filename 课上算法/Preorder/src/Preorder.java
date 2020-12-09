import java.util.ArrayList;



public class Preorder {
    public static ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
    public static void main(String[] args){
        String str = "[3,15,5,13,null,12,5]";//中序遍历
        TreeNode node = TreeNode.mkTree(str);
        System.out.println("Preorder: "+preorderTraversal(node));
    }
}
