import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N, L, R, ans;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        boolean chk = false;
        while (!chk) {
            int[][] tmp_map = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(tmp_map[i], -1);

            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    visited[i][j] = true;
                    boolean chk2 = chkCountry(i, j); // 이동 여부 확인
                    if (chk2) {
                        move(tmp_map); // 국경선이 열린 값 새로 저장 (다른 나라에 영향 줌) & visited 해제
                        chk = true;
                    } else visited[i][j] = false;
                }
            }

            // map 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmp_map[i][j] == -1) tmp_map[i][j] = map[i][j];
                    map[i][j] = tmp_map[i][j];
                }
            }

            if (chk) { // 인구 이동을 했으면 계속 진행
                ans++;
                chk = false;
            }
            else break; // 아무런 이동이 없었으면 멈추기
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static boolean chkCountry(int i, int j) {
        boolean chk = false;
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];

            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];

                if (0 <= y && y < N && 0 <= x && x < N && !visited[y][x]) {
                    int diff = Math.abs(map[now_y][now_x] - map[y][x]);
                    if (L <= diff && diff <= R) {
                        visited[y][x] = true;
                        myqueue.add(new int[]{y, x});
                        chk = true;
                    }
                }
            }
        }
        return chk;
    }

    private static void move(int[][] tmp_map) {
        // 1. 전체 인구 더하기
        // 2. 같은 수로 만들기 : (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
        int total = 0;
        int country_num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    total += map[i][j];
                    country_num++;
                }
            }
        }

        int new_number = total / country_num;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    tmp_map[i][j] = new_number;
                    visited[i][j] = false;
                }
            }
        }
    }
}