class Solution {
    public int[] solution(String s) {
        int howmanytimes = 0;
        int zeroNum = 0;
        while (!s.equals("1")) {
            howmanytimes++;
            String tmp = "";
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') tmp += s.charAt(i);
                else zeroNum++;
            }
        
            int tmp2 = tmp.length();
            String newS = Integer.toBinaryString(tmp2);
            s = newS;
        }
        
        int[] answer = new int[2];
        answer[0] = howmanytimes;
        answer[1] = zeroNum;
        return answer;
    }
}