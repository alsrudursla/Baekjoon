import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {0, 1, 0, -1}; // ← ↓ → ↑
    static int[] dx = {-1, 0, 1, 0};
    static int N, sum;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start_i = N / 2 + 1;
        int start_j = N / 2 + 1;
        int dir = 0;
        int cnt = 1;
        sum = 0;
        boolean isFinished = false;

        while (!isFinished) {
            // 가운데 칸부터 한 번에 한 칸 이동
            // 1. 방향으로 이동
            // 2. 모래 흩날리기

            for (int c = 0; c < cnt * 2; c++) {
                start_i += dy[dir];
                start_j += dx[dir];

                int sand = map[start_i][start_j];
                if (sand != 0) {
                    setSandDir(dir, start_i, start_j, sand);
                }

                if (c == cnt - 1 || c == 2 * cnt - 1) dir = (dir + 1) % 4;
                if (start_i == 1 && start_j == 1) {
                    isFinished = true;
                    break;
                }
            }

            cnt++;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static int setSandDir(int dir, int i, int j, int sand) {
        int moved = 0;
        if (dir == 0) { // 왼쪽으로 갈 때
            moved += moveSand(i - 1, j, sand, 0.07);
            moved += moveSand(i - 2, j, sand, 0.02);
            moved += moveSand(i - 1, j - 1, sand, 0.1);
            moved += moveSand(i, j - 2, sand, 0.05);
            moved += moveSand(i + 1, j - 1, sand, 0.1);
            moved += moveSand(i + 1, j, sand, 0.07);
            moved += moveSand(i + 2, j, sand, 0.02);
            moved += moveSand(i + 1, j + 1, sand, 0.01);
            moved += moveSand(i - 1, j + 1, sand, 0.01);

            int rest = sand - moved;
            if (chkBoundary(i, j - 1)) map[i][j - 1] += rest;
            else sum += rest;
            map[i][j] = 0;
        } else if (dir == 1) { // 아래로 갈 때
            moved += moveSand(i, j - 1, sand, 0.07);
            moved += moveSand(i, j - 2, sand, 0.02);
            moved += moveSand(i + 1, j - 1, sand, 0.1);
            moved += moveSand(i + 2, j, sand, 0.05);
            moved += moveSand(i + 1, j + 1, sand, 0.1);
            moved += moveSand(i, j + 1, sand, 0.07);
            moved += moveSand(i, j + 2, sand, 0.02);
            moved += moveSand(i - 1, j + 1, sand, 0.01);
            moved += moveSand(i - 1, j - 1, sand, 0.01);

            int rest = sand - moved;
            if (chkBoundary(i + 1, j)) map[i + 1][j] += rest;
            else sum += rest;
            map[i][j] = 0;
        } else if (dir == 2) { // 오른쪽으로 갈 때
            moved += moveSand(i - 1, j, sand, 0.07);
            moved += moveSand(i - 2, j, sand, 0.02);
            moved += moveSand(i - 1, j + 1, sand, 0.1);
            moved += moveSand(i, j + 2, sand, 0.05);
            moved += moveSand(i + 1, j + 1, sand, 0.1);
            moved += moveSand(i + 1, j, sand, 0.07);
            moved += moveSand(i + 2, j, sand, 0.02);
            moved += moveSand(i + 1, j - 1, sand, 0.01);
            moved += moveSand(i - 1, j - 1, sand, 0.01);

            int rest = sand - moved;
            if (chkBoundary(i, j + 1)) map[i][j + 1] += rest;
            else sum += rest;
            map[i][j] = 0;
        } else { // 위로 갈 때
            moved += moveSand(i, j - 1, sand, 0.07);
            moved += moveSand(i, j - 2, sand, 0.02);
            moved += moveSand(i - 1, j - 1, sand, 0.1);
            moved += moveSand(i - 2, j, sand, 0.05);
            moved += moveSand(i - 1, j + 1, sand, 0.1);
            moved += moveSand(i, j + 1, sand, 0.07);
            moved += moveSand(i, j + 2, sand, 0.02);
            moved += moveSand(i + 1, j + 1, sand, 0.01);
            moved += moveSand(i + 1, j - 1, sand, 0.01);

            int rest = sand - moved;
            if (chkBoundary(i - 1, j)) map[i - 1][j] += rest;
            else sum += rest;
            map[i][j] = 0;
        }
        return moved;
    }

    private static int moveSand(int i, int j, int sand, double amount) {
        if (chkBoundary(i, j)) map[i][j] += (int) (sand * amount);
        else sum += (int) (sand * amount);
        return (int) (sand * amount);
    }

    private static boolean chkBoundary(int i, int j) {
        return 1 <= i && i <= N && 1 <= j && j <= N;
    }
}