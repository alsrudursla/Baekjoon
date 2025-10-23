import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static Queue<int[]> myqueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        myqueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) myqueue.add(new int[]{i, j});
            }
        }

        boolean changed = true;
        int date = 0;
        while (changed) {
            // 1. 토마토가 모두 익었는지 확인
            if (chkTomatoes()) break;
            // 2. 주변 토마토 영향
            changed = bfs();
            // 3. 변경 없을 시 종료, 있을 시 날짜 + 1
            if (!changed) break;
            else date++;
        }

        // 토마토가 모두 익지 않았으면 -1, 아니면 날짜 출력
        if (!chkTomatoes()) bw.write("-1");
        else bw.write(String.valueOf(date));
        bw.flush();
        bw.close();
    }

    private static boolean bfs() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        int qSize = myqueue.size();
        
        boolean chk = false;
        for (int i = 0; i < qSize; i++) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (map[nextY][nextX] == 0) {
                        map[nextY][nextX] = 1;
                        myqueue.add(new int[]{nextY, nextX});
                        chk = true;
                    }
                }
            }
        }

        return chk;
    }

    private static boolean chkTomatoes() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }
}