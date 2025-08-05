import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        // 1부터 10까지 결과를 저장해놓기
        // 왜냐하면 수가 커질수록 더하는 경우가 반복됨
        int[] dp = makeDpArr();

        // 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수
        // n : 1 ~ 10
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[num]+"\n"));
        }
        bw.flush();
        bw.close();
    }

    private static int[] makeDpArr() {
        int[] dp = new int[11];
        dp[1] = 1; // 1
        dp[2] = 2; // 11, 2
        dp[3] = 4; // 111, 12, 21, 3
        //dp[4] = 7; // (1111, 112, 121, 13), (211, 22), (31)
        //    -> 1+dp[3], 2+dp[2], 3+dp[1]
        //dp[5] = 13; // (11111, 1112, 1121, 113, 1211, 122, 131), (2111, 212, 221, 23), (311, 32)
        //    -> 1+dp[4], 2+dp[3], 3+dp[2]

        for (int i = 4; i <= 10; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        return dp;
    }
}