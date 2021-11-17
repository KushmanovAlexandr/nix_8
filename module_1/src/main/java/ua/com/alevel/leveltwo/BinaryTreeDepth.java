package ua.com.alevel.leveltwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryTreeDepth {

    public void run(BufferedReader reader) throws IOException {
        TreeNode nodeRoot = null;
        String choice;
        System.out.println("********** BinaryTreeDepth **********");
        System.out.print("Please enter array numbers separated by any symbol: ");
        while ((choice = reader.readLine()) != null) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(choice);
            while (matcher.find()) {
                if (nodeRoot == null) {
                    nodeRoot = new TreeNode(Integer.parseInt(matcher.group()));
                    ;
                } else {
                    inputNode(nodeRoot, Integer.parseInt(matcher.group()));
                }
            }
            System.out.println("Binary tree depth = " + deptTree(nodeRoot));
            System.out.println("=========================================");
            System.out.println("If you want try again please press Enter");
            System.out.println("If you want return please enter 0");
            System.out.print("Make your choice: ");
            nodeRoot = null;
            if (reader.readLine().equals("0")) {
                return;
            }
            System.out.print("Please enter array numbers separated by any symbol: ");
        }
    }

    private void inputNode(TreeNode node, int val) {
        if (val < node.val) {
            if (node.left != null) {
                inputNode(node.left, val);
            } else node.left = new TreeNode(val);
        } else if (val > node.val) {
            if (node.right != null) {
                inputNode(node.right, val);
            } else node.right = new TreeNode(val);
        }
    }

    private int deptTree(TreeNode nodeRoot) {
        int left;
        int right;
        if (nodeRoot == null) {
            return 0;
        } else {
            left = deptTree(nodeRoot.left);
            right = deptTree(nodeRoot.right);
            return (left > right ? left : right) + 1;
        }
    }
}
