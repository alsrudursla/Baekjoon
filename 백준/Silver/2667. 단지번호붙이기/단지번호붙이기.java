import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[][] house;
    static boolean[][] visited;
    static int N;
    static int house_size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        house = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(String.valueOf(tmp[j]));
            }
        }

        int house_num = 0;
        List<Integer> house_size_list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (house[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    house_num++;
                    house_size = 0;
                    dfs(i, j);
                    house_size_list.add(house_size);
                }
            }
        }

        bw.write(String.valueOf(house_num));
        bw.newLine();
        
        Collections.sort(house_size_list);
        for (int i = 0; i < house_size_list.size(); i++) {
            bw.write(String.valueOf(house_size_list.get(i)));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int i, int j) {
        house_size++;
        for (int k = 0; k < 4; k++) {
            int x = dx[k] + j;
            int y = dy[k] + i;
            if (0 <= x && x < N && 0 <= y && y < N) {
                if (house[y][x] == 1 && !visited[y][x]) {
                    visited[y][x] = true;
                    dfs(y, x);
                }
            }
        }
    }
}