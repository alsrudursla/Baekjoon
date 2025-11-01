import java.util.*;
import java.io.*;

class Main {
    static int hmove, N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        hmove = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static int bfs() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int[] hdy = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] hdx = {1, 2, 2, 1, -1, -2, -2, -1};
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{0, 0, 0, 0}); // i, j, 움직인 횟수, 말로 움직인 횟수

        boolean[][][] visited = new boolean[N][M][hmove+1];
        visited[0][0][0] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int moveCnt = now[2];
            int moveAsHorse = now[3];

            if (nowY == N-1 && nowX == M-1) return moveCnt;

            if (moveAsHorse < hmove) {
                for (int k = 0; k < 8; k++) {
                    int nextY = nowY + hdy[k];
                    int nextX = nowX + hdx[k];
                    if (chkBoundary(nextY, nextX) && !visited[nextY][nextX][moveAsHorse+1] && map[nextY][nextX] == 0) {
                        visited[nextY][nextX][moveAsHorse+1] = true;
                        myqueue.add(new int[]{nextY, nextX, moveCnt+1, moveAsHorse+1});
                    }
                }
            }

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];
                if (chkBoundary(nextY, nextX) && !visited[nextY][nextX][moveAsHorse] && map[nextY][nextX] == 0) {
                    visited[nextY][nextX][moveAsHorse] = true;
                    myqueue.add(new int[]{nextY, nextX, moveCnt+1, moveAsHorse});
                }
            }
        }

        return -1;
    }

    private static boolean chkBoundary(int i, int j) {
        if (0 <= i && i < N && 0 <= j && j < M) return true;
        else return false;
    }
}