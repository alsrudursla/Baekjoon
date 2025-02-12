import java.io.*;
import java.util.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int ans = bfs(N, M);
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int bfs(int N, int M) {
        boolean[] visited = new boolean[100001];
        int tmp = 0;
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{N, 0}); // 위치, 시간
        visited[N] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int pos = now[0];
            int time = now[1];

            if (pos == M) {
                tmp = time;
                break;
            }

            for (int next : new int[]{pos+1, pos-1, pos*2}) {
                if (0 <= next && next <= 100000 && !visited[next]) {
                    myqueue.add(new int[]{next, time+1});
                    visited[next] = true;
                }
            }
        }

        return tmp;
    }
}