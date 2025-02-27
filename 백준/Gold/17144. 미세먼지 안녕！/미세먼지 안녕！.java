import java.io.*;
import java.util.*;
public class Main {
    static int R, C, cleanerUpX, cleanerUpY, cleanerDownX, cleanerDownY;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        boolean chk = false;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) { // 공기 청정기 위치
                    if (!chk) {
                        chk = true;
                        cleanerUpY = i;
                        cleanerUpX = j;
                    } else {
                        cleanerDownY = i;
                        cleanerDownX = j;
                    }
                }
            }
        }

        for (int i = 0; i < T; i++) {
            // 1. 미세먼지 확산
            // 2. 공기청정기 작동

            spread();
            moveUp();
            moveDown();
        }

        int dust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) continue;
                dust += map[i][j];
            }
        }

        // T초가 지난 후 구사과 방에 남아있는 미세먼지의 양
        bw.write(String.valueOf(dust));
        bw.flush();
        bw.close();
    }

    private static void spread() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        int[][] tmp_map = new int[R][C];
        // 주변에 확산된 양은 tmp_map 에 저장, 원래 지점은 남은 양만 기록, 마지막에 합치기
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int spread_area = 0; // 확산된 영역
                int added = map[r][c] / 5; // 확산되는 양
                if (added == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nextY = r + dy[k];
                    int nextX = c + dx[k];

                    if (0 <= nextX && nextX < C && 0 <= nextY && nextY < R) {
                        if (map[nextY][nextX] != -1) {
                            tmp_map[nextY][nextX] += added;
                            spread_area++;
                        }
                    }
                }
                map[r][c] -= added * spread_area;
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] += tmp_map[r][c];
            }
        }
    }

    private static void moveUp() {
        // 좌
        for (int r = cleanerUpY-1; r >= 1; r--) {
            map[r][cleanerUpX] = map[r-1][cleanerUpX];
        }
        // 상
        for (int c = 0; c < C-1; c++) {
            map[0][c] = map[0][c+1];
        }
        // 우
        for (int r = 0; r < cleanerUpY; r++) {
            map[r][C-1] = map[r+1][C-1];
        }
        // 하
        for (int c = C-1; c >= 1; c--) {
            if (c == 1) map[cleanerUpY][c] = 0;
            else map[cleanerUpY][c] = map[cleanerUpY][c-1];
        }
    }

    private static void moveDown() {
        // 좌
        for (int r = cleanerDownY+1; r < R-1; r++) {
            map[r][cleanerDownX] = map[r+1][cleanerDownX];
        }
        // 하
        for (int c = 0; c < C-1; c++) {
            map[R-1][c] = map[R-1][c+1];
        }
        // 우
        for (int r = R-1; r > cleanerDownY; r--) {
            map[r][C-1] = map[r-1][C-1];
        }
        // 상
        for (int c = C-1; c >= 1; c--) {
            if (c == 1) map[cleanerDownY][c] = 0;
            else map[cleanerDownY][c] = map[cleanerDownY][c-1];
        }
    }
}