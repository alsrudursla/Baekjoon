import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int Rx, Ry, Bx, By;
    static int Ox, Oy;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                map[i][j] = input.charAt(j);

                if (input.charAt(j) == 'R') {
                    Rx = j;
                    Ry = i;
                }
                if (input.charAt(j) == 'B') {
                    Bx = j;
                    By = i;
                }
                if (input.charAt(j) == 'O') {
                    Ox = j;
                    Oy = i;
                }
            }
        }

        bfs();

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{Ry, Rx, By, Bx, 0});
        visited[Ry][Rx][By][Bx] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();

            int nowR_y = now[0];
            int nowR_x = now[1];
            int nowB_y = now[2];
            int nowB_x = now[3];
            int now_cnt = now[4];

            if (now_cnt >= 10) return;

            for (int k = 0; k < 4; k++) {
                int nextR_y = nowR_y;
                int nextR_x = nowR_x;
                int nextB_y = nowB_y;
                int nextB_x = nowB_x;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬
                while (map[nextR_y + dy[k]][nextR_x + dx[k]] != '#') {
                    nextR_y += dy[k];
                    nextR_x += dx[k];

                    if (nextR_y == Oy && nextR_x == Ox) {
                        isRedHole = true;
                        break;
                    }
                }

                // 파란구슬
                while (map[nextB_y + dy[k]][nextB_x + dx[k]] != '#') {
                    nextB_y += dy[k];
                    nextB_x += dx[k];

                    if (nextB_y == Oy && nextB_x == Ox) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) continue;
                if (isRedHole) {
                    now_cnt++;
                    ans = (ans == -1) ? now_cnt : Math.min(ans, now_cnt);
                    return;
                }

                // 두 구슬이 같은 위치에 있을 경우
                if (nextR_y == nextB_y && nextR_x == nextB_x) {

                    // 얼마나 떨어져 있었는지 거리 비교
                    int red_distance = Math.abs(nextR_x - nowR_x) + Math.abs(nextR_y - nowR_y);
                    int blue_distance = Math.abs(nextB_x - nowB_x) + Math.abs(nextB_y - nowB_y);

                    if (red_distance > blue_distance) {
                        nextR_x -= dx[k];
                        nextR_y -= dy[k];
                    } else {
                        nextB_x -= dx[k];
                        nextB_y -= dy[k];
                    }
                }

                if (!visited[nextR_y][nextR_x][nextB_y][nextB_x]) {
                    visited[nextR_y][nextR_x][nextB_y][nextB_x] = true;
                    myqueue.add(new int[]{nextR_y, nextR_x, nextB_y, nextB_x, now_cnt+1});
                }
            }
        }
    }
}