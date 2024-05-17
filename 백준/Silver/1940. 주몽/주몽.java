import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;
        int start_index = 0;
        int end_index = total - 1;

        while (start_index < end_index) {
            if (arr[start_index] + arr[end_index] > num) {
                end_index--;
            } else if (arr[start_index] + arr[end_index] < num) {
                start_index++;
            } else {
                cnt++;
                start_index++;
                end_index--;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cnt));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
