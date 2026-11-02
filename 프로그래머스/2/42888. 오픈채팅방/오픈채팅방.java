import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        // change 기록만 받아서 저장해놓기?
        HashMap<String, String> nameMap = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < record.length; i++) {
            String[] splitRecord = record[i].split(" ");
            String action = splitRecord[0];
            String uid = splitRecord[1];
            String name = "";
            
            if (!action.equals("Leave")) {
                name = splitRecord[2];
            }
            
            if (action.equals("Change")) {
                nameMap.put(uid, name);
                cnt++;
            }
            
            if (action.equals("Enter")) {
                nameMap.put(uid, name);
            }
        }
        
        String[] answer = new String[record.length - cnt];
        int cidx = 0;
        for (int i = 0; i < record.length; i++) {
            String[] splitRecord = record[i].split(" ");
            String action = splitRecord[0];
            String uid = splitRecord[1];
            String name = "";
            
            if (!action.equals("Leave")) {
                name = splitRecord[2];
            } else {
                name = nameMap.get(uid);
            }
            
            if (nameMap.containsKey(uid)) {
                name = nameMap.get(uid);
            }
            
            if (action.equals("Enter")) {
                answer[cidx++] = name + "님이 들어왔습니다.";
            } else if (action.equals("Leave")) {
                answer[cidx++] = name + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}