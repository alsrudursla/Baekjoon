import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static int row_max, col_max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[101][101];
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (map[r][c] == k) {
            bw.write(String.valueOf(0));
            bw.flush();
            bw.close();
            return;
        }

        row_max = 3;
        col_max = 3;
        boolean chk = false;
        for (int i = 1; i <= 100; i++) {
            if (row_max >= col_max) calculateR(row_max, col_max);
            else calculateC(col_max, row_max);

            if (map[r][c] == k) {
                bw.write(String.valueOf(i));
                chk = true;
                break;
            }
        }

        if (!chk) bw.write("-1");
        bw.flush();
        bw.close();
    }

    private static void calculateR(int main, int sub) {
        HashMap<Integer, Integer> myHash = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                o1[1] != o2[1] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

        for (int i = 1; i <= main; i++) {
            for (int j = 1; j <= sub; j++) {
                if (map[i][j] == 0) continue;
                myHash.put(map[i][j], myHash.getOrDefault(map[i][j], 0) + 1);
            }

            for (int value : myHash.keySet()) {
                pq.add(new int[]{value, myHash.get(value)});
            }

            Arrays.fill(map[i], 0);

            int j_idx = 1;
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                map[i][j_idx++] = now[0];
                if (j_idx > 100) break;
                map[i][j_idx++] = now[1];
                if (j_idx > 100) break;
            }

            col_max = Math.max(col_max, j_idx-1);

            myHash.clear();
            pq.clear();
        }
    }

    private static void calculateC(int main, int sub) {
        HashMap<Integer, Integer> myHash = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                o1[1] != o2[1] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

        for (int j = 1; j <= main; j++) {
            for (int i = 1; i <= sub; i++) {
                if (map[i][j] == 0) continue;
                myHash.put(map[i][j], myHash.getOrDefault(map[i][j], 0) + 1);
            }

            for (int value : myHash.keySet()) {
                pq.add(new int[]{value, myHash.get(value)});
            }

            for (int i = 1; i <= 100; i++) map[i][j] = 0;

            int i_idx = 1;
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                map[i_idx++][j] = now[0];
                if (i_idx > 100) break;
                map[i_idx++][j] = now[1];
                if (i_idx > 100) break;
            }

            row_max = Math.max(row_max, i_idx-1);

            myHash.clear();
            pq.clear();
        }
    }
}