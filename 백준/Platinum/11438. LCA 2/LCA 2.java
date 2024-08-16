import java.io.*;
import java.util.*;

import static java.lang.Math.pow;

// LCA 빠르게 구하기
public class Main {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[][] parent;
    static boolean[] visited;
    static int kmax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for (int i = 0; i < tree.length; i++) { // 트리 생성
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N-1; i++) { // 트리에 입력값 넣기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        // 부모 노드[0], 깊이 저장
        depth = new int[N+1];
        parent = new int[21][100001];
        visited = new boolean[N+1];
        BFS(1);

        // 부모 노드 배열 채우기
        int tmp = 1;
        kmax = 0;
        while (tmp <= N) {
            tmp <<= 1;
            kmax++;
        }
        for (int k = 1; k <= kmax; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k-1][parent[k-1][n]];
            }
        }

        // 질의
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            int LCA = executeLCA(node1, node2);
            bw.write(String.valueOf(LCA));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int executeLCA(int node1, int node2) {
        if (depth[node1] < depth[node2]) { // 깊이 node1 > node2 로 고정
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // 깊이 맞추기
        for (int k = kmax; k >= 0; k--) {
            if (pow(2,k) <= depth[node1] - depth[node2]) {
                node1 = parent[k][node1];
            }
        }

        // 최소 공통 조상 구하기
        for (int k = kmax; k >= 0; k--) {
            if (parent[k][node1] != parent[k][node2]) {
                node1 = parent[k][node1];
                node2 = parent[k][node2];
            }
        }

        int LCA = node1;
        if (node1 != node2) {
            LCA = parent[0][LCA];
        }
        return LCA;
    }

    private static void BFS(int node) {
        Queue<Integer> myqueue = new LinkedList<>();
        myqueue.offer(node);
        visited[node] = true;

        int level = 1;
        int now_size = 1;
        int count = 0;

        while (!myqueue.isEmpty()) {
            int now = myqueue.poll();
            for (int next : tree[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    myqueue.offer(next);

                    depth[next] = level;
                    parent[0][next] = now;
                }
            }
            count++;

            if (count == now_size) {
                level++;
                count = 0;
                now_size = myqueue.size();
            }
        }
    }
}