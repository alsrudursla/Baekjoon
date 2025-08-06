import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        int[] fibo_cnt_1 = new int[41];
        fibo_cnt_1[0] = 0;
        fibo_cnt_1[1] = 1;

        int[] fibo_cnt_0 = new int[41];
        fibo_cnt_0[0] = 1;
        fibo_cnt_0[1] = 0;

        for (int i = 2; i < 41; i++) {
            fibo_cnt_1[i] = fibo_cnt_1[i-1] + fibo_cnt_1[i-2];
            fibo_cnt_0[i] = fibo_cnt_0[i-1] + fibo_cnt_0[i-2];
        }

        for (int i = 0; i < testcase; i++) {
            int num = Integer.parseInt(br.readLine());
            bw.write(fibo_cnt_0[num] + " " + fibo_cnt_1[num] + "\n");
        }
        bw.flush();
        bw.close();
    }
}