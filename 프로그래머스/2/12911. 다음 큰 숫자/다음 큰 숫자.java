class Solution {
    public int solution(int n) {
        int answer = 0;
        int ori_binary = binaryNumChk(n);
        
        while (true) {
            n += 1;
            // 2진수 변환했을 때 1의 개수 체크
            int new_binary = binaryNumChk(n);
            
            if (ori_binary == new_binary) {
                answer = n;
                break;
            }
        }
        
        return answer;
    }
    
    private static int binaryNumChk(int n) {
        String newS = Integer.toBinaryString(n);
        int answer = 0;
        for (int i = 0; i < newS.length(); i++) {
            if (newS.charAt(i) == '1') answer++;
        }
        return answer;
    }
}