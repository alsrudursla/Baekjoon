import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 시험장 개수

        int[] students = new int[N]; // 시험장 별 응시자 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); // 총감독관 감시 응시자 수
        int C = Integer.parseInt(st.nextToken()); // 부감독관 감시 응시자 수
        long ans = 0; // 필요한 감독관 수의 최솟값
        // 각각의 시험장에 총감독관은 오직 1명만 있어야 하고, 부감독관은 여러 명 있어도 된다.

        for (int i = 0; i < N; i++) {
            int student = students[i];
            student -= B; // 총감독관 감시
            ans++;

            int supervisor = 0; // 필요한 부감독관
            if (student > 0) {
                if (student <= C) supervisor = 1;
                else {
                    if (student % C == 0) {
                        supervisor = student / C;
                    } else {
                        supervisor = student / C + 1;
                    }
                }
            }
            ans += supervisor;
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}