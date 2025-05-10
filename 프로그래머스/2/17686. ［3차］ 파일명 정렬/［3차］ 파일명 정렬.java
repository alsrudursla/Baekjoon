import java.util.*;
class Solution {
    public class File {
        String name;
        String head;
        int number;
        int oriNum;
        File (String name, String head, int number, int oriNum) {
            this.name = name;
            this.head = head;
            this.number = number;
            this.oriNum = oriNum;
        }
    }
    public String[] solution(String[] files) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        HashSet<Character> numSet = new HashSet<>();
        for (char n : numbers) numSet.add(n);
            
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String now = files[i];
            // head 엔 대문자로 저장하기
            // number 엔 앞의 연속된 0 제거하고 넣기
            
            // 처음 등장한 숫자가 끝나는 지점이 number 끝나는 지점 
            boolean isReceived = false;
            boolean numberChk = false;
            int numberCnt = 0;
            String HEAD = "";
            String NUMBER = "";
            for (int n = 0; n < now.length(); n++) {
                // 수 부분이 끝났음
                if ((numberChk && !numSet.contains(now.charAt(n))) || numberCnt == 5) {
                    fileList.add(new File(now, HEAD.toUpperCase(), Integer.valueOf(NUMBER), i));
                    isReceived = true;
                    break;
                }
                
                if (numSet.contains(now.charAt(n))) {
                    numberChk = true;
                    numberCnt++;
                    NUMBER += String.valueOf(now.charAt(n));
                } else {
                    HEAD += String.valueOf(now.charAt(n));
                }
            }
            
            // 수로 끝났을 경우 (TAIL 이 없음)
            if (!isReceived) fileList.add(new File(now, HEAD.toUpperCase(), Integer.valueOf(NUMBER), i));
        }
        // 1. head 기준 정렬
        // 2. number 기준 정렬
        Collections.sort(fileList, (o1, o2) -> {
            if (!o1.head.equals(o2.head)) {
                return o1.head.compareTo(o2.head);
            } else if (o1.number != o2.number) {
                return Integer.compare(o1.number, o2.number);
            } else return Integer.compare(o1.oriNum, o2.oriNum);
        });
        
        String[] answer = new String[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            answer[i] = fileList.get(i).name;
        }
        return answer;
    }
}