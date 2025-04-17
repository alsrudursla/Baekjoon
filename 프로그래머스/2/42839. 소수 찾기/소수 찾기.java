import java.util.*;
class Solution {
    int answer;
    boolean[] visited;
    HashSet<Integer> myHash;
    public int solution(String numbers) {
        char[] charArr = numbers.toCharArray();
        
        // 조합하기
        answer = 0;
        myHash = new HashSet<>();
        for (int i = 1; i <= numbers.length(); i++) {
            // 나올 수 있는 수의 길이는 나 자신 ~ 전체 이어붙인 값
            visited = new boolean[numbers.length()];
            dfs(i, "", charArr);
        }
        
        return answer;
    }
    
    private void dfs(int len, String nowNum, char[] charArr) {
        if (len == nowNum.length()) { // len 길이일 때
            // 1글자인데 0일 경우 -> chkPrimeNumber 에서 걸러짐
            // 2글자 이상인데 처음이 0일 경우 -> 0을 제외한 수로 변경
            if (nowNum.length() >= 2 && nowNum.charAt(0) == '0') {
                String newNum = "";
                for (int i = 1; i < nowNum.length(); i++) {
                    newNum += nowNum.charAt(i);
                }
                nowNum = newNum;
            }
            
            // 소수인지 확인 && 중복되지 않는 값
            if (chkPrimeNumber(Integer.valueOf(nowNum)) && !myHash.contains(Integer.valueOf(nowNum))) {
                // System.out.println(Integer.valueOf(nowNum));
                myHash.add(Integer.valueOf(nowNum));
                answer++;
            }
        }
        
        for (int i = 0; i < charArr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(len, nowNum + charArr[i], charArr);
            visited[i] = false;
        }
    }
    
    private boolean chkPrimeNumber(int num) {
        // 소수 : 나와 1을 제외하고 나뉘어지는 값이 없어야 함
        if (num == 1 || num == 0) return false;
        if (num == 2) return true;
        for (int i = 2; i <= num-1; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}