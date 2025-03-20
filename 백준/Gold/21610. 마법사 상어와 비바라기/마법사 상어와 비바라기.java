import java.io.*;
import java.util.*;
public class Main {
    // 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] waterMap;
    static boolean[][] cloudMap;
    static boolean[][] visited;
    static Queue<int[]> moveQueue;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        waterMap = new int[N+1][N+1];
        cloudMap = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                waterMap[i][j] = Integer.parseInt(st.nextToken()); // 바구니 물의 양 저장
            }
        }

        moveQueue = new LinkedList<>(); // [d방향, s칸이동]
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            moveQueue.add(new int[]{dir, count});
        }

        /* 맵이 계속 이어진다! (((이동에서만)))
        격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.
        마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다.
        즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.
         */

        // 0. 비바라기 구름 생성하기 : (N, 1), (N, 2), (N-1, 1), (N-1, 2)
        cloudMap[N][1] = true;
        cloudMap[N][2] = true;
        cloudMap[N-1][1] = true;
        cloudMap[N-1][2] = true;

        while (!moveQueue.isEmpty()) {
            int[] now = moveQueue.poll();

            // 3번에서의 제약 조건을 고려하여 이동 자리 바구니 체크하는 배열 미리 생성 + 물복사버그
            visited = new boolean[N+1][N+1];
            // 1. 구름 이동 -> 이동 자리 바구니 물 양 증가 -> 구름 사라짐
            moveCloud(now[0], now[1]);
            // 2. 물복사버그 : 이동 자리 바구니에 물 증가 (대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼)
            addWater();
            // 3. 구름 생성 : 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다 (이때 구름이 생기는 칸은 1에서 구름이 사라진 칸이 아니어야 한다)
            addCloud();
        }

        // 4. M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합
        int sum = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) sum += waterMap[r][c];
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static void addCloud() {
        // 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다
        // visited 배열 활용 - (이때 구름이 생기는 칸은 1에서 구름이 사라진 칸이 아니어야 한다)

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (waterMap[r][c] >= 2 && !visited[r][c]) {
                    cloudMap[r][c] = true;
                    waterMap[r][c] -= 2;
                    if (waterMap[r][c] < 0) waterMap[r][c] = 0;
                }
            }
        }
    }

    private static void addWater() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (!visited[r][c]) continue;

                // 물이 증가했던 바구니 위치 도착

                int bugCnt = 0;
                for (int k = 2; k <= 8; k += 2) { // 대각선 검사
                    int chk_i = r + dy[k];
                    int chk_j = c + dx[k];

                    if (chkBoundary(chk_i, chk_j) && waterMap[chk_i][chk_j] > 0) bugCnt++;
                }

                // 대각선 방향에 물이 들어있는 바구니 수만큼 (r,c) 바구니에 물 증가
                waterMap[r][c] += bugCnt;
            }
        }
    }

    private static void moveCloud(int dir, int count) {
        // dir : 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙

        // 1. 구름 이동 (동시 이동)
        boolean[][] tmp_cloud = new boolean[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (!cloudMap[r][c]) continue;

                // 범위 넘어갔을 때 처리
                int next_i = r;
                int next_j = c;
                for (int s = 0; s < count; s++) {
                    next_i += dy[dir];
                    if (next_i > N) next_i = 1;
                    else if (next_i < 1) next_i = N;

                    next_j += dx[dir];
                    if (next_j > N) next_j = 1;
                    else if (next_j < 1) next_j = N;
                }

                tmp_cloud[next_i][next_j] = true;
            }
        }

        // 바뀐 구름 위치 복사
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) cloudMap[r][c] = tmp_cloud[r][c];
        }

        // 2. 구름이 있는 칸 바구니 물의 양 1 증가
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (cloudMap[r][c]) {
                    waterMap[r][c]++;
                    visited[r][c] = true; // 물복사버그 & 구름 새로 생성할 때 조건 처리
                }
            }
        }

        // 3. 구름이 모두 사라진다
        cloudMap = new boolean[N+1][N+1];
    }

    private static boolean chkBoundary(int i, int j) {
        return (1 <= i && i <= N && 1 <= j && j <= N);
    }
}