import java.io.*;

// DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        // 1. N=1 : 2x1 1개
        // 2. N=2 : 2x1 2개 or 1x2 2개 → 2가지
        // 3. N=3 : 2x1 3개 or (2x1 1개 & N=2일때경우) → 3가지
        // 점화식 An = An-1 + An-2

        int[] square = new int[1001];
        square[1] = 1;
        square[2] = 2;

        for (int i = 3; i <= N; i++) {
            square[i] = (square[i-1] + square[i-2]) % 10007;
        }

        bw.write(String.valueOf(square[N]));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}