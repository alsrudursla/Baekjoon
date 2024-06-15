import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        arr[0] = 0;
        arr[1] = 0;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (arr[i] == 0) continue;
            for (int j = i * i; j <= N; j = j+i) {
                arr[j] = 0;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = M; i <= N; i++) {
            if (arr[i] != 0) {
                bw.write(String.valueOf(arr[i]));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}