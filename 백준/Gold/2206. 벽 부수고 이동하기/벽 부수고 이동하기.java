import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int[] dx = {0, 0, -1, 1};
    static int N, M, startX, startY, endX, endY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j-1)));
            }
        }

        startX = 1;
        startY = 1;
        endX = M;
        endY = N;

        // 최단 거리 맵을 구해야 됨
        int shortest = getDistance(map);
        if (shortest == Integer.MAX_VALUE) shortest = -1;

        bw.write(String.valueOf(shortest));
        bw.flush();
        bw.close();
    }

    private static int getDistance(int[][] map) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{startY, startX, 1, 0}); // [i, j, distance, broken여부(0/1)]

        boolean[][][] visited = new boolean[N+1][M+1][2];
        visited[startY][startX][0] = true;

        int distance = Integer.MAX_VALUE;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowDistance = now[2];
            int chkBroken = now[3];

            if (nowY == endY && nowX == endX) {
                distance = nowDistance;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (chkBoundary(nextY, nextX)) {
                    if (map[nextY][nextX] == 0 && !visited[nextY][nextX][chkBroken]) { // 길
                        visited[nextY][nextX][chkBroken] = true;
                        myqueue.add(new int[]{nextY, nextX, nowDistance+1, chkBroken});
                    } else { // 벽
                        if (chkBroken == 0 && !visited[nextY][nextX][0]) { // 부순 적이 없음
                            visited[nextY][nextX][1] = true;
                            myqueue.add(new int[]{nextY, nextX, nowDistance+1, 1});
                        }
                    }
                }
            }
        }

        return distance;
    }

    private static boolean chkBoundary(int i, int j) {
        return (1 <= i && i <= N && 1 <= j && j <= M);
    }
}