import java.io.*;
import java.util.*;
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() != 1) {
            queue.poll();
            int front = queue.peek();
            queue.add(front);
            queue.poll();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(queue.peek()));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
