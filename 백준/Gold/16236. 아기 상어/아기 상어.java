import java.io.*;
import java.util.*;

/*
- 우선 순위를 활용한 BFS
https://girawhale.tistory.com/39
 */
public class Main {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N;
    static int[][] space;
    static boolean[][] visited;
    static int shark_y, shark_x;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // 공간 크기 NxN

        space = new int[N][N];
        visited = new boolean[N][N];
        ans = 0;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());

                if (space[i][j] == 9) {
                    shark_x = j;
                    shark_y = i;
                    space[i][j] = 0;
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

        int shark_size = 2;
        int eat = 0; // 먹은 물고기 개수

        while (true) {
            // [ y좌표, x좌표, 이동 거리 ]
            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) ->
                    // 가까운 물고기 → 가장 위에 있는 물고기 → 가장 왼쪽에 있는 물고기
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1])))
            );

            pq.offer(new int[]{shark_y, shark_x, 0});

            boolean fish_chk = false; // 물고기 먹었는지 안먹었는지 확인

            visited = new boolean[N][N]; // 매번 BFS 마다 방문 배열 초기화
            visited[shark_y][shark_x] = true;

            while (!pq.isEmpty()) {
                int[] now_pq = pq.poll();
                int now_y = now_pq[0];
                int now_x = now_pq[1];
                int distance = now_pq[2];

                if (space[now_y][now_x] != 0 && space[now_y][now_x] < shark_size) {
                    space[now_y][now_x] = 0;
                    visited[now_y][now_x] = true;
                    eat++;
                    fish_chk = true;
                    ans += distance;

                    shark_x = now_x;
                    shark_y = now_y;

                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int x = now_x + dx[k];
                    int y = now_y + dy[k];

                    if (0 <= x && x < N && 0 <= y && y < N) {
                        if (space[y][x] <= shark_size && !visited[y][x]) {
                            pq.offer(new int[]{y, x, distance + 1});
                            visited[y][x] = true;
                        }
                    }
                }
            }

            if (!fish_chk) { // 물고기 안먹음
                return;
            }
            if (eat == shark_size) {
                shark_size++;
                eat = 0;
            }
        }
    }
}