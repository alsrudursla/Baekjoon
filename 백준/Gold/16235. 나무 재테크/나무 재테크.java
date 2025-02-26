import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map, nutrient;
    static List<int[]> trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N x N 땅 크기
        int M = Integer.parseInt(st.nextToken()); // 구매한 나무 수
        int K = Integer.parseInt(st.nextToken());
        // K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램

        map = new int[N+1][N+1]; // 가장 처음에 양분은 모든 칸에 5만큼 들어있다
        for (int i = 1; i <= N; i++) Arrays.fill(map[i], 5);

        nutrient = new int[N+1][N+1]; // 겨울에 추가되는 양분의 양
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken()); // 나무 나이
            trees.add(new int[]{x, y, z, 1});
            // j, i, 나이, 살아있는지 여부(O 사망, 1 생존)
        }

        for (int i = 0; i < K; i++) {
            trees.sort((o1, o2) -> Integer.compare(o1[2], o2[2]));

            spring();
            summer();

            // 사망한 나무 지우기
            trees.removeIf(tree -> tree[3] == 0);

            fall();
            winter();
        }

        bw.write(String.valueOf(trees.size()));
        bw.flush();
        bw.close();
    }

    private static void spring() {
        // 가장 처음에 양분은 모든 칸에 5만큼 들어있다
        // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다
        // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
        // 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다

        for (int i = 0; i < trees.size(); i++) {
            int[] tree = trees.get(i);
            int x = tree[0];
            int y = tree[1];
            int age = tree[2];

            if (map[x][y] - age < 0) trees.get(i)[3] = 0;
            else {
                map[x][y] -= age;
                trees.get(i)[2]++;
            }
        }
    }

    private static void summer() {
        // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다
        // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.

        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i)[3] == 1) continue;
            int added = trees.get(i)[2] / 2;
            map[trees.get(i)[0]][trees.get(i)[1]] += added;
        }
    }

    private static void fall() {
        // 가을에는 나무가 번식한다
        // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다
        // 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다

        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<int[]> new_trees = new ArrayList<>();

        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i)[2] % 5 != 0) continue;

            for (int k = 0; k < 8; k++) {
                int j2 = trees.get(i)[1] + dy[k];
                int i2 = trees.get(i)[0] + dx[k];

                if (1 <= i2 && i2 <= N && 1 <= j2 && j2 <= N) {
                    new_trees.add(new int[]{i2, j2, 1, 1});
                }
            }
        }

        trees.addAll(new_trees);
    }

    private static void winter() {
        // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다
        // 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += nutrient[i][j];
            }
        }
    }
}