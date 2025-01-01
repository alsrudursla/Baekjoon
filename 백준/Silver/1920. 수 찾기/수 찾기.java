import java.io.*;
import java.util.*;

public class Main {
    static int[] A;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            A[i] = input;
        }
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int ans = findNumber(num, 0, N-1);
            bw.write(String.valueOf(ans));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static int findNumber(int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                return 1;
            } else if (A[mid] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return 0;
    }
}