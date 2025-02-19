import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static Queue<int[]> virus;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virus = new LinkedList<>();
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        // 1. 벽 세우기 (3개)
        // 2. 바이러스 퍼뜨리기
        // 3. 안전 영역 크기 구하기
        ans = Integer.MIN_VALUE;
        dfs(0, map);

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void dfs(int wall, int[][] map) {
        if (wall == 3) {
            int area = spreadVirus(map);
            ans = Math.max(ans, area);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wall+1, map);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int spreadVirus(int[][] map) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> tmp_virus = new LinkedList<>(virus); // 바이러스 큐 복사
        int[][] tmp_map = new int[N][M];

        for (int i = 0; i < N; i++) { // 맵 복사
            for (int j = 0; j < M; j++) {
                tmp_map[i][j] = map[i][j];
            }
        }

        while (!tmp_virus.isEmpty()) {
            int[] pos = tmp_virus.poll();
            tmp_map[pos[0]][pos[1]] = 2;

            for (int k = 0; k < 4; k++) {
                int next_y = pos[0] + dy[k];
                int next_x = pos[1] + dx[k];

                if (0 <= next_y && next_y < N && 0 <= next_x && next_x < M && !visited[next_y][next_x]) {
                    if (tmp_map[next_y][next_x] == 0) {
                        visited[next_y][next_x] = true;
                        tmp_virus.add(new int[]{next_y, next_x});
                    }
                }
            }
        }

        int zero_num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp_map[i][j] == 0) zero_num++;
            }
        }
        return zero_num;
    }
}