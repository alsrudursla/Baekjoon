import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int countryCnt = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        // 금메달 개수 -> 은메달 -> 동메달
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                o1[1] != o2[1] ? Integer.compare(o2[1], o1[1]) :
                o1[2] != o2[2] ? Integer.compare(o2[2], o1[2]) : Integer.compare(o2[3], o1[3]));

        for (int i = 0; i < countryCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int cid = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            pq.add(new int[]{cid, gold, silver, bronze});
        }

        boolean chk = false;
        int beforeG = -1;
        int beforeS = -1;
        int beforeB = -1;
        int cnt = 0;
        int rank = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cid = now[0];
            int gold = now[1];
            int silver = now[2];
            int bronze = now[3];

            // 셋 다 같을 때만 동일 순위 (rank 유지)
            if (beforeG == gold && beforeS == silver && beforeB == bronze) {
                // 동일 등수 카운트
                cnt++;
            } else {
                rank += cnt;
                cnt = 0;
                
                beforeG = gold;
                beforeS = silver;
                beforeB = bronze;
                rank++;
            }

            if (cid == target) {
                chk = true;
                break;
            }
        }
        
        bw.write(String.valueOf(rank));
        bw.flush();
        bw.close();
    }
}