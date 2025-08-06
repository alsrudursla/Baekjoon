import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        /*
        모든 이전 색 조합 중 최솟값 고려

        dp[i][j] = i번째 집을 j 색상으로 칠할 때의 최소 비용
        dp[i][j] = cost[i][j] + min(dp[i-1][k]) where k ≠ j
         */

        // 비용 저장
        int[][] cost = new int[N+1][4];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // i번째 집을 j 색상으로 칠할 때의 최소 비용
        int[][] dp = new int[N+1][4];
        for (int i = 1; i <= 3; i++) dp[1][i] = cost[1][i];

        for (int i = 2; i <= N; i++) {
            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + cost[i][2];
            dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][3];
        }

        bw.write(String.valueOf(Math.min(Math.min(dp[N][1], dp[N][2]), dp[N][3])));
        bw.flush();
        bw.close();
    }
}