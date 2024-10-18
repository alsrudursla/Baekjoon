/*
- 숫자 문자열 정렬 : 앞에서부터 하나씩 비교 ex. 10 보다 2 가 뒤에 온다
- 정렬한 후, 인접한 두 전화번호만 비교해도 접두사인지 쉽게 확인할 수 있습니다. 왜냐하면, 문자열 배열을 정렬하면 접두사가 있는 경우, 접두사 관계에 있는 전화번호가 인접하게 배치되기 때문입니다.
*/

import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book); 
        
        for (int t = 0; t < phone_book.length - 1; t++) {
            // 앞 번호가 뒤 번호의 접두사인지 확인
            if (phone_book[t+1].startsWith(phone_book[t])) answer = false;
        }
        
        return answer;
    }
}