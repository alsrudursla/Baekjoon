/*
- HashMap<Key, Value>
- 원소 넣기 : put
- 원소 조회 : get
- for문 돌기 : keySet()
*/

import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> myHash = new HashMap<>();
        for (String p : participant) myHash.put(p, myHash.getOrDefault(p, 0) + 1);
        for (String c : completion) myHash.put(c, myHash.get(c) - 1);
        
        String answer = "";
        for (String person : myHash.keySet()) {
            if (myHash.get(person) == 1) {
                answer += person;
            }
        }
        
        return answer;
    }
}