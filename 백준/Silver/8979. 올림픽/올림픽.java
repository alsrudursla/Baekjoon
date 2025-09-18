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
        int rank = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cid = now[0];
            int gold = now[1];
            int silver = now[2];
            int bronze = now[3];

            // 첫 번째 원소일 때
            if (beforeG == -1) {
                beforeG = gold;
                beforeS = silver;
                beforeB = bronze;
                rank++;
            } else { // 그 외
                if (beforeG == gold) {
                    if (beforeS == silver) {
                        // 모두 동일하면 rank 도 동일, 그대로 넘어감
                        if (beforeB != bronze) {
                            beforeB = bronze;
                            rank++;
                        }
                    } else {
                        beforeS = silver;
                        beforeB = bronze;
                        rank++;
                    }
                } else {
                    beforeG = gold;
                    beforeS = silver;
                    beforeB = bronze;
                    rank++;
                }
            }

            ///System.out.println(cid + " " + rank);

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