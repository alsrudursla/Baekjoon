import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int switchCnt = Integer.parseInt(br.readLine());
        boolean[] switchArr = new boolean[switchCnt+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= switchCnt; i++) {
            int status = Integer.parseInt(st.nextToken());
            if (status == 1) switchArr[i] = true;
        }

        int studentCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int switchNum = Integer.parseInt(st.nextToken());

            if (sex == 1) {
                // 남학생일 때
                for (int j = switchNum; j <= switchCnt; j += switchNum) switchArr[j] = !switchArr[j];
            } else {
                // 여학생일 때
                // 구간 내 + 양쪽 대칭
                switchArr[switchNum] = !switchArr[switchNum]; // 해당 지점 변경
                int lidx = switchNum - 1;
                int ridx = switchNum + 1;
                while (1 <= lidx && ridx <= switchCnt && switchArr[lidx] == switchArr[ridx]) {
                    switchArr[lidx] = !switchArr[lidx];
                    switchArr[ridx] = !switchArr[ridx];
                    lidx--;
                    ridx++;
                }
            }
        }

        for (int i = 1; i <= switchCnt; i++) {
            boolean val = switchArr[i];
            if (val) bw.write("1 ");
            else bw.write("0 ");

            if (i % 20 == 0) bw.write("\n");
        }
        
        bw.flush();
        bw.close();
    }
}