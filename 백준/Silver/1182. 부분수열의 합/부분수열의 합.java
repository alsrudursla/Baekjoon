import java.io.*;
import java.util.*;

class Main {
    static int N, S, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        ans = 0;
        makeSum(0, 0, 0);
        
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void makeSum(int sum, int cnt, int idx) {
        // 이 이후에 더 답이 있을 수도 있어서 찾았다고 return 하면 안 됨!!
        if (sum == S && cnt > 0) {
            ans++;
        }

        for (int i = idx; i < arr.length; i++) {
            makeSum(sum + arr[i], cnt + 1, i + 1);
        }
    }
}