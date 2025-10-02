import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalDate = Integer.parseInt(st.nextToken());
        int duration = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] dateArr = new long[totalDate];
        for (int i = 0; i < totalDate; i++) {
            dateArr[i] = Long.parseLong(st.nextToken());
        }

        long ans = 0;
        long[] cntArr = new long[totalDate];
        // 처음
        for (int i = 0; i < duration; i++) ans += dateArr[i];
        cntArr[0] = ans;

        // 나머지
        long sum = ans;
        for (int i = 1; i <= totalDate - duration; i++) {
            sum -= dateArr[i-1];
            sum += dateArr[i+duration-1];
            cntArr[i] = sum;
            if (sum > ans) ans = sum;
        }

        if (ans == 0) bw.write(String.valueOf("SAD"));
        else {
            bw.write(String.valueOf(ans));
            int tmp = 0;
            for (int i = 0; i < cntArr.length; i++) {
                if (cntArr[i] == ans) tmp++;
            }
            bw.newLine();
            bw.write(String.valueOf(tmp));
        }
        bw.flush();
        bw.close();
    }
}