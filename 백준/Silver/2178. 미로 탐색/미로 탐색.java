import java.io.*;
import java.util.*;
public class Main {
    // 상하좌우 탐색
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static boolean[][] visited; // 방문 체크 배열
    static int[][] miro; // 주어진 배열
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        miro = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                miro[i][j] = Integer.parseInt(line.substring(j, j+1));
            }
        }

        BFS(0,0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(miro[N-1][M-1]));
        bw.flush();
        bw.close();
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) { // 상하좌우 탐색
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                if (x >= 0 && y>= 0 && x < N && y < M) {
                    if (miro[x][y]!=0 && !visited[x][y]) {
                        visited[x][y] = true;
                        miro[x][y] = miro[now[0]][now[1]] + 1;
                        queue.add(new int[] {x, y});
                    }
                }
            }
        }
    }
}