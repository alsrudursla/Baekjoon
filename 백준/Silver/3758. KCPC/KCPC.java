import java.util.*;
import java.io.*;

class Main {
    static class Question {
        int score;
        Question(int score) {
            this.score = score;
        }
    }
    static class Team {
        List<Question> qList;
        int logCnt;
        int lastSolvedTime;
        Team(List<Question> qList, int logCnt, int lastSolvedTime) {
            this.qList = qList;
            this.logCnt = logCnt;
            this.lastSolvedTime = lastSolvedTime;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int testcase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testcase; t++) {
            st = new StringTokenizer(br.readLine());
            int teamCnt = Integer.parseInt(st.nextToken());
            int questionCnt = Integer.parseInt(st.nextToken());
            int myTeamId = Integer.parseInt(st.nextToken())-1;
            int logEntryCnt = Integer.parseInt(st.nextToken());

            // 1. 팀 리스트 생성
            List<Team> teamList = new ArrayList<>();
            for (int i = 0; i < teamCnt; i++) {
                teamList.add(new Team(new ArrayList<>(), 0, Integer.MAX_VALUE));
                for (int j = 0; j < questionCnt; j++) {
                    teamList.get(i).qList.add(new Question(0));
                }
            }

            int time = 0;
            for (int l = 0; l < logEntryCnt; l++) {
                st = new StringTokenizer(br.readLine());
                int teamId = Integer.parseInt(st.nextToken())-1;
                int questionNum = Integer.parseInt(st.nextToken())-1;
                int score = Integer.parseInt(st.nextToken());

                // 2. 로그 별 기록 저장
                int savedScore = teamList.get(teamId).qList.get(questionNum).score;
                teamList.get(teamId).qList.get(questionNum).score = Math.max(savedScore, score);
                teamList.get(teamId).logCnt++;
                teamList.get(teamId).lastSolvedTime = time;
                
                time++;
            }

            // 3. 팀 별 기록 비교
            // 팀 번호, 최종 점수, 풀이 횟수, 마지막 제출 시간
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> 
                    o1[1] != o2[1] ? Integer.compare(o2[1], o1[1]) :
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : Integer.compare(o1[3], o2[3]));
            
            for (int i = 0; i < teamList.size(); i++) {
                int totalScore = 0;
                for (int j = 0; j < teamList.get(i).qList.size(); j++) {
                     totalScore += teamList.get(i).qList.get(j).score;
                }
                pq.add(new int[]{i, totalScore, teamList.get(i).logCnt, teamList.get(i).lastSolvedTime});
            }

            int rank = 0;
            while (!pq.isEmpty()) {
                rank++;
                int[] now = pq.poll();
                if (now[0] == myTeamId) break;
            }

            bw.write(String.valueOf(rank));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}