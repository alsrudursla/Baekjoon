import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int bmax = 1;
        for (int i = 0; i < B.length; i++) {
            bmax = Math.max(bmax, B[i]);
        }
        
        int bid = 0;
        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            if (bid == B.length) break;
            if (A[i] < B[bid]) {
                answer++;
                bid++;
            } else {
                if (A[i] > bmax) break;
                for (int j = bid; j < B.length; j++) {
                    if (A[i] < B[j]) {
                        answer++;
                        bid = j+1;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}