import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] miro = new int[N+1][M+1];
        boolean[][] visited = new boolean[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            char[] tmp_arr = line.toCharArray();
            for (int j = 1; j <= M; j++) {
                int input = Integer.parseInt(String.valueOf(tmp_arr[j-1]));
                miro[i][j] = input;
            }
        }

        int ans = bfs(miro, visited, N, M);

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int bfs(int[][] miro, boolean[][] visited, int N, int M) {
        Queue<int[]> myqueue = new LinkedList<>(); // y, x, move
        myqueue.add(new int[]{1,1,1});

        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];
            int now_move = now[2];

            if (now_y == N && now_x == M) {
                return now_move;
            }

            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];

                if (1 <= y && y <= N && 1 <= x && x <= M &&
                        miro[y][x] == 1 && !visited[y][x]) {
                    visited[y][x] = true;
                    myqueue.add(new int[]{y,x,now_move+1});
                }
            }
        }

        return 0;
    }
}