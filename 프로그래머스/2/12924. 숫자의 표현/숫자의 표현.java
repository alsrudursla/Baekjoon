class Solution {
    static int tmp;
    static int matchingNum;
    public int solution(int n) {
        matchingNum = n;
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            tmp = 0;
            boolean chk = Finn(i);
            if (chk) answer++;
        }
        
        return answer;
    }
    
    private static boolean Finn(int n) {
        tmp += n;
        if (tmp == matchingNum) return true;
        if (tmp > matchingNum) return false;
        return Finn(n+1);
    }
}