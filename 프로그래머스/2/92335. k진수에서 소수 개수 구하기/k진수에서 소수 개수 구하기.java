import java.util.*;
class Solution {
    String changed;
    public int solution(int n, int k) {
        // 1. k 진수로 바꾸기
        changed = "";
        ChangeNtoK(n, k);
        
        // 2. 규칙에 맞는 소수 개수 찾기
        int answer = FindPrimeNumber(changed);
        return answer;
    }
    
    // 소수 판별
    public boolean chkPrimeNumber(long number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        
        return true;
    }
    
    // 조건 맞는 소수 개수 반환
    public int FindPrimeNumber(String s) {
        int ans = 0;
        String[] s_split = s.split("0");
        
        for (int i = 0; i < s_split.length; i++) {
            if (s_split[i].equals("")) continue;
            if (chkPrimeNumber(Long.parseLong(s_split[i]))) ans++;
        }
        
        return ans;
    }
    
    public void ChangeNtoK(int n, int k) {
        // 몫을 나눌 수 없을 때까지 진행
        int quo = n;
        int remain = 0;
        while (quo >= k) {
            quo = n / k;
            remain = n % k;
            changed = remain + changed;
            
            n /= k;
        }
        changed = quo + changed;
    }
}