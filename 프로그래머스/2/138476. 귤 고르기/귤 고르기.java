import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        // 가장 많은 것부터 담기
        
        HashMap<Integer, Integer> myHash = new HashMap<>(); // 귤 크기, 개수
        
        for (int i = 0; i < tangerine.length; i++) {
            myHash.put(tangerine[i], myHash.getOrDefault(tangerine[i], 0) + 1);
        }
        
        // 귤 개수로 정렬
        List<Map.Entry<Integer, Integer>> myList = new ArrayList<>(myHash.entrySet());
        myList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        
        int answer = 0;
        int now_tan_num = 0;
        for (Map.Entry<Integer, Integer> tan : myList) {
            int now_size = tan.getKey();
            int now_number = tan.getValue();
            
            now_tan_num += now_number;
            answer++;
            
            if (now_tan_num >= k) break;
        }
        
        return answer;
    }
}