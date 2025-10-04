import java.util.*;
class Solution {
    class Node {
        Node left, right;
        int idx, x, y;
        Node(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodeList.add(new Node(i+1, x, y));
        }
        Collections.sort(nodeList, (o1, o2) ->
                        o1.y != o2.y ? o2.y - o1.y : o1.x - o2.x);
        
        Node root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            insert(root, nodeList.get(i));
        }
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        preorder(root, pre);
        postorder(root, post);
        
        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        return answer;
    }
    
    private void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    private void preorder(Node node, List<Integer> nList) {
        if (node == null) return;
        nList.add(node.idx);
        preorder(node.left, nList);
        preorder(node.right, nList);
    }
    
    private void postorder(Node node, List<Integer> nList) {
        if (node == null) return;
        postorder(node.left, nList);
        postorder(node.right, nList);
        nList.add(node.idx);
    }
}