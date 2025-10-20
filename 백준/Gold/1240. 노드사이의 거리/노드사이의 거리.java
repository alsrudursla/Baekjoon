import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCnt = Integer.parseInt(st.nextToken());
        int testcase = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] tree = new ArrayList[nodeCnt+1];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();
        
        for (int i = 0; i < nodeCnt-1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            tree[node1].add(new int[]{node2, distance});
            tree[node2].add(new int[]{node1, distance});
        }

        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[nodeCnt+1];
            bw.write(String.valueOf(findNode(tree, visited, node1, node2, 0)));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int findNode(ArrayList<int[]>[] tree, boolean[] visited, int node1, int node2, int distance) {
        if (node1 == node2) return distance;
        visited[node1] = true;
        
        for (int[] node : tree[node1]) {
            int nowNode = node[0];
            int nowDistance = node[1];

            if (!visited[nowNode]) {
                int result = findNode(tree, visited, nowNode, node2, distance + nowDistance);
                if (result != -1) return result;
            }
        }

        return -1;
    }
}