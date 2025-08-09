import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long T = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] Asum = new long[N+1];
        for (int i = 1; i <= N; i++) {
            Asum[i] = Long.parseLong(st.nextToken()) + Asum[i-1];
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] Bsum = new long[M+1];
        for (int i = 1; i <= M; i++) {
            Bsum[i] = Long.parseLong(st.nextToken()) + Bsum[i-1];
        } 

        /*
            Asum + Bsum = T
            Asum = T - Bsum
        */

        Map<Long, Long> value = new HashMap<>(); // T - Bsum
        for (int i = 1; i <= M; i++) value.put(T-Bsum[i], value.getOrDefault(T-Bsum[i], 0L) + 1);
        
        int start = 1;
        int end = start+1;
        while (start <= M-1) {
            long now = Bsum[end] - Bsum[start];
            value.put(T-now, value.getOrDefault(T-now, 0L) + 1);

            end++;
            if (end == M+1) {
                start++;
                end = start+1;
            }
        }

        long ans = 0;

        for (int i = 1; i <= N; i++) {
            if (value.containsKey(Asum[i])) ans += value.get(Asum[i]);
        }
        
        start = 1;
        end = start+1;
        while (start <= N-1) {
            long now = Asum[end] - Asum[start];
            if (value.containsKey(now)) ans += value.get(now);

            end++;
            if (end == N+1) {
                start++;
                end = start+1;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}