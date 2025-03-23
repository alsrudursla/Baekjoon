import java.io.*;
import java.util.*;
public class Main {
    static class Ball {
        int r;
        int c;
        int dir;
        int speed;
        int mass;
        Ball(int r, int c, int mass, int speed, int dir) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M;
    static ArrayList<Ball>[][] map;
    static List<Ball> fireball;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // NxN 격자
        M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
        int K = Integer.parseInt(st.nextToken()); // K번 명령

        fireball = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireball.add(new Ball(r,c,m,s,d));
        }

        for (int i = 0; i < K; i++) {
            // 맵 초기화
            map = new ArrayList[N+1][N+1];
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) map[r][c] = new ArrayList<>();
            }

            // 1. 파이어볼 이동
            moveFireball();

            // 2. 같은 칸 파이어볼 처리
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c].size() < 2) continue;
                    samePos(r, c);
                }
            }

            // 3. 질량 0인 파이어볼 소멸
            disappear();
        }

        // 남아있는 파이어볼 질량의 합
        int sum = 0;
        for (int i = 0; i < fireball.size(); i++) {
            Ball now = fireball.get(i);
            sum += now.mass;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static void disappear() {
        List<Ball> tmp = new ArrayList<>();
        for (int i = 0; i < fireball.size(); i++) {
            Ball now = fireball.get(i);
            if (now.mass == 0) tmp.add(now);
        }
        fireball.removeAll(tmp);
    }

    private static void samePos(int i, int j) {
        // 2개 이상의 파이어볼 칸
        // 파이어볼은 모두 하나로 합쳐진다 (합쳐지는 질량, 속력, 개수, 방향만 기록하고 삭제하기)
        int total_mass = 0;
        int total_speed = 0;
        int total_num = 0;
        List<Integer> total_dir = new ArrayList<>();
        List<Ball> deadBall = new ArrayList<>();
        for (Ball b : map[i][j]) {
            total_mass += b.mass;
            total_speed += b.speed;
            total_num++;
            total_dir.add(b.dir);
            deadBall.add(b);
        }
        fireball.removeAll(deadBall);

        // 파이어볼은 4개의 파이어볼로 나누어진다
        int new_mass = total_mass / 5;
        int new_speed = total_speed / total_num;
        int new_dir_start = 100;

        boolean chkOdd = true;
        boolean chkEven = true;
        for (int d = 0; d < total_dir.size(); d++) { // 방향 체크
            if ((total_dir.get(d)+2) % 2 == 0) { // 짝수 (2 더해서 0 으로 나누기 방지)
                chkOdd = false;
            } else { // 홀수
                chkEven = false;
            }
        }

        if (chkOdd || chkEven) new_dir_start = 0;
        else new_dir_start = 1;

        for (int f = 0; f < 4; f++) {
            fireball.add(new Ball(i, j, new_mass, new_speed, new_dir_start));
            new_dir_start += 2;
        }

    }

    private static void moveFireball() {
        for (int i = 0; i < fireball.size(); i++) {
            Ball now = fireball.get(i);

            // 이동
            int next_i = (now.r + dy[now.dir] * (now.speed % N));
            int next_j = (now.c + dx[now.dir] * (now.speed % N));

            if (next_i > N) next_i -= N;
            else if (next_i < 1) next_i += N;
            if (next_j > N) next_j -= N;
            else if (next_j < 1) next_j += N;

            fireball.get(i).r = next_i;
            fireball.get(i).c = next_j;
            map[next_i][next_j].add(now);
        }
    }
}