import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static int[][] map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        // 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine()); // (4 ≤ N ≤ 20, N은 짝수)

        map = new int[N+1][N+1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 팀 짜기
        // 2. 능력치 계산

        ans = Integer.MAX_VALUE;
        visited = new boolean[N+1];
        dfs(1, 0); // 시작 인덱스 (팀 중복 방지), 팀원

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void dfs(int idx, int member) {
        if (member == N/2) {
            int diff = calculateScore();
            ans = Math.min(ans, diff);
            return;
        }

        for (int i = idx; i <= N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i+1, member+1);
            visited[i] = false;
        }
    }

    private static int calculateScore() {
        int start_score = 0;
        int link_score = 0;
        // i < j 조건을 활용하여 중복 연산 방지
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                if (visited[i] && visited[j]) {
                    start_score += map[i][j] + map[j][i];
                } else if (!visited[i] && !visited[j]) {
                    link_score += map[i][j] + map[j][i];
                }
            }
        }
        return Math.abs(start_score - link_score);
    }
}