import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N]; // i번째 원소를 포함하는 연속 부분합의 최대값

        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0] = Integer.parseInt(st.nextToken());
        int answer = dp[0];
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(num + dp[i-1], num);
            answer = Math.max(answer, dp[i]);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}