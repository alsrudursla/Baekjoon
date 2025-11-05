import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int totalLen = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[totalLen];
        for (int i = 0; i < totalLen; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sidx = 0;
        int longestLen = 0;

        int[] cntArr = new int[100001];
        for (int eidx = 0; eidx < totalLen; eidx++) {
            cntArr[arr[eidx]]++;

            while (cntArr[arr[eidx]] > limit) {
                cntArr[arr[sidx]]--;
                sidx++;
            }

            longestLen = Math.max(longestLen, eidx - sidx + 1);
        }
        
        bw.write(String.valueOf(longestLen));
        bw.flush();
        bw.close();
    }
}