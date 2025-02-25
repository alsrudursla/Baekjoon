import java.io.*;
import java.util.*;
public class Main {
    static int N, M, H, ans;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선
        M = Integer.parseInt(st.nextToken()); // 가로선
        H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치의 개수

        ans = Integer.MAX_VALUE;
        visited = new boolean[H+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            visited[a][b] = true;
        }

        // 사다리 놓아보기
        for (int i = 0; i <= 3; i++) {
            dfs(0, i); // 현재 추가한 사다리 개수, 최대 사다리 개수
            if (ans != Integer.MAX_VALUE) break;
        }

        if (ans == Integer.MAX_VALUE) bw.write("-1");
        else bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void dfs(int now_ladder, int extra_ladder) {
        if (now_ladder == extra_ladder) {
            // i -> i 갈 수 있는지 확인
            if (chkPath()) ans = Math.min(ans, extra_ladder);
            return;
        }

        // 사다리 놓아보기
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (visited[i][j] || visited[i][j+1]) continue;
                if (2 <= j && visited[i][j-1]) continue;

                visited[i][j] = true;
                dfs(now_ladder+1, extra_ladder);
                visited[i][j] = false;
            }
        }
    }

    private static boolean chkPath() {
        for (int i = 1; i <= N; i++) {
            int now = i;
            for (int j = 1; j <= H; j++) {
                if (visited[j][now]) now++;
                else if (now >= 2 && visited[j][now-1]) now--;
            }
            if (now != i) return false;
        }
        return true;
    }
}