import java.util.*;
import java.io.*;

class Main {
    static int N, M, limit;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i+1][j+1] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        int answer = findPath();
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static int findPath() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        // i, j, pathCnt, 벽 부순 횟수, 낮(0)/밤(1)
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{1, 1, 1, 0, 0});

        boolean[][][] visited = new boolean[N+1][M+1][limit+1];
        visited[1][1][0] = true;

        int answer = -1;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowDist = now[2];
            int nowLimit = now[3];
            int nowDayOrNight = now[4];

            int afterDayOrNight = -1;
            if (nowDayOrNight == 0) afterDayOrNight = 1;
            else afterDayOrNight = 0;

            if (nowY == N && nowX == M) {
                answer = nowDist;
                break;
            }

            if (nowDayOrNight == 1) myqueue.add(new int[]{nowY, nowX, nowDist+1, nowLimit, afterDayOrNight});

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (1 <= nextY && nextY <= N && 1 <= nextX && nextX <= M) {
                    if (map[nextY][nextX] == 1 && nowDayOrNight == 0) { // 벽일 때 + 낮일 때
                        if (nowLimit < limit && !visited[nextY][nextX][nowLimit+1]) {
                            visited[nextY][nextX][nowLimit+1] = true;
                            myqueue.add(new int[]{nextY, nextX, nowDist+1, nowLimit+1, afterDayOrNight});
                        }
                    } else if (map[nextY][nextX] == 0) {
                        if (!visited[nextY][nextX][nowLimit]) {
                            visited[nextY][nextX][nowLimit] = true;
                            myqueue.add(new int[]{nextY, nextX, nowDist+1, nowLimit, afterDayOrNight});
                        }
                    }
                }
            }
        }

        return answer;
    }
}