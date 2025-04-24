/*
- number : 1,000,000 "자리" 이하 숫자 -> (1000000 이하인 수라는 게 아님!! 자리 수가 백만)
  - ex) 2 -> 1자리, 10 -> 2자리, 12345678910 -> 11자리
- 수가 너무 커서 dfs + 백트래킹으로 구하면 무조건 시간 초과
- Integer.valueOf() 로 변환할 때 int 범위 넘어서 런타임 에러
  - int 범위 : -2,147,483,648부터 2,147,483,647까지
*/

import java.util.*;
class Solution { 
    public String solution(String number, int k) {
        Stack<String> myStack = new Stack<>();
        int erased = 0;
        for (int i = 0; i < number.length(); i++) {
            // 지우는 횟수 끝 or 빈 스택
            if (erased == k || myStack.isEmpty()) myStack.push(String.valueOf(number.charAt(i)));
            else {
                String now = String.valueOf(number.charAt(i));
                while (!myStack.isEmpty()) {
                    if (erased == k) break;
                    String top = myStack.peek();
                    
                    if (Integer.valueOf(top) < Integer.valueOf(now)) {
                        myStack.pop();
                        erased++;
                    } else break;
                }
                myStack.push(now);
            }
        }
        
        while (erased < k) {
            myStack.pop();
            erased++;
        }
        
        String answer = "";
        for (String st : myStack) answer += st;
        
        return answer;
    }
}