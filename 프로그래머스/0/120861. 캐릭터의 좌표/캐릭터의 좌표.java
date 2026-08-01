class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        int maxUp = board[1] / 2;
        int minDown = -(board[1] / 2);
        int maxRight = board[0] / 2;
        int minLeft = -(board[0] / 2);
        int nowY = 0;
        int nowX = 0;
        
        for (int i = 0; i < keyinput.length; i++) {
            if (keyinput[i].equals("up")) {
                if (nowY + 1 <= maxUp) nowY++;
            } else if (keyinput[i].equals("right")) {
                if (nowX + 1 <= maxRight) nowX++;
            } else if (keyinput[i].equals("down")) {
                if (nowY - 1 >= minDown) nowY--;
            } else {
                if (nowX - 1 >= minLeft) nowX--;
            }
        }
        
        answer[0] = nowX;
        answer[1] = nowY;
        return answer;
    }
}