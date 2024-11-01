/*
- 모양에 집착할 필요가 없다. DFS 무작위로 4방향으로 뻗어나가게 두면 그게 테트로미노의 전체 모양이 된다.
- 상하좌우 완전 탐색으로 1,2,3,4번의 형태는 만들 수 있는 것을 확인, 회전한 형태의 경우도 완전 탐색으로 전부 구해진다
- ㅜ 만들어줘야 할 경우 : 탐색의 2번째 칸에서 양쪽으로 하나씩 움직여야 만들 수 있는 형태이기 때문에 상하좌우 탐색으로는 결과를 구할 수 없다
  - 2번째 칸에서 다시 한번 탐색하도록 하는 경우를 추가해주면 구할 수 있다
*/
import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int tmp_max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j, 1, map[i][j]);
                    visited[i][j] = false;
                }
            }
        }

        bw.write(String.valueOf(tmp_max));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void dfs(int y, int x, int depth, int sum) {
        if (depth == 4) {
            tmp_max = Math.max(sum, tmp_max);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nextY = y + dy[k];
            int nextX = x + dx[k];

            if (0 <= nextY && 0 <= nextX && nextY < N && nextX < M) {
                if (!visited[nextY][nextX]) {

                    if (depth == 2) {
                        visited[nextY][nextX] = true;
                        dfs(y, x, depth+1, sum+map[nextY][nextX]);
                        visited[nextY][nextX] = false;
                    }

                    visited[nextY][nextX] = true;
                    dfs(nextY, nextX, depth+1, sum+map[nextY][nextX]);
                    visited[nextY][nextX] = false;
                }
            }
        }
    }
}