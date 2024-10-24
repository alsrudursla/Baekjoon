/*
- 이 문제를 해결하는 방법은 각 명함의 가로와 세로 중 큰 값을 가로로 설정하고, 작은 값을 세로로 설정한 뒤, 최종적으로 가장 큰 가로와 세로 값을 찾아 계산하는 것입니다.
*/
class Solution {
    public int solution(int[][] sizes) {
        int width = Integer.MIN_VALUE;
        int height = Integer.MIN_VALUE;
        
        for (int i = 0; i < sizes.length; i++) {
            int[] now = sizes[i];
            int nw = Math.max(now[0], now[1]);
            int hw = Math.min(now[0], now[1]);
            
            width = Math.max(nw, width);
            height = Math.max(hw, height);
        }
        
        int answer = width * height;
        return answer;
    }
}