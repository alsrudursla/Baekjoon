import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 우선순위 큐 : 우선순위가 높은 (크기가 작은) 수가 먼저 나온다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
           int first_abs = Math.abs(o1);
           int second_abs = Math.abs(o2);
           if (first_abs == second_abs) { // 절대값이 같을 경우
               return o1 > o2 ? 1 : -1; // 음수인 실제값 o2 의 우선순위가 더 높게 됨
           }
           return first_abs - second_abs; // 음수 출력 시 o1 우선순위가 더 높음, 양수 출력 시 o2 우선순위가 더 높음
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());

            if (M == 0) {
                if (pq.isEmpty()) {
                    bw.write("0");
                    bw.newLine();
                } else {
                    bw.write(String.valueOf(pq.poll()));
                    bw.newLine();
                }
            } else {
                pq.add(M);
            }
        }

        bw.flush();
        bw.close();
    }
}
