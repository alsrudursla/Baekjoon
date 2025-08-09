import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int limitWeight = Integer.parseInt(st.nextToken());

        /*
            dp[w] = 현재 고려 중인 물건까지 넣었을 때, 배낭 무게 제한이 w일 때의 "최대 가치"
        */

        int[] dp = new int[limitWeight+1];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int j = limitWeight; j >= weight; j--) {
                // 안넣었을 때, 넣었을 때
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        bw.write(String.valueOf(dp[limitWeight]));
        bw.flush();
        bw.close();
    }
}