import java.util.*;
import java.io.*;

// 조합
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // nCr = n! / (n-r)!r!
        int a = calculate(N); // n! 구하기
        int b = calculate(N-M); // (n-r)! 구하기
        int c = calculate(M); // r! 구하기

        bw.write(String.valueOf(a / (b*c)));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static int calculate(int num) {
        if (num == 0) {
            return 1;
        }
        return num * calculate(num - 1);
    }
}