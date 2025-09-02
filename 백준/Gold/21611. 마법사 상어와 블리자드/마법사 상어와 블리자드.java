import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static int sharkY, sharkX;
    static List<int[]> mapList;
    static List<Integer> valList;
    static int[] ball;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());
        int turn = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sharkY = map.length / 2;
        sharkX = map.length / 2;
        mapList = makeMapList();
        valList = new ArrayList<>();

        ball = new int[3];
        for (int i = 0; i < turn; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());

            if (chkMapEmpty()) break;

            // 1. 블리자드 시전
            // while () 시작 : 구슬 폭발이 없을 때까지
                // 2. 구슬 이동
                // 3. 구슬 폭발
            // 4. 구슬 A/B 변화

            blizzard(direction, distance);

            moveBall();

            boolean explosion = true;
            while (explosion) explosion = explodeBall();

            changeAB();
        }

        int ans = ball[0] + 2*ball[1] + 3*ball[2];
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static List<int[]> makeMapList () {
        // 최대 시행 횟수 = 상어 칸 제외한 전체 맵 칸의 개수
        int cnt = map.length * map.length - 1;

        int[] dy = {1, 0, -1, 0}; // 하 우 상 좌
        int[] dx = {0, 1, 0, -1};
        
        int startY = sharkY;
        int startX = sharkX - 1;
        int startDir = 0;
        int move = 0; // 현재 방향에서 몇 번 움직였는지
        int moveTarget = 1; // 현재 방향에서 몇 번 움직여야 하는지
        int chk = 1; // 이 방향으로 몇 번 움직였는지

        List<int[]> tmp = new ArrayList<>();
        // 두 번의 방향씩 동일한 칸 개수를 지나면 하나 늘어남
        for (int i = 0; i < cnt; i++) {
            // 현재 값 저장
            tmp.add(new int[]{startY, startX, map[startY][startX]});

            // 방향 바꾸는지 확인
            if (move == moveTarget) {
                startDir = (startDir + 1) % 4;
                move = 0;
                chk++;

                // 움직이는 칸 수 늘려야 하는지 확인
                if (chk == 2) {
                    moveTarget++;
                    chk = 0;
                }
            }
            
            // 다음 칸으로 이동
            startY += dy[startDir];
            startX += dx[startDir];

            move++;
        }
        return tmp;
    }

    private static boolean chkMapEmpty() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    private static void blizzard(int direction, int distance) {
        int[] dy = {-1, 1, 0, 0}; // 상 하 좌 우
        int[] dx = {0, 0, -1, 1};
        
        int nowY = sharkY;
        int nowX = sharkX;
        for (int i = 0; i < distance; i++) {
            nowY += dy[direction];
            nowX += dx[direction];

            map[nowY][nowX] = 0;
        }
    }

    private static void moveBall() {
        // List에 좌표를 저장하기 (0이면 저장X)
        
        // 최대 시행 횟수 = 상어 칸 제외한 전체 맵 칸의 개수
        int cnt = map.length * map.length - 1;

        int[] dy = {1, 0, -1, 0}; // 하 우 상 좌
        int[] dx = {0, 1, 0, -1};
        
        int startY = sharkY;
        int startX = sharkX - 1;
        int startDir = 0;
        int move = 0; // 현재 방향에서 몇 번 움직였는지
        int moveTarget = 1; // 현재 방향에서 몇 번 움직여야 하는지
        int chk = 1; // 이 방향으로 몇 번 움직였는지

        valList = new ArrayList<>();
        // 두 번의 방향씩 동일한 칸 개수를 지나면 하나 늘어남
        for (int i = 0; i < cnt; i++) {
            // 현재 값 저장
            if (map[startY][startX] != 0) valList.add(map[startY][startX]);

            // 방향 바꾸는지 확인
            if (move == moveTarget) {
                startDir = (startDir + 1) % 4;
                move = 0;
                chk++;

                // 움직이는 칸 수 늘려야 하는지 확인
                if (chk == 2) {
                    moveTarget++;
                    chk = 0;
                }
            }
            
            // 다음 칸으로 이동
            startY += dy[startDir];
            startX += dx[startDir];

            move++;
        }
    }

    private static boolean explodeBall() {
        // 1. 폭발시키기
        // 2-1. 폭발 true -> valList 갱신 & true 반환
        // 2-2. 폭발 false -> false 반환
        boolean chk = false;
        
        List<Integer> tmpList = new ArrayList<>();
        int beforeVal = -1;
        int cnt = 0;
        for (int i = 0; i < valList.size(); i++) {
            int now = valList.get(i);
            
            if (now == beforeVal) {
                cnt++;
            } else {
                // 이 전까지 연속된 공이었다면 삭제
                if (cnt >= 4) {
                    chk = true;
                    ball[beforeVal-1] += cnt;
                    for (int j = 0; j < cnt; j++) tmpList.remove(tmpList.size()-1);
                }
                // 새로 입력 시작
                beforeVal = now;
                cnt = 1;
            }

            tmpList.add(now);
        }

        // 마지막 처리
        if (cnt >= 4) {
            chk = true;
            ball[beforeVal-1] += cnt;
            for (int j = 0; j < cnt; j++) tmpList.remove(tmpList.size()-1);
        }

        if (!chk) return false;

        // valList 갱신하기
        valList.clear();
        for (int i = 0; i < tmpList.size(); i++) valList.add(tmpList.get(i));
        
        return true;
    }

    private static void changeAB() {
        // valList 순회 -> [A, B] 형태로 저장
        // 맵 갱신

        List<int[]> tmpList = new ArrayList<>(); // [개수, 번호]
        int beforeVal = -1;
        int cnt = 0;
        for (int i = 0; i < valList.size(); i++) {
            int now = valList.get(i);
            
            if (now == beforeVal) {
                cnt++;
            } else {
                // 이 전까지 연속된 공이었다면 저장
                if (cnt >= 1) {
                    tmpList.add(new int[]{cnt, beforeVal});
                }
                
                // 새로 입력 시작
                beforeVal = now;
                cnt = 1;
            }
        }

        // 마지막 처리
        tmpList.add(new int[]{cnt, beforeVal});

        // 맵 갱신
        int[][] newMap = new int[map.length][map[0].length];
        int total = map.length * map.length - 1; // 최대 시행 횟수
        int idx = 0;
        for (int i = 0; i < tmpList.size(); i++) {
            if (idx+2 > total) break;
            int[] now = tmpList.get(i);
            int A = now[0];
            int B = now[1];

            int[] pos = mapList.get(idx++);
            newMap[pos[0]][pos[1]] = A;

            int[] pos2 = mapList.get(idx++);
            newMap[pos2[0]][pos2[1]] = B;
        }

        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[i].length; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }
}