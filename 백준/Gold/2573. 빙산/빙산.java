import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        boolean chk = false; // 맵이 모두 0 이면 종료
        while (!chk) {
            // 1. 맞닿은 0 개수 파악
            int[][] tmp_map = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int water = search(i, j);
                    tmp_map[i][j] = water;
                }
            }

            // 2. 맵 업데이트
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] -= tmp_map[i][j];
                    if (map[i][j] < 0) map[i][j] = 0;
                }
            }
            year++;

            // 3. 덩어리 수 확인
            boolean[][] visited = new boolean[N][M];
            int area = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        bfs(i, j, visited);
                        area++;
                    }
                }
            }

            if (area >= 2) break;

            // 4. 다 녹았는지 확인
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) tmp++;
                }
            }
            if (tmp == N * M) {
                year = 0;
                chk = true;
            }
        }

        bw.write(String.valueOf(year));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs(int i, int j, boolean[][] visited) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        while(!myqueue.isEmpty()) {
            int[] now = myqueue.poll();

            for (int k = 0; k < 4; k++) {
                int y = now[0] + dy[k];
                int x = now[1] + dx[k];

                if (0 <= y && y < N && 0 <= x && x < M && !visited[y][x] && map[y][x] != 0) {
                    myqueue.add(new int[]{y, x});
                    visited[y][x] = true;
                }
            }
        }
    }

    private static int search(int i , int j) {
        int zero_area = 0;
        for (int k = 0; k < 4; k++) {
            int y = i + dy[k];
            int x = j + dx[k];

            if (0 <= y && y < N && 0 <= x && x < M) {
                if (map[y][x] == 0) zero_area++;
            }
        }

        return zero_area;
    }
}