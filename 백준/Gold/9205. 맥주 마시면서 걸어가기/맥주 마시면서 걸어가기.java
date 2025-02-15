import java.io.*;
import java.util.*;
public class Main {
    static int homeX, homeY, festivalX, festivalY;
    static List<int[]> store;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            int store_total = Integer.parseInt(br.readLine());
            store = new ArrayList<>();
            for (int i = 0; i < 2 + store_total; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (i == 0) {
                    homeX = x;
                    homeY = y;
                } else if (i == 1 + store_total) {
                    festivalX = x;
                    festivalY = y;
                } else {
                    store.add(new int[]{y, x});
                }
            }

            boolean ans = bfs();
            if (ans) bw.write("happy");
            else bw.write("sad");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static boolean bfs() {
        boolean[] visited = new boolean[store.size()];
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{homeY, homeX});

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_y = now[0];
            int now_x = now[1];
            int distance = Math.abs(festivalX - now_x) + Math.abs(festivalY - now_y);

            if (distance <= 20 * 50) {
                return true;
            } else { // 맥주로 갈 수 있는 거리보다 많은 거리가 남았음 (편의점 가야함)
                for(int k = 0; k < store.size(); k++) {
                    int storeY = store.get(k)[0];
                    int storeX = store.get(k)[1];
                    int tmp_distance = Math.abs(storeY - now_y) + Math.abs(storeX - now_x);
                    if (tmp_distance <= 20 * 50 && !visited[k]) {
                        myqueue.add(new int[]{storeY, storeX});
                        visited[k] = true;
                    }
                }
            }
        }

        return false;
    }
}