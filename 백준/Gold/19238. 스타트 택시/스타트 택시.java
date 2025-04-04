import java.io.*;
import java.util.*;
public class Main {
    static class Client {
        int startR;
        int startC;
        int endR;
        int endC;
        boolean isFinished;
        Client(int startR, int startC, int endR, int endC, boolean isFinished) {
            this.startR = startR;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
            this.isFinished = isFinished;
        }
    }
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static int carX, carY, N, fuel;
    static List<Client> clients;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) { // 0은 빈칸, 1은 벽 (운전을 시작하는 칸은 빈칸)
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) map[i][j] = -1; // 승객 번호 표시하려고 벽을 -1 로 바꿈
            }
        }

        // 운전을 시작하는 칸의 행 번호와 열 번호
        st = new StringTokenizer(br.readLine());
        carY = Integer.parseInt(st.nextToken());
        carX = Integer.parseInt(st.nextToken());

        clients = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            //  각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());
            clients.add(new Client(startR, startC, endR, endC, false));
            map[startR][startC] = i+1; // 맵에 승객 시작 위치 표시
        }

        for (int i = 0; i < clients.size(); i++) {
            // 1. 최단거리인 승객 고르기 + 자동차 이동
            int cidx = chooseClient();
            if (cidx == -1) {
                fuel = -1;
                break;
            }

            // 2. 이동시키기
            boolean canGo = move(cidx);
            if (!canGo) {
                fuel = -1;
                break;
            }
            // 2-1. 이동 도중에 연료가 바닥나서 다음 출발지나 목적지로 이동할 수 없으면 -1을 출력 (끝)
            // 2-2. 다음 루프
        }

        // 모든 손님을 이동시킬 수 없는 경우에도 -1을 출력
        // 모든 손님을 이동시키고 연료를 충전했을 때 남은 연료의 양
        bw.write(String.valueOf(fuel));
        bw.flush();
        bw.close();
    }

    private static boolean move(int cidx) {
        Client now_client = clients.get(cidx-1);
        int distance = -1;

        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{carY, carX, 0});

        boolean[][] visited = new boolean[N+1][N+1];
        visited[carY][carX] = true;

        while (!myqueue.isEmpty()) {
            int[] car = myqueue.poll();
            int nowY = car[0];
            int nowX = car[1];
            int dist = car[2];

            if (now_client.endR == nowY && now_client.endC == nowX) {
                distance = dist;
                carY = nowY;
                carX = nowX;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 < nextY && nextY <= N && 0 < nextX && nextX <= N && !visited[nextY][nextX]) {
                    if (map[nextY][nextX] != -1) { // 벽이 아닐 때
                        myqueue.add(new int[]{nextY, nextX, dist+1});
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }

        if (distance == -1 || fuel < distance) return false;
        else {
            fuel -= distance;
            fuel += distance*2;
            return true;
        }
    }

    private static int chooseClient() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]); // 거리 기준 오름차순
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]); // 행 기준 오름차순
            return Integer.compare(o1[1], o2[1]); // 열 기준 오름차순
        });
        pq.add(new int[]{carY, carX, 0});

        boolean[][] visited = new boolean[N+1][N+1];
        visited[carY][carX] = true;

        int cidx = -1;
        int dist = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int now_y = now[0];
            int now_x = now[1];
            int now_dist = now[2];

            if (map[now_y][now_x] > 0) {
                cidx = map[now_y][now_x];
                map[now_y][now_x] = 0;
                dist = now_dist;
                carY = now_y;
                carX = now_x;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int next_y = now_y + dy[k];
                int next_x = now_x + dx[k];

                if (0 < next_y && next_y <= N && 0 < next_x && next_x <= N) {
                    if (!visited[next_y][next_x] && map[next_y][next_x] != -1) {
                        pq.add(new int[]{next_y, next_x, now_dist+1});
                        visited[next_y][next_x] = true;
                    }
                }
            }
        }

        if (fuel < dist) return -1;
        fuel -= dist;

        return cidx;
    }
}