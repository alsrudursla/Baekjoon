import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] dp = new long[N+1][10];
        dp[1][0] = 0;
        for (int i = 1; i <= 9; i++) dp[1][i] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) dp[i][j] = dp[i-1][1] % 1000000000;
                else if (j == 9) dp[i][j] = dp[i-1][8] % 1000000000;
                else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + dp[N][i]) % 1000000000;
        }

        System.out.println(answer);
        sc.close();
    }
}

/*
1 2 3 4 5 6 7 8 9
뒤에 0이 붙는 경우 : 앞이 1일 때
뒤에 1이 붙는 경우 : 앞이 0, 2일 때
뒤에 2가 붙는 경우 : 앞이 1, 3일 때
뒤에 3이 붙는 경우 : 앞이 2, 4일 때
뒤에 4가 붙는 경우 : 앞이 3, 5일 때
뒤에 5가 붙는 경우 : 앞이 4, 6일 때
뒤에 6이 붙는 경우 : 앞이 5, 7일 때
뒤에 7이 붙는 경우 : 앞이 6, 8일 때
뒤에 8이 붙는 경우 : 앞이 7, 9일 때
뒤에 9가 붙는 경우 : 앞이 8일 때

dp[i][j] : i 자릿수일 때 j 로 끝나는 수의 개수
dp[1][0] = 0
dp[1][1 .. 9] = 1

dp[2][0] = dp[1][1] : 자릿수가 하나 적고, 앞자리가 1로 끝난 경우에만 0이 붙는다
dp[2][1] = dp[1][0] + dp[1][2];
*/