import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        // 삼각형은 2차원 배열로 접근
        // 왼쪽 대각선 : [i+1][똑같은j]
        // 오른쪽 대각선 : [i+1][j+1]
        
        // DP
        // triangle 배열 자체를 dp 배열로 사용하기
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) { // 왼쪽 끝
                    triangle[i][j] += triangle[i-1][j];
                } else if (j == (triangle[i].length - 1)) { // 오른쪽 끝
                    triangle[i][j] += triangle[i-1][j-1];
                } else {
                    triangle[i][j] = Math.max(triangle[i][j] + triangle[i-1][j], triangle[i][j] + triangle[i-1][j-1]);
                }
                
            }
        }
        
        int answer = 0;
        // 마지막 줄
        int last_line = triangle.length - 1;
        for (int i = 0; i < triangle[last_line].length; i++) {
            answer = Math.max(answer, triangle[last_line][i]);
        }
        return answer;
    }
}