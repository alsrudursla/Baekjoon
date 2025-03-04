import java.io.*;
import java.util.*;
public class Main {
    static int N, turn;
    static int[][] map, visited;
    static List<int[]> horse;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 체스판 크기
        int K = Integer.parseInt(st.nextToken()); // 말의 개수

        map = new int[N+1][N+1];
        visited = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 0은 흰색, 1은 빨간색, 2는 파란색
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horse = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            // 1부터 순서대로 →, ←, ↑, ↓
            horse.add(new int[]{r,c,dir,1}); // r,c,dir,밑에서부터순서
            visited[r][c]++;
        }

        turn = 0;
        boolean isFinished = false;

        // 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동
        for (int i = 0; i < 1000; i++) {
            // 1. 1~K 말 이동
            // 2. 말이 4개 쌓이면 종료
            isFinished = move();
            turn++;
            if (isFinished) break;
        }

        // 말이 4개 이상 쌓이는 순간 게임이 종료
        // 게임이 종료되는 턴의 번호를 출력
        // 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력
        if (turn > 1000 || !isFinished) bw.write("-1");
        else bw.write(String.valueOf(turn));
        bw.flush();
        bw.close();
    }

    private static boolean move() {
        // 1. 이동 방향으로 한 칸 이동
        // 흰색인 경우에는 그 칸으로 이동
        // 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다
        // 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동, 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다
        // 체스판을 벗어나는 경우에는 파란색과 같은 경우

        int[] dy = {0, 0, 0, -1, 1}; // 1부터 순서대로 →, ←, ↑, ↓
        int[] dx = {0, 1, -1, 0, 0};

        for (int i = 0; i < horse.size(); i++) {
            int[] now_horse = horse.get(i); // r,c,dir,밑에서부터순서
            int now_y = now_horse[0];
            int now_x = now_horse[1];
            int now_dir = now_horse[2];
            int now_order = now_horse[3];

            int next_y = now_y + dy[now_dir];
            int next_x = now_x + dx[now_dir];

            // 0은 흰색, 1은 빨간색, 2는 파란색
            if (0 >= next_y || 0 >= next_x || N < next_y || N < next_x || map[next_y][next_x] == 2) {
                if (now_dir == 1) horse.get(i)[2] = 2;
                else if (now_dir == 2) horse.get(i)[2] = 1;
                else if (now_dir == 3) horse.get(i)[2] = 4;
                else horse.get(i)[2] = 3;

                next_y = now_y + dy[horse.get(i)[2]];
                next_x = now_x + dx[horse.get(i)[2]];

                // 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다
                if (0 >= next_y || 0 >= next_x || N < next_y || N < next_x || map[next_y][next_x] == 2) continue;

                if (map[next_y][next_x] == 0) follow(0, now_y, now_x, now_order, next_y, next_x);
                else follow(1, now_y, now_x, now_order, next_y, next_x);
            } else if (map[next_y][next_x] == 1) {
                follow(1, now_y, now_x, now_order, next_y, next_x);
            } else {
                follow(0, now_y, now_x, now_order, next_y, next_x);
            }

            // 말이 4개인지 확인
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (visited[r][c] >= 4) return true;
                }
            }
        }

        return false;
    }

    private static void follow(int color, int now_y, int now_x, int now_order, int next_y, int next_x) {
        if (color == 1) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
            for (int j = 0; j < horse.size(); j++) {
                if (horse.get(j)[0] == now_y && horse.get(j)[1] == now_x && horse.get(j)[3] >= now_order) {
                    pq.add(new int[]{j, horse.get(j)[3]}); // 인덱스 번호, 우선 순위
                }
            }

            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                int now_idx = now[0];

                horse.get(now_idx)[0] = next_y;
                horse.get(now_idx)[1] = next_x;

                visited[now_y][now_x]-=1;
                visited[next_y][next_x]+=1;

                horse.get(now_idx)[3] = visited[next_y][next_x];
            }
        } else {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            for (int j = 0; j < horse.size(); j++) {
                if (horse.get(j)[0] == now_y && horse.get(j)[1] == now_x && horse.get(j)[3] >= now_order) {
                    pq.add(new int[]{j, horse.get(j)[3]}); // 인덱스 번호, 우선 순위
                }
            }

            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                int now_idx = now[0];

                horse.get(now_idx)[0] = next_y;
                horse.get(now_idx)[1] = next_x;

                visited[now_y][now_x]-=1;
                visited[next_y][next_x]+=1;

                horse.get(now_idx)[3] = visited[next_y][next_x];
            }
        }
    }
}