import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken()); // 1~32000
        int information = Integer.parseInt(st.nextToken()); // 1~10^5

        /*
            - 항상 진입차수 0인 문제 중에서 번호가 가장 작은 것을 먼저 선택
            - 선택한 문제의 간선을 제거하고, 그로 인해 indegree가 0이 된 문제를 즉시 pq에 넣음
            - 모든 문제를 한 번씩 출력
        */
        
        ArrayList<Integer>[] graph = new ArrayList[total+1];
        for (int i = 1; i <= total; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[total+1];

        for (int i = 0; i < information; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            indegree[B]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= total; i++) {
            if (indegree[i] == 0) pq.add(i);
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            bw.write(now + " ");

            for (int next : graph[now]) {
                indegree[next]--;
                if (indegree[next] == 0) pq.add(next);
            }
        }
        
        bw.flush();
        bw.close();
    }
}