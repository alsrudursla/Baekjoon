class Solution {
    public String solution(String s) {
        String answer = "";
        boolean chk = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                answer += s.charAt(i);
                chk = true;
                continue;
            }
            if (chk) {
                answer += Character.toUpperCase(s.charAt(i));
                chk = false;
            } else {
                answer += Character.toLowerCase(s.charAt(i));
            }
        }
        
        return answer;
    }
}