import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }

        int tmp = 0;
        int answer = 0;
        while (!pq.isEmpty()) {
            int now = pq.poll();
            tmp++;
            if (tmp == N) {
                answer = now;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}