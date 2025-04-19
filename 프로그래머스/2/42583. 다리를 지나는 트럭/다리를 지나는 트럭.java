import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 시간을 큐로 관리
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) bridge.add(0);
        
        int now_weight = 0;
        int now_cnt = 0;
        int tidx = 0;
        while (!bridge.isEmpty()) {
            // 시간 경과
            answer++;
            
            // 대기 트럭이 없을 경우
            if (tidx == truck_weights.length) {
                bridge.poll();
                continue;
            }
            
            int now_truck_weight = truck_weights[tidx];
            
            // 맨 앞에 있는 트럭
            int popTruck = bridge.poll();
            if (popTruck != 0) {
                now_weight -= popTruck;
                now_cnt--;
            }
            
            // 들어갈 수 있으면 들어가기
            // 무게를 넘거나 최대 수가 넘어가면 못 들어감
            if (now_weight + now_truck_weight > weight || now_cnt == bridge_length) {
                bridge.add(0);
            }
            else {
                bridge.add(now_truck_weight);
                now_weight += now_truck_weight;
                now_cnt++;
                tidx++;
            }
        }
        
        return answer;
    }
}