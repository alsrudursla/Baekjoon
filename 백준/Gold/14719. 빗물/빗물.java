import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        int[] harr = new int[width];
        int highest = 0; // 가장 높은 높이
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < width; i++) {
            harr[i] = Integer.parseInt(st.nextToken());
            highest = Math.max(highest, harr[i]);
        }

        // 가장 높은 높이 개수
        int cnt = 0;
        int shidx = -1;
        int ehidx = -1;
        for (int i = 0; i < width; i++) {
            if (harr[i] == highest) cnt++;
            if (cnt == 1 && shidx == -1) shidx = i;
            if (cnt > 1 && harr[i] == highest) ehidx = i;
        }

        int answer = 0;
        int beforeHighest = harr[0];
        int value = 0;
        // 가장 높은 높이 왼쪽에 고이는 물
        for (int i = 1; i < width; i++) {
            int nowHeight = harr[i];
            if (nowHeight >= beforeHighest) {
                // 이전에 나왔던 가장 긴 블록보다 같거나 긴 경우 물 고임 더해주기
                beforeHighest = nowHeight;
                answer += value;
                value = 0;
            } else {
                // 고이는 물
                value += (beforeHighest - nowHeight);
            }
                
            if (nowHeight == highest) break;
        }

        beforeHighest = harr[width-1];
        value = 0;
        // 가장 높은 높이 오른쪽에 고이는 물 (역방향)
        for (int i = width-2; i >= 0; i--) {
            int nowHeight = harr[i];
            if (nowHeight >= beforeHighest) {
                // 이전에 나왔던 가장 긴 블록보다 같거나 긴 경우 물 고임 더해주기
                beforeHighest = nowHeight;
                answer += value;
                value = 0;
            } else {
                // 고이는 물
                value += (beforeHighest - nowHeight);
            }
                
            if (nowHeight == highest) break;
        }

        if (cnt > 1) {
            // 중간에 고이는 물 더하기
            for (int i = shidx+1; i <= ehidx-1; i++) {
                answer += (highest - harr[i]);
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}