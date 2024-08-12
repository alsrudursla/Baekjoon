import java.io.*;
import java.util.*;

// 최소 공통 조상 (LCA)
public class Main {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 노드 개수

        tree = new ArrayList[N+1]; // 트리 생성
        for (int i = 0; i < tree.length; i++) { // 인접 리스트로 표현 준비
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N-1; i++) { // 트리 입력값 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        // 부모 노드, 깊이 저장
        depth = new int[N+1];
        parent = new int[N+1];
        visited = new boolean[N+1];
        BFS(1);

        int M = Integer.parseInt(br.readLine()); // LCA 알고 싶은 쌍의 개수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int lca = executeLCA(node1, node2);

            bw.write(String.valueOf(lca));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int executeLCA(int node1, int node2) {
        // node1 깊이가 더 깊은 걸로 고정
        if (depth[node1] < depth[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // 노드 두 개 같은 깊이로 맞추기 (깊이가 더 깊은 node1 을 움직임)
        while (depth[node1] != depth[node2]) {
            node1 = parent[node1];
        }

        // 같은 조상이 나올 때까지 노드 두 개 움직이기
        while (node1 != node2) {
            node1 = parent[node1];
            node2 = parent[node2];
        }

        return node1;
    }

    private static void BFS(int node) {
        Queue<Integer> myqueue = new LinkedList<>();
        myqueue.offer(node);
        visited[node] = true;

        int level = 1;
        int now_size = 1; // 현재 depth(level) 크기
        int count = 0; // 현재 depth 에서 몇 개의 노드를 처리했는지

        while (!myqueue.isEmpty()) {
            int now = myqueue.poll();
            for (int next : tree[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    myqueue.offer(next);

                    parent[next] = now; // 부모 노드 저장
                    depth[next] = level; // 깊이 저장
                }
            }
            count++;
            if (count == now_size) {
                count = 0;
                now_size = myqueue.size();
                level++;
            }
        }
    }
}