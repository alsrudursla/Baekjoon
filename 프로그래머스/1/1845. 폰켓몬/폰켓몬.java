/*
- 중복 처리에는 HashSet !
- 값 추가 : add
- 값 삭제 : remove
- 비우기 : clear
- 원소 한 개씩 접근 불가 대신 contains(값)으로 있는지 없는지 확인 가능
*/
import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int mine_len = nums.length / 2;
        
        // 중복 제거 hashset
        HashSet<Integer> myhash = new HashSet<>();
        for (int num : nums) {
            myhash.add(num);
        }

        int answer = 0;
        if (myhash.size() >= mine_len) {
            answer = mine_len;
        } else {
            answer = myhash.size();
        }
        
        return answer;
    }
}