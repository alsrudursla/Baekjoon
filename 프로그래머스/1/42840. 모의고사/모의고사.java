import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        // 1 : 1 2 3 4 5
        // 2 : 2 1 2 3 2 4 2 5
        // 3 : 3 3 1 1 2 2 4 4 5 5
        
        List<int[]> myList = new ArrayList<>();
        myList.add(new int[]{1,2,3,4,5});
        myList.add(new int[]{2,1,2,3,2,4,2,5});
        myList.add(new int[]{3,3,1,1,2,2,4,4,5,5});
        
        List<int[]> tmpList = new ArrayList<>();
        int max_tmp = 0;
        for (int i = 0; i < 3; i++) {
            int tmp = 0;
            int list_idx = 0;
            
            for (int j = 0; j < answers.length; j++) {
                if (list_idx == myList.get(i).length) {
                    list_idx = 0;
                }
                
                if (answers[j] == myList.get(i)[list_idx++]) {
                    tmp++;
                }
            }
            
            tmpList.add(new int[]{i+1, tmp});
            max_tmp = Math.max(tmp, max_tmp);
        }
        
        int num = 0;
        for (int i = 0; i < tmpList.size(); i++) {
            if (tmpList.get(i)[1] == max_tmp) {
                num++;
            }
        }
        
        int[] answer;
        if (num == 1) {
            answer = new int[1];
            for (int i = 0; i < tmpList.size(); i++) {
                if (tmpList.get(i)[1] == max_tmp) {
                    answer[0] = tmpList.get(i)[0];
                }
            }
        } else {
            answer = new int[num];
            int idx = 0;
            for (int i = 0; i < tmpList.size(); i++) {
                if (tmpList.get(i)[1] == max_tmp) {
                    answer[idx++] = tmpList.get(i)[0];
                }
            }
            
            Arrays.sort(answer);
        }
        
        return answer;
    }
}