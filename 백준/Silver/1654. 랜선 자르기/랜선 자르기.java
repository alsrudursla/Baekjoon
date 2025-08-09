import java.util.*;
import java.io.*;

// 만들 수 있는 최대 랜선의 길이를 구하는 프로그램
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int have = Integer.parseInt(st.nextToken()); // 1~10^4
        int need = Integer.parseInt(st.nextToken()); // 1~10^6

        // 주어진 랜선 길이 저장 & 가장 긴 길이 랜선 찾기
        int[] line = new int[have];
        int longest = Integer.MIN_VALUE;
        for (int i = 0; i < have; i++) {
            // 1~2^31-1
            int now = Integer.parseInt(br.readLine());
            longest = Math.max(longest, now);
            line[i] = now;
        }

        // 가장 긴 길이의 랜선으로 이분 탐색 & 개수 비교하기
        long start = 1;
        long end = longest;
        long ans = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            int cnt = 0;
            for (int i = 0; i < line.length; i++) {
                cnt += line[i] / mid;
            }

            if (cnt >= need) { // 필요한 랜선 수 충족 -> 길이를 늘려본다
                start = mid+1;
                ans = mid;
            } else { // 필요한 랜선 수 보다 적게 나옴 -> 길이를 줄인다
                end = mid-1;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}