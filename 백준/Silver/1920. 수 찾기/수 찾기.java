import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
            int num = Integer.parseInt(st.nextToken());

            int start_idx = 0;
            int end_idx = A.length - 1;
            boolean find = false;
            while (start_idx <= end_idx) {
                int mid_idx = (start_idx + end_idx)/2;
                int mid_val = A[mid_idx];
                if (mid_val > num) {
                    end_idx = mid_idx - 1;
                } else if (mid_val < num) {
                    start_idx = mid_idx + 1;
                } else {
                    find = true;
                    break;
                }
            }

            if (find) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}