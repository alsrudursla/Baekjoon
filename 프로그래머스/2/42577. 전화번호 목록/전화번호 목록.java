import java.io.*;
import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        // phone_book : 전화번호 배열 (중복 X)
        // 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true
        boolean answer = true;
        
        // 정렬
        Arrays.sort(phone_book);
        
        // 앞 뒤로 비교
        for (int i = 0; i < phone_book.length-1; i++) {
            if (phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
            }
        }
        
        return answer;
    }
}