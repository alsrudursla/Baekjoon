/*
- 문자열에서 공백을 기준으로 나누기
*/
import java.io.*;
import java.util.*;

class Solution {
    public String solution(String s) {
        String[] number = s.split(" ");
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < number.length; i++) {
            max = Math.max(Integer.parseInt(number[i]), max);
            min = Math.min(Integer.parseInt(number[i]), min);
        }
    
        String answer = String.valueOf(min) + " " + String.valueOf(max);
        return answer;
    }
}