/*
- 재귀를 사용했더니 시간 초과가 났다
- 재귀에 시간 초과라면 DP 를 고려하자
*/
class Solution {
    public int solution(int n) {
        int[] fibo = new int[n+1];
        fibo[0] = 0;
        fibo[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i-1] + fibo[i-2];
            fibo[i] %= 1234567;
        }
        
        return fibo[n];
    }
}