import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());

        int answer = bfs(subin, sister);
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static int bfs(int subin, int sister) {
        Queue<int[]> myqueue = new LinkedList<>(); // 위치, 시간
        myqueue.add(new int[]{subin, 0});
        
        boolean[] visited = new boolean[100001];

        int shortestTime = Integer.MAX_VALUE;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowPos = now[0];
            int nowTime = now[1];

            visited[nowPos] = true;

            if (nowPos == sister) {
                shortestTime = Math.min(shortestTime, nowTime);
                continue;
            }

            for (int nextPos : new int[]{nowPos*2, nowPos+1, nowPos-1}) {
                if (0 <= nextPos && nextPos <= 100000 && !visited[nextPos]) {
                    // visited[nextPos] = true; << 여기서 하면 다른 이동 경로가 막힐 수 있음!!
                    if (nextPos == nowPos*2) myqueue.add(new int[]{nextPos, nowTime});
                    else myqueue.add(new int[]{nextPos, nowTime+1});
                }
            }
        }

        return shortestTime;
    }
}