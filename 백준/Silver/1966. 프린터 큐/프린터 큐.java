import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<int[]> orderQ = new LinkedList<>();
        
        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            pq.clear();
            orderQ.clear();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int importance = Integer.parseInt(st.nextToken());
                pq.add(importance);
                orderQ.add(new int[]{j, importance});
            }

            int ans = 0;
            while (!pq.isEmpty()) {
                ans++;
                int importance = pq.poll();

                boolean chk = false;
                while (!orderQ.isEmpty()) {
                    int[] now = orderQ.poll();
                    int idx = now[0];
                    int imp = now[1];
                    
                    if (importance == imp) {
                        if (idx == M) {
                            chk = true;
                            bw.write(String.valueOf(ans));
                        }
                        break;
                    } else {
                        orderQ.add(now);
                    }
                }
                if (chk) break;
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}