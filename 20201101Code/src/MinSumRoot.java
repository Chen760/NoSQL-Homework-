import MakeLeetCodeClass.TreeNode;


public class MinSumRoot {
    private static int minSum;
    private static TreeNode minRoot;

    public static TreeNode findSubtree(TreeNode root){
        minSum=Integer.MAX_VALUE;
        minRoot=null;
        getSum(root);
        return minRoot;
    }

    private static int getSum(TreeNode root){
        if(root == null){
            return 0;
        }

        int sum = getSum(root.left) +getSum(root.right) +root.val;
        if(sum<minSum){
            minSum=sum;
            minRoot=root;
        }
        return sum;
    }
    public static void main(String[] args){
        String str = "[3,9,20,null,null,15,7]";
        TreeNode node = TreeNode.mkTree(str);
        System.out.println(findSubtree(node));
    }
}
