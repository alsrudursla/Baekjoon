/*
- union-find
- hashset : 중복을 허용하지 않음
*/
import java.util.*;
class Solution {
    static int[] arr;
    public int solution(int n, int[][] computers) {
        // union - find ??? 최초의 연결 시작점을 찾아야 되는 거 아닌감
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        
        int N = computers.length;
        int M = computers[0].length;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (computers[i][j] == 1) {
                    if (i == j) continue;
                    change_val(i, j);
                }
            }
        }
        
        HashSet<Integer> myhash = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            myhash.add(find(arr[i]));
        }
        
        return myhash.size();
    }
    
    private static void change_val(int i, int j) {
        i = find(i);
        j = find(j);
        
        if (i != j) {
            if (i < j) {
                arr[j] = i;
            } else {
                arr[i] = j;
            }
        } 
    }
    
    private static int find(int num) {
        if (num == arr[num]) return num;
        return arr[num] = find(arr[num]);
    }
}