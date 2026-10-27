import java.util.*;
class Solution {
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    String[][] map;
    public int solution(String[] maps) {
        map = new String[maps.length][maps[0].length()];
        int si = 0, sj = 0, ei = 0, ej = 0, li = 0, lj = 0;
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                map[i][j] = String.valueOf(maps[i].charAt(j));
                if (map[i][j].equals("S")) {
                    si = i;
                    sj = j;
                } else if (map[i][j].equals("L")) {
                    li = i;
                    lj = j;
                } else if (map[i][j].equals("E")) {
                    ei = i;
                    ej = j;
                }
            }
        }
        
        // 레버까지 최단 시간 + 레버에서 출구까지 최단시간
        int sToL = bfs(si, sj, li, lj);
        int lToE = bfs(li, lj, ei, ej);
        if (sToL == -1 || lToE == -1) return -1;
        else return (sToL + lToE);
    }
    
    private int bfs(int si, int sj, int ei, int ej) {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{si, sj, 0});
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[si][sj] = true;
        
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowTime = now[2];
            
            if (nowY == ei && nowX == ej) return nowTime; 
            
            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];
                
                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (!visited[nextY][nextX] && !map[nextY][nextX].equals("X")) {
                        visited[nextY][nextX] = true;
                        myqueue.add(new int[]{nextY, nextX, nowTime+1});
                    }
                }
            }
        }
        
        return -1;
    }
}