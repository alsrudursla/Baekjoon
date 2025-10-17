import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int spaceNum = Integer.parseInt(br.readLine());
        
        int[] peopleNum = new int[spaceNum];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < spaceNum; i++) {
            peopleNum[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int mainCnt = Integer.parseInt(st.nextToken());
        int subCnt = Integer.parseInt(st.nextToken());

        long answer = 0L;
        for (int i = 0; i < spaceNum; i++) {
            int rest = peopleNum[i] - mainCnt;
            answer++;
            
            if (rest <= 0) continue; // 총감독관으로 충분
            else {
                int quo = rest / subCnt;
                int remain = rest % subCnt;

                if (remain != 0) {
                    answer += (quo + 1);
                } else answer += quo;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}