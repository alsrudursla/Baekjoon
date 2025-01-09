class Solution {
    public int solution(int[] arr) {
        // 배열에서 가장 큰 수
        int max_num = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int now_num = arr[i];
            if (now_num > max_num) {
                max_num = now_num;
            }
        }
        
        while (true) {
            max_num++;
            boolean chk = true;
            for (int i = 0; i < arr.length; i++) {
                if (max_num % arr[i] != 0) {
                    chk = false;
                    break;
                }
            }
            
            if (chk) break;
        }
        return max_num;
    }
}