import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] family;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int total_num = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        family = new ArrayList[total_num+1];
        for (int i = 0; i < family.length; i++) {
            family[i] = new ArrayList<>();
        }

        int relation_num = Integer.parseInt(br.readLine());
        for (int i = 0; i < relation_num; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            family[c].add(d);
            family[d].add(c);
        }

        visited = new boolean[total_num+1];

        int ans = bfs(a, b);
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int bfs(int a, int b) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.offer(new int[]{a, 0});

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int member = now[0];
            int cnt = now[1];

            visited[member] = true;

            for (int next : family[member]) {
                if (!visited[next]) {

                    if (next == b) {
                        return cnt + 1;
                    }

                    myqueue.offer(new int[]{next, cnt+1});
                    visited[next] = true;
                }
            }
        }

        return -1;
    }
}