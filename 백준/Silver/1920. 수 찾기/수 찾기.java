import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이진 탐색
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            long number = Long.parseLong(st.nextToken());

            boolean chk = false;
            int start_idx = 0;
            int end_idx = N-1;
            while(start_idx <= end_idx) {
                int mid = (start_idx + end_idx) / 2;
                if (number == nums[mid]) {
                    chk = true;
                    bw.write(String.valueOf(1));
                    bw.newLine();
                    break;
                } else if (number > nums[mid]) {
                    start_idx = mid + 1;
                } else {
                    end_idx = mid - 1;
                }
            }

            if (!chk) {
                bw.write(String.valueOf(0));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}