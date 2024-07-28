import java.io.*;
import java.util.*;

// 최소 신장 트리
// 유니온 파인드
public class Main {
    static int[] unionList;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        // 유니온 파인드 리스트
        unionList = new int[v+1];
        for (int i = 0; i < unionList.length; i++) {
            unionList[i] = i;
        }

        // 에지 리스트로 그래프 표현 (입력값)
        List<Edge> graph = new ArrayList<>();
        for (int i = 0; i < e; i ++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a,b,c));
        }

        // 가중치 기준 오름차순 정렬
        graph.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        // 정답 그래프
        List<Edge> graph_ans = new ArrayList<>();

        // 연결된 에지가 N-1 일 때까지 반복
        int connected_edge = 0;
        for (Edge edge : graph) {
            if (connected_edge == v-1) break;

            // 사이클이 아닐 때만 연결 (유니온 파인드 리스트 대표 노드 확인)
            int node1 = find(edge.node1);
            int node2 = find(edge.node2);
            if (node1 != node2) {
                change_val(node1, node2);
                graph_ans.add(edge);
                connected_edge++;
            }
        }

        // 가중치 합 출력
        int sum = 0;
        for (Edge edge : graph_ans) {
            sum += edge.weight;
        }

        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    // 대표 노드 값 변경
    private static void change_val(int node1, int node2) {
        int node1_val = find(node1);
        int node2_val = find(node2);

        if (node1_val != node2_val) {
            unionList[node2] = node1_val;
        }
    }

    // 대표 노드 값 탐색
    private static int find(int node) {
        if (node == unionList[node]) {
            return node;
        } else {
            return unionList[node] = find(unionList[node]);
        }
    }
}

class Edge {
    int node1;
    int node2;
    int weight;

    public Edge (int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
}