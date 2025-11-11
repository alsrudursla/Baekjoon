import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = sc.nextInt();

        int answer = 1;
        int[] dp = new int[size]; // i번까지 부분 수열 중 길이가 가장 긴 값
        for (int i = 0; i < size; i++) dp[i] = 1; // 초기화
        // dp[1] = (if arr[0] < arr[1] ? dp[0] + 1 : 1)
        // dp[2] = (if arr[0/1] < arr[2] ? dp[0/1] + 1 : 1)
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[j] + 1, dp[i]);
                answer = Math.max(answer, dp[i]);
            }
        }

        System.out.println(answer);
    }
}