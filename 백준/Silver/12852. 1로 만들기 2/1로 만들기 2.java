import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        /*
        dp[4] = 4 / 2 -> dp[2] : 1 + dp[2]
        dp[5] = 5 - 1 -> dp[4]
        dp[6] = 6 / 2 -> dp[3]
        dp[7] = 7 - 1 -> dp[6]
        dp[8] = 8 / 2 -> dp[4]
        dp[9] = 9 / 3 -> dp[3]
        dp[10] = 10 5 4 2 1 / 10 9 3 1
        ...
        2 or 3배수일 때, 배수아닐 때
         */

        ArrayList<Integer>[] arr = new ArrayList[1000001];
        for (int i = 1; i < 1000001; i++) arr[i] = new ArrayList<>();
        arr[1].add(1);
        arr[2].add(2);
        arr[2].add(1);
        arr[3].add(3);
        arr[3].add(1);

        for (int i = 4; i < 1000001; i++) {
            arr[i].add(i);

            int tmp = Integer.MAX_VALUE;
            int chk = 0;
            if (i % 3 == 0)  {
                if (tmp > 1 + dp[i/3]) chk = 0;
                tmp = Math.min(tmp, 1 + dp[i/3]);
            }
            if (i % 2 == 0) {
                if (tmp > 1 + dp[i/2]) chk = 1;
                tmp = Math.min(tmp, 1 + dp[i/2]);
            }
            if (tmp > 1 + dp[i-1]) chk = 2;
            tmp = Math.min(tmp, 1 + dp[i-1]);

            dp[i] = tmp;

            if (chk == 0) for (int j = 0; j < arr[i/3].size(); j++) arr[i].add(arr[i/3].get(j));
            else if (chk == 1) for (int j = 0; j < arr[i/2].size(); j++) arr[i].add(arr[i/2].get(j));
            else for (int j = 0; j < arr[i-1].size(); j++) arr[i].add(arr[i-1].get(j));
        }

        bw.write(String.valueOf(dp[N]));
        bw.newLine();

        for (int i = 0; i < arr[N].size(); i++) bw.write(arr[N].get(i) + " ");
        bw.flush();
        bw.close();
    }
}