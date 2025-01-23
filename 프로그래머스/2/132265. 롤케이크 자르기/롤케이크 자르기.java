import java.util.*;
class Solution {
    public int solution(int[] topping) {
        // 토핑 번호, 개수 저장
        HashMap<Integer, Integer> tp_map = new HashMap<>();
        for (int i = 0; i < topping.length; i++) {
            tp_map.put(topping[i], tp_map.getOrDefault(topping[i], 0) + 1);
        }
        
        HashSet<Integer> tp_set = new HashSet<>();
        int answer = 0;
        for (int i = 0; i < topping.length; i++) {
            int now = topping[i];
            tp_set.add(now);
            tp_map.put(now, tp_map.get(now) - 1);
            if (tp_map.get(now) == 0) tp_map.remove(now);
            if (tp_map.size() == tp_set.size()) answer++;
        }
        
        return answer;
    }
}