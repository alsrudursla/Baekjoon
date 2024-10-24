import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        ArrayList<Integer> myArr = new ArrayList<>();
        int tmp = 10;
        for (int i = 0; i < arr.length; i++) {
            if (tmp == 10) {
                myArr.add(arr[i]);
                tmp = arr[i];
            } else {
                if (arr[i] == tmp) continue;
                myArr.add(arr[i]);
                tmp = arr[i];
            }
        }
        
        int[] answer = new int[myArr.size()];
        int idx = 0;
        for (int var : myArr) {
            answer[idx++] = var;
        }

        return answer;
    }
}