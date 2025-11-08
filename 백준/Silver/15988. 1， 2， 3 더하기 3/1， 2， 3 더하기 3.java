import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        long[] dp = new long[1000001];
        // 1 : 1
        // 2 : 1+1, 2 -> 2
        // 3 : 1+1+1, 1+2(2), 3 -> 4
        // 4 : 1+1+1+1, 1+1+2(3), 1+3(2), 2+2 -> 7
        // 5 : 1+1+1+1+1, 1+1+1+2(4), 1+1+3(3), 1+2+2(3), 2+3(2) -> 13
        // 6 : 1+1+1+1+1+1, 1+1+1+1+2(5), 1+1+2+2(6), 2+2+2, 1+1+1+3(4), 1+2+3(6), 3+3 -> 24
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= 1000000; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
        }

        for (int i = 0; i < testcase; i++) {
            int number = sc.nextInt();
            System.out.println(dp[number]);
        }

        sc.close();
    }
}