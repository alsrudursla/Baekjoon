import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 1 ~ 1000000

        /* 1 만들기
        dp[1] = 0
        dp[2] = 1
            2-1 or 2/2
        dp[3] = 1
            3-1-1, (3-1)/2, 3/3 -> 3에서 1 빼고 dp[2], 3에서 3나눔
        dp[4] = 2
            4에서 1 빼고 dp[3] or 4에서 2 나누고 dp[2] (2배수)
        dp[5] = 3
            5에서 1 빼고 dp[4]
        dp[6] = 2
            6에서 1 빼고 dp[5] or 6/2 dp[6/2] or 6/3 dp[6/3] (2, 3배수)
         */
        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= 1000000; i++) {
            int tmp = Integer.MAX_VALUE;
            if (i % 2 == 0) tmp = Math.min(dp[i/2] + 1, tmp);
            if (i % 3 == 0) tmp = Math.min(dp[i/3] + 1, tmp);
            tmp = Math.min(dp[i-1] + 1, tmp);
            dp[i] = tmp;
        }

        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
    }
}