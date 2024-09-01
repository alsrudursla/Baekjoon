import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dx = {0, 1, 0, -1};
    static int N, M;
    static int[][] house;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 로봇 청소기 위치
        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); // 0 : 북쪽, 1 : 동쪽, 2 : 남쪽, 3 : 서쪽

        // 방 생김새
        house = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        dfs(R, C, D);

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void dfs(int y, int x, int d) {
        if (house[y][x] == 0) {
            house[y][x] = 2;
            ans++;
        }

        for (int k = 0; k < 4; k++) {
            d = (d + 3) % 4; // 반시계 회전
            int now_x = x + dx[d];
            int now_y = y + dy[d];
            if (0 <= now_x && now_x < M && 0 <= now_y && now_y < N) {
                if (house[now_y][now_x] == 0) {
                    dfs(now_y, now_x, d);
                    return;
                }
            }
        }

        // 후진
        int back_d = (d + 2) % 4;
        int back_x = x + dx[back_d];
        int back_y = y + dy[back_d];
        if (0 <= back_x && back_x < M && 0 <= back_y && back_y < N) {
            if (house[back_y][back_x] != 1) { // 후진은 벽만 아니면 가능하다
                dfs(back_y, back_x, d);
            }
        }

    }
}