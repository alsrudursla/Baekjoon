import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int studentCnt = Integer.parseInt(br.readLine());

        int[] predict = new int[studentCnt];
        for (int i = 0; i < studentCnt; i++) {
            predict[i] = Integer.parseInt(br.readLine()) - 1;
        }

        // 정렬로 최적의 매칭이 자동으로 만들어짐
        Arrays.sort(predict);

        long ans = 0;
        for (int i = 0; i < predict.length; i++) {
            ans += (long) Math.abs(i - predict[i]);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}