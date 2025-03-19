import java.io.*;
import java.util.*;

public class Main {
    static class Shark {
        int i;
        int j;
        int dir;
        boolean isAlived;
        int[] up;
        int[] down;
        int[] left;
        int[] right;

        Shark (int i, int j, boolean isAlived) {
            this.i = i;
            this.j = j;
            this.isAlived = isAlived;
        }
    }
    static int[] dy = {0, -1, 1, 0, 0}; // 위, 아래, 왼쪽, 오른쪽
    static int[] dx = {0, 0, 0, -1, 1};
    static int N, M, K;
    static int[][] map, countMap;
    static Shark[] shark;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 상어 수
        K = Integer.parseInt(st.nextToken()); // 냄새 유지 시간

        map = new int[N][N];
        countMap = new int[N][N];
        shark = new Shark[M+1]; // i번 상어 -> i번 인덱스
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) { // 상어 좌표 저장
                    shark[map[i][j]] = new Shark(i, j, true);
                    countMap[i][j] = K;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) { // 상어 맨 처음 바라보고 있는 방향
            shark[i].dir = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) { // 상어 방향 우선순위 저장
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if (j == 0) shark[i].up = new int[]{a, b, c, d};
                else if (j == 1) shark[i].down = new int[]{a, b, c, d};
                else if (j == 2) shark[i].left = new int[]{a, b, c, d};
                else shark[i].right = new int[]{a, b, c, d};
            }
        }

        int time = -1;
        for (int i = 1; i <= 1000; i++) {
            moveShark();

            if (chkOnlyOne()) {
                time = i;
                break;
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    private static boolean chkOnlyOne() {
        int shark_num = 0;
        for (int i = 1; i <= M; i++) {
            if (shark[i].isAlived) shark_num++;
        }

        if (shark[1].isAlived && shark_num == 1) return true;
        else return false;
    }

    private static void moveShark() {
        // 동시 이동!!! 어떤 맵을 tmp로 생성해야 하는지 생각하기

        int[][] tmp_shark_map = new int[N][N]; // 이동했을 때 같은 칸에 겹치는지 확인

        for (int i = 1; i <= M; i++) { // 모든 상어 이동 (맵에 이동 표현 & 냄새 즉시 뿌리면 안됨 - 동시 이동에 영향)
            // 1. 상하좌우로 인접한 칸 중 하나로 이동
            // 1-1. 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로
            // 1-2. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로
            // 1-3. 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다
            // 2. 자신의 냄새를 그 칸에 뿌린다

            if (!shark[i].isAlived) continue;

            // 상어 현재 위치
            Shark now = shark[i];
            int now_i = shark[i].i;
            int now_j = shark[i].j;
            int now_dir = shark[i].dir;

            int empty_space = 0;
            for (int k = 1; k <= 4; k++) { // 인접한 칸 중 아무 냄새 없는 칸 탐색
                int chk_i = now_i + dy[k];
                int chk_j = now_j + dx[k];

                if (chkBoundary(chk_i, chk_j) && map[chk_i][chk_j] == 0) empty_space++;
            }

            // 방향 우선순위 불러오기
            int[] priority = new int[4];
            if (now_dir == 1) priority = now.up;
            else if (now_dir == 2) priority = now.down;
            else if (now_dir == 3) priority = now.left;
            else priority = now.right;

            if (empty_space > 0) { // 아무 냄새 없는 칸으로 이동하기
                for (int k = 0; k < 4; k++) {
                    int next_dir = priority[k];
                    int next_i = now_i + dy[next_dir];
                    int next_j = now_j + dx[next_dir];

                    if (chkBoundary(next_i, next_j) && countMap[next_i][next_j] == 0) {
                        shark[i].i = next_i;
                        shark[i].j = next_j;
                        shark[i].dir = next_dir;
                        tmp_shark_map[next_i][next_j]++;
                        break;
                    }
                }
            } else { // 자신의 냄새가 있는 칸의 방향으로 이동하기
                for (int k = 0; k < 4; k++) {
                    int next_dir = priority[k];
                    int next_i = now_i + dy[next_dir];
                    int next_j = now_j + dx[next_dir];

                    if (chkBoundary(next_i, next_j) && map[next_i][next_j] == i) {
                        shark[i].i = next_i;
                        shark[i].j = next_j;
                        shark[i].dir = next_dir;
                        tmp_shark_map[next_i][next_j]++;
                        break;
                    }
                }
            }
        }

        // 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (tmp_shark_map[r][c] > 1) {
                    boolean first_shark = false;
                    for (int s = 1; s <= M; s++) {
                        if (!shark[s].isAlived) continue;
                        if (shark[s].i == r && shark[s].j == c) {
                            if (!first_shark) { // 처음 발견되는 상어가 우선순위가 높음
                                first_shark = true;
                            } else { // 이후로 나머지 상어 없애기
                                shark[s].isAlived = false;
                            }
                        }
                    }
                }
            }
        }

        // countMap 처리 (새로 옮긴 자리 추가 전에 모든 count -1 -> 그 후 새 자리에 K 초 입력)
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (countMap[r][c] != 0) {
                    countMap[r][c]--;
                    if (countMap[r][c] == 0) map[r][c] = 0;
                }
            }
        }

        for (int s = 1; s <= M; s++) {
            if (!shark[s].isAlived) continue;
            countMap[shark[s].i][shark[s].j] = K;
        }

        // map 에 표시
        for (int s = 1; s <= M; s++) {
            if (!shark[s].isAlived) continue;
            map[shark[s].i][shark[s].j] = s;
        }
    }

    private static boolean chkBoundary(int i, int j) {
        if (0 <= i && i < N && 0 <= j && j < N) return true;
        else return false;
    }
}