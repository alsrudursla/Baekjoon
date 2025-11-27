import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> height = new Stack<>();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (height.isEmpty()) {
                if (y != 0) {
                    height.push(y);
                    answer++; // 새로 추가 : 건물 + 1
                }
            } else {
                while (!height.isEmpty() && height.peek() > y) {
                    // 현재 높이보다 크면 pop
                    height.pop();
                }

                if (height.isEmpty()) {
                    if (y != 0) {
                        height.push(y);
                        answer++;
                    }
                } else {
                    if (height.peek() != y && y != 0) {
                        height.push(y);
                        answer++;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}