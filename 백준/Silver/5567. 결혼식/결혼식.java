import java.util.*;
import java.io.*;

class Main {
    static int friends;
    static ArrayList<Integer>[] fgraph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        friends = Integer.parseInt(br.readLine()); // 2~500
        int list_len = Integer.parseInt(br.readLine()); // 1~10^4

        fgraph = new ArrayList[friends+1];
        for (int i = 1; i <= friends; i++) fgraph[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < list_len; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            fgraph[A].add(B);
            fgraph[B].add(A);
        }

        // BFS 로 친구의 친구까지만 탐색할 수 있도록 하기
        int ans = bfs(1);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static int bfs(int start) {
        Queue<int[]> myqueue = new LinkedList<>(); // [번호, 깊이]
        myqueue.add(new int[]{start, 0});

        boolean[] visited = new boolean[friends+1];
        visited[start] = true;

        int ans = 0;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int number = now[0];
            int depth = now[1];

            if (depth == 2) continue; // 친구의 친구까지만 탐색

            for (int next : fgraph[number]) {
                if (visited[next]) continue;
                visited[next] = true;
                myqueue.add(new int[]{next, depth+1});
                ans++;
            }
        }

        return ans;
    }
}