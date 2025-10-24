import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static Queue<int[]> fireQ;
    static Queue<int[]> jQ;
    static boolean[][] jVisited;
    static int startR, startC;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        fireQ = new LinkedList<>();
        startR = -1;
        startC = -1;
        for (int i = 0 ; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char now = input.charAt(j);
                // 벽 -1 공간 0 지훈 0 (좌표 따로 저장) 불 1
                if (now == '#') map[i][j] = -1;
                else if (now == '.') map[i][j] = 0;
                else if (now == 'F') {
                    map[i][j] = 1;
                    fireQ.add(new int[]{i, j});
                } else if (now == 'J') {
                    startR = i;
                    startC = j;
                    map[i][j] = 0;
                }
            }
        }

        jQ = new LinkedList<>();
        jQ.add(new int[]{startR, startC});
        jVisited = new boolean[R][C];
        jVisited[startR][startC] = true;
        
        boolean canExit = true;
        int time = 1;
        while (canExit) {
            // 1. 불 이동
            moveFire();
            // 2. 지훈이 이동 : 이동 가능(1) / 불가능(2) / 탈출(3)
            int status = moveJ();
            if (status == 1) time++;
            else if (status == 2) {
                canExit = false;
                break;
            } else if (status == 3) break;
        }

        if (canExit) bw.write(String.valueOf(time));
        else bw.write("IMPOSSIBLE");
        bw.flush();
        bw.close();
    }

    private static int moveJ() {
        int qSize = jQ.size();
        boolean canMove = false;
        
        for (int i = 0; i < qSize; i++) {
            int[] now = jQ.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                // 단순 이동
                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (!jVisited[nextY][nextX] && map[nextY][nextX] == 0) { 
                        jVisited[nextY][nextX] = true;
                        jQ.add(new int[]{nextY, nextX});
                        canMove = true;
                    }
                } else { // 범위 초과 > 탈출
                    return 3;
                }
            }
        }

        if (canMove) return 1;
        else return 2;
    }

    private static void moveFire() {
        int qSize = fireQ.size(); // 초기 크기
        for (int i = 0; i < qSize; i++) {
            int[] now = fireQ.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < map.length && 0 <= nextX && nextX < map[0].length) {
                    if (map[nextY][nextX] == 0) { // 벽이 아닌 빈 공간에만 확산
                        map[nextY][nextX] = 1;
                        fireQ.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
    }
}