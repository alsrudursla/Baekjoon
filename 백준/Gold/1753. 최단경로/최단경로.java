import java.io.*;
import java.util.*;

// 다익스트라
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        ArrayList<int[]>[] graph = new ArrayList[V+1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] shortest = new int[V+1];
        for (int i = 0; i < shortest.length; i++) {
            shortest[i] = Integer.MAX_VALUE;
        }
        shortest[K] = 0;

        boolean[] visited = new boolean[V+1];
        PriorityQueue<int[]> myqueue = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        myqueue.offer(new int[]{K,0});

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start_vertex = Integer.parseInt(st.nextToken());
            int end_vertex = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start_vertex].add(new int[]{end_vertex, weight});
        }

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_vertex = now[0];
            int now_weight = now[1];

            if (!visited[now_vertex]) {
                visited[now_vertex] = true;
            }

            for (int[] next : graph[now_vertex]) {
                int next_vertex = next[0];
                int next_weight = next[1];

                int new_weight = now_weight + next_weight;

                if (!visited[next_vertex] && shortest[next_vertex] > new_weight) {
                    shortest[next_vertex] = new_weight;
                    myqueue.offer(new int[]{next_vertex, new_weight});
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