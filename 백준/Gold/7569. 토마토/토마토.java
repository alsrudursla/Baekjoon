import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int M, N, H;
    static int[][][] tomatoes;
    static boolean[][][] visited;
    static Queue<int[]> myqueue;
    static boolean chk_zero;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        tomatoes = new int[H][N][M];
        visited = new boolean[H][N][M];
        myqueue = new LinkedList<>();
        chk_zero = false;
        ans = 0;

        // M개의 수가 N개 들어옴
        boolean chk = false;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomatoes[k][i][j] = Integer.parseInt(st.nextToken());
                    if (tomatoes[k][i][j] == 0){
                        chk = true;
                    } else if (tomatoes[k][i][j] == 1) {
                        myqueue.offer(new int[]{k, i, j});
                    }
                }
            }
        }

        if (!chk) { // 모두 익은 토마토
            bw.write(String.valueOf(0));
        } else {
            bfs();

            if (chk_zero) { // 안익은 토마토가 있음
                bw.write(String.valueOf(-1));
            } else {
                bw.write(String.valueOf(ans-1));
            }
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_z = now[0];
            int now_y = now[1];
            int now_x = now[2];

            for (int k = 0; k < 6; k++) {
                int z = now_z + dz[k];
                int y = now_y + dy[k];
                int x = now_x + dx[k];

                if (0 <= y && y < N && 0<= x && x < M && 0<= z && z < H) {
                    if (!visited[z][y][x]) {
                        visited[z][y][x] = true;

                        if (tomatoes[z][y][x] == 0) {
                            myqueue.offer(new int[]{z, y, x});
                            tomatoes[z][y][x] = tomatoes[now_z][now_y][now_x]+1;
                        }
                    }
                }
            }
        }

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (tomatoes[z][y][x] > ans) {
                        ans = tomatoes[z][y][x];
                    }
                    if (tomatoes[z][y][x] == 0) {
                        chk_zero = true;
                    }
                }
            }
        }
    }
}