import java.io.*;
import java.util.*;

public class Main {
    static int[] dice, horse;
    static Node[] map;
    static boolean[] map_visited, horse_visited;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        for (int i = 0; i < 10; i++) {
            int dice_num = Integer.parseInt(st.nextToken());
            dice[i] = dice_num;
        }

        horse = new int[4];
        horse_visited = new boolean[horse.length];

        map = new Node[32]; // 시작 ~ 40
        map[0] = new Node(0, 1,0);
        for (int i = 1; i <= 19; i++) {
            map[i] = new Node(i*2, i+1,0);
        }
        map[20] = new Node(40, 100, 0);

        map[5].blue_next_idx = 21;
        map[10].blue_next_idx = 28;
        map[15].blue_next_idx = 27;

        map[21] = new Node(13, 22, 0);
        map[22] = new Node(16, 23, 0);
        map[23] = new Node(19, 24, 0);
        map[24] = new Node(25, 30, 0);
        map[25] = new Node(26, 24, 0);
        map[26] = new Node(27, 25, 0);
        map[27] = new Node(28, 26, 0);
        map[28] = new Node(22, 29, 0);
        map[29] = new Node(24, 24, 0);
        map[30] = new Node(30, 31, 0);
        map[31] = new Node(35, 20, 0);

        map_visited = new boolean[map.length];
        ans = Integer.MIN_VALUE;

        rollTheDice(0, 0); // 현재 턴 수, 합

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void rollTheDice(int turn, int sum) {
        if (turn == 10) {
            ans = Math.max(ans, sum);
            return;
        }

        // 1. 말 선택
        // 2. 말 이동
        // 3. 점수 추가

        for (int i = 0; i < horse.length; i++) {
            if (horse_visited[i]) continue; // 이미 도착 지점에 있는 말은 pass

            int now_idx = horse[i]; // 현재 위치 (map 에서의 idx)
            int next_idx = now_idx;
            for (int j = 0; j < dice[turn]; j++) { // 이동
                if (j == 0 && map[next_idx].blue_next_idx != 0) { // 현재 파란 칸에 있을 때
                    next_idx = map[next_idx].blue_next_idx;
                } else next_idx = map[next_idx].red_next_idx;

                if (next_idx == 100) break;
            }

            if (next_idx == 100) { // 도착
                map_visited[now_idx] = false; // 있었던 위치
                horse_visited[i] = true;
                rollTheDice(turn + 1, sum);
                horse_visited[i] = false;
                map_visited[now_idx] = true;
            } else {

                // 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다
                if (map_visited[next_idx]) continue;

                map_visited[now_idx] = false; // 있었던 위치
                map_visited[next_idx] = true; // 새로운 위치
                horse[i] = next_idx;
                sum += map[next_idx].now;
                rollTheDice(turn + 1, sum);

                sum -= map[next_idx].now;
                horse[i] = now_idx;
                map_visited[next_idx] = false;
                map_visited[now_idx] = true;
            }
        }
    }

    static class Node {
        int now; // 현재 인덱스 원안에 있는 값
        int red_next_idx; // 빨간색 길로 갔을 때의 다음 원의 인덱스
        int blue_next_idx; // 파란색 길로 갔을 때의 다음 원의 인덱스

        Node (int now, int red_next_idx, int blue_next_idx) {
            this.now = now;
            this.red_next_idx = red_next_idx;
            this.blue_next_idx = blue_next_idx;
        }
    }
}