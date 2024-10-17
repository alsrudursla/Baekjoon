/* 
- BFS 알고리즘에서 도착 지점에 처음 도달했을 때의 거리 값을 반환하면, 이는 최단 거리임을 의미합니다
- 범위 유의
- 주어진 이차원 배열 전체 크기 : maps.length;
- 원소 배열 크기 : maps[0].length;
*/
import java.util.*;

class Solution {
    static int[] dy = {0,0,1,-1}; // 동서남북
    static int[] dx = {1,-1,0,0};
    static int N,M;
    static int answer = 0;
    public int solution(int[][] maps) {
        // 0 이 벽, 1 이 길
        // (0,0) ~ (n-1,m-1)
        
        N = maps.length;
        M = maps[0].length;
        
        bfs(maps);
        return answer;
    }
    private static void bfs(int[][] maps) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{0,0,1}); // i,j,distance
        visited[0][0] = true;
        
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];
            int now_distance = now[2];
            
            if (now_y == N-1 && now_x == M-1) {
                answer = now_distance;
                return;
            }
            
            for (int k = 0; k < 4; k++) {
                int y = now_y + dy[k];
                int x = now_x + dx[k];
                
                if (0 <= x && x < M && 0 <= y && y < N && maps[y][x] != 0) {
                    if (!visited[y][x]) {
                        visited[y][x] = true;
                        myqueue.add(new int[]{y,x,now_distance+1});
                        answer++;
                    }
                }
            }
        }
        
        answer = -1;
    }
}