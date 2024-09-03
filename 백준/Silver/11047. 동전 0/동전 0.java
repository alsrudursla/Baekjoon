import java.io.*;
import java.util.*;

// 그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동전 종류 개수
        int K = Integer.parseInt(st.nextToken()); // 총 합

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        for (int i = N-1; i >= 0; i--) {
            if (coins[i] > K) {
                continue;
            }
            int num = K / coins[i];
            ans += num;

            K = K - coins[i] * num;
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}