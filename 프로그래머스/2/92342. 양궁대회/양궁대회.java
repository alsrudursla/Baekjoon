// 1. 가장 큰 점수 차이로 우승하기 -> 높은 점수 우선
// 2. 가장 낮은 점수를 더 많이 맞힌 경우를 return
import java.util.*;
class Solution {
    int[] lionCnt;
    int diff;
    public int[] solution(int n, int[] info) {
        lionCnt = new int[11];
        diff = 0;
        dfs(info, new int[11], n, 0);
        
        boolean chk = false;
        for (int i = 0; i < lionCnt.length; i++) {
            if (lionCnt[i] != 0) chk = true;
        }
        
        if (chk) return lionCnt;
        else return new int[]{-1};
    }
    
    private void dfs(int[] info, int[] lionInfo, int arrowLeft, int idx) {
        // 종료 조건 : 화살 다 쏘기
        if (arrowLeft == 0 || idx == 10) {
            if (idx == 10) lionInfo[idx] = arrowLeft;
            
            // 점수 계산
            int apeachScore = 0;
            int lionScore = 0;
            for (int i = 0; i < info.length; i++) {
                if (info[i] == 0 && lionInfo[i] == 0) continue;
                if (info[i] >= lionInfo[i]) apeachScore += (10-i);
                else lionScore += (10-i);
            }
            if (lionScore <= apeachScore) return;
            
            if (lionScore - apeachScore > diff) { // 점수가 더 크면 갱신
                diff = lionScore - apeachScore;
                for (int i = 0; i < lionCnt.length; i++) lionCnt[i] = lionInfo[i];
            } else if (lionScore - apeachScore == diff) { // 같으면 낮은 점수 더 많이 맞힌 경우 우선
                for (int i = 10; i >= 0; i--) {
                    if (lionInfo[i] == lionCnt[i]) continue;
                    if (lionInfo[i] > lionCnt[i]) {
                        for (int j = 0; j < lionCnt.length; j++) lionCnt[j] = lionInfo[j];
                        break;
                    } else break;
                }
            }
            return;
        }
        
        int nowApeachCnt = info[idx];
        if (nowApeachCnt == 0) {
            // 어피치가 안쏜 과녁 점수
            // 1. 라이언은 안쐈을 때
            dfs(info, lionInfo, arrowLeft, idx+1);
            // 2. 라이언은 쐈을 때
            int[] tmp = new int[11];
            for (int i = 0; i < idx; i++) tmp[i] = lionInfo[i];
            tmp[idx] = 1;
            dfs(info, tmp, arrowLeft-tmp[idx], idx+1);
        } else {
            // 어피치가 쏜 과녁 점수
            // 3. 남은 화살로 점수를 가져올 수 있는 상황일 때
            if (nowApeachCnt < arrowLeft) {
                int[] tmp = new int[11];
                for (int i = 0; i < idx; i++) tmp[i] = lionInfo[i];
                tmp[idx] = nowApeachCnt+1;
                dfs(info, tmp, arrowLeft-tmp[idx], idx+1);
            }
            // 4. 라이언은 안쐈을 때
            dfs(info, lionInfo, arrowLeft, idx+1);
        }
    }
}