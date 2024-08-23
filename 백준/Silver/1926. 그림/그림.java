import java.io.*;
import java.util.*;

// BFS
public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int N, M;
    static int[][] img;
    static boolean[][] visited;
    static Queue<int[]> myqueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        img = new int[N][M];
        visited = new boolean[N][M];
        myqueue = new LinkedList<>();

        // 그림 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                img[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int img_num = 0;
        int img_max_size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (img[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    img_num++;
                    int img_size = bfs(i, j);
                    if (img_size > img_max_size) {
                        img_max_size = img_size;
                    }
                }
            }
        }

        bw.write(String.valueOf(img_num));
        bw.newLine();
        bw.write(String.valueOf(img_max_size));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int bfs(int i, int j) {
        int now_img_size = 1;
        myqueue.offer(new int[]{i,j});
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];

            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];
                if (0 <= x && x < M && 0 <= y && y < N) {
                    if (img[y][x] == 1 && !visited[y][x]) {
                        visited[y][x] = true;
                        now_img_size++;
                        myqueue.offer(new int[]{y,x});
                    }
                }
            }
        }
        return now_img_size;
    }
}