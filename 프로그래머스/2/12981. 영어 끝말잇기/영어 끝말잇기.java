import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        // 가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지
        int[] answer = new int[2];

        HashSet<String> myHash = new HashSet<>();
        
        int idx = 1;
        int turns = 1;
        char before_end_word = words[0].charAt(words[0].length()-1);
        for (int i = 0; i < words.length; i++) {
            // 같은 단어 말하면 탈락
            if (myHash.contains(words[i])) {
                answer[0] = idx;
                answer[1] = turns;
                break;
            }
            
            // 끝말잇기 실패 시 탈락
            char now_start_word = words[i].charAt(0);
            if (i != 0) {
                if (now_start_word != before_end_word) {
                    answer[0] = idx;
                    answer[1] = turns;
                    break;
                }
            } 
            
            myHash.add(words[i]);
            idx++;
            before_end_word = words[i].charAt(words[i].length()-1);
            
            if (idx > n) {
                idx = 1;
                turns++;
            }
        }

        return answer;
    }
}