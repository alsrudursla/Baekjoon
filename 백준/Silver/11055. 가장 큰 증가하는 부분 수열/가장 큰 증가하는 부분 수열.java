import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = sc.nextInt();

        int answer = arr[0];
        int[] dp = new int[size]; // i번까지 부분 수열 중 합이 가장 큰
        for (int i = 0; i < size; i++) dp[i] = arr[i]; // 초기화
        // dp[1] = (if arr[0] < arr[1] ? dp[0] + arr[1] : arr[1])
        // dp[2] = (if arr[0 or 1] < arr[2] ? dp[0 or 1] + arr[2] : arr[2])
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                answer = Math.max(answer, dp[i]);
            }
        }

        System.out.println(answer);
    }
}