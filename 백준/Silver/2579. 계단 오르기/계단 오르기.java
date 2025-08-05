import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N+1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        /*
        첫 번째 계단까지의 최댓값
        두 번째 계단까지의 최댓값
        세 번째 계단까지의 최댓값
        ...
        마지막 계단까지의 최댓값

        arr[1] = stairs[1]
        arr[2] = stairs[1] + stairs[2]
        arr[3] = Math.max(stairs[1], stairs[2]) + stairs[3]
        arr[4] = 1 2 4 / 1 3 4
            -> arr[3] + stairs[4]
        arr[5] = 1 2 4 5 / 1 3 5 / 2 3 5
            -> arr[2] + stairs[4] + stairs[5] or arr[3] + stairs[5]

        ...

        arr[n] = Math.max(arr[n-3]+stairs[n-1], arr[n-2]) + stairs[n]
         */

        if (N == 1) bw.write(String.valueOf(stairs[1]));
        else if (N == 2) bw.write(String.valueOf(stairs[1] + stairs[2]));
        else if (N == 3) bw.write(String.valueOf(Math.max(stairs[1], stairs[2]) + stairs[3]));
        else {
            int[] dp = new int[N+1];
            dp[1] = stairs[1];
            dp[2] = stairs[1] + stairs[2];
            dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];

            for (int i = 4; i <= N; i++) {
                dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
            }

            bw.write(String.valueOf(dp[N]));
        }

        bw.flush();
        bw.close();
    }
}