import java.io.*;
import java.util.*;

public class Main {
    static int N, score;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        score = 0;
        map = new int[10][10];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int t = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[2]);

            // 1. 블록 이동
            // 2. 행이나 열이 타일로 가득찬 경우
            // 3. 연한 칸에 블록이 있는 경우
            move(t, x, y);
            removeTile();
        }

        int tile = count(map);
        bw.write(score + "\n" + tile);
        bw.flush();
        bw.close();
    }

    private static void move(int block_type, int x, int y) {
        if (block_type == 1) { // 1x1
            // 파란색으로 이동
            int nextY = 0;
            for (int j = 4; j <= 9; j++) {
                if (map[x][j] == 1) break;
                nextY = j;
            }
            map[x][nextY] = 1;

            // 초록색으로 이동
            int nextX = 0;
            for (int i = 4; i <= 9; i++) {
                if (map[i][y] == 1) break;
                nextX = i;
            }
            map[nextX][y] = 1;
        } else if (block_type == 2) { // 1x2
            // 파란색으로 이동
            int nextY = 0;
            for (int j = 4; j <= 8; j++) {
                if (map[x][j] == 1 || map[x][j+1] == 1) break;
                nextY = j;
            }
            map[x][nextY] = 1;
            map[x][nextY+1] = 1;

            // 초록색으로 이동
            int nextX = 0;
            for (int i = 4; i <= 9; i++) {
                if (map[i][y] == 1 || map[i][y+1] == 1) break;
                nextX = i;
            }
            map[nextX][y] = 1;
            map[nextX][y+1] = 1;
        } else { // 2x1
            // 파란색으로 이동
            int nextY = 0;
            for (int j = 4; j <= 9; j++) {
                if (map[x][j] == 1 || map[x+1][j] == 1) break;
                nextY = j;
            }
            map[x][nextY] = 1;
            map[x+1][nextY] = 1;

            // 초록색으로 이동
            int nextX = 0;
            for (int i = 4; i <= 8; i++) {
                if (map[i][y] == 1 || map[i+1][y] == 1) break;
                nextX = i;
            }
            map[nextX][y] = 1;
            map[nextX+1][y] = 1;
        }
    }

    private static void removeTile() {
        // 파란색 열 삭제
        for (int j = 6; j <= 9; j++) {
            boolean chk = false;
            for (int i = 0; i < 4; i++) {
                if (map[i][j] == 0) {
                    chk = true;
                    break;
                }
            }

            if (chk) continue;

            for (int j2 = j; j2 > 4; j2--) {
                for (int i = 0; i < 4; i++) map[i][j2] = map[i][j2-1];
            }

            for (int i = 0; i < 4; i++) map[i][4] = 0;

            score++;
        }

        // 초록색 행 삭제
        for (int i = 6; i <= 9; i++) {
            boolean chk = false;
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 0) {
                    chk = true;
                    break;
                }
            }

            if (chk) continue;

            for (int i2 = i; i2 > 4; i2--) {
                for (int j = 0; j < 4; j++) map[i2][j] = map[i2-1][j];
            }

            for (int j = 0; j < 4; j++) map[4][j] = 0;

            score++;
        }

        // 파란색 연한색 처리
        for (int j = 4; j <= 5; j++) {
            boolean chk = false;
            for (int i = 0; i < 4; i++) {
                if (map[i][j] == 1) {
                    chk = true;
                    break;
                }
            }

            if (!chk) continue;

            for (int j2 = 9; j2 > j; j2--) {
                for (int i = 0; i < 4; i++) map[i][j2] = map[i][j2-1];
            }

            for (int i = 0; i < 4; i++) map[i][j] = 0;
        }

        // 초록색 연한색 처리
        for (int i = 4; i <= 5; i++) {
            boolean chk = false;
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 1) {
                    chk = true;
                    break;
                }
            }

            if (!chk) continue;

            for (int i2 = 9; i2 > i; i2--) {
                for (int j = 0; j < 4; j++) map[i2][j] = map[i2-1][j];
            }

            for (int j = 0; j < 4; j++) map[i][j] = 0;
        }
    }

    private static int count(int[][] map) {
        int tmp = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1) tmp++;
            }
        }
        return tmp;
    }
}