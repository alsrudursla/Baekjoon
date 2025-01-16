import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        List<Long> myList = new ArrayList<>();
        // left ~ right 까지만 돌기
        for (long i = left; i <= right; i++) {
            long col = i/n + 1;
            long row = i%n + 1;
            long val = Math.max(col, row);
            myList.add(val);
        }
         
        int[] answer = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            answer[i] = myList.get(i).intValue();
        }
        
        return answer;
    }
}