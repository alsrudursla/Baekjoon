import java.util.*;
class Solution {
    // URULDD
    List<int[]> pos;
    int characterX;
    int characterY;
    public int solution(String dirs) {
        char[] dir = dirs.toCharArray();
        pos = new ArrayList<>(); // [x1, y1, x2, y2]
        characterX = 0;
        characterY = 0;
        int answer = 0;
        
        for (int i = 0; i < dir.length; i++) {
            boolean chk = true;
            int beforeX = characterX;
            int beforeY = characterY;
            
            // 이동
            if (dir[i] == 'U') { // 위
                if (chkPosition(characterX, characterY+1)) characterY++;
                else chk = false;
            } else if (dir[i] == 'D') { // 아래
                if (chkPosition(characterX, characterY-1)) characterY--;
                else chk = false;
            } else if (dir[i] == 'R') { // 오른쪽
                if (chkPosition(characterX+1, characterY)) characterX++;
                else chk = false;
            } else { // 왼쪽
                if (chkPosition(characterX-1, characterY)) characterX--;
                else chk = false;
            }
            
            if (chk) {
                // 처음 걷는 길인지 판단
                if (chkNewPath(beforeX, beforeY, characterX, characterY)) answer++;
            }
        }
        
        return answer;
    }
    
    public boolean chkNewPath(int x1, int y1, int x2, int y2) {
        
        boolean chk = true;
        for (int i = 0; i < pos.size(); i++) {
            // 이미 걸어본 길
            if (x1 == pos.get(i)[0] && y1 == pos.get(i)[1] &&
               x2 == pos.get(i)[2] && y2 == pos.get(i)[3]) {
                chk = false;
                break;
            }
            
            // 이미 걸어본 길 (반대 방향으로)
            if (x2 == pos.get(i)[0] && y2 == pos.get(i)[1] &&
               x1 == pos.get(i)[2] && y1 == pos.get(i)[3]) {
                chk = false;
                break;
            }
        }
        
        if (chk) {
            pos.add(new int[]{x1, y1, x2, y2});
            return true;
        } else {
            return false;
        }
        
    }
    
    public boolean chkPosition(int x, int y) {
        if (-5 <= x && x <= 5 && -5 <= y && y <= 5) return true;
        else return false;
    }
}