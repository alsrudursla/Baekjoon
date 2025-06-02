import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
        }
        
        for (int i = 0; i < queue2.length; i++) {
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        // 최대 연산 횟수 : 옮기기 큐 2개 + 비교 연산 -> O(3N)
        int maxOps = queue1.length * 3;
        int nowOps = 0;
        int ans = 0;
        while (nowOps <= maxOps) {
            if (sum1 == sum2) return ans;
            else if (sum1 > sum2) {
                int val = q1.poll();
                sum1 -= val;
                
                q2.add(val);
                sum2 += val;
                ans++;
            } else {
                int val = q2.poll();
                sum2 -= val;
                
                q1.add(val);
                sum1 += val;
                ans++;
            }
            nowOps++;
        }
        
        return -1;
    }
}