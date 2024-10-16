/*
- 틀린 이유 : () 의 개수만 맞으면 된다고 생각 but for 문 도중 ) 로 먼저 시작하면 안됨!!
- ( 로 시작해서 ) 로 닫히기
- 반례 : ())((()))(()
*/
class Solution {
    boolean solution(String s) {        
        int chk = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) != '(') return false;
            if (i == s.length()-1 && s.charAt(i) != ')') return false;
            if (s.charAt(i) == ')') chk--;
            if (s.charAt(i) == '(') chk++;
            
            if (chk < 0) return false;
        }
        
        if (chk == 0) return true;
        return false;
    }
}