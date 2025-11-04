import java.util.*;
import java.io.*;

class Main {
    static ArrayList<Integer>[] tree;
    static int[] subTreeCnt;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCnt = Integer.parseInt(st.nextToken());
        int rootNum = Integer.parseInt(st.nextToken());
        int queryCnt = Integer.parseInt(st.nextToken());

        tree = new ArrayList[nodeCnt+1];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < nodeCnt-1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        // 서브트리 크기 미리 구하기
        subTreeCnt = new int[nodeCnt+1];
        visited = new boolean[nodeCnt+1];
        visited[rootNum] = true;
        saveSubTreeSize(rootNum);
        
        for (int i = 0; i < queryCnt; i++) {
            int newRoot = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(subTreeCnt[newRoot]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static int saveSubTreeSize(int nowNode) {
        int cnt = 1;
        for (int child : tree[nowNode]) {
            if (visited[child]) continue;
            visited[child] = true;
            cnt += saveSubTreeSize(child);
        }
        subTreeCnt[nowNode] = cnt;
        return subTreeCnt[nowNode];
    }
}