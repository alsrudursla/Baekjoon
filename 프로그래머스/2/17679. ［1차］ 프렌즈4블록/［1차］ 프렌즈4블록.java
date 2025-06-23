class Solution {
    String[][] map; // 게임판
    int ans; // 지워지는 블록
    boolean[][] visited; // 지워질 영역 체크
    public int solution(int m, int n, String[] board) {
        map = new String[m][n];
        for (int i = 0; i < m; i++) {
            String line = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = String.valueOf(board[i].charAt(j));
            }
        }
        
        // 1. 2x2 체크
        // 2. 2x2 영역 삭제
        // 3. 사라진 부분 채우기
        // 4. 2x2 체크
        // 5. 2x2 영역 삭제
        
        ans = 0;
        while (true) {
            visited = new boolean[m][n];
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (map[i][j].equals("Empty")) continue;
                    checkArea(i, j);
                }
            }
            
            // 바뀐 부분이 없으면 끝내기
            boolean chk = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        chk = true;
                        break;
                    }
                }
                if(chk) break;
            }
            
            if (!chk) break;
            
            removeArea(m, n);
            move(m, n);
        }
        
        return ans;
    }
    
    public void checkArea(int i, int j) {
        // 확인할 영역 : 2x2
        boolean chk = true;
        String block = map[i][j];
        if (!map[i+1][j].equals(block) || !map[i][j+1].equals(block) || !map[i+1][j+1].equals(block)) chk = false; 
        if (chk) {
            visited[i][j] = true;
            visited[i+1][j] = true;
            visited[i][j+1] = true;
            visited[i+1][j+1] = true;
        }
    }
    
    public void removeArea(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    ans++;
                    map[i][j] = "Empty";
                }
            }
        }
    }
    
    public void move(int m, int n) {
        for (int i = m-1; i >= 1; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].equals("Empty")) {
                    int before_i = i-1;
                    while (map[before_i][j].equals("Empty")) {
                        if (before_i == 0) break;
                        before_i--;
                    }
                    
                    map[i][j] = map[before_i][j];
                    map[before_i][j] = "Empty";
                }
            }
        }
    }
}