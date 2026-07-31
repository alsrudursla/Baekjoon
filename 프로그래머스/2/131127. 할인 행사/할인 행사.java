import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[i]; j++) {
                wantMap.put(want[i], wantMap.getOrDefault(want[i], 0) + 1);
            }
        }
        
        int answer = 0;
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> tmpMap = new HashMap<>(wantMap);
            
            for (int j = i; j < i + 10; j++) {
                if (!tmpMap.containsKey(discount[j])) break;
                tmpMap.put(discount[j], tmpMap.get(discount[j]) - 1);
                if (tmpMap.get(discount[j]) == 0) tmpMap.remove(discount[j]);
            }
            
            if (tmpMap.isEmpty()) answer++;
        }
        
        return answer;
    }
}