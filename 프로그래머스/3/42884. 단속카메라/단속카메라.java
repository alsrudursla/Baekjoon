import java.util.Arrays;
class Solution {
    public int solution(int[][] routes) {
        // 종료 지점 기준 정렬
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        int answer = 0;
        int before_cctv_loc = 0;
        for (int i = 0; i < routes.length; i++) {
            if (i == 0) {
                before_cctv_loc = routes[i][1];
                answer++;
            } else {
                if (routes[i][0] > before_cctv_loc) {
                    before_cctv_loc = routes[i][1];
                    answer++;
                } else continue;
            }
        }
        return answer;
    }
}