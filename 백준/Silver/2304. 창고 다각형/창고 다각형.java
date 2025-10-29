import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        List<int[]> boxList = new ArrayList<>();
        int highest = 0;
        for (int i = 0; i < testcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            boxList.add(new int[]{pos, height});
            highest = Math.max(highest, height);
        }

        Collections.sort(boxList, (o1, o2) -> o1[0] - o2[0]); // 위치 오름차순

        // 제일 큰 기둥 기준 왼쪽 + 제일 큰 기둥(여러 개 가능) 부분 + 기둥 기준 오른쪽
        int answer = 0;

        // 왼쪽
        int beforePos = 0;
        int beforeHeight = 0;
        int startIdx = 0;
        for (int i = 0; i < boxList.size(); i++) {
            int nowPos = boxList.get(i)[0];
            int nowHeight = boxList.get(i)[1];
            if (i == 0) {
                beforePos = nowPos;
                beforeHeight = nowHeight;
            } else if (nowHeight > beforeHeight) {
                answer += ((nowPos - beforePos) * beforeHeight);
                beforePos = nowPos;
                beforeHeight = nowHeight;
            }
            
            if (nowHeight == highest) {
                startIdx = i;
                break;
            }
        }

        // 중간
        int endIdx = -1;
        for (int i = startIdx + 1; i < boxList.size(); i++) {
            if (boxList.get(i)[1] == highest) endIdx = i;
        }
        if (endIdx == -1) answer += (1 * highest);
        else answer += ((boxList.get(endIdx)[0]+1 - boxList.get(startIdx)[0]) * highest);

        // 오른쪽
        for (int i = boxList.size()-1; i >= 0; i--) {
            int nowPos = boxList.get(i)[0]+1;
            int nowHeight = boxList.get(i)[1];
            if (i == boxList.size()-1) {
                beforePos = nowPos;
                beforeHeight = nowHeight;
            } else if (nowHeight > beforeHeight) {
                answer += ((beforePos - nowPos) * beforeHeight);
                beforePos = nowPos;
                beforeHeight = nowHeight;
            }
            
            if (nowHeight == highest) break;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}