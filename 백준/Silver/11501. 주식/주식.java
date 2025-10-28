import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            int dateCnt = Integer.parseInt(br.readLine());

            int[] price = new int[dateCnt];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < dateCnt; i++) price[i] = Integer.parseInt(st.nextToken());

            int maxPrice = price[dateCnt-1];
            long answer = 0;
            for (int i = dateCnt-2; i >= 0; i--) {
                if (price[i] < maxPrice) answer += (maxPrice - price[i]);
                else if (price[i] > maxPrice) maxPrice = price[i];
            }

            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}