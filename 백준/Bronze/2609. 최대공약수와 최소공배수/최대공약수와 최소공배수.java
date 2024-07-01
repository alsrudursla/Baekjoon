import java.io.*;
import java.util.*;
public class Main {
    static int gcd_num;
    static int lcm_num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        if (A < B) {
            int tmp = A;
            A = B;
            B = tmp;
        }

        gcd(A, B);
        lcm(A, B);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(gcd_num));
        bw.newLine();
        bw.write(String.valueOf(lcm_num));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void gcd(int A, int B) {
        int mod = A % B;
        if (mod == 0) {
            gcd_num = B;
        }
        else {
            gcd(B, mod);
        }
    }

    private static void lcm(int A, int B) {
        int a = A / gcd_num;
        int b = B / gcd_num;
        lcm_num = gcd_num * a * b;
        // A * B / gcd_num
    }
}