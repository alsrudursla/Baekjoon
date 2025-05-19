import java.io.*;
import java.util.*;
public class Main {
    static boolean[][] map;
    static List<Integer> areaList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1. 직사각형 색칠하기
        map = new boolean[M+1][N+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int leftX = Integer.parseInt(st.nextToken());
            int leftY = Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());
            int rightY = Integer.parseInt(st.nextToken());

            for (int r = leftY+1; r <= rightY; r++) {
                for (int c = leftX+1; c <= rightX; c++) {
                    map[r][c] = true;
                }
            }
        }

        // 2. 영역 정보 구하기
        areaList = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j]) continue;
                getAreaSize(i, j);
            }
        }

        bw.write(String.valueOf(areaList.size()));
        bw.newLine();

        Collections.sort(areaList);

        for (int area : areaList) {
            bw.write(area + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void getAreaSize(int i, int j) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{i, j});

        map[i][j] = true;

        int tmp_area = 1;
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (1 <= nextY && nextY < map.length && 1 <= nextX && nextX < map[0].length) {
                    if (!map[nextY][nextX]) {
                        map[nextY][nextX] = true;
                        myqueue.add(new int[]{nextY, nextX});
                        tmp_area++;
                    }
                }
            }
        }

        areaList.add(tmp_area);
    }
}