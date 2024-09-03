import java.io.*;
import java.util.*;

// 투 포인터
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 전체 개수
        int K = Integer.parseInt(st.nextToken()); // 연속되는 개수

        int[] days = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            days[i] = Integer.parseInt(st.nextToken());
        }

        int start_index = 0;
        int end_index = start_index + K - 1;
        int ans = -Integer.MAX_VALUE;
        while (end_index < N) {
            int tmp = 0;
            for (int i = start_index; i <= end_index; i++) {
                tmp += days[i];
            }

            if (tmp > ans) {
                ans = tmp;
            }

            start_index++;
            end_index++;
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}