import MakeLeetCodeClass.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllPath {
    public static List<String> binaryTreePaths(TreeNode root){
        List<String> paths = new ArrayList<>();
        if(root==null){
            return paths;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        for(String path : leftPaths){
            paths.add(root.val + "->" + path);
        }
        for(String path : rightPaths){
            paths.add(root.val + "->" + path);
        }

        if(paths.size() ==0){
            paths.add(""+root.val);
        }
        return paths;
    }

    public static void main(String[] args){
        String str = "[3,9,20,null,null,15,7]";
        TreeNode node = TreeNode.mkTree(str);
        Iterator it = binaryTreePaths(node).iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}

