import java.util.*;
import java.io.*;

class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int shortestPath;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());

        // 1. 전체 맵 입력받기
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 대륙 구분하기
        visited = new boolean[size][size];
        int areaNum = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    color(i, j, areaNum);
                    areaNum++;
                }
            }
        }

        // 3. 가장 짧은 다리 찾기
        shortestPath = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if (0 <= nY && nY < size && 0 <= nX && nX < size) {
                            if (map[nY][nX] == 0) { // 바다로 이어지는 경우에만 탐색
                                findPath(i, j);
                                break;
                            }
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(shortestPath));
        bw.flush();
        bw.close();
    }

    private static void findPath(int i, int j) {
        int myColor = map[i][j]; // 현재 내 대륙의 색깔

        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j, 0});

        boolean[][] v = new boolean[map.length][map[0].length];
        v[i][j] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowDistance = now[2];

            if (map[nowY][nowX] != 0 && map[nowY][nowX] != myColor) {
                shortestPath = Math.min(shortestPath, nowDistance - 1);
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (map[nextY][nextX] != myColor && !v[nextY][nextX]) {
                        // 바다나 다른 대륙으로만 이동
                        v[nextY][nextX] = true;
                        myqueue.add(new int[]{nextY, nextX, nowDistance+1});
                    }
                }
            }
        }
    }

    private static void color(int i, int j, int areaNum) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        visited[i][j] = true;
        map[i][j] = areaNum;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] != 0) {
                        visited[nextY][nextX] = true;
                        map[nextY][nextX] = areaNum;
                        myqueue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
    }
}