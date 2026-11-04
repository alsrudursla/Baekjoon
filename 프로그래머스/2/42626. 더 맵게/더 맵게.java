import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Integer num : scoville) pq.add(num);
        
        int answer = 0;
        while (pq.peek() < K) {
            if (pq.size() == 1) return -1;
            answer++;
            int food1 = pq.poll();
            int food2 = pq.poll();
            int newScoville = food1 + (food2 * 2);
            pq.add(newScoville);
        }
        return answer;
    }
}