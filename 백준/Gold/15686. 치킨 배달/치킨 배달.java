import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static List<int[]> chicken;
    static int N, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업

        map = new int[N+1][N+1];
        ans = Integer.MAX_VALUE;
        chicken = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }
        
        for (int i = 1; i <= M; i++) {
            List<int[]> tmp = new ArrayList<>();
            calculate(i, 0, tmp, 0);
            // 타겟 치킨집 개수, 현재 치킨집 개수, 치킨집 위치 정보, 치킨집 인덱스 (중복 방지)
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void calculate(int target, int now, List<int[]> tmp, int idx) {
        if (target == now) {
            int distance = getCityChickenDistance(tmp);
            ans = Math.min(ans, distance);
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            tmp.add(chicken.get(i));
            calculate(target, now+1, tmp, i+1);
            tmp.remove(tmp.size() - 1);
        }
    }

    private static int getCityChickenDistance(List<int[]> tmp_chicken) {
        int chicken_distance = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) {
                    // 모든 치킨집과의 거리 비교, 가장 작은 값 갖는 거리
                    int tmp = Integer.MAX_VALUE;
                    for (int[] c : tmp_chicken) {
                        int distance = Math.abs(i - c[0]) + Math.abs(j - c[1]);
                        tmp = Math.min(tmp, distance);
                    }
                    chicken_distance += tmp;
                }
            }
        }
        return chicken_distance;
    }
}