/*
- 곱을 최대로 만들려면 원소들이 최대한 균등 해야 해
- 나머지를 한 개의 원소에 몰아주면 안 되고, 여러 개의 원소에 나눠야 해
*/
import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        // 집합 존재 X
        if (s < n) return new int[]{-1};
        
        // 균등한 원소 -> 평균값 구하기
        int base = s / n;
        int[] answer = new int[n];
        Arrays.fill(answer, base);
        
        // 나누어 떨어지지 않았을 때, 원소들 값이 모두 같지는 않음
        // 나머지만큼 원소 개수에 1 더해주기
        if (s % n != 0) {
            int iter = s % n;
            int idx = answer.length-1;
            for (int i = 0; i < iter; i++) {
                answer[idx--]++;
            }
        }
        
        return answer;
    }
}