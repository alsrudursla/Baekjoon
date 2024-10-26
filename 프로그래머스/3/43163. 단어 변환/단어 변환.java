import java.util.*;
class Solution {
    static boolean[] visited;
    static int answer = 0;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);

        return answer;
    }
    
    private static void dfs(String now, String target, String[] words, int step) {
        // 하나의 단어만 다를 경우 그 단어로 체인지
        // 바꾼 단어가 타깃이면 return
        // 바꾼 단어 다시 dfs
        // 더 이상 없을 때 처리
        
        if (now.equals(target)) {
            answer = step;
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            int chk = 0;
            for (int j = 0 ; j < now.length(); j++) {
                if (now.charAt(j) != words[i].charAt(j)) chk++;
            }
            
            if (chk == 1) {
                visited[i] = true;
                dfs(words[i], target, words, step+1);
                visited[i] = false;
            }
        }
    }
}