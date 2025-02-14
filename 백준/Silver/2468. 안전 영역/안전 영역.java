import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        List<Integer> height_list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (!height_list.contains(map[i][j])) {
                    height_list.add(map[i][j]);
                }
            }
        }

        int area = 1;
        for (int tc : height_list) {
            // 1. 물에 잠긴 영역 표시
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= tc) visited[i][j] = true;
                }
            }

            // 2. 잠기지 않은 영역 개수 확인
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        tmp++;
                        bfs(visited, i ,j);
                    }
                }
            }

            // 3. 최대 개수 비교
            area = Math.max(area, tmp);
        }

        bw.write(String.valueOf(area));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs(boolean[][] visited, int i, int j) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];

            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];

                if (0 <= y && y <= N-1 && 0 <= x && x <= N-1 && !visited[y][x]) {
                    visited[y][x] = true;
                    myqueue.add(new int[]{y, x});
                }
            }
        }
    }
}