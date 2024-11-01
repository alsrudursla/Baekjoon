import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] myArr = new ArrayList[n+1];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            myArr[a].add(b);
            myArr[b].add(a);
        }
        
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{1, 0}); // [노드 번호, 거리]
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        
        List<int[]> myList = new ArrayList<>();
        int max_tmp = 0;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int node_num = now[0];
            int distance = now[1];
            
            boolean chk = false;
            for (int node : myArr[node_num]) {
                if (!visited[node]) {
                    visited[node] = true;
                    myqueue.add(new int[]{node, distance+1});
                    chk = true;
                }
            }
            
            if (!chk) {
                myList.add(new int[]{node_num, distance});
                max_tmp = Math.max(max_tmp, distance);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i)[1] == max_tmp) {
                answer++;
            }
        }
        return answer;
    }
}