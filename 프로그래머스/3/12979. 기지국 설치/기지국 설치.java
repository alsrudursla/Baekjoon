import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        // 앞에서부터 필요할 때만 설치하기 O(N)
        int answer = 0;
        int sidx = 0;
        int now_loc = 1;
        while (now_loc <= n) {
            // System.out.println(now_loc);
            // 모든 기지국을 지남
            if (sidx >= stations.length) {
                // System.out.println("설치 : " + (now_loc+w));
                answer++;
                now_loc = (now_loc + w + w + 1);
                continue;
            }
            
            // 기지국 범위에 포함되는지 확인
            int llimit = stations[sidx] - w;
            int rlimit = stations[sidx] + w;
            if (now_loc >= llimit && now_loc <= rlimit) {
                // 범위에 포함되면 스킵
                now_loc = rlimit + 1;
                sidx++;
            } else {
                // 기지국 추가 증설 필요
                // 설치 위치 : now_loc + w
                // System.out.println("설치 : " + (now_loc+w));
                answer++;
                now_loc = (now_loc + w + w + 1);
            }
        }
        
        return answer;
    }
}