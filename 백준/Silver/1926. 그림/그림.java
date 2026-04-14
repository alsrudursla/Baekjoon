import java.util.*;
import java.io.*;

class Main {
    static int height, width;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        
        map = new int[height][width];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[height][width];
        int picCnt = 0;
        int picMax = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                visited[i][j] = true;
                picCnt++;
                int size = findSize(i, j);
                picMax = Math.max(picMax, size);
            }
        }

        bw.write(String.valueOf(picCnt) + '\n');
        bw.write(String.valueOf(picMax));
        bw.flush();
        bw.close();
    }

    private static int findSize(int i, int j) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        int size = 1;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && 0 <= nextX && nextY < height && nextX < width) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] == 1) {
                        visited[nextY][nextX] = true;
                        size++;
                        myqueue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }

        return size;
    }
}