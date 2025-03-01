import java.io.*;
import java.util.*;
public class Main {
    static int N, M, ans;
    static int[][] map;
    static List<int[]> virus;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수

        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // 0은 빈 칸, 1은 벽, 2는 비활성 바이러스의 위치
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        visited = new boolean[virus.size()];

        dfs(0, 0); // 현재 바이러스 개수

        if (ans == Integer.MAX_VALUE) bw.write("-1");
        else bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void dfs(int now_virus, int start) {
        if (now_virus == M) {
            int time = getVirusTime();
            ans = Math.min(ans, time);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(now_virus+1, i+1);
            visited[i] = false;
        }
    }

    private static int getVirusTime() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        int[][] tmp_map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) tmp_map[i][j] = 0; // 빈 칸
                else if (map[i][j] == 1) tmp_map[i][j] = -1; // 벽
                else tmp_map[i][j] = -2; // 바이러스
            }
        }

        boolean[][] map_visited = new boolean[N][N];
        Queue<int[]> myqueue = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) continue;
            myqueue.add(new int[]{virus.get(i)[0], virus.get(i)[1], 0});
            // i, j, 바이러스 퍼진 시간
            map_visited[virus.get(i)[0]][virus.get(i)[1]] = true;
        }

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];
            int now_time = now[2];

            // 다 퍼진 경우 종료
            boolean chk = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmp_map[i][j] == 0) chk = true;
                }
            }
            if (!chk) break;

            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];

                if (0 <= y && y < N && 0 <= x && x < N && !map_visited[y][x]) {
                    if (tmp_map[y][x] == 0 || tmp_map[y][x] == -2) {
                        // 빈 칸 또는 비활성 바이러스
                        map_visited[y][x] = true;
                        myqueue.add(new int[]{y, x, now_time+1});
                        tmp_map[y][x] = now_time+1;
                    }
                }
            }
        }

        // 다 퍼지지 못한 경우 검증
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tmp_map[i][j] == 0) return Integer.MAX_VALUE;
            }
        }

        int time = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                time = Math.max(time, tmp_map[i][j]);
            }
        }

        return time;
    }
}