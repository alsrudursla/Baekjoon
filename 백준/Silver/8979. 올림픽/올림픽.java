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
        int before = -1;
        int rank = 0;
        // 금메달 개수로 판별
        while (!pq.isEmpty()) {
            if (pq.peek()[1] == 0) break;
            int[] now = pq.poll();
            int cid = now[0];
            int gold = now[1];

            if (before == -1) {
                before = gold;
                rank++;
            } else {
                if (before != gold) {
                    rank++;
                    before = gold;
                }
            }

            ///System.out.println(cid + " " + rank);

            if (cid == target) {
                chk = true;
                break;
            }
        }

        // 은메달 개수로 판별
        if (!chk) {
            before = -1;
            while (!pq.isEmpty()) {
                if (pq.peek()[2] == 0) break;
                int[] now = pq.poll();
                int cid = now[0];
                int silver = now[2];

                if (before == -1) {
                    before = silver;
                    rank++;
                } else {
                    if (before != silver) {
                        rank++;
                        before = silver;
                    }
                }

                //System.out.println(cid + " " + rank);

                if (cid == target) {
                    chk = true;
                    break;
                }
            }
        }

        // 동메달 개수로 판별
        if (!chk) {
            before = -1;
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                int cid = now[0];
                int bronze = now[3];

                if (before == -1) {
                    before = bronze;
                    rank++;
                } else {
                    if (before != bronze) {
                        rank++;
                        before = bronze;
                    }
                }

                //System.out.println(cid + " " + rank);

                if (cid == target) {
                    chk = true;
                    break;
                }
            }
        }
        
        bw.write(String.valueOf(rank));
        bw.flush();
        bw.close();
    }
}