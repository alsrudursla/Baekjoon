import java.util.*;
class Solution {
    char[][] map;
    int startY, startX, endY, endX, leverY, leverX;
    public int solution(String[] maps) {
        // 1. 지도 정리
        map = new char[maps.length][maps[0].length()];
        startY = 0; startX = 0;
        endY = 0; endX = 0;
        leverY = 0; leverX = 0;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    startY = i;
                    startX = j;
                } else if (map[i][j] == 'E') {
                    endY = i;
                    endX = j;
                } else if (map[i][j] == 'L') {
                    leverY = i;
                    leverX = j;
                }
            }
        }
        
        int answer = findLeverNExit();
        return answer;
    }
    
    public int findLeverNExit() {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{startY, startX, 0});
        
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[startY][startX] = true;
        
        boolean findLever = false;
        
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowTime = now[2];
            
            if (findLever && nowY == endY && nowX == endX) {
                return nowTime;
            }
            
            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];
                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length && !visited[nextY][nextX] && map[nextY][nextX] != 'X') {
                    // 만약 레버 칸일 때
                    if (nextY == leverY && nextX == leverX && !findLever) {
                        findLever = true;
                        resetVisited(visited);
                        visited[leverY][leverX] = true;
                        myqueue = new LinkedList<>();
                        myqueue.add(new int[]{nextY, nextX, nowTime + 1});
                        break;
                    } else {
                        visited[nextY][nextX] = true;
                        myqueue.add(new int[]{nextY, nextX, nowTime + 1});
                    }
                }
            }
        }
        
        return -1;
    }
    
    public void resetVisited(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
    }
}