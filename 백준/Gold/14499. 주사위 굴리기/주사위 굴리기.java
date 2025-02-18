import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 명령 개수

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[7];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
            int dir = Integer.parseInt(st.nextToken());

            // 1. 주사위 굴리기 - 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시
            if (dir == 1) {
                if (!chkBoundary(X, Y+1, N, M)) continue;
                Y++;

                int tmp = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = tmp;
            } else if (dir == 2) {
                if (!chkBoundary(X, Y-1, N, M)) continue;
                Y--;

                int tmp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = tmp;
            } else if (dir == 3) {
                if (!chkBoundary(X-1, Y, N, M)) continue;
                X--;

                int tmp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = tmp;
            } else {
                if (!chkBoundary(X+1, Y, N, M)) continue;
                X++;

                int tmp = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = tmp;
            }

            // 2. 칸 복사
            if (map[X][Y] == 0) {
                map[X][Y] = dice[6];
            } else {
                dice[6] = map[X][Y];
                map[X][Y] = 0;
            }

            // 3. 출력
            bw.write(String.valueOf(dice[1]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static boolean chkBoundary(int i, int j, int N, int M) {
        if (0 <= i && i < N && 0 <= j && j < M) return true;
        else return false;
    }
}