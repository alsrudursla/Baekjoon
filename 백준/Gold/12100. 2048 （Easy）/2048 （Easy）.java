import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        ans = Integer.MIN_VALUE;

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0, board);

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void move(int times, int[][] board) {
        if (times == 5) {
            int value = findValue(board);
            ans = Math.max(ans, value);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int[][] tmp_board = copyArray(board);
            boolean[][] visited = new boolean[N][N]; // 한 턴에서 변경된 블록은 바꾸지 못함

            // 1. 빈 칸 없도록 이동
            // 2. 비교 + 수가 같으면 합치기

            if (k == 0) { // 위로 이동
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        push(i, j, 0, tmp_board, visited);
                    }
                }
            } else if (k == 1) { // 오른쪽으로 합치기
                for (int j = N-1; j >= 0; j--) {
                    for (int i = 0; i < N; i++) {
                        push(i, j, 1, tmp_board, visited);
                    }
                }
            } else if (k == 2) { // 아래로 합치기
                for (int i = N-1; i >= 0; i--) {
                    for (int j = 0; j < N; j++) {
                        push(i, j, 2, tmp_board, visited);
                    }
                }
            } else { // 왼쪽으로 합치기
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        push(i, j, 3, tmp_board, visited);
                    }
                }
            }

            move(times+1, tmp_board);
        }
    }

    private static void push(int i, int j, int dir, int[][] tmp_board, boolean[][] visited) {
        int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌
        int[] dx = {0, 1, 0, -1};
        int next_y = i + dy[dir];
        int next_x = j + dx[dir];

        boolean moved = false;
        while (chkBoundary(next_y, next_x) && tmp_board[next_y][next_x] == 0) {
            // 경계값 안에 있을 때, 빈칸일 때 이동
            next_y += dy[dir];
            next_x += dx[dir];
            moved = true;
        }

        // 이동하는 곳
        int moved_y = next_y - dy[dir];
        int moved_x = next_x - dx[dir];

        if (moved) {
            // 움직였을 경우
            tmp_board[moved_y][moved_x] = tmp_board[i][j];
            tmp_board[i][j] = 0;
        }

        // 비교
        if (chkBoundary(moved_y + dy[dir], moved_x + dx[dir]) && !visited[moved_y + dy[dir]][moved_x + dx[dir]] &&
                tmp_board[moved_y + dy[dir]][moved_x + dx[dir]] == tmp_board[moved_y][moved_x]) {
            visited[moved_y + dy[dir]][moved_x + dx[dir]] = true;
            tmp_board[moved_y + dy[dir]][moved_x + dx[dir]] *= 2;
            tmp_board[moved_y][moved_x] = 0;
        }
    }

    private static boolean chkBoundary(int i, int j) {
        if (0 <= i && i < N && 0 <= j && j < N) return true;
        else return false;
    }

    private static int[][] copyArray(int[][] arr) {
        int[][] tmp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    private static int findValue(int[][] board) {
        int tmp = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp = Math.max(tmp, board[i][j]);
            }
        }
        return tmp;
    }
}