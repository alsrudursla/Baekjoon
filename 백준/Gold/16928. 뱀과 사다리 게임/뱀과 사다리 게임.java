import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ladderCnt = Integer.parseInt(st.nextToken());
        int snakeCnt = Integer.parseInt(st.nextToken());

        int[] map = new int[101];

        for (int i = 0; i < ladderCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start] = end;
        }

        for (int i = 0; i < snakeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start] = end;
        }

        int answer = rollTheDice(map);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static int rollTheDice(int[] map) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{1, 0}); // 시작 위치, 주사위 굴린 횟수

        boolean[] visited = new boolean[101];
        visited[1] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowPos = now[0];
            int nowDice = now[1];

            if (nowPos == 100) return nowDice;

            // 도착한 칸이 사다리 혹은 뱀일 때
            if (map[nowPos] != 0) {
                nowPos = map[nowPos];
                visited[nowPos] = true;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPos = nowPos + i;
                if (1 <= nextPos && nextPos <= 100 && !visited[nextPos]) {
                    visited[nextPos] = true;
                    myqueue.add(new int[]{nextPos, nowDice+1});
                }
            }
        }

        return -1;
    }
}