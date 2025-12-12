import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int testcase = 0;
        while (size != 0) {
            testcase++;
            int[][] map = new int[size][size];

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = findRoute(map, size);
            bw.write("Problem " + testcase + ": " + answer);
            bw.newLine();

            size = Integer.parseInt(br.readLine());
        }

        bw.flush();
        bw.close();
    }

    // 가중치 있는 최단거리 -> 다익스트라
    private static int findRoute(int[][] map, int size) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        PriorityQueue<int[]> path = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        path.add(new int[]{0, 0, map[0][0]}); // i, j, cost

        int[][] dist = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = map[0][0];

        while (!path.isEmpty()) {
            int[] now = path.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowCost = now[2];

            if (nowCost > dist[nowY][nowX]) continue;

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < size && 0 <= nextX && nextX < size) {
                    if (nowCost + map[nextY][nextX] < dist[nextY][nextX]) {
                        dist[nextY][nextX] = nowCost + map[nextY][nextX];
                        path.add(new int[]{nextY, nextX, dist[nextY][nextX]});
                    }
                }
            }
        }

        return dist[size-1][size-1];
    }
}