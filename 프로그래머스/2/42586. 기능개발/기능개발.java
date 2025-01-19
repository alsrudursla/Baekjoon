import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int[] date_arr = new int[progresses.length]; // 각각 완료되는 기간
        for (int i = 0; i < progresses.length; i++) {
            int now_progress = progresses[i];
            int now_speed = speeds[i];
            
            int date = 0;
            while (now_progress < 100) {
                date++;
                now_progress += now_speed;
            }
            date_arr[i] = date;
        }
        
        List<Integer> finished = new ArrayList<>();
        boolean[] visited = new boolean[date_arr.length];
        for (int i = 0; i < date_arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            int today = date_arr[i];
            int finished_num = 1;
            for (int j = 1; j < date_arr.length; j++) {
                if (today >= date_arr[j] && !visited[j] && visited[j-1]) {
                    // 작업 완료 기간 비교, 이전 기능 완료 여부 반영
                    finished_num++;
                    visited[j] = true;
                }
            }
            finished.add(finished_num);
        }
        
        int[] answer = new int[finished.size()];
        for (int i = 0; i < finished.size(); i++) {
            answer[i] = finished.get(i).intValue();
        }
        
        return answer;
    }
}