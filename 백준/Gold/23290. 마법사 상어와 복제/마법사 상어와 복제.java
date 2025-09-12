import java.util.*;
import java.io.*;

class Main {
    static class Fish {
        int r;
        int c;
        int dir;
        boolean alived;
        Fish(int r, int c, int dir, boolean alived) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.alived = alived;
        }
    }
    static List<Fish> fishList;
    static int sharkR, sharkC;
    static int[][] smellMap; // 물고기 냄새
    static int[][] fishMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int fishCnt = Integer.parseInt(st.nextToken());
        int turn = Integer.parseInt(st.nextToken());

        smellMap = new int[4][4];
        fishMap = new int[4][4];

        fishList = new ArrayList<>();
        for (int i = 0; i < fishCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            fishList.add(new Fish(r, c, dir, true));
            fishMap[r][c]++;
        }

        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken()) - 1;
        sharkC = Integer.parseInt(st.nextToken()) - 1;

        //System.out.println("맨 처음");
        //printMap();

        for (int i = 0; i < turn; i++) {
            // 1. 물고기 복제 (적용X)
            List<Fish> tmpFishList = new ArrayList<>();
            for (Fish f : fishList) if (f.alived) tmpFishList.add(new Fish(f.r, f.c, f.dir, f.alived));
            // 2. 물고기 이동
            moveFish();
            // 3. 상어 이동
            moveShark();
            // 4. 물고기 냄새 처리
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (smellMap[r][c] == 0) continue;
                    smellMap[r][c]--;
                }
            }
            // 5. 물고기 복제 적용
            for (Fish f : tmpFishList) {
                fishList.add(new Fish(f.r, f.c, f.dir, f.alived));
                fishMap[f.r][f.c]++;
            }
        }

        int ans = 0;
        for(Fish f : fishList) if (f.alived) ans++;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void moveShark() {
        int[] dy = {-1, 0, 1, 0}; // 상좌하우
        int[] dx = {0, -1, 0, 1};

        // 1. 가장 많이 물고기를 만나는 경로 찾기
        int largestCnt = -1;
        List<int[]> path = new ArrayList<>(); // 위치 저장
        for (int i = 0; i < 4; i++) {
            int firstR = sharkR + dy[i];
            int firstC = sharkC + dx[i];
            if (0 > firstR || firstR >= 4 || 0 > firstC || firstC >= 4) continue;

            int firstFishCnt = fishMap[firstR][firstC];
            int firsttmp = firstFishCnt;
            fishMap[firstR][firstC] = 0;
            
            for (int j = 0; j < 4; j++) {
                int secondR = firstR + dy[j];
                int secondC = firstC + dx[j];
                if (0 > secondR || secondR >= 4 || 0 > secondC || secondC >= 4) continue;

                int secondFishCnt = fishMap[secondR][secondC];
                int secondtmp = secondFishCnt;
                fishMap[secondR][secondC] = 0;
                
                for (int k = 0; k < 4; k++) {
                    int thirdR = secondR + dy[k];
                    int thirdC = secondC + dx[k];
                    if (0 > thirdR || thirdR >= 4 || 0 > thirdC || thirdC >= 4) continue;

                    int thirdFishCnt = fishMap[thirdR][thirdC];
                    int totalFishCnt = firstFishCnt + secondFishCnt + thirdFishCnt;

                    if (totalFishCnt > largestCnt) {
                        largestCnt = totalFishCnt;
                        path.clear();
                        path.add(new int[]{firstR, firstC});
                        path.add(new int[]{secondR, secondC});
                        path.add(new int[]{thirdR, thirdC});

                        //System.out.println("현재 경로 : " + firstR + " " + firstC);
                        //System.out.println(secondR + " " + secondC);
                        //System.out.println(thirdR + " " + thirdC);
                    }
                }
                fishMap[secondR][secondC] = secondtmp;
            }
            fishMap[firstR][firstC] = firsttmp;
        }

        // 2. 해당 경로 물고기 제거, 냄새 남기기(3)
        for (int i = 0; i < 3; i++) {
            int nowR = path.get(i)[0];
            int nowC = path.get(i)[1];

            for (int j = 0; j < fishList.size(); j++) {
                Fish nowFish = fishList.get(j);
                if (!nowFish.alived) continue;

                if (nowFish.r == nowR && nowFish.c == nowC) {
                    nowFish.alived = false;
                    fishMap[nowR][nowC]--;
                    smellMap[nowR][nowC] = 3;
                }
            }
        }

        // 3. 상어 위치 변화
        sharkR = path.get(2)[0];
        sharkC = path.get(2)[1];
    }

    private static void moveFish() {
        int[] dy = {0, -1, -1, -1, 0, 1, 1, 1}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

        for (int i = 0; i < fishList.size(); i++) {
            Fish now = fishList.get(i);
            if (!now.alived) continue;

            boolean chk = false;
            for (int k = 0; k < 8; k++) {
                int nextR = now.r + dy[now.dir];
                int nextC = now.c + dx[now.dir];

                if (0 <= nextR && nextR < 4 && 0 <= nextC && nextC < 4) {
                    if (!(nextR == sharkR && nextC == sharkC) && smellMap[nextR][nextC] == 0) {
                        fishMap[now.r][now.c]--;
                        fishMap[nextR][nextC]++;
                        
                        now.r = nextR;
                        now.c = nextC;
                        chk = true;
                    }
                }

                // 이동했으면 루프 탈출, 아니면 방향 반시계 회전
                if (!chk) now.dir = (now.dir + 7) % 8;
                else break;
            }
        }
    }
}