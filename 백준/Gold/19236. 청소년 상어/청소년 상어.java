import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int sum, shark_i, shark_j, shark_dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] map = new int[4][4];
        List<int[]> fish = new ArrayList<>(); // (물고기 번호, 방향, i, j)
        sum = Integer.MIN_VALUE;

        int first_value = 0;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken()); // 물고기 번호
                int b = Integer.parseInt(st.nextToken()); // 물고기 방향

                // (0,0) 은 상어가 투입된다 (물고기 리스트에 저장 안함)
                if (i == 0 && j == 0) {
                    shark_dir = b-1;
                    first_value = a;
                    continue;
                }

                fish.add(new int[]{a, b-1, i, j});
                map[i][j] = a;
            }
        }

        fish.sort((o1, o2) -> Integer.compare(o1[0], o2[0])); // 물고기 번호 순

        // 1. 상어 투입
        shark_i = 0;
        shark_j = 0;
        map[0][0] = -1; // 상어는 -1 로 표현 (물고기 번호 : 1~16)

        // (백트래킹) - 상어가 갈 수 있는 곳이 여러 군데임
        // 2. 물고기 이동
        // 3. 상어가 움직일 수 있는지 확인
        // 3-1. 상어 움직이기
        // 3-2. 먹은 물고기 번호 합 비교 (break/return)
        move(map, fish, first_value);

        // 상어가 먹을 수 있는 물고기 번호의 합의 최댓값
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static void move(int[][] map, List<int[]> fish, int now_sum) {
        moveFish(map, fish);

        if (!canMove(map)) {
            sum = Math.max(sum, now_sum);
            return;
        }

        int[][] copy_map = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) copy_map[i][j] = map[i][j];
        }

        List<int[]> copy_fish = new ArrayList<>();
        for (int[] f : fish) copy_fish.add(new int[]{f[0], f[1], f[2], f[3]});

        int chk_i = shark_i + dy[shark_dir];
        int chk_j = shark_j + dx[shark_dir];
        while (chkBoundary(chk_i, chk_j)) {
            if (map[chk_i][chk_j] != 0) {
                int eaten_num = map[chk_i][chk_j];
                int si_ori = shark_i;
                int sj_ori = shark_j;
                int sd_ori = shark_dir;
                int[] eaten_fish = {};
                for (int f = 0; f < fish.size(); f++) {
                    int[] switch_fish = fish.get(f);
                    if (switch_fish[0] == eaten_num) {
                        eaten_fish = fish.get(f);
                        break;
                    }
                }

                map[si_ori][sj_ori] = 0;
                shark_i = chk_i;
                shark_j = chk_j;
                shark_dir = eaten_fish[1];
                fish.remove(eaten_fish);
                map[chk_i][chk_j] = -1;
                move(map, fish, now_sum + eaten_num);

                shark_i = si_ori;
                shark_j = sj_ori;
                shark_dir = sd_ori;

                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) map[r][c] = copy_map[r][c];
                }

                fish = new ArrayList<>();
                for (int[] f : copy_fish) fish.add(new int[]{f[0], f[1], f[2], f[3]});
            }
            chk_i += dy[shark_dir];
            chk_j += dx[shark_dir];
        }
    }

    private static boolean canMove(int[][] map) {
        int chk_i = shark_i + dy[shark_dir];
        int chk_j = shark_j + dx[shark_dir];
        while (chkBoundary(chk_i, chk_j)) {
            if (map[chk_i][chk_j] != 0) return true;
            chk_i += dy[shark_dir];
            chk_j += dx[shark_dir];
        }
        return false;
    }

    private static void moveFish(int[][] map, List<int[]> fish) {
        for (int i = 0; i < fish.size(); i++) {
            int[] now = fish.get(i);
            int fish_num = now[0];
            int fish_dir = now[1];
            int fish_i = now[2];
            int fish_j = now[3];

            for (int k = 0; k < 8; k++) {
                int chk_i = fish_i + dy[fish_dir];
                int chk_j = fish_j + dx[fish_dir];

                // 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
                if (!chkBoundary(chk_i, chk_j) || map[chk_i][chk_j] == -1) {
                    fish_dir =  (fish_dir + 1) % 8;
                    continue;
                }

                // 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸
                if (map[chk_i][chk_j] == 0) {
                    map[chk_i][chk_j] = fish_num; // 새로운 칸으로 이동
                    map[fish_i][fish_j] = 0; // 기존 칸 없애기

                    fish.get(i)[2] = chk_i;
                    fish.get(i)[3] = chk_j;
                } else {
                    // 이동하려는 자리에 있는 물고기
                    int fish2_num = map[chk_i][chk_j];

                    map[chk_i][chk_j] = fish_num;
                    map[fish_i][fish_j] = fish2_num;

                    fish.get(i)[2] = chk_i;
                    fish.get(i)[3] = chk_j;

                    for (int f = 0; f < fish.size(); f++) {
                        int[] switch_fish = fish.get(f);
                        if (switch_fish[0] == fish2_num) {
                            fish.get(f)[2] = fish_i;
                            fish.get(f)[3] = fish_j;
                            break;
                        }
                    }
                }

                fish.get(i)[1] = fish_dir;
                break;
            }
        }
    }

    private static boolean chkBoundary(int i, int j) {
        if (0 <= i && i < 4 && 0 <= j && j < 4) return true;
        else return false;
    }
}