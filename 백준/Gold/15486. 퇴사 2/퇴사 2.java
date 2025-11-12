import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int dateCnt = Integer.parseInt(br.readLine());

        List<int[]> reservationList = new ArrayList<>(); // 소요시간, 수익
        for (int i = 0; i < dateCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            reservationList.add(new int[]{time, price});
        }

        int[] dp = new int[dateCnt+2]; // i 날까지 얻을 수 있는 최대 수익

        for (int i = 1; i <= dateCnt; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            
            // 현재 상담을 했을 때
            int nextDate = i + reservationList.get(i-1)[0];
            int nextPrice = reservationList.get(i-1)[1];
            if (nextDate-1 <= dateCnt && dp[i] + nextPrice > dp[nextDate]) {
                dp[nextDate] = dp[i] + nextPrice;
            }
        }

        dp[dateCnt+1] = Math.max(dp[dateCnt], dp[dateCnt+1]);
        bw.write(String.valueOf(dp[dateCnt+1]));
        bw.flush();
        bw.close();
    }
}