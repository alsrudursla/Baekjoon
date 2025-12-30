import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int drinkCnt = Integer.parseInt(br.readLine());
        
        int[] amount = new int[drinkCnt+1];
        for (int i = 1; i <= drinkCnt; i++) amount[i] = Integer.parseInt(br.readLine());

        // dp[i][j] : i번째 포도주를 j번 연속 마셨을 때 최대 양
        int[][] dp = new int[drinkCnt+1][3];
        for (int j = 0; j < 3; j++) dp[0][j] = 0;

        for (int i = 1; i <= drinkCnt; i++) {
            // j == 0 지금 안마심 <- 이전까지 몇 번 연속이든 상관없음
            int beforeMax = 0;
            for (int j = 0; j < 3; j++) beforeMax = Math.max(beforeMax, dp[i-1][j]);
            dp[i][0] = beforeMax;

            // j == 1/2 마심 <- 이전에도 마심
            dp[i][1] = dp[i-1][0] + amount[i];
            dp[i][2] = dp[i-1][1] + amount[i];
        }

        int maxAmount = 0;
        for (int j = 0; j < 3; j++) maxAmount = Math.max(maxAmount, dp[drinkCnt][j]);

        bw.write(String.valueOf(maxAmount));
        bw.flush();
        bw.close();
    }
}