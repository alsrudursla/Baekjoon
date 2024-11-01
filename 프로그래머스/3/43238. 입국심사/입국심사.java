/*
- 이분 탐색 : 탐색 범위를 반으로 나누어 탐색
- 우리가 찾아야 할 값은 모든 사람이 심사를 받는데 걸리는 시간의 최솟값이다.
- 그렇다면 시간의 범위는 0 ~ 가장 오래 걸린 심사시간 일 것이다.
- left : 0 , right : 모든 사람이 가장 오래걸리는 심사대에서 심사를 받은 시간
*/
import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long left = 0;
        Arrays.sort(times);
        long right = (long)times[times.length - 1] * (long)n;
        
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2; // 시간
            long person = 0;
            for (int i = 0; i < times.length; i++) {
                person += mid / (long)times[i];
            }
            if (person >= (long)n) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}