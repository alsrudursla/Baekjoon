import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> members = new HashMap<>();
        Queue<String[]> orders = new LinkedList<>();
        
        // 1. 기록 입력받기
        for (int i = 0; i < record.length; i++) {
            String[] input = record[i].split(" ");
            String action = input[0];
            String uid = input[1];
            
            if (action.equals("Enter")) {
                members.put(uid, input[2]);
                orders.add(new String[]{uid, "Enter"});
            } else if (action.equals("Leave")) {
                orders.add(new String[]{uid, "Leave"});
            } else {
                // 닉네임 변경의 경우 해당 uid 가진 key 를 map 에서 찾기, 변경
                members.put(uid, input[2]);
            }
        }
        
        // 2. 제출 형식으로 변환
        String[] answer = new String[orders.size()];
        int aidx = 0;
        while (!orders.isEmpty()) {
            String[] now = orders.poll();
            String uid = now[0];
            String action = now[1];
            String nickname = members.get(uid);
            
            if (action.equals("Enter")) {
                String str = nickname + "님이 들어왔습니다.";
                answer[aidx++] = str;
            } else {
                String str = nickname + "님이 나갔습니다.";
                answer[aidx++] = str;
            }
        }
        return answer;
    }
}