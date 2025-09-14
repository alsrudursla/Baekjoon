import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Stack<Integer> s1; // 기본 줄
        Stack<Integer> s2;
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int tc_num = Integer.parseInt(st.nextToken());
            int total = 0;

            s1 = new Stack<>();
            s2 = new Stack<>();
            for (int j = 0; j < 20; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (j == 0) s1.push(now);
                else {
                    if (s1.peek() < now) s1.push(now);
                    else {
                        while (s1.peek() > now) {
                            // 나보다 큰 키 다 s2 스택에 넣어둠
                            int taller = s1.pop();
                            s2.push(taller);
                            total++;

                            if (s1.isEmpty()) break;
                        }

                        // 다시 나를 넣음
                        s1.push(now);

                        // 스택 값 다시 넣기
                        while (!s2.isEmpty()) s1.push(s2.pop());
                    }
                }
            }

            bw.write(tc_num + " " + total + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}