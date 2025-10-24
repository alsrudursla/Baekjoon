import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3];
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) dp[0][j][d] = map[0][j];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 3; d++) dp[i][j][d] = Integer.MAX_VALUE;
            }
        }

        int[] dy = {1, 1, 1};
        int[] dx = {-1, 0, 1};
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 3; d++) { // 현재 이동 방향 : 단순 값 저장
                    int beforeY = i-1;
                    int beforeX = j+dx[d];
                    if (beforeX < 0 || beforeX >= M) continue;

                    for (int k = 0; k < 3; k++) { // 이전 이동 방향
                        if (k == d) continue;
                        if (dp[beforeY][beforeX][k] == Integer.MAX_VALUE) continue;
                        dp[i][j][d] = Math.min(dp[i][j][d], dp[beforeY][beforeX][k] + map[i][j]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) answer = Math.min(answer, dp[N-1][j][d]);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}