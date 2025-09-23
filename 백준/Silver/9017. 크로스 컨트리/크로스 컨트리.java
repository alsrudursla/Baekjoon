import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // 6명 안되면 걸러내기
            List<Integer> tmpList = new ArrayList<>();
            HashMap<Integer, Integer> teamMap = new HashMap<>();
            int totalCnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= totalCnt; j++) {
                int nowTeam = Integer.parseInt(st.nextToken());
                tmpList.add(nowTeam);
                teamMap.put(nowTeam, teamMap.getOrDefault(nowTeam, 0) + 1);
            }

            PriorityQueue<int[]> all = new PriorityQueue<>((o1, o2) ->
                    o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));

            int idx = 1;
            for (int j = 0; j < tmpList.size(); j++) {
                int teamNum = tmpList.get(j);
                if (teamMap.get(teamNum) == 6) {
                    all.add(new int[]{teamNum, idx});
                    idx++;
                }
            }
            
            int bestTeamNumber = -1;
            int bestTeamScore = Integer.MAX_VALUE;
            int bestTeamFifth = 1001;

            int beforeTeamNumber = -1;

            int nowTeamScore = 0;
            int nowTeamCnt = 0;
            int nowTeamFifth = 1001;
            while (!all.isEmpty()) {
                int[] now = all.poll();
                int nowTeamNumber = now[0];
                int nowOrder = now[1];
                //System.out.println("들어온 값 : " + nowTeamNumber + " " + nowOrder);

                if (beforeTeamNumber == -1) {
                    // 처음 시작
                    beforeTeamNumber = nowTeamNumber;
                    nowTeamScore = nowOrder;
                    nowTeamCnt = 1;
                } else if (beforeTeamNumber == nowTeamNumber) {
                    // 같은 팀
                    nowTeamCnt++;
                    if (nowTeamCnt < 5) nowTeamScore += nowOrder;
                    else if (nowTeamCnt == 5) nowTeamFifth = nowOrder;
                } else {
                    // 다른 팀
                    // 1. 베스트 점수 비교
                    // 2. 새로 시작
                    //System.out.println("합계 : " + nowTeamScore);
                    if (nowTeamScore == bestTeamScore) { // 갱신
                        if (nowTeamFifth < bestTeamFifth) {
                            bestTeamNumber = beforeTeamNumber;
                            bestTeamFifth = nowTeamFifth;
                            //System.out.println("갱신됨");
                        }
                    } else if (nowTeamScore < bestTeamScore) { 
                        bestTeamNumber = beforeTeamNumber;
                        bestTeamScore = nowTeamScore;
                        bestTeamFifth = nowTeamFifth;
                        //System.out.println("갱신됨");
                    }
                    
                    beforeTeamNumber = nowTeamNumber;
                    nowTeamScore = nowOrder;
                    nowTeamCnt = 1;
                    nowTeamFifth = 1001;
                }
            }
            // 마지막 팀 반영하기
            if (nowTeamScore == bestTeamScore) { // 갱신
                if (nowTeamFifth < bestTeamFifth) bestTeamNumber = beforeTeamNumber;
            } else if (nowTeamScore < bestTeamScore) bestTeamNumber = beforeTeamNumber;
               
            bw.write(bestTeamNumber + "\n");
        }
        bw.flush();
        bw.close();
    }
}