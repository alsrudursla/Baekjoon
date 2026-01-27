import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 아이디어 : 칸 마다 가중치가 다르다 -> DFS/BFS 불가
        // 1) 음수가 포함된 가치? : 벨만 포드 알고리즘 (안되는 이유 : 같은 칸을 통해서 더 좋은 선택지가 있을 수 있음)
        // 2) (i, j) 에 도착했을 때 최대 가치 구하기 : 다이나믹 프로그래밍

        // 위에서 아래로 내려가는 구조

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) dp[i][j] = Integer.MIN_VALUE;
        }

        dp[0][0] = map[0][0];
        // 첫 번째 줄은 (0, 0) 에서부터 가는 방법 밖에 없음
        for (int j = 1; j < M; j++) dp[0][j] = dp[0][j-1] + map[0][j];

        // i, j 에 도착했을 때 최대 가치 구하기
        for (int i = 1; i < N; i++) {
            // 한 행에서 로봇은:
                // 왼쪽에서 와서 -> 오른쪽으로 쭉 가거나
                // 오른쪽에서 와서 -> 왼쪽으로 쭉 감 (한 번씩만 탐사 가능)
            
            // 1. 왼쪽 or 위 에서 온 경우
            int[] left = new int[M];
            for (int j = 0; j < M; j++) {
                if (j == 0) left[j] = dp[i-1][j] + map[i][j]; // 왼쪽이 없으니까
                else left[j] = Math.max(dp[i-1][j], left[j-1]) + map[i][j];
            }
            
            // 2. 오른쪽 or 위 에서 온 경우
            int[] right = new int[M];
            for (int j = M-1; j >= 0; j--) {
                if (j == M-1) right[j] = dp[i-1][j] + map[i][j];
                else right[j] = Math.max(dp[i-1][j], right[j+1]) + map[i][j];
            }

            // 3. 더 큰 값 저장
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        bw.write(String.valueOf(dp[N-1][M-1]));
        bw.flush();
        bw.close();
    }
}