import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        long[][] dp = new long[10001][4];
        // 1 : 1
        // 2 : 1+1, 2
        // 3 : 1+1+1, 1+2, 3
        // 4 : 1+1+1+1, 1+1+2, 1+3, 2+2
        // 5 : 1+1+1+1+1, 1+1+1+2, 1+1+3, 1+2+2, 2+3
        // dp[i][j] : 임의의 수 i 수식이 j로 끝나는 경우의 수
        
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        for (int i = 0; i < testcase; i++) {
            int number = sc.nextInt();
            System.out.println(dp[number][1] +dp[number][2] + dp[number][3]);
        }

        sc.close();
    }
}