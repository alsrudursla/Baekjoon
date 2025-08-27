class Solution {
    public int solution(int[][] board, int[][] skill) {
        // 1. 2차원 누적합 배열 만들기
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        // 2. skill 실행하기
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int startY = skill[i][1];
            int startX = skill[i][2];
            int endY = skill[i][3];
            int endX = skill[i][4];
            int degree = skill[i][5];
            
            // 모서리에 기록하기
            if (type == 1) {
                // attack
                sum[startY][startX] -= degree;
                sum[startY][endX+1] += degree;
                sum[endY+1][startX] += degree;
                sum[endY+1][endX+1] -= degree;
            } else {
                // recovery
                sum[startY][startX] += degree;
                sum[startY][endX+1] -= degree;
                sum[endY+1][startX] -= degree;
                sum[endY+1][endX+1] += degree;
            }
        }
        
        // 3. 누적합 배열 완성하기
        // 3-1. 행 단위
        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j < sum[i].length; j++) {
                sum[i][j] += sum[i][j-1];
            }
        }
        
        // 3-2. 열 단위
        for (int j = 0; j < sum[0].length; j++) {
            for (int i = 1; i < sum.length; i++) {
                sum[i][j] += sum[i-1][j];
            }
        }
        
        // 4. 맵에 반영 && 파괴되지 않은 건물 개수 구하기
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += sum[i][j];
                if (board[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
}