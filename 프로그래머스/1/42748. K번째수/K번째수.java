import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        // 전체 반복 횟수
        int testcase = commands.length;
        int[] answer = new int[testcase];
        for (int t = 0; t < testcase; t++) {
            // 현재 i, j, k
            int i = commands[t][0];
            int j = commands[t][1];
            int k = commands[t][2];
            
            int[] tmp = new int[j-i+1];
            int idx = 0;
            for (int l = i-1; l <= j-1; l++) {
                tmp[idx++] = array[l];
            }
            
            Arrays.sort(tmp);
            
            answer[t] = tmp[k-1];
        }
        
        return answer;
    }
}