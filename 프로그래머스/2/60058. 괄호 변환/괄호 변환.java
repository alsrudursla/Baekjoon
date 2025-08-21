import java.util.*;
class Solution {
    public String solution(String p) {
        return recursion(p);
    }
    
    private String recursion(String p) {
        if (p.length() == 0) return "";
        
        // u, v 분리
        int cnt = 1;
        int idx = 0;
        for (int i = 1; i < p.length(); i++) {
            char now = p.charAt(i);
            if (now == p.charAt(0)) cnt++;
            else cnt--;
            
            idx++;
            if (cnt == 0) break;
        }
        
        String u = p.substring(0, idx+1);
        String v = p.substring(idx+1, p.length());
        
        if (chkString(u)) return u + recursion(v);
        else return "(" + recursion(v) + ")" + flip(u);
    }
    
    private String flip(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < str.length()-1; i++) {
            char now = str.charAt(i);
            if (now == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
    
    // 올바른 괄호 문자열인지 판별
    private boolean chkString(String str) {
        Stack<Character> myStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            
            if (myStack.isEmpty()) myStack.push(now);
            else {
                if (now == '(') myStack.push(now);
                else {
                    if (myStack.peek() == '(') myStack.pop();
                    else return false;
                }
            }
        }
        
        if (myStack.size() != 0) return false;
        else return true;
    }
}