import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int map_size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken()); // 파이어스톰 시행 횟수

        map_size = 1;
        for (int i = 0; i < N; i++) {
            map_size *= 2;
        }

        map = new int[map_size][map_size];
        for (int i = 0; i < map_size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map_size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] rotate_size = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            rotate_size[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            // 1. 부분 격자 나누기
            int rotate_map_size = 1;
            for (int r = 0; r < rotate_size[i]; r++) { // 2^L 격자 크기
                rotate_map_size *= 2;
            }
            // 2. 부분 격자 시계 방향 90도 회전
            rotateMap(rotate_map_size);
            // 3. 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다
            melt();
        }

        int sum = 0;
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) sum += map[i][j];
        }

        int largest_ice = 0;
        visited = new boolean[map_size][map_size];
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map[i][j] == 0) continue;
                if (visited[i][j]) continue;
                visited[i][j] = true;
                int size = bfs(i, j);
                largest_ice = Math.max(size, largest_ice);
            }
        }

        bw.write(sum + "\n" + largest_ice);
        bw.flush();
        bw.close();
    }

    private static int bfs(int i, int j) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});
        int size = 0;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_i = now[0];
            int now_j = now[1];
            size++;

            for (int k = 0; k < 4; k++) {
                int next_i = now_i + dy[k];
                int next_j = now_j + dx[k];

                if (0 <= next_i && next_i < map_size && 0 <= next_j && next_j < map_size && !visited[next_i][next_j]) {
                    if (map[next_i][next_j] != 0) {
                        visited[next_i][next_j] = true;
                        myqueue.add(new int[]{next_i, next_j});
                    }
                }
            }
        }
        return size;
    }

    private static void melt() {
        int[][] tmp = new int[map_size][map_size];
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map[i][j] == 0) continue;
                int adjacent = 0;
                for (int k = 0; k < 4; k++) {
                    int next_i = i + dy[k];
                    int next_j = j + dx[k];

                    if (0 <= next_i && next_i < map_size && 0 <= next_j && next_j < map_size) {
                        if (map[next_i][next_j] > 0) adjacent++;
                    }
                }

                if (adjacent < 3) tmp[i][j] = map[i][j]-1;
                else tmp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    private static void rotateMap(int size) {
        int now_i = 0;
        int now_j = 0; // 0, 0 에서 시작

        int[][] new_map = new int[map_size][map_size];

        while (now_i < map_size) {
            int si = now_i; // 회전하는 기준점
            int sj = now_j;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    new_map[si+j][sj+size-1-i] = map[si+i][sj+j];
                }
            }

            // new_map 을 map 에 옮기기
            for (int i = si; i < si+size; i++) {
                for (int j = sj; j < sj+size; j++) {
                    map[i][j] = new_map[i][j];
                }
            }

            now_j += size;
            if (now_j >= map_size) {
                now_i += size;
                now_j = 0;
            }
        }
    }
}