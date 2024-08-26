import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int N;
    static int[][] house;
    static boolean[][] visited;
    static Queue<int[]> myqueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        house = new int[N][N];
        visited = new boolean[N][N];
        myqueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char[] house_tmp = br.readLine().toCharArray();
            for (int j = 0; j < house_tmp.length; j++) {
                house[i][j] = Integer.parseInt(String.valueOf(house_tmp[j]));
            }
        }

        int house_cnt = 0;
        List<Integer> house_size = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (house[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    house_cnt++;
                    house_size.add(bfs(i, j));
                }
            }
        }

        bw.write(String.valueOf(house_cnt));
        bw.newLine();

        Collections.sort(house_size);
        for (int i = 0; i < house_size.size(); i++) {
            bw.write(String.valueOf(house_size.get(i)));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int bfs(int i, int j) {
        int house_size_val = 1;
        myqueue.offer(new int[]{i,j});

        while(!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_x = now[1];
            int now_y = now[0];

            for (int k = 0; k < 4; k++) {
                int x = now_x + dx[k];
                int y = now_y + dy[k];
                if (0 <= x && x < N && 0 <= y && y < N) {
                    if (house[y][x] == 1 && !visited[y][x]) {
                        visited[y][x] = true;
                        house_size_val++;
                        myqueue.offer(new int[]{y,x});
                    }
                }
            }
        }

        return house_size_val;
    }
}