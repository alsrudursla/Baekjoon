import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> myMap = new HashMap<>();
        int cnt = clothes.length;
        for (int i = 0; i < cnt; i++) {
            myMap.put(clothes[i][1], myMap.getOrDefault(clothes[i][1],0)+1);
        }
        // 조합 - (전체 안 입은 경우의 수 1)
        int answer = 1;
        for (String key : myMap.keySet()) {
            // 의상을 입지 않는 경우도 고려해야 하므로, 각 의상 종류별로 (의상 개수 + 1)을 곱합니다
            answer *= myMap.get(key) + 1;
        }
        
        return answer-1;
    }
}