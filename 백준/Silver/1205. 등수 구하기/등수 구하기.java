import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalCnt = Integer.parseInt(st.nextToken());
        int taesuScore = Integer.parseInt(st.nextToken());
        int rankingCnt = Integer.parseInt(st.nextToken());

        List<Integer> scoreList = new ArrayList<>();
        scoreList.add(taesuScore);

        if (totalCnt != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < totalCnt; i++) {
                int nowScore = Integer.parseInt(st.nextToken());
                scoreList.add(nowScore);
            }
            Collections.sort(scoreList, (o1, o2) -> o2 - o1);
        }

        // 태수 랭킹 찾기
        int trank = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            if (scoreList.get(i) == taesuScore) {
                trank = i+1;
                break;
            }
        }

        // rankingCnt 를 넘으면 못들어감
        // 태수 점수가 rankingCnt의 마지막 원소와 같으면 못들어감
        boolean chk = true;
        if (trank > rankingCnt) chk = false;
        if (scoreList.size() > rankingCnt && scoreList.get(rankingCnt) == taesuScore) chk = false;

        if (chk) bw.write(String.valueOf(trank));
        else bw.write("-1");
        bw.flush();
        bw.close();
    }
}