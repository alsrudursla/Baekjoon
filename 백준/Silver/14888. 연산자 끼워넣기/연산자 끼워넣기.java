import java.io.*;
import java.util.*;
public class Main {
    static int N, ansMax, ansMin;
    static int[] arr, ops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ops = new int[4]; // 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) ops[i] = Integer.parseInt(st.nextToken());

        /*
        - 주어진 수의 순서를 바꾸면 안 된다
        - 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행
        - 나눗셈은 정수 나눗셈으로 몫만 취한다
        - 음수를 양수로 나눌 때는 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다
        => N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램
         */

        ansMax = Integer.MIN_VALUE;
        ansMin = Integer.MAX_VALUE;
        dfs(arr[0], 1); // 계산 결과, arr 인덱스

        bw.write(ansMax + "\n");
        bw.write(ansMin + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int tmp, int idx) {
        if (idx == N) {
            ansMax = Math.max(ansMax, tmp);
            ansMin = Math.min(ansMin, tmp);
            return;
        }

        int now_num = arr[idx];
        for (int j = 0; j < 4; j++) {
            if (ops[j] == 0) continue;
            else {
                ops[j]--;
                if (j == 0) { // 덧셈
                    dfs(tmp + now_num, idx+1);
                } else if (j == 1) { // 뺄셈
                    dfs(tmp - now_num, idx+1);
                } else if (j == 2) { // 곱셈
                    dfs(tmp * now_num, idx+1);
                } else { // 나눗셈
                    if (tmp < 0 && now_num > 0) {
                        int changed = -tmp;
                        int quo = changed / now_num;
                        dfs(-quo, idx+1);
                    } else {
                        dfs(tmp / now_num, idx+1);
                    }
                }
                ops[j]++;
            }
        }
    }
}