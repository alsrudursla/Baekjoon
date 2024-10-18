/*
- String 도 정렬 가능
- 더 큰 수 Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
- 0 으로 시작할 경우 고려!!
*/
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);    
        }
        
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        String answer = "";
        
        for (int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }
        
        if (answer.startsWith("0")) {
            return "0";
        }
        
        return answer;
    }
}