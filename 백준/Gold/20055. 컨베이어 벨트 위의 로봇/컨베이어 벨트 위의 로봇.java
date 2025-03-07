import java.io.*;
import java.util.*;

public class Main {
    static int N, K, turn;
    static int[] durability;
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료

        durability = new int[2*N];
        robot = new boolean[2*N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        turn = 0;
        while (true) {
            turn++;

            // 1. 벨트 회전 (+로봇)
            rotate();

            // 2. 로봇 이동
            move();

            // 3. 올리는 위치에 로봇 올리기
            addRobot();

            // 4. 종료 조건
            if (chkFinish()) break;
        }

        bw.write(String.valueOf(turn));
        bw.flush();
        bw.close();
    }

    private static void rotate() {
        int tmp = durability[2*N-1];
        for (int i = 2*N-1; i >= 1; i--) {
            durability[i] = durability[i-1]; // 1. 벨트 회전
            robot[i] = robot[i-1]; // 2. 로봇 회전
        }
        durability[0] = tmp;
        robot[0] = false; // 올리는 자리
        robot[N-1] = false; // 내리는 자리
    }

    private static void move() {
        // 2-1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다
        // 2-2. 이동할 수 없다면 가만히 있는다
        for (int i = 2*N-1; i >= 1; i--) {
            if (!robot[i] && robot[i-1] && durability[i] >= 1) {
                robot[i] = true;
                robot[i-1] = false;
                durability[i]--;

                if (i == N-1) robot[i] = false;
            }
        }
    }

    private static void addRobot() {
        if (durability[0] > 0) {
            robot[0] = true;
            durability[0]--;
        }
    }

    private static boolean chkFinish() {
        int chk = 0;
        for (int i = 0; i < 2*N; i++) {
            if (durability[i] == 0) chk++;
        }

        if (chk >= K) return true;
        else return false;
    }
}