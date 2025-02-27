import java.io.*;
import java.util.*;
public class Main {
    static int R, C, ans;
    static List<Shark> sharks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 상어 수

        sharks = new ArrayList<>();
        ans = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 이동 방향
            int z = Integer.parseInt(st.nextToken()); // 크기
            sharks.add(new Shark(r,c,s,d,z));
        }

        for (int play = 1; play <= C; play++) {
            // 1. 낚시왕 이동 : 낚시왕 위치 = play
            // 2. 상어 잡기
            // 3. 상어 이동

            sharks.sort((o1, o2) -> Integer.compare(o1.i, o2.i));
            for (Shark s : sharks) {
                if (s.j == play) {
                    ans += s.size;
                    sharks.remove(s);
                    break;
                }
            }

            move();
            eat();
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void move() {
        int[] dy = {0, -1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽
        int[] dx = {0, 0, 0, 1, -1};

        for (Shark s : sharks) {

            // 불필요한 이동 방지
            int moveIter = s.speed;
            if (s.dir == 1 || s.dir == 2) moveIter %= (R - 1) * 2; // 한 사이클
            else moveIter %= (C - 1) * 2;

            for (int move = 0; move < moveIter; move++) {
                int nextY = s.i + dy[s.dir];
                int nextX = s.j + dx[s.dir];
                if (1 <= nextY && nextY <= R && 1 <= nextX && nextX <= C) {
                    s.i = nextY;
                    s.j = nextX;
                } else {
                    if (s.dir == 1) s.dir = 2;
                    else if (s.dir == 2) s.dir = 1;
                    else if (s.dir == 3) s.dir = 4;
                    else s.dir = 3;

                    s.i += dy[s.dir];
                    s.j += dx[s.dir];
                }
            }
        }
    }

    private static void eat() {
        boolean[][] visited = new boolean[R+1][C+1];
        List<Shark> dead_shark = new ArrayList<>();
        PriorityQueue<Shark> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.size, o1.size));
        pq.addAll(sharks);

        while (!pq.isEmpty()) {
            Shark now_shark = pq.poll();
            if (visited[now_shark.i][now_shark.j]) dead_shark.add(now_shark);
            else visited[now_shark.i][now_shark.j] = true;
        }

        sharks.removeAll(dead_shark);
    }

    static class Shark {
        int i;
        int j;
        int speed;
        int dir;
        int size;

        Shark (int i, int j, int speed, int dir, int size) {
            this.i = i;
            this.j = j;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}