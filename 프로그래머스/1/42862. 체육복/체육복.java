import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 옷 개수 배열
        int[] clothes = new int[n];
        Arrays.fill(clothes, 1);
        for (int i = 0; i < lost.length; i++) clothes[lost[i]-1]--;
        for (int i = 0; i < reserve.length; i++) clothes[reserve[i]-1]++;
        
        // 왼쪽 먼저 빌리고 안 되면 오른쪽 빌린다
        for (int i = 0; i < n; i++) {
            if (clothes[i] == 0) {
                if (i > 0 && clothes[i-1] > 1) {
                    clothes[i]++;
                    clothes[i-1]--;
                } else if (i < n-1 && clothes[i+1] > 1) {
                    clothes[i]++;
                    clothes[i+1]--;
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (clothes[i] >= 1) answer++;
        }
        
        return answer;
    }
}