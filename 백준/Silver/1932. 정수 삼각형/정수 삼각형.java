import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());

        int[][] triangle = new int[size][size];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            idx++;
            for (int j = 0; j < idx; j++) {
                int num = Integer.parseInt(st.nextToken());
                triangle[i][j] = num;
            }
        }

        /*
        오른쪽을 선택했을 경우, 왼쪽을 선택했을 경우 DP

        가장자리 왼쪽, 오른쪽 -> 선택지가 하나
        dp[2][0] = dp[1][0] + triangle[2][0];
        dp[2][1] = Math.max(dp[1][0], dp[1][1]) + triangle[2][1];
        dp[2][2] = dp[1][1] + triangle[2][2];
         */

        if (size == 1) bw.write(String.valueOf(triangle[0][0]));
        else {
            int[][] dp = new int[size][size];
            dp[0][0] = triangle[0][0];
            dp[1][0] = triangle[1][0] + dp[0][0];
            dp[1][1] = triangle[1][1] + dp[0][0];

            idx = 2;
            for (int i = 2; i < size; i++) {
                idx++;
                for (int j = 0; j < idx; j++) {
                    if (j == 0) dp[i][j] = dp[i-1][0] + triangle[i][j];
                    else if (j == idx-1) dp[i][j] = dp[i-1][idx-2] + triangle[i][j];
                    else dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }

            int ans = Integer.MIN_VALUE;
            for (int j = 0; j < idx; j++) ans = Math.max(ans, dp[size-1][j]);
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        bw.close();
    }
}