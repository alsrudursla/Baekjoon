import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int testcase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testcase; t++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll() + "\n");
            } else pq.add(num);
        }

        bw.flush();
        bw.close();
    }
}