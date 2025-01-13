class Solution
{
    public int solution(int n, int a, int b)
    { // 전체 사람 수, a 번호, b 번호
        
        int answer = 0;
        int roundA = a;
        int roundB = b;
        while (roundA != roundB) {
            if (roundA == roundB) break;
            if (roundA % 2 == 0) {
                roundA /= 2;
            } else {
                roundA = roundA / 2 + 1;
            }
            if (roundB % 2 == 0) {
                roundB /= 2;
            } else {
                roundB = roundB / 2 + 1;
            }
            answer++;
        }
        
        return answer;
    }
}