import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int towerCnt = Integer.parseInt(br.readLine());
        int[] height = new int[towerCnt];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < towerCnt; i++) {
            int nowHeight = Integer.parseInt(st.nextToken());
            height[i] = nowHeight;
        }

        int[] answer = new int[towerCnt];

        Stack<int[]> tStack = new Stack<>(); // idx, height
        tStack.push(new int[]{height.length-1, height[height.length-1]});

        int idx = height.length-2;
        while (idx >= 0) {
            int nowHeight = height[idx];

            while (!tStack.isEmpty()) {
                int[] top = tStack.peek();
                if (top[1] < nowHeight) {
                    answer[top[0]] = idx+1;
                    tStack.pop();
                } else break;
            }

            tStack.push(new int[]{idx, nowHeight});
            idx--;
        }
        
        for (int i = 0; i < answer.length; i++) {
            bw.write(answer[i] + " ");
        }
        
        bw.flush();
        bw.close();
    }
}