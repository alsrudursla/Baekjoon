
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[][] board;
    static Queue<Move> action;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // 보드 크기
        K = Integer.parseInt(br.readLine()); // 사과 개수

        board = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = true; // 사과 위치
        }

        action = new LinkedList<>();

        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            // X초 후 C 방향으로 이동 (L 왼쪽 / D 오른쪽)
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();

            action.offer(new Move(X, C));
        }

        solve();
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void solve() {
        Queue<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        int now_dir = 0; // 현재 방향
        ans = 0; // 시간 측정

        int now_x = 0;
        int now_y = 0;

        if (!action.isEmpty()) {
            Move now = action.poll();
            int time = now.time;
            String dir = now.dir;

            while (true) {

                ans++;

                int x = now_x + dx[now_dir];
                int y = now_y + dy[now_dir];

                // 정해진 좌표 내에 있어야 함
                if (x < 0 || x >= N || y < 0 || y >= N) return;

                // 몸통에 박으면 안됨
                for (int[] now_snake : snake) {
                    if (now_snake[0] == x && now_snake[1] == y) return;
                }

                snake.add(new int[]{x, y});
                now_x = x;
                now_y = y;

                if (board[x][y]) { // 사과가 있음
                    board[x][y] = false; // 사과 삭제
                } else { // 사과 없으면 꼬리 있던 칸 삭제 (꼬리가 안길어짐)
                    snake.poll();
                }

                if (time == ans) {
                    if (dir.equals("D")) {
                        now_dir = (now_dir + 1) % 4;
                    } else {
                        now_dir = (now_dir + 3) % 4;
                    }

                    if (!action.isEmpty()) {
                        now = action.poll();
                        time = now.time;
                        dir = now.dir;
                    }
                }
            }
        }
    }
}
class Move {
    int time;
    String dir;

    public Move(int time, String dir) {
        this.time = time;
        this.dir = dir;
    }
}