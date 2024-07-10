import java.io.*;
import java.util.*;
// 다익스트라 최단 거리 알고리즘
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start_node = Integer.parseInt(br.readLine());

        ArrayList<int[]>[] graph = new ArrayList[V+1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,w});
        }

        int[] shortest = new int[V+1]; // 최단 거리 배열
        for (int i = 0; i < shortest.length; i++) {
            shortest[i] = Integer.MAX_VALUE;
        }
        shortest[start_node] = 0;

        boolean[] visited = new boolean[V+1]; // 방문 배열

        for (int i = 1; i <= V; i++) {
            int min_value = Integer.MAX_VALUE;
            int min_index = 0;
            for (int j = 1; j <= V ; j++) {
                // 값이 제일 작은 노드 찾기 → 시작 노드 탐색
                if (shortest[j] < min_value && !visited[j]) {
                    min_value = shortest[j];
                    min_index = j;
                }
            }

            visited[min_index] = true;

            for (int[] node : graph[min_index]) {
                if (shortest[min_index] + node[1] < shortest[node[0]]) {
                    shortest[node[0]] = shortest[min_index] + node[1];
                }
            }
        }

        for (int i = 1; i < shortest.length; i++) {
            if (shortest[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
            } else {
                bw.write(String.valueOf(shortest[i]));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }
}