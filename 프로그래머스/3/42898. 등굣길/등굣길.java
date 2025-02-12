class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n+1][m+1];
        
        // 물웅덩이 표시
        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        // dp 거리 계산 (오른쪽, 아래로만 움직임)
        map[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue; // 집 pass
                if (map[i][j] == -1) continue; // 웅덩이 pass
                if (map[i-1][j] > 0) { // 윗칸 검사
                    map[i][j] += map[i-1][j] % 1000000007;
                }
                if (map[i][j-1] > 0) { // 왼쪽칸 검사
                    map[i][j] += map[i][j-1] % 1000000007;
                }
            }
        }
        
        int answer = map[n][m] % 1000000007;
        return answer;
    }
}