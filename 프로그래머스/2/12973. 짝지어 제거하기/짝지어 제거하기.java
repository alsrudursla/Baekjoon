/*
- Stack 사용하려면 java.util 있어야 함
- 맨 위 원소 확인 : peek()
- 맨 위 원소 삭제 : pop()
- 원소 저장 : push()
- 문제 포인트 : 스택에 넣기 전에 현재 스택의 맨 위 원소랑 비교하기
*/
import java.util.*;
class Solution {
    public int solution(String s) {
        Stack<Character> st = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char now = s.charAt(i);
            
            if (!st.isEmpty() && st.peek() == now) {
                st.pop();
            } else {
                st.push(now);
            }
        }
        
        if (st.isEmpty()) {
            return 1;
        } else return 0;
    }
}

/* 시간 초과
- 문자열에서 특정 인덱스를 중간에서 삭제하고 싶다 : StringBuilder 사용
- 가변 문자열을 활용하려면 toString() 으로 다시 변환해주어야 한다
- 특정 인덱스를 삭제하고 나면, 남은 문자들의 인덱스가 바로 바뀐다

class Solution
{
    public int solution(String s)
    {
        boolean chk = true;
        
        while (chk) {
            if (s.length() == 0) return 1;
            
            chk = false;
            
            StringBuilder sb = new StringBuilder(s);
            
            for (int i = 0; i < sb.length()-1; i++) {
                char start_val = sb.charAt(i);
                char end_val = sb.charAt(i+1);
                
                if (start_val == end_val) {
                    chk = true;
                    sb.deleteCharAt(i);     // i 번째 문자 삭제
                    sb.deleteCharAt(i);     // i 번째 문자 삭제 후 인덱스가 변경됨 (i+1 번째 문자 삭제)
                    s = sb.toString();
                    break;
                }
            }
            
            if (!chk) break;
        }
        
        return 0;
    }
}
*/