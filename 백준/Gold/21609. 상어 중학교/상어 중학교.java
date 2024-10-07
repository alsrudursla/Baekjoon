import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N, M;
    static int[][] box;
    static boolean[][] visited;
    static Queue<int[]> myqueue;
    static PriorityQueue<Block> pq;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 일반 블록 색깔 개수
        // 검은색 블록 -1, 무지개 블록 0
        // 무지개 블록은 다른 색상의 블록 그룹에 속할 수 있기 때문에 방문 여부 초기화 해야 한다

        box = new int[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int block = Integer.parseInt(st.nextToken());
                box[i][j] = block;
            }
        }

        while(true) {
            pq = new PriorityQueue<>((o1, o2) ->
                // 크기가 가장 큰 블록 그룹 찾기(내림차순) -> 무지개 블록 가장 많은(내림차순) -> 기준 블록 행이 가장 큰 -> 열이 가장 큰
                o1.allCnt != o2.allCnt ? Integer.compare(o2.allCnt, o1.allCnt) :
                        (o1.rainbowCnt != o2.rainbowCnt ? Integer.compare(o2.rainbowCnt, o1.rainbowCnt) :
                                (o1.y != o2.y ? Integer.compare(o2.y, o1.y) : Integer.compare(o2.x, o1.x)))
            );

            // 0. 기준 블록 위치 큐에 저장
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || box[i][j] == -1 || box[i][j] == 0 || box[i][j] == -2) {
                        continue;
                    }

                    bfs(i, j, box[i][j]);
                }
            }

            // 1. 크기가 가장 큰 블록 그룹 찾기 -> 무지개 블록 가장 많은 -> 기준 블록 행이 가장 큰 -> 열이 가장 큰
            Block selectedBlock = pq.poll();
            if (selectedBlock == null) {
                break;
            }

            // 2. 해당 블록 그룹 삭제 && 점수 추가
            List<int[]> selectedRemoveBlockList = selectedBlock.members;
            for (int[] selected : selectedRemoveBlockList) {
                box[selected[0]][selected[1]] = -2; // 빈 공간
            }

            ans += (selectedBlock.allCnt * selectedBlock.allCnt);

            // 3. 중력 작용
            gravity();

            // 4. 반시계 90도 회전
            int[][] newBox = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBox[N-1-j][i] = box[i][j];
                }
            }

            box = newBox;

            // 5. 중력 작용
            gravity();
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void gravity() {
        for (int j = N-1; j >= 0; j--) {
            for (int i = N-1; i >= 0; i--) {
                if (box[i][j] == -1) continue; // 검은 블럭은 스킵
                if (box[i][j] == -2) { // 빈 공간
                    for (int k = i-1; k >= 0; k--) {
                        if (box[k][j] == -1) break;
                        if (box[k][j] != -2) {
                            box[i][j] = box[k][j];
                            box[k][j] = -2;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void bfs(int i, int j, int color) {
        myqueue = new LinkedList<>();

        visited[i][j] = true;
        myqueue.add(new int[]{i,j});

        int all_block_size = 1;
        int rainbow_block_size = 0;
        List<int[]> block_list = new ArrayList<>();
        block_list.add(new int[]{i,j});

        while(!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_x = now[1];
            int now_y = now[0];

            for (int k = 0; k < 4; k++) {
                int x = now_x + dx[k];
                int y = now_y + dy[k];

                if (x < 0 || x >= N || y < 0 || y >= N ||
                        visited[y][x] || box[y][x] == -1 || box[y][x] == -2) {
                    continue;
                }

                if (box[y][x] == color || box[y][x] == 0) { // 해당 색일 때, 무지개 블럭일 때
                    myqueue.add(new int[]{y, x});
                    block_list.add(new int[]{y, x});
                    all_block_size++;
                    visited[y][x] = true;

                    if (box[y][x] == 0) rainbow_block_size++;
                }
            }
        }
        
        if (all_block_size >= 2) { // 크기가 2 이상인 블록 그룹만 가능
            Block block = new Block(j, i, all_block_size, rainbow_block_size, block_list);
            pq.add(block);
        }

        // 무지개 블록 방문 여부 초기화
        for (int i2 = 0; i2 < N; i2++) {
            for (int j2 = 0; j2 < N; j2++) {
                if (box[i2][j2] == 0) {
                    visited[i2][j2] = false;
                }
            }
        }
    }

    static class Block {
        int x, y; // 기준 블록 위치
        int allCnt; // 모든 블록 개수
        int rainbowCnt; // 무지개 블록 개수
        List<int[]> members = new ArrayList<>(); // 포함된 블록들 위치

        Block(int x, int y, int allCnt, int rainbowCnt, List<int[]> members) {
            this.x = x;
            this.y = y;
            this.allCnt = allCnt;
            this.rainbowCnt = rainbowCnt;
            this.members = members;
        }
    }
}