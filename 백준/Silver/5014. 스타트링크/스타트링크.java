import java.util.*;
import java.io.*;
public class Main {
    static int total, now_position, target_position, add_up, add_down;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        total = Integer.parseInt(st.nextToken());
        now_position = Integer.parseInt(st.nextToken());
        target_position = Integer.parseInt(st.nextToken());
        add_up = Integer.parseInt(st.nextToken());
        add_down = Integer.parseInt(st.nextToken());

        int ans = bfs();
        if (ans == -1) {
            bw.write("use the stairs");
        } else {
            bw.write(String.valueOf(ans));
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int bfs() {
        boolean[] visited = new boolean[1000001];
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{now_position, 0}); // 위치, 누른 버튼의 수
        visited[now_position] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int pos = now[0];
            int tmp = now[1];

            if (pos == target_position) {
                return tmp;
            }

            for (int diff : new int[]{add_up, -add_down}) {
                int now_pos = pos + diff;
                if (1 <= now_pos && now_pos <= total && !visited[now_pos]) {
                    visited[now_pos] = true;
                    myqueue.add(new int[]{now_pos, tmp+1});
                }
            }
        }

        return -1;
    }
}