import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[][] circle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 원판 개수
        M = Integer.parseInt(st.nextToken()); // 원판에 적힌 수의 개수
        int T = Integer.parseInt(st.nextToken()); // 회전 수

        circle = new int[N+1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            // 1. 번호가 x의 배수인 원판을 d방향으로 k칸 회전 (0인 경우는 시계 방향, 1인 경우는 반시계 방향)
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            rotate(x,d,k);
            // 2-1. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다 (지움)
            // 2-2. 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다
        }

        // 원판을 T번 회전시킨 후 원판에 적힌 수의 합
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += circle[i][j];
            }
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static void rotate(int x, int d, int k) {
        // 번호가 x의 배수인 원판을 d방향으로 k칸 회전
        for (int i = 1; i <= N; i++) {
            if (i % x != 0) continue;

            if (d == 0) { // 0인 경우는 시계 방향
                for (int r = 0; r < k; r++) {
                    int tmp = circle[i][M - 1];
                    for (int j = M - 1; j >= 0; j--) {
                        if (j == 0) circle[i][j] = tmp;
                        else circle[i][j] = circle[i][j - 1];
                    }
                }
            } else { // 1인 경우는 반시계 방향
                for (int r = 0; r < k; r++) {
                    int tmp = circle[i][0];
                    for (int j = 0; j < M; j++) {
                        if (j == M-1) circle[i][j] = tmp;
                        else circle[i][j] = circle[i][j+1];
                    }
                }
            }
        }

        // 인접한 수를 지웠는지 체크
        boolean chk = chkCircle();

        if (!chk) useAverage();
    }

    private static boolean chkCircle() {
        boolean chk = false;
        boolean[][] erase = new boolean[N+1][M];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0) continue;

                if (j == M-1) {
                    if (circle[i][j] == circle[i][0]) {
                        erase[i][j] = true;
                        erase[i][0] = true;
                    }
                } else {
                    if (circle[i][j] == circle[i][j+1]) {
                        erase[i][j] = true;
                        erase[i][j+1] = true;
                    }
                }

                if (i != N) {
                    if (circle[i][j] == circle[i+1][j]) {
                        erase[i][j] = true;
                        erase[i+1][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (erase[i][j]) {
                    chk = true;
                    circle[i][j] = 0;
                }
            }
        }

        return chk;
    }

    private static void useAverage() {
        // 1. 평균 구하기 (합, 수의 개수 필요)
        double sum = 0;
        double num = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0) continue;
                sum += circle[i][j];
                num++;
            }
        }

        double avg = sum / num;

        // 2. 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0) continue;

                double now = (double) circle[i][j];
                if (now > avg) circle[i][j]--;
                if (now < avg) circle[i][j]++;
            }
        }
    }
}