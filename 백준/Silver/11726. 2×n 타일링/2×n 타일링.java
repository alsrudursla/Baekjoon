import java.io.*;

// 동적 계획법
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[1001];

        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= n; i++) {
            arr[i] = (arr[i-1] + arr[i-2])%10007;
        }

        bw.write(String.valueOf(arr[n]));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}