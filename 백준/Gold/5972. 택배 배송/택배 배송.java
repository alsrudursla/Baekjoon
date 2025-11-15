import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCnt = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] graph = new ArrayList[nodeCnt+1];
        for (int i = 1; i <= nodeCnt; i++) graph[i] = new ArrayList<>();

        for (int e = 0; e < edgeCnt; e++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());
            graph[node1].add(new int[]{node2, cow});
            graph[node2].add(new int[]{node1, cow});
        }

        int[] dist = new int[nodeCnt+1];
        for (int i = 1; i < dist.length; i++) dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]);
        pq.add(1);

        while (!pq.isEmpty()) {
            int now = pq.poll();

            for (int[] next : graph[now]) {
                int nextNode = next[0];
                int weight = next[1];
                if (dist[nextNode] > dist[now] + weight) {
                    dist[nextNode] = dist[now] + weight;
                    pq.add(nextNode);
                }
            }
        }

        bw.write(String.valueOf(dist[nodeCnt]));
        bw.flush();
        bw.close();
    }
}