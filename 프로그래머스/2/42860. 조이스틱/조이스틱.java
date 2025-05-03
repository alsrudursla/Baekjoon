class Solution {
    public int solution(String name) {
        int answer = 0;
        
        // 1. 알파벳마다 위 아래 최소 이동 횟수 먼저 구하기
        for (int i = 0; i < name.length(); i++) {
            char targetAlpha = name.charAt(i);
            answer += Math.min(targetAlpha - 'A', 'Z' - targetAlpha + 1);
        }
        
        // 2. 최소 좌우 이동 횟수 구하기
        // 2-1. 처음부터 끝까지 순서대로 이동
        int tmp = name.length() - 1;
        
        // 2-2. 돌아가는 경우
        for (int i = 0; i < name.length(); i++) {
            int next_i = i+1;
            
            // 다음 문자가 A 일 경우 + 문자 길이 내에서 -> 연속된 A 가 끝나는 인덱스 구하기
            while (next_i < name.length() && name.charAt(next_i) == 'A') next_i++;
            
            tmp = Math.min(tmp, i*2 + (name.length() - next_i)); // 오른쪽 갔다가 왼쪽
            tmp = Math.min(tmp, (name.length() - next_i)*2 + i); // 왼쪽 갔다가 오른쪽
        }
        
        answer += tmp;
        return answer;
    }
}